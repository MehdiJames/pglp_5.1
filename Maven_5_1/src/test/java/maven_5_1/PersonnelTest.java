package maven_5_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.uvsq21602618.pglp_5_1_1.Personnel.Builder;
/**
 * Classe de tests pour la classe Personnel.
 * @author Mehdi
 *
 */

public class PersonnelTest {

	/**
     * Instances de Personnel.
     */
    Personnel secretaire;
    /**
     * Le nom du dossier contenant les fichiers 
     * li�s aux Personnels.
     */
    String nomDir;
    /**
     * Instance du dossier contenant les fichiers objets.
     */
    File dir;
    /**
     * Le DAO des PersonnelDAO.
     */
    DAO<Personnel> personnel;
    
    /**
     * Initialisation des instances pour les tests.
     * @throws IOException Exception liee aux entre�s/sorties
     */
    @Before
    public void setUp() throws IOException {
        nomDir = "Personnels";
        dir = new File(nomDir);
        personnel = DAOFactory.getPersonnelDAO();
        
        NumeroTelephone portable =
                new NumeroTelephone("portable", "0651624519", 1);
        Builder b = new Builder("SMITH", "John", "secr�taire",
                LocalDate.of(1964, 8, 25), 1);
        b.numTelephones(portable);
        Personnel p = b.build();
        secretaire = p;
    }
    /**
     * Teste le constructeur de Personnel utilisant
     * le pattern Builder.
     */
    @Test
    public void constructeurTest() {
        String expDesc = "portable";
        String expNum = "0651624519";
        String expNom = "SMITH";
        String expPrenom = "John";
        String expFonction = "secr�taire";
        LocalDate expDate = LocalDate.of(1964, 8, 25);

        assertEquals(expDesc,
                secretaire.getNumTelephones().get(0).getDescriptif());
        assertEquals(expNum, secretaire.getNumTelephones().get(0).getNumero());
        assertEquals(expNom, secretaire.getNom());
        assertEquals(expPrenom, secretaire.getPrenom());
        assertEquals(expFonction, secretaire.getFonction());
        assertEquals(expDate, secretaire.getDateNaissance());

    }
    /**
     * M�thode de d�s�rialisation.
     * @param bytes le tableau d'octets � transformer en objet.
     * @return L'objet obtenu.
     * @throws ClassNotFoundException Exception si la classe n'existe pas.
     * @throws IOException Exception liee aux entre�s/sorties
     */
    private Object deserialize(final byte[] bytes) throws ClassNotFoundException, IOException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
    /**
     * M�thode de s�rialisation.
     * @param obj L'objet � transformer en flux d'octets.
     * @return flux d'octets de l'objet.
     * @throws IOException Exception liee aux entre�s/sorties
     */
    private byte[] serialize(final Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }
    /**
     * Tests sur la transformation dans les deux sens.
     * @throws IOException Exception liee aux entre�s/sorties
     * @throws ClassNotFoundException Exception si la classe n'existe pas
     */
    @Test
    public void ConsistencyTest() throws IOException, ClassNotFoundException {
        Personnel p = secretaire;
        byte[] serialized1 = serialize(p);
        byte[] serialized2 = serialize(p);

        Object deserialized1 = deserialize(serialized1);
        Object deserialized2 = deserialize(serialized2);
        Assert.assertEquals(deserialized1, deserialized2);
        Assert.assertEquals(p, deserialized1);
        Assert.assertEquals(p, deserialized2);
    }
    /**
     * Test pour verifier si la methode create de PersonnelDAO fonctionne.
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception si la classe n'existe pas
     */
    @Test
    public void createTest() throws IOException, ClassNotFoundException {       
        personnel.create(secretaire);
        
        File search = new File(nomDir + "\\" + secretaire.getId() + ".txt");
        Object deserialized = null;
        
        byte[] fileContent = Files.readAllBytes(search.toPath());
       
        deserialized = deserialize(fileContent);
        Personnel expected = (Personnel) deserialized;
        
        assertTrue(dir.exists());
        assertTrue(search.exists());
        assertEquals(expected, secretaire);
        
        personnel.delete(secretaire);
        dir.delete();
    }
    /**
     * Test pour verifier si la methode delete de PersonnelDAO fonctionne.
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception si la classe n'existe pas
     */
    @Test
    public void deleteTest() throws IOException, ClassNotFoundException {      
        NumeroTelephone portable =
                new NumeroTelephone("portable", "0651724519", 2);
        Builder b = new Builder("SMITH", "Jeanne", "secr�taire",
                LocalDate.of(1964, 5, 25), 2);
        b.numTelephones(portable);
        Personnel secretaire2 = b.build();
        
        File search = new File(nomDir + "\\" + secretaire.getId() + ".txt");
        File expected = new File(nomDir + "\\" + secretaire2.getId() + ".txt");  

        personnel.create(secretaire);
        personnel.create(secretaire2);
        personnel.delete(secretaire);
        
        assertTrue(!search.exists());
        assertTrue(expected.exists());
        personnel.delete(secretaire2);
    }
    /**
     * Test pour verifier si la methode update de PersonnelDAO fonctionne.
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception si la classe n'existe pas
     */
    @Test
    public void updateTest() throws IOException, ClassNotFoundException {      
        File search = new File(nomDir + "\\" + secretaire.getId() + ".txt");

        personnel.create(secretaire);
        personnel.update(secretaire);
        Object deserialized = null;
        
        byte[] fileContent = Files.readAllBytes(search.toPath());
       
        deserialized = deserialize(fileContent);
        Personnel expected = (Personnel) deserialized;
        
        assertTrue(search.exists());
        assertEquals(expected, secretaire);
        personnel.delete(secretaire);
    }  
    /**
     * Test pour verifier si la methode find de PersonnelDAO fonctionne.
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception si la classe n'existe pas
     */
    @Test
    public void findTest() throws IOException, ClassNotFoundException {      
        File search = new File(nomDir + "\\" + secretaire.getId() + ".txt");
        Personnel expected;
        personnel.create(secretaire);
        
        expected = personnel.find(1);
        
        assertTrue(search.exists());
        assertEquals(expected, secretaire);
        personnel.delete(secretaire);
    }
	
}

package maven_5_1;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
/**
 * Classe pour le DAO de GroupePersonnels.
 * @author Mehdi
 *
 */

public class GroupePersonnelsDAO extends DAO<GroupePersonnels> {
	/**
     * M�thode de cr�ation.
     * @param obj L'objet � cr�er
     * @return obj qui vient d'etre cree
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    @Override
    public GroupePersonnels create(final GroupePersonnels obj)
            throws IOException {
        String nomDir = "Groupes";
        File dir = new File(nomDir);
        FileOutputStream fileOut;
        ObjectOutputStream objOut;

        File file = new File(nomDir + "\\" + obj.getId() + ".txt");
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Le dossier est cr��!");
            } else {
                System.out.println("le dossier n'a pas pu �tre cr��!");
            }
        }
        fileOut = new FileOutputStream(file);
        objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(obj);
        objOut.close();
        System.out.println("Le fichier est cr��!");
        return obj;
    }
    /**
     * M�thode pour effacer.
     * @param obj L'objet � effacer
     */
    public void delete(final GroupePersonnels obj) {
        String nomDir = "Groupes";
        File dir = new File(nomDir);
        if (dir.exists()) {
            File file = new File(nomDir + "\\" + obj.getId() + ".txt");
            if (file.exists()) {
                boolean test = file.delete();
                if (test) {
                    System.out.println("Le fichier est supprim�!");
                } else {
                    System.out.println("Echec de la supression du fichier!");
                }
                System.out.println("Le fichier est supprim�!");
            } else {
                System.out.println("Le fichier � supprimer n'existe pas!");
            }
        } else {
            System.out.println("Le dossier contenant le fichier n'existe pas!");
        }
    }
    /**
     * M�thode de mise � jour.
     * @param obj L'objet � mettre � jour
     * @throws IOException Exceptions liees aux entrees/sorties
     * @return obj L'objet � mettre � jour
     */
    public GroupePersonnels update(final GroupePersonnels obj)
            throws IOException {
        String nomDir = "Groupes";
        File dir = new File(nomDir);
        if (dir.exists()) {
            File file = new File(nomDir + "\\" + obj.getId() + ".txt");
            if (file.exists()) {
                boolean test = file.delete();
                if (test) {
                    this.delete(obj);
                    this.create(obj);
                } else {
                    System.out.println("Echec de la mise a jour du fichier!");
                }
            } else {
                System.out.println("Le fichier � mettre � jour n'existe pas!");
            }
        } else {
            System.out.println("Le dossier contenant le fichier n'existe pas!");
        }
        return obj;
    }
    /**
     * M�thode de recherche des informations.
     * @param id de l'information
     * @return gp le GroupePersonnel du fichier, null sinon
     * @throws IOException liee aux entre�s/sorties
     * @throws ClassNotFoundException Exception li� � une classe inexistante
     */
    public GroupePersonnels find(final int id)
            throws IOException, ClassNotFoundException {
        String nomDir = "Groupes";
        File dir = new File(nomDir);
        File search = new File(nomDir + "\\" + id + ".txt");
        Object deserialized = null;

        if (dir.exists()) {
            if (search.exists()) {
                byte[] fileContent = Files.readAllBytes(search.toPath());
                deserialized = deserialize(fileContent);
                GroupePersonnels gp = (GroupePersonnels) deserialized;
                gp.hierarchie();
                return gp;
            } else {
                System.out.println("Le fichier n'existe pas!");
            }
        } else {
            System.out.println("Le dossier n'existe pas!");
        }
        return null;
    }
}

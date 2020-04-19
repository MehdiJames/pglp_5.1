package maven_5_1;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * Classe abstraite de DataAccessObject.
 * @author Mehdi
 *
 * @param <T> La classe choisie
 */

public abstract class DAO<T> {
	 /**
     * M�thode de cr�ation.
     * @param obj L'objet � cr�er
     * @return T une classe donn�e
     * @throws IOException Exceptions li�es aux entr�es/sorties
     */
    public abstract T create(T obj) throws IOException;
    /**
     * M�thode pour effacer.
     * @param obj l'objet � supprimer
     */
    public abstract void delete(T obj);
    /**
     * M�thode de mise � jour.
     * @param obj L'objet � mettre � jour
     * @throws IOException Exception liee aux entrees/sorties
     * @return T une instance de la classe donn�e
     */
    public abstract T update(T obj) throws IOException;
    /**
     * M�thode de recherche des informations.
     * @param id de l'information
     * @return T une classe donn�e
     * @throws FileNotFoundException Exception si le fichier n'existe pas
     * @throws IOException Exception liee aux entrees/sorties
     * @throws ClassNotFoundException Exception si la classe n'existe pas
     */
    public abstract T find(int id) throws FileNotFoundException,
    ClassNotFoundException, IOException;
    /**
     * M�thode de d�s�rialisation.
     * @param bytes le tableau d'octets � transformer en objet.
     * @return L'objet obtenu.
     * @throws ClassNotFoundException Exception si la
     * classe n'existe pas
     * @throws IOException Exception liee aux entrees/sorties
     */
    public Object deserialize(final byte[] bytes) throws ClassNotFoundException,
    IOException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
}

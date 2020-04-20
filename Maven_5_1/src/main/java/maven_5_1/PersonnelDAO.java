package maven_5_1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

/**
 * Classe PersonnelDAO.
 * @author Mehdi
 *
 */

public class PersonnelDAO extends DAO<Personnel>{

	/**
     * M�thode de cr�ation.
     * @param obj L'objet � cr�er
     * @return obj l'objet qui vient d'etre creer
     * @throws IOException Exceptions liees aux entrees/sorties
     */
    @Override
    public Personnel create(final Personnel obj) throws IOException {
        String nomDir = "Personnels";
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
     * M�thode de mise � jour.
     * @param obj L'objet � mettre � jour
     * @throws IOException Exceptions liees aux entrees/sorties
     * @return obj
     */
    public Personnel update(final Personnel obj)
            throws IOException {
        String nomDir = "Personnels";
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
     * M�thode pour effacer.
     * @param obj L'objet � effacer
     */
    public void delete(final Personnel obj) {
        String nomDir = "Personnels";
        File dir = new File(nomDir);
        if (dir.exists()) {
            File file = new File(nomDir + "\\" + obj.getId() + ".txt");
            if (file.exists()) {
                boolean test = file.delete();
                if (test) {
                    System.out.println("Le fichier est supprim�!\n");
                } else {
                    System.out.println("Echec de la suppression du fichier!\n");
                }
            } else {
                System.out.println("Le fichier � supprimer n'existe pas!");
            }
        } else {
            System.out.println("Le dossier contenant"
                    + " le fichier n'existe pas!");
        }
    }
    /**
     * M�thode de recherche des informations.
     * @param id de l'information
     * @return p le personnel du fichier
     * @throws IOException Exception liee aux entre�s/sorties
     * @throws ClassNotFoundException Exception li� � une classe inexistante
     */
    public Personnel find(final int id)
            throws IOException, ClassNotFoundException {
        String nomDir = "Personnels";
        File dir = new File(nomDir);
        File search = new File(nomDir + "\\" + id + ".txt");
        Object deserialized = null;
        if (dir.exists()) {
            if (search.exists()) {
                byte[] fileContent = Files.readAllBytes(search.toPath());
                deserialized = deserialize(fileContent);
                Personnel pers = (Personnel) deserialized;
                pers.print();
                return pers;
            } else {
                System.out.println("Le fichier n'existe pas!");
            }
        } else {
            System.out.println("Le dossier n'existe pas!");
        }
        return null;
    }
}
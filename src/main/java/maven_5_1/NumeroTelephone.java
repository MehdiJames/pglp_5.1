package maven_5_1;

import java.io.Serializable;

/**
 * Classe qui repr�sente le num�ro de t�l�phone.
 * @author Mehdi
 *
 */

public class NumeroTelephone implements Serializable {
	 /**
     * SerialVersion.
     */
    private static final long serialVersionUID = 1L;
    /**
     * M�thode de comparaison.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NumeroTelephone other = (NumeroTelephone) obj;
        if (descriptif == null) {
            if (other.descriptif != null) {
                return false;
            }
        } else if (!descriptif.equals(other.descriptif)) {
            return false;
        }
        if (numero == null) {
            if (other.numero != null) {
                return false;
            }
        } else if (!numero.equals(other.numero)) {
            return false;
        }
        return true;
    }
    /**
     * M�thode de hachage.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((descriptif == null) ? 0 : descriptif.hashCode());
        result = prime * result + id;
        result = prime * result
                + ((numero == null) ? 0 : numero.hashCode());
        return result;
    }
    /**
     * Le type de num�ro (fixe, portable...).
     */
    private String descriptif;
    /**
     * Le num�ro en lui-m�me avec des chiffres.
     */
    private String numero;
    /**
     * Le numero d'id du telephone.
     */
    private int id;
    /**
     * Le constructeur du num�ro de t�l�phone.
     * @param desc la description du num�ro (fixe, portable ...)
     * @param num le num�ro de t�l�phones
     * @param id2 l'id du numero
     */
    public NumeroTelephone(final String desc, final String num, final int id2) {
        this.descriptif = desc;
        this.numero = num;
        this.setId(id2);
    }
    /**
     * M�thode pour r�cup�rer le descriptif du num�ro.
     * @return string le descriptif
     */
    public String getDescriptif() {
        return descriptif;
    }
    /**
     * M�thode pour definir le descriptif du telephone.
     * @param desc le descriptif du telephone
     */
    public void setDescriptif(final String desc) {
        this.descriptif = desc;
    }
    /**
     * M�thode pour r�cup�rer le num�ro de t�l�phone.
     * @return string le num�ro
     */
    public String getNumero() {
        return numero;
    }
    /**
     * M�thode pour definir le numero du telephone.
     * @param num le numero du telephone
     */
    public void setNumero(final String num) {
        this.numero = num;
    }
    /**
     * M�thode pour recuperer le descriptif ainsi que le numero.
     * @return le descriptif et le numero
     */
    public String toString() {
        return this.getDescriptif() + ": " + this.getNumero();
    }
    /**
     * M�thode pour r�cup�rer l'id du telephone.
     * @return int l'id
     */
    public int getId() {
        return id;
    }
    /**
     * M�thode pour definir l'id du telephone.
     * @param id2 du telephone
     */
    public void setId(final int id2) {
        this.id = id2;
    }
}

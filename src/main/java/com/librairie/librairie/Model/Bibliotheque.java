//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source.
// G�n�r� le : 2023.03.23 � 02:30:44 PM CET
//
package com.librairie.librairie.Model;
import javafx.beans.property.StringProperty;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Classe Java pour anonymous complex type.
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="livre" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="titre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="auteur">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="presentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="parution" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="colonne" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="rangee" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "livre"
})

/**
 * Classe principale Bibliothèque.
 */
@XmlRootElement(name = "bibliotheque")
public class Bibliotheque {

    @XmlElement(required = true)
    protected List<Bibliotheque.Livre> livre;
    public Bibliotheque() {
        livre = new ArrayList<>();
    }

    /**
     * Gets the value of the livre property.
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the livre property.
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLivre().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bibliotheque.Livre }
     */

    /**
     * Méthode qui permet de récupérer une liste.
     * Si elle est null alors on instancie une nouvelle liste appelé "livre".
     * @return this.livre
     */
    public List<Bibliotheque.Livre> getLivre() {
        if (livre == null) {
            livre = new ArrayList<Bibliotheque.Livre>();
        }
        return this.livre;
    }

    /**
     * Méthode qui permet d'ajouter un livre.
     * @param titre
     * @param auteur
     * @param presentation
     * @param parution
     * @param colonne
     * @param rangee
     * @param image
     * @param etat
     */
    public void addLivre(String titre, Livre.Auteur auteur, String presentation , int colonne, int rangee , String parution, String image, String etat){
        livre.add(new Livre(titre, auteur, presentation, colonne, rangee, parution ,image, etat));
    }

    /**
     * Méthode qui permet d'afficher du texte dans la console de sortie.
     * Ici, c'est le texte de la méthode "addLivre"
     */
    public void print(){
        System.out.println(this);
        livre.forEach(e->System.out.println(e.print()));
    }

    /**
     * <p>Classe Java pour anonymous complex type.
     * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="titre" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="auteur">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="presentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="parution" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="colonne" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="rangee" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "livre")
    @XmlType(name = "", propOrder = {
            "titre",
            "auteur",
            "presentation",
            "parution",
            "colonne",
            "rangee",
            "image",
            "etat"
    })

    /**
    * Classe qui permet d'initialiser les attributs d'un livre, ainsi que les constructeurs et les getters/setters.
    */
    @EqualsAndHashCode
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor

    public static class Livre {
        /**
         * Déclaration des attributs de la classe Livre.
         */
        @XmlElement(required = true)
        protected String titre;
        @XmlElement(required = true)
        protected Bibliotheque.Livre.Auteur auteur;
        @XmlElement(required = true)
        protected String presentation;
        @XmlSchemaType(name ="unsignedShort")
        protected String parution;
        @XmlSchemaType(name = "unsignedByte")
        protected int colonne;
        @XmlSchemaType(name = "unsignedByte")
        protected int rangee;
        @XmlSchemaType(name = "unsignedByte")
        protected String image;
        @XmlSchemaType(name = "unsignedByte")
        protected String etat;

        /**
         * Constructeur principal de la classe Livre avec tous les attributs.
         * Initialisation des données membres de la classe.
         * @param titre
         * @param auteur
         * @param presentation
         * @param parution
         * @param colonne
         * @param rangee
         * @param image
         * param etat
         */
        public Livre(String titre, Bibliotheque.Livre.Auteur auteur,String presentation,Integer colonne, Integer rangee,String parution, String image,String etat){
            this.titre = titre;
            this.auteur =auteur;
            this.presentation = presentation;
            this.colonne= colonne;
            this.rangee= rangee;
            this.parution= parution;
            this.image = image;
            this.etat = etat;
        }

        /**
         * Constructeur vide de la classe Livre.
         */
        public Livre(){
            this.titre= null;
            this.auteur =null;
            this.presentation= null;
            this.colonne= 0;
            this.rangee= 0;
            this.parution= null;
            this.image=null;
            this.etat=null;
        }

        /**
         * Obtient la valeur de la propriété titre.
         * @return possible object is
         *         {@link StringProperty }
         * Getter Titre
         */
        public String getTitre() {
            return titre;
        }
        public String titreProperty() {
            return titre;
        }

        /**
         * Définit la valeur de la propriété titre.
         * Setter Titre.
         *
         * @param value
         *     allowed object is
         *     {@link StringProperty }
         */
        public void setTitre(String value) {
            this.titre=value;
        }

        /**
         * Obtient la valeur de la propriété auteur.
         * Getter Auteur.
         * @return
         *     possible object is
         *     {@link Bibliotheque.Livre.Auteur }
         */
        public Bibliotheque.Livre.Auteur getAuteur() {
            return auteur;
        }
        public String getStringAuteur(){

            return auteur.getPrenom()+ " " + auteur.getNom();
        }

        /**
         * D�finit la valeur de la propri�t� auteur.
         * Setter Auteur.
         *
         * @param value
         *     allowed object is
         *     {@link Bibliotheque.Livre.Auteur }
         */
        public void setAuteur(Bibliotheque.Livre.Auteur value) {
            this.auteur = value;
        }

        /**
         * Obtient la valeur de la propri�t� presentation.
         * Getter Presentation.
         *
         * @return
         *     possible object is
         *     {@link StringProperty }
         */
        public String getPresentation() {
            return presentation;
        }

        /**
         * D�finit la valeur de la propri�t� presentation.
         * Setter Presentation.
         *
         * @param value
         *     allowed object is
         *     {@link StringProperty }
         */
        public void setPresentation(String value) {
            this.presentation = value;
        }

        /**
         * Obtient la valeur de la propriété image.
         * Getter Image.
         *
         * @return
         *      possible object is
         *      {@link StringProperty }
         */
        public String getImage(){
            return image;
        }

        /**
         * Définit la valeur de la propriété image.
         * Setter Image
         * @param value
         *     allowed object is
         *     {@link StringProperty }
         */
        public void setImage(String value){this.presentation=value;}

        /**
         * Obtient la valeur de la propriété parution.
         * Getter Parution.
         * @return
         *      possible object is
         *      {@link StringProperty }
         */
        public String getParution() {
            return parution;
        }

        /**
         * Définit la valeur de la propriété parution.
         * Setter Parution.
         * @param value
         *     allowed object is
         *     {@link StringProperty }
         */
        public void setParution(String value) {
            this.parution = value;
        }

        /**
         * Obtient la valeur de la propri�t� colonne.
         * Getter colonne
         * @return
         */
        public int getColonne() {
            return colonne;
        }

        /**
         * Définit la valeur de la propriété colonne.
         * Setter Colonne.
         * @param value
         */
        public void setColonne(int value) {
            this.colonne = value;
        }

        /**
         * Obtient la valeur de la propriété rangee.
         * Getter Rangee.
         * @return
         */
        public int getRangee() {
            return rangee;
        }
        public int rangeeProperty() {
            return rangee;
        }

        /**
         * Définit la valeur de la propriété rangee.
         * Setter Rangee.
         * @param value
         */
        public void setRangee(Integer value) {
            this.rangee=value;
        }

        public String print(){
            return this.toString() + "\n" + this.getTitre() + "\n" + this.getAuteur().toString() ;
        }

        /**
         * Obtient la valeur de la propriété etat.
         * Getter Etat.
         * @return
         */
        public String getEtat() {
            return etat;
        }

        /**
         * Définit la valeur de la propriété etat.
         * Setter Etat.
         */
        public void setEtat(String etat) {
            this.etat = etat;
        }

        /**
         * <p>Classe Java pour anonymous complex type.
         * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */

        /**
         * Classe pour différencier le prenom et le nom de l'auteur.
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "nom",
                "prenom"
        })

        public static class Auteur {
            /**
             * Déclarations des attributs de la classe Auteur.
             */
            @XmlElement(required = true)
            protected String nom;
            @XmlElement(required = true)
            protected String prenom;

            /**
             * Constructeur avec les différents attributs de la classe Auteur.
             * Initilisation des attributs.
             * @param nom
             * @param prenom
             */
            public Auteur(String nom, String prenom){
                this.nom = nom;
                this.prenom = prenom;
            }

            /**
             * Constructeur vide de la classe Auteur.
             */
            public Auteur(){
                this.nom = null;
                this.prenom = null;
            }

            /**
             * Obtient la valeur de la propriété nom.
             * Getter Nom.
             * @return
             *     possible object is
             *     {@link String }
             */
            public String getNom() {
                return nom;
            }

            /**
             * Définit la valeur de la propriété nom.
             * Setter Nom.
             * @param value
             *     allowed object is
             *     {@link String }
             */
            public void setNom(String value) {
                this.nom = value;
            }

            /**
             * Obtient la valeur de la propriété prenom.
             * Getter Prenom.
             * @return
             *     possible object is
             *     {@link String }
             */
            public String getPrenom() {
                return prenom;
            }

            /**
             * Définit la valeur de la propriété prenom.
             * Setter Prenom.
             * @param value
             *     allowed object is
             *     {@link String }
             */
            public void setPrenom(String value) {
                this.prenom = value;
            }
        }
    }
}

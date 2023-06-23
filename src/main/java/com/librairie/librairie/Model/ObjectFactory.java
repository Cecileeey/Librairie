package com.librairie.librairie.Model;
import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.esiee.java_avance_lot_1 package.
 * <p>An com.librairie.librairie.Model.ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {
    /**
     * Create a new com.librairie.librairie.Model.ObjectFactory that can be used to create new instances of schema derived classes for package: com.esiee.java_avance_lot_1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Bibliotheque }
     * @return
     */
    public Bibliotheque createBibliotheque() {
        return new Bibliotheque();
    }

    /**
     * Create an instance of {@link Bibliotheque.Livre }
     * @return
     */
    public Bibliotheque.Livre createBibliothequeLivre() {
        return new Bibliotheque.Livre();
    }

    /**
     * Create an instance of {@link Bibliotheque.Livre.Auteur }
     * @return
     */
    public Bibliotheque.Livre.Auteur createBibliothequeLivreAuteur() {
        return new Bibliotheque.Livre.Auteur();
    }
}

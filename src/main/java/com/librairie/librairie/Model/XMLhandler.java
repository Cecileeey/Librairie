package com.librairie.librairie.Model;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XMLhandler {

    protected File selectedFile = null;

    protected boolean fileSaved = true;

    public boolean isFileSaved(){ return fileSaved;}

    public File getSelectedFile(){return selectedFile;}

    public void setFileSaved(boolean fileSaved) {
        this.fileSaved = fileSaved;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public void SaveAs(Window jvfxwindow, Bibliotheque bibliotheque) throws JAXBException {



        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        selectedFile = fileChooser.showSaveDialog(jvfxwindow);
        if (selectedFile != null){
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println("ok");
            jaxbMarshaller.marshal(bibliotheque, selectedFile);
            fileSaved = true;

        }

    }

    /**
     * Ouvre un fichier XML contenant une bibliothèque et renvoie l'objet Bibliotheque correspondant.
     *
     * @param jvfxwindow la fenêtre JavaFX parente.
     * @return l'objet Bibliotheque correspondant au fichier XML ou null si aucun fichier n'est sélectionné.
     * @throws JAXBException si une exception se produit lors de la désérialisation XML.
     * @throws SAXException si une exception se produit lors de la validation XML avec le schéma XSD.
     */
    public Bibliotheque Open(Window jvfxwindow) throws JAXBException, SAXException {
        File xsdf = new File("src/main/xsd/Biblio.xsd");
        Bibliotheque bibliotheque = new Bibliotheque();

        /* ouverture du fichier xml */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XML", "*.xml"));
        File openFile = fileChooser.showOpenDialog(jvfxwindow);
        if (openFile != null) {
            //unmarshalling ( xml -> java)
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            Unmarshaller jaxbunMarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemafactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            //try
            Schema sch = schemafactory.newSchema(xsdf);
            jaxbunMarshaller.setSchema(sch);
            bibliotheque = (Bibliotheque) jaxbunMarshaller.unmarshal(openFile);
            //bibliotheque.print();

            /* mise a jour du tableau d'affichage */
            fileSaved = true;
            selectedFile = openFile;
        }
        System.out.println("erreur  "+ bibliotheque.getLivre().size());
        return bibliotheque;
    }
}
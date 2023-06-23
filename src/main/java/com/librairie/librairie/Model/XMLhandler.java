package com.librairie.librairie.Model;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Classe qui permet de sauvegarder le XML
 */
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

    /**
     * Méthode qui permet de sauvegarder sous le fichier dans l'ordinateur
     * @param jvfxwindow
     * @param bibliotheque
     * @throws JAXBException
     */
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
}
package com.librairie.librairie.Fixture;

import com.librairie.librairie.Model.Bibliotheque;

public class LivreFixture {
    private String titre = "TitreTest";
    private Bibliotheque.Livre.Auteur auteur = new Bibliotheque.Livre.Auteur("Musso", "Guillaume");
    private String presentation = "Presentation fictive pour test";
    private String parution = "date de parution";
    private int colonne = '0';
    private int rangee = '0';
    private String image = "url image";

    public static LivreFixture leLivre() {
        return new LivreFixture();
    }

    public Bibliotheque.Livre build() {
        return Bibliotheque.Livre
                .builder()
                .titre(titre)
                .auteur(auteur)
                .image(image)
                .rangee(rangee)
                .presentation(presentation)
                .parution(parution)
                .build();
    }
}
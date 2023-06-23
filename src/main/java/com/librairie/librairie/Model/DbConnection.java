package com.librairie.librairie.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.0
 * @author Marine
 */

/**
 * Classe de la connection à la base de données.
 */
public class DbConnection {

    /**
     * Déclaration des attributs utilisés dans la classe dbConnection.
     */
    private String url = "jdbc:mysql://localhost:3306/bibliotheque";
    private String user = "root";
    private String password = "";
    private Connection connection;

    /**
     * Méthode qui permet de passer les instructions SQL qui sont exécutées et les résultats sont renvoyés dans le contexte d'une connexion.
     * @return
     * @throws SQLException
     */
    public static Connection connect() throws SQLException{
        return null;
    }

    /**
     * Méthode qui permet de faire la connection à la base de données via les attributs déclarés.
     * @return
     */
    public Connection getConnexion() {
        if (this.connection == null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                this.connection = DriverManager.getConnection(url, user, password);

            }catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return connection;
    }

    /**
     * Méthode qui permet d'insérer dans la BDD les données rentrées dans le tableau
     * @param biblio
     * @return
     */
    public static int save(Bibliotheque.Livre biblio){
        int bi = 0;
        try{
            String sql = "INSERT INTO livre(titre,auteur,presentation,colonne,rangee,parution, image, etat) " +
                    "VALUES(?,?,?,?,?,?,?,?)";
            Connection con = new DbConnection().getConnexion();
            PreparedStatement stat;
            stat = con.prepareStatement(sql);
            stat.setString(1, biblio.getTitre());
            stat.setString(2, biblio.auteur.getPrenom()+biblio.auteur.getNom());
            stat.setString(3, biblio.getPresentation());
            stat.setInt(4, biblio.getColonne());
            stat.setInt(5, biblio.getRangee());
            stat.setInt(6, biblio.getParution());
            stat.setString(7, biblio.getImage());
            stat.setBoolean(8, biblio.getEtat());
            bi = stat.executeUpdate();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return bi;
    }
}
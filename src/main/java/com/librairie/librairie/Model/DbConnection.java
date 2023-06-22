package com.librairie.librairie.Model;

import java.sql.Connection;
import java.sql.DriverManager;
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
     *
     * @return
     * @throws SQLException
     */
    public static Connection connect() throws SQLException{
        return null;
    }

    /**
     * Méthode qui permet de faire la connection à la base de données via les attributs déclarés.
     *
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
}
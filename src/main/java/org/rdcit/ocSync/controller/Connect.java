/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sa841
 */
public class Connect {

    Connection connection;
    ConfFileReader confFileReader;

    public Connect() {
        confFileReader = new ConfFileReader();
    }

    public Connection openConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + this.confFileReader.getHost() + ":" + this.confFileReader.getPort() + "/" + this.confFileReader.getDb_name(), this.confFileReader.getDb_user_name(), this.confFileReader.getDb_user_pwd());
            if (connection != null) {
                System.out.println("You made it, take control your database now!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return connection;

    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.rdcit.ocSync.model.User;
import org.rdcit.ocSync.ocws.ListStudies_ws;

/**
 *
 * @author sa841
 */
public class UserCredentials {

    String userName;
    String password;
    boolean verifiy;
    User user;

    public UserCredentials(String userName, String password) {
        this.userName = userName;
        this.password = DigestUtils.sha1Hex(password);
        this.verifiy = false;
    }

    public boolean verifyCredentials() {
        try {
            PreparedStatement prepStmt = Connect.openConnection().prepareStatement("SELECT user_id FROM user_account WHERE user_name = '" + this.userName + "' AND passwd = '" + this.password + "';");
            ResultSet rs = prepStmt.executeQuery();
            if (!rs.next()) {
                System.out.println("NOT FOUND");
                this.verifiy = false;
            } else {
                this.verifiy = true;
                user = new User( this.userName, this.password);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return this.verifiy;
    }

    public void redirectLoginPage() {
        ListStudies_ws listStudies_ws = new ListStudies_ws(this.user);
        listStudies_ws.getUserStudyList();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
        if (this.user.getlStudy().isEmpty()) {
            facesContext.getExternalContext().getSessionMap().put("logged", "noStudyFound.xhtml");
        } else {
            facesContext.getExternalContext().getSessionMap().put("logged", "userStudies.xhtml");
        }
        nh.handleNavigation(facesContext, null, "loggedIn.xhtml");
    }

}

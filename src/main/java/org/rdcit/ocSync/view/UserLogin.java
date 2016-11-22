/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.view;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.rdcit.ocSync.controller.UserCredentials;

/**
 *
 * @author sa841
 */
@ManagedBean(name = "UserLogin")
@SessionScoped
public class UserLogin implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(ActionEvent event) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage message = null;
        try {
            if (!facesContext.getExternalContext().getSessionMap().get("ocInstance").equals("OC_Play")) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Loggin Error", "Sorry, this instance is not defined yet.");
            } else {
                UserCredentials userCredentials = new UserCredentials(username, password);
                boolean loggedIn = userCredentials.verifyCredentials();
                if (loggedIn == true) {
                    userCredentials.redirectLoginPage();
                    facesContext.getExternalContext().redirect("loggedIn.xhtml");
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
                }
            }
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Please fill the form first");
            System.out.println(ex.getMessage());
        }
        facesContext.addMessage(null, message);
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";
    }
}

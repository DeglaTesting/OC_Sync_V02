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
              System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ +this.instance " +  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ocInstance"));
        if (facesContext.getExternalContext().getSessionMap().get("ocInstance") == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "You have to choose an OC instance first!!");
            facesContext.addMessage(null, message);
        } else if (!facesContext.getExternalContext().getSessionMap().get("ocInstance").equals("OC_Play")) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Sorry, but this instance is not defined yet!!");
            facesContext.addMessage(null, message);
        } else {
            UserCredentials userCredentials = new UserCredentials(username, password);
            boolean loggedIn = userCredentials.verifyCredentials();
            if (loggedIn == true) {
                userCredentials.redirectLoginPage();
                facesContext.getExternalContext().redirect("loggedIn.xhtml");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
                facesContext.addMessage(null, message);
            }
        }
    }
}

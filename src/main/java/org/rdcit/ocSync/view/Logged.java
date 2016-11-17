/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.view;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author sa841
 */
@ManagedBean(name = "Logged")
public class Logged {

    String userName;
    String loggedPage;

    public String getUserName() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        userName = (String) facesContext.getExternalContext().getSessionMap().get("user");
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoggedPage() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        loggedPage = (String) facesContext.getExternalContext().getSessionMap().get("logged");
        return loggedPage;
    }

    public void setLoggedPage(String loggedPage) {
        this.loggedPage = loggedPage;
    }
    
    public String logout(){
         FacesContext facesContext = FacesContext.getCurrentInstance();
         facesContext.getExternalContext().invalidateSession();
         return  "index.xhtml";
    }

}

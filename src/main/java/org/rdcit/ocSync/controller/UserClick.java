package org.rdcit.ocSync.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author sa841
 */
@ManagedBean(name = "UserClick")
@ViewScoped
public class UserClick implements Serializable {
     
    String formLodder = "default.xhtml";
    public String getFormLodder() {
        return formLodder;
    }

    public void setFormLodder(String formLodder) {
        switch (formLodder) {
            case "Map":
                this.formLodder = "map.xhtml";
                break;
            case "Reset":
                UploadedFile.sourceUploadedFile = null;
                this.formLodder = "default.xhtml";
                break;
            case "Error":
                this.formLodder = "error.xhtml";
                break;
            default:
                break;
        }
    }
}

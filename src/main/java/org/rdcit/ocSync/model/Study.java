/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.model;

/**
 *
 * @author sa841
 */
public class Study {

    String study_name;
    String study_oid;
     String study_id;
    String owner_id;
    String owner_name;

    public Study() {
    }
    
    public Study(String study_id, String study_name, String study_oid) {
        this.study_id = study_id;
        this.study_name = study_name;
        this.study_oid = study_oid;
    }

    public String getStudy_name() {
        return study_name;
    }

    public void setStudy_name(String study_name) {
        this.study_name = study_name;
    }
    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getStudy_oid() {
        return study_oid;
    }

    public void setStudy_oid(String study_oid) {
        this.study_oid = study_oid;
    }

    public String getStudy_id() {
        return study_id;
    }

    public void setStudy_id(String study_id) {
        this.study_id = study_id;
    }

    @Override
    public String toString() {
        return "Study {" + "study_name=" + study_name + ", study_oid=" + study_oid + ", study_id=" + study_id + '}';
    }
}

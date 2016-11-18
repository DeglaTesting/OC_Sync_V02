/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.model;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author sa841
 */

@ManagedBean(name = "StructureModel")
public class StructureModel {

    String name;
    String sourceOID;
    String targetOID;
    String type;

    public StructureModel() {
    }

    public StructureModel(String type, String name, String sourceOID, String targetOID) {
       this.type = type;
        this.name = name;
        this.sourceOID = sourceOID;
        this.targetOID = targetOID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceOID() {
        return sourceOID;
    }

    public void setSourceOID(String sourceOID) {
        this.sourceOID = sourceOID;
    }

    public String getTargetOID() {
        return targetOID;
    }

    public void setTargetOID(String targetOID) {
        this.targetOID = targetOID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
 
}

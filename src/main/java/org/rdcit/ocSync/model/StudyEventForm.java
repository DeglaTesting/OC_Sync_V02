    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sa841
 */
public class StudyEventForm {

    String formOID;
    String formVersion;
    String formID;
    String formName;
    List<ItemGroup> lItemGroup;

    public StudyEventForm(String formOID, String formID, String formName) {
        this.formOID = formOID;
        this.formID = formID;
        this.formName = formName;
         lItemGroup = new ArrayList();
    }

    public StudyEventForm(String formOID) {
        this.formOID = formOID;
        lItemGroup = new ArrayList();
    }

    public String getFormOID() {
        return formOID;
    }

    public void setFormOID(String formOID) {
        this.formOID = formOID;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormVersion() {
        return formVersion;
    }

    public void setFormVersion(String formVersion) {
        this.formVersion = formVersion;
    }

    public String getFormID() {
        return formID;
    }

    public void setFormID(String formID) {
        this.formID = formID;
    }


    public List<ItemGroup> getlItemGroup() {
        return lItemGroup;
    }

    public void setlItemGroup(List<ItemGroup> lItemGroup) {
        this.lItemGroup = lItemGroup;
    }
    
    public void addItemGroup(ItemGroup itemGroup){
        lItemGroup.add(itemGroup);
    }
 
}

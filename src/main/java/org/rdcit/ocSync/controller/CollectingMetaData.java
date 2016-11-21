/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.rdcit.ocSync.model.StructureModel;
import org.rdcit.ocSync.model.StudyEvent;
import org.rdcit.ocSync.ocws.StudyMetaData_ws;

/**
 *
 * @author sa841
 */
@ManagedBean(name = "CollectingMetaData")
@RequestScoped
public class CollectingMetaData {

    List<StructureModel> lStructureModel = new ArrayList();
    List<String> lEmptyField = new ArrayList();
    boolean iftheymatch = true;
    @ManagedProperty(value = "#{UserClick}")
    UserClick userClick;
    @ManagedProperty(value = "#{StudyMetaData_ws}")
    StudyMetaData_ws targetStudyMetaData;
    @ManagedProperty(value = "#{FileUpload}")
    FileUpload sourceStudyMetaData;
    //  List<StudyEvent> lSourceStudyEvent;

    public CollectingMetaData() {
     System.out.println("DO MAPPING METHOD CALLLED");}

    public List<StructureModel> getlStructureModel() {
        System.out.println(lStructureModel.size());
        return lStructureModel;
    }

    public List<String> getlEmptyField() {
        System.out.println(lEmptyField.size());
        return lEmptyField;
    }

    public UserClick getUserClick() {
        return userClick;
    }

    public void setUserClick(UserClick userClick) {
        this.userClick = userClick;
    }

    public Object[] doMapping(ActionEvent actionEvent) {
        System.out.println("DO MAPPING METHOD CALLLED");
        Object[] res = null;
        try {
         //CollectingMetaData collectingMetaData = new CollectingMetaData();
            List<StudyEvent> lSourceStudyEvent = sourceStudyMetaData.getlSourceStudyEvent();
            System.out.println("DO MAPPING METHOD CALLLED lSourceStudyEvent" +lSourceStudyEvent.size());
            List<StudyEvent> lTargetStudyEvent = targetStudyMetaData.getlTargetStudyEvent();
              System.out.println("DO MAPPING METHOD CALLLED lTargetStudyEvent" +lTargetStudyEvent.size());
            // List<StudyEvent> lSourceStudyEvent = collectingMetaData.collectingStudyMetaData(collectingMetaData.openFile(sourceFile));
            // List<StudyEvent> lTargetStudyEvent = collectingMetaData.collectingStudyMetaData(collectingMetaData.openFile(targetFile));
            Mapper mapper = new Mapper();
            res = mapper.mapping(lSourceStudyEvent, lTargetStudyEvent);
            lEmptyField = (List<String>) res[1];
            lStructureModel = (List<StructureModel>) res[0];
            iftheymatch = (boolean) res[2];
            if (iftheymatch == true) {
                userClick.setFormLodder("Map");
            } else if (lEmptyField.isEmpty()) {
                userClick.setFormLodder("Error");

            } else if (lEmptyField.size() >= 1) {
                userClick.setFormLodder("Map");
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage("You should upload your source and taget files first!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return res;
    }

}

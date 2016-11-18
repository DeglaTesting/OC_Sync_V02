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
import javax.faces.context.FacesContext;
import org.rdcit.ocSync.model.StructureModel;
import org.rdcit.ocSync.model.StudyEvent;
import org.rdcit.ocSync.ocws.StudyMetaData_ws;

/**
 *
 * @author sa841
 */
@ManagedBean(name = "CollectingMetaData")
public class CollectingMetaData {

    List<StructureModel> lStructureModel;
    List<String> lEmptyField;
    boolean iftheymatch;
    @ManagedProperty(value = "#{UserClick}")
    UserClick userClick;
    @ManagedProperty(value = "#{StudyMetaData_ws}")
    StudyMetaData_ws targetStudyMetaData;
    @ManagedProperty(value = "#{FileUpload}")
    FileUpload sourceStudyMetaData;
    //  List<StudyEvent> lSourceStudyEvent;

    public CollectingMetaData() {
        lStructureModel = new ArrayList();
        lEmptyField = new ArrayList();

    }

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

    public Object[] doMapping() {
        Object[] res = null;
        try {
            CollectingMetaData collectingMetaData = new CollectingMetaData();
            List<StudyEvent> lSourceStudyEvent = sourceStudyMetaData.getlSourceStudyEvent();
            List<StudyEvent> lTargetStudyEvent = targetStudyMetaData.getlTargetStudyEvent();
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

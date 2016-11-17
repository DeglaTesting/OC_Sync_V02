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
public class StudyEvent {

    String eventOID;
    String eventDefID;
    String eventName;
    List<StudyEventForm> lStudyEventForm;

    public StudyEvent(String eventOID, String eventDefID, String eventName) {
        this.eventOID = eventOID;
        this.eventDefID = eventDefID;
        this.eventName = eventName;
        lStudyEventForm = new ArrayList();
    }
    
    public StudyEvent(String eventOID) {
        this.eventOID = eventOID;
        lStudyEventForm = new ArrayList();
    }

    public String getEventOID() {
        return eventOID;
    }

    public void setEventOID(String eventOID) {
        this.eventOID = eventOID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDefID() {
        return eventDefID;
    }

    public void setEventDefID(String eventDefID) {
        this.eventDefID = eventDefID;
    }

    public List<StudyEventForm> getlStudyEventForm() {
        return lStudyEventForm;
    }

    public void setlStudyEventForm(List<StudyEventForm> lStudyEventForm) {
        this.lStudyEventForm = lStudyEventForm;
    }

   public void addStudyEventForm (StudyEventForm studyEventForm){
       this.lStudyEventForm.add(studyEventForm);
   } 
   
}

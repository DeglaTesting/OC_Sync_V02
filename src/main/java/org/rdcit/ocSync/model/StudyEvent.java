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
    String eventName;
    List<StudyEventForm> lStudyEventForm;

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

    public List<StudyEventForm> getlStudyEventForm() {
        return lStudyEventForm;
    }

    public void setlStudyEventForm(List<StudyEventForm> lStudyEventForm) {
        this.lStudyEventForm = lStudyEventForm;
    }

   public void addStudyEventForm (StudyEventForm studyEventForm){
       this.lStudyEventForm.add(studyEventForm);
   } 

    @Override
    public String toString() {
        return "StudyEvent{" + "eventOID=" + eventOID + " eventName=" + eventName + '}';
    }
   
   
   
}

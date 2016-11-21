/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sa841
 */
public class Study implements Serializable{

    String study_name;
    String study_oid;
    String study_u_p_id;
    List<StudyEvent> lStudyEvent;

    public Study() {
    }

    public Study(String study_u_p_id, String study_name, String study_oid) {
        this.study_u_p_id = study_u_p_id;
        this.study_name = study_name;
        this.study_oid = study_oid;
        lStudyEvent = new ArrayList();
    }

    public String getStudy_name() {
        return study_name;
    }

    public void setStudy_name(String study_name) {
        this.study_name = study_name;
    }

    public String getStudy_oid() {
        return study_oid;
    }

    public void setStudy_oid(String study_oid) {
        this.study_oid = study_oid;
    }

    public String getStudy_u_p_id() {
        return study_u_p_id;
    }

    public void setStudy_u_p_id(String study_u_p_id) {
        this.study_u_p_id = study_u_p_id;
    }

    public List<StudyEvent> getlStudyEvent() {
        return lStudyEvent;
    }

    public void setlStudyEvent(List<StudyEvent> lStudyEvent) {
        this.lStudyEvent = lStudyEvent;
    }
    public void addStudyEvent(StudyEvent StudyEvent) {
        this.lStudyEvent.add(StudyEvent);
    }
    

    @Override
    public String toString() {
        return "Study {" + "study_name=" + study_name + ", study_oid=" + study_oid + ", study_id=" + study_u_p_id + '}';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.rdcit.ocSync.model.*;

/**
 *
 * @author sa841
 */
@ManagedBean( name = "TargetStudyMetaData")
public class TargetStudyMetaData {

    String study_id;
    String owner_id;
    Statement stmt;
    ResultSet rs;
    List<StudyEvent> lStudyEvent;

    public TargetStudyMetaData() {
        this.owner_id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_id");
      //  this.study_id = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("study_id");
        lStudyEvent = new ArrayList();
    }

    public List<StudyEvent> getlStudyEvent() {
         System.out.println("SELECT study_event_definition_id, name, oc_oid  FROM study_event_definition WHERE study_id = '" + study_id + "' "
                    + "AND owner_id = '" + owner_id + "';");
        try {
            stmt = Connect.openConnection().createStatement();
            rs = stmt.executeQuery("SELECT study_event_definition_id, name, oc_oid  FROM study_event_definition WHERE study_id = '" + study_id + "' "
                    + "AND owner_id = '" + owner_id + "';");
             
            while (rs.next()) {
                System.out.println(" ############## NEWW ONEEE ############## ");
                System.out.println("@study_event_definition_id " + rs.getString("study_event_definition_id"));
                System.out.println("@name " + rs.getString("name"));
                System.out.println("@oc_oid " + rs.getString("oc_oid"));
                StudyEvent studyEvent = new StudyEvent(rs.getString("oc_oid"), rs.getString("study_event_definition_id"), rs.getString("name"));
                lStudyEvent.add(studyEvent);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lStudyEvent;
    }

    public void getlStudyEventForm(StudyEvent studyEvent) {
        try {
            stmt = Connect.openConnection().createStatement();
            rs = stmt.executeQuery("SELECT crf.crf_id, crf.name, crf.owner_id, crf.oc_oid"
                    + " FROM crf, crf_version, event_crf"
                    + " WHERE crf_version.crf_version_id = event_crf.crf_version_id"
                    + " AND crf_version.crf_id = crf.crf_id"
                    + " AND event_crf.study_event_id = '" + studyEvent.getEventDefID() + "'"
                    + " AND event_crf.owner_id = '" + owner_id + "';");
            while (rs.next()) {
                System.out.println(" ############## NEWW ONEEE ############## ");
                System.out.println("@crf_id " + rs.getString("crf_id"));
                System.out.println("@oc_oid " + rs.getString("oc_oid"));
                System.out.println("@name " + rs.getString("name"));
                StudyEventForm studyEventForm = new StudyEventForm(rs.getString("oc_oid"), rs.getString("crf_id"), rs.getString("name"));
                studyEvent.addStudyEventForm(studyEventForm);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getlItemGroup(StudyEventForm studyEventForm) {
        try {
            stmt = Connect.openConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM item_group WHERE crf_id = '" + studyEventForm.getFormID() + "';");
            while (rs.next()) {
                System.out.println(" ############## NEWW ONEEE ############## ");
                System.out.println("@item_group_id " + rs.getString("item_group_id"));
                System.out.println("@name " + rs.getString("name"));
                System.out.println("@oc_oid " + rs.getString("oc_oid"));
                ItemGroup itemGroup = new ItemGroup(rs.getString("oc_oid"), rs.getString("item_group_id"), rs.getString("name"));
                studyEventForm.addItemGroup(itemGroup);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getlItem(ItemGroup itemGroup) {
        try {
            stmt = Connect.openConnection().createStatement();
            rs = stmt.executeQuery("SELECT item.name, item.oc_oid, item_group_metadata.item_id "
                    + "FROM item, item_group_metadata  "
                    + "WHERE item_group_metadata.item_group_id = '" + itemGroup.getItemGroupID() + "';");
            while (rs.next()) {
                System.out.println(" ############## NEWW ONEEE ############## ");
                System.out.println("@name" + rs.getString("name"));
                System.out.println("@oc_oid " + rs.getString("oc_oid"));
                System.out.println("@item_id " + rs.getString("item_id"));
                Item item = new Item(rs.getString("oc_oid"), rs.getString("item_id"), rs.getString("name"));
                itemGroup.addItem(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getStudyMetaData() {
        this.lStudyEvent = getlStudyEvent();
        for (int i = 0; i < lStudyEvent.size(); i++) {
            getlStudyEventForm(lStudyEvent.get(i));
            List<StudyEventForm> lStudyEventForm = lStudyEvent.get(i).getlStudyEventForm();
            for (int j = 0; j < lStudyEventForm.size(); j++) {
                getlItemGroup(lStudyEventForm.get(j));
                List<ItemGroup> lItemGroup = lStudyEventForm.get(j).getlItemGroup();
                for (int k = 0; k < lItemGroup.size(); k++) {
                    getlItem(lItemGroup.get(k));
                    List<Item> lItem = lItemGroup.get(k).getlItem();
                }
            }
        }
      //  FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("lTargetStudyEvent", this.lStudyEvent);
    }

    /*  public static void main(String[] args) {
        TargetStudyMetaData targetStudyMetaData = new TargetStudyMetaData("9", "8");
        targetStudyMetaData.getlStudyEvent();
        for (int i = 0; i < targetStudyMetaData.lStudyEvent.size(); i++) {
            targetStudyMetaData.getlStudyEventForm(targetStudyMetaData.lStudyEvent.get(i));
            List<StudyEventForm> lStudyEventForm = targetStudyMetaData.lStudyEvent.get(i).getlStudyEventForm();
            for (int j = 0; j < lStudyEventForm.size(); j++) {
                targetStudyMetaData.getlItemGroup(lStudyEventForm.get(j));
                List<ItemGroup> lItemGroup = lStudyEventForm.get(j).getlItemGroup();
                for (int k = 0; k < lItemGroup.size(); k++) {
                    targetStudyMetaData.getlItem(lItemGroup.get(k));
                    List<Item> lItem = lItemGroup.get(k).getlItem();
                }
            }
            
        }
    }*/
}

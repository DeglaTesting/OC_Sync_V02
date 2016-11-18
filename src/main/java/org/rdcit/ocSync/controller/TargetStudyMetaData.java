/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;

import java.util.List;
import org.rdcit.ocSync.model.*;
import org.w3c.dom.Document;

/**
 *
 * @author sa841
 */
public class TargetStudyMetaData {

    public void getTargetStudyMetaData(Document document){
        
         System.out.println("@@@@@@@@@@@@@ " + document.getElementsByTagName("StudyEventDef"));
        CollectingStudyEvents collectingStudyEvents = new CollectingStudyEvents();
        List<StudyEvent> lTargetStudyEvent = collectingStudyEvents.collectingStudyEvents(document);
        for(int i = 0; i<lTargetStudyEvent.size(); i++){
            CollectingStudyEventForms collectingStudyEventForms = new CollectingStudyEventForms();
            List<StudyEventForm> lTargetStudyEventForms = collectingStudyEventForms.collectingStudyEventForms(document, lTargetStudyEvent.get(i));
            for(int j = 0; j < lTargetStudyEventForms.size(); j++){
                CollectingItemGroup collectingItemGroup= new CollectingItemGroup();
                List<ItemGroup> lTargetItemGroup = collectingItemGroup.collectingItemGroup(document, lTargetStudyEventForms.get(j));
                for(int k=0 ; k < lTargetItemGroup.size(); k++){
                    CollectingItems collectingItems = new CollectingItems();
                    List<Item> lTargetItem = collectingItems.collectingItems(document, lTargetItemGroup.get(k));
                    
                }
            }
            
        }
        System.out.println("the Target study has + "+ lTargetStudyEvent.get(0).getlStudyEventForm().get(0).getlItemGroup().get(0).getlItem().size());
    }

   
}

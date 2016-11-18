/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;


import java.util.ArrayList;
import java.util.List;
import org.rdcit.ocSync.model.Study;
import org.rdcit.ocSync.model.StudyEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author sa841
 */
public class CollectingStudyEvents {
    

    public CollectingStudyEvents() {
    }
    public  List<StudyEvent> collectingStudyEvents(Document doc) {
        List<StudyEvent> lStudyEvent = new ArrayList();
            NodeList nlStudyEvent = doc.getElementsByTagName("StudyEventDef");
            for (int i = 0; i < nlStudyEvent.getLength(); i++) {
                Node nStudyEvent = nlStudyEvent.item(i);
                if ((nStudyEvent.getNodeType() == Node.ELEMENT_NODE) ) {
                    Element eStydyEvent = (Element) nStudyEvent;
                    StudyEvent studyEvent = new StudyEvent(eStydyEvent.getAttribute("OID"));
                    studyEvent.setEventName(eStydyEvent.getAttribute("Name"));
                    studyEvent.toString();
                    lStudyEvent.add(studyEvent);
                }
            }
            System.out.println("WOWOWOOWOW " + lStudyEvent.size());
        return lStudyEvent;
    }
    
    
}

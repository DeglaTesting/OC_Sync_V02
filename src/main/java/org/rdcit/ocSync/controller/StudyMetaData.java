/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.rdcit.ocSync.model.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author sa841
 */
public class StudyMetaData {

    public void getStudyMetaData(Document document) {
        CollectingStudyEvents collectingStudyEvents = new CollectingStudyEvents();
        List<StudyEvent> lStudyEvent = collectingStudyEvents.collectingStudyEvents(document);
        for (int i = 0; i < lStudyEvent.size(); i++) {
            CollectingStudyEventForms collectingStudyEventForms = new CollectingStudyEventForms();
            List<StudyEventForm> lStudyEventForms = collectingStudyEventForms.collectingStudyEventForms(document, lStudyEvent.get(i));
            for (int j = 0; j < lStudyEventForms.size(); j++) {
                CollectingItemGroup collectingItemGroup = new CollectingItemGroup();
                List<ItemGroup> lItemGroup = collectingItemGroup.collectingItemGroup(document, lStudyEventForms.get(j));
                for (int k = 0; k < lItemGroup.size(); k++) {
                    CollectingItems collectingItems = new CollectingItems();
                    List<Item> lItem = collectingItems.collectingItems(document, lItemGroup.get(k));
                }
            }
        }
        System.out.println("the Target study has + " + lStudyEvent.get(0).getlStudyEventForm().get(0).getlItemGroup().get(0).getlItem().size());
    }
    
     public void getStudyMetaDataFromFile(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            getStudyMetaData(doc);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;


import java.util.ArrayList;
import java.util.List;
import org.rdcit.ocSync.model.ItemGroup;
import org.rdcit.ocSync.model.StudyEventForm;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author sa841
 */
public class CollectingItemGroup {

    public List<ItemGroup> collectingItemGroup(Document doc, StudyEventForm studyEventForm) {
        List<ItemGroup> lItemGroup = new ArrayList();
        NodeList nlFormDef = doc.getElementsByTagName("FormDef");
        for (int i = 0; i < nlFormDef.getLength(); i++) {
            Node nFormDef = nlFormDef.item(i);
            if (nFormDef.getNodeType() == Node.ELEMENT_NODE) {
                Element eFormDef = (Element) nFormDef;
                if (eFormDef.getAttribute("OID").equals(studyEventForm.getFormOID())) {
                    NodeList nlItemGroupRef = nFormDef.getChildNodes();
                    for (int j = 0; j < nlItemGroupRef.getLength(); j++) {
                        Node nItemGroupRef = nlItemGroupRef.item(j);
                        if ((nItemGroupRef.getNodeType() == Node.ELEMENT_NODE) && (nItemGroupRef.getNodeName().equals("ItemGroupRef"))) {
                            Element eItemGroupRef = (Element)nItemGroupRef;
                            ItemGroup itemGroup = new ItemGroup(eItemGroupRef.getAttribute("ItemGroupOID"));
                            findItemGroupName(doc, eItemGroupRef.getAttribute("ItemGroupOID"), itemGroup);
                            studyEventForm.addItemGroup(itemGroup);
                            lItemGroup.add(itemGroup);
                        }
                    }
                }
            }
        }
        return lItemGroup;
    }

    public void findItemGroupName(Document doc, String itemGroupOID, ItemGroup itemGroup) {
        NodeList nlGroupDef = doc.getElementsByTagName("ItemGroupDef");
        for (int i = 0; i < nlGroupDef.getLength(); i++) {
            Node nGroupDef = nlGroupDef.item(i);
            if (nGroupDef.getNodeType() == Node.ELEMENT_NODE) {
                Element eGroupDef = (Element) nGroupDef;
                if (eGroupDef.getAttribute("OID").equals(itemGroupOID)) {
                    itemGroup.setItemGroupName(eGroupDef.getAttribute("Name"));
                }
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;

import java.util.ArrayList;
import java.util.List;
import org.rdcit.ocSync.model.Item;
import org.rdcit.ocSync.model.ItemGroup;
import org.rdcit.ocSync.model.StructureModel;
import org.rdcit.ocSync.model.StudyEvent;
import org.rdcit.ocSync.model.StudyEventForm;

/**
 *
 * @author sa841
 */
public class Mapper {
    
    List<StructureModel> lStructureModel;
    List<String> lEmptyField;
    List<StudyEvent> lSourceStudyEvent;
    List<StudyEvent> lTargetStudyEvent;
    Object[] mappingRes = new Object[3];
    boolean iftheymatch;
    
    public Mapper() {
        lStructureModel = new ArrayList();
        lEmptyField = new ArrayList();
        iftheymatch = true;
    }
    
    public Object[] mapping(List<StudyEvent> lSourceStudyEvent, List<StudyEvent> lTargetStudyEvent) {
        if (lSourceStudyEvent.size() <= lTargetStudyEvent.size()) {
            int countevents = 0;
            for (int i = 0; i < lSourceStudyEvent.size(); i++) {
                for (int j = i; j < lSourceStudyEvent.size(); j++) {
                    if (lSourceStudyEvent.get(i).getEventName().equals(lTargetStudyEvent.get(j).getEventName())) {
                        StructureModel structureModel1 = new StructureModel("StudyEvent", lSourceStudyEvent.get(i).getEventName(), lSourceStudyEvent.get(i).getEventOID(), lTargetStudyEvent.get(j).getEventOID());
                        lStructureModel.add(structureModel1);
                        countevents++;
                        List<StudyEventForm> lSourceStudyEventFrom = lSourceStudyEvent.get(i).getlStudyEventForm();
                        List<StudyEventForm> lTargetStudyEventFrom = lTargetStudyEvent.get(j).getlStudyEventForm();
                        if (lSourceStudyEventFrom.size() <= lTargetStudyEventFrom.size()) {
                            int counteventforms = 0;
                            for (int k = 0; k < lSourceStudyEventFrom.size(); k++) {
                                for (int l = k; l < lSourceStudyEventFrom.size(); l++) {
                                    if (lSourceStudyEventFrom.get(k).getFormName().equals(lTargetStudyEventFrom.get(l).getFormName())) {
                                        StructureModel structureModel2 = new StructureModel("StudyEventForm", lSourceStudyEventFrom.get(k).getFormName(), lSourceStudyEventFrom.get(k).getFormOID(), lTargetStudyEventFrom.get(l).getFormOID());
                                        lStructureModel.add(structureModel2);
                                        counteventforms++;
                                        List<ItemGroup> lSourceItemGroup = lSourceStudyEventFrom.get(k).getlItemGroup();
                                        List<ItemGroup> lTargetItemGroup = lTargetStudyEventFrom.get(l).getlItemGroup();
                                        if (lSourceItemGroup.size() <= lTargetItemGroup.size()) {
                                            int countgroupitems = 0;
                                            for (int m = 0; m < lSourceItemGroup.size(); m++) {
                                                for (int n = m; n < lSourceItemGroup.size(); n++) {
                                                    if (lSourceItemGroup.get(m).getItemGroupName().equals(lTargetItemGroup.get(n).getItemGroupName())) {
                                                        StructureModel structureModel3 = new StructureModel("ItemGroup", lSourceItemGroup.get(m).getItemGroupName(), lSourceItemGroup.get(m).getItemGroupOID(), lTargetItemGroup.get(n).getItemGroupOID());
                                                        lStructureModel.add(structureModel3);
                                                        countgroupitems++;
                                                        List<Item> lSourceItem = lSourceItemGroup.get(m).getlItem();
                                                        List<Item> lTargetItem = lTargetItemGroup.get(n).getlItem();
                                                        if (lSourceItem.size() <= lTargetItem.size()) {
                                                            int countitems = 0;
                                                            for (int o = 0; o < lSourceItem.size(); o++) {
                                                                for (int p = 0; p < lSourceItem.size(); p++) {
                                                                    if (lSourceItem.get(o).getItemName().equals(lTargetItem.get(p).getItemName())) {
                                                                        StructureModel structureModel4 = new StructureModel("Item", lSourceItem.get(o).getItemName(), lSourceItem.get(o).getItemOID(), lTargetItem.get(p).getItemOID());
                                                                        lStructureModel.add(structureModel4);
                                                                        countitems++;
                                                                    }
                                                                }
                                                            }
                                                            if (lSourceItem.size() != countitems) {
                                                                iftheymatch = false;
                                                            }
                                                            collectingEmptyField("Item", lTargetItem, lSourceItem.size(), lTargetStudyEvent.get(j).getEventName().concat(" "+lTargetStudyEventFrom.get(l).getFormName().concat(" "+lTargetItemGroup.get(n).getItemGroupName())));
                                                            
                                                        } else {
                                                            iftheymatch = false;
                                                             
                                                        }
                                                    }
                                                }
                                            }
                                            if (lSourceItemGroup.size() != countgroupitems) {
                                                iftheymatch = false;
                                            }
                                            collectingEmptyField("GroupItem", lTargetItemGroup, lSourceItemGroup.size(), lTargetStudyEvent.get(j).getEventName().concat(lTargetStudyEventFrom.get(l).getFormName()));
                                            
                                        } else {
                                            iftheymatch = false;
                                        }
                                    }
                                }
                            }
                            if (lSourceStudyEventFrom.size() != counteventforms) {
                                iftheymatch = false;
                            }
                            collectingEmptyField("StudyEventForm", lTargetStudyEventFrom, lSourceStudyEventFrom.size(), lTargetStudyEvent.get(j).getEventName());
                        } else {
                            iftheymatch = false;
                        }
                    }
                }
            }
            if (lSourceStudyEvent.size() != countevents) {
                iftheymatch = false;
            }
            collectingEmptyField("StudyEvent", lTargetStudyEvent, lSourceStudyEvent.size(), "");
        } else {
            iftheymatch = false;
        }

        mappingRes[0] = lStructureModel;
        mappingRes[1] = lEmptyField;
        mappingRes[2] = iftheymatch;
        return mappingRes;
    }
      
    public void collectingEmptyField(String nodeType, List lTargetStudy, int index, String pathNode) {
        switch (nodeType) {
            case "StudyEvent":
                for (int i = index; i < lTargetStudy.size(); i++) {
                    StudyEvent studyEvent = (StudyEvent) lTargetStudy.get(i);
                    List<StudyEventForm> lStudyEventForm = studyEvent.getlStudyEventForm();
                    for (int j = 0; j < lStudyEventForm.size(); j++) {
                        List<ItemGroup> lItemGroup = lStudyEventForm.get(j).getlItemGroup();
                        for (int k = 0; k < lItemGroup.size(); k++) {
                            List<Item> lItem = lItemGroup.get(k).getlItem();
                            for (int l = 0; l < lItem.size(); l++) {
                                String itemID = lTargetStudyEvent.get(i).getEventName().concat(" " + lStudyEventForm.get(j).getFormName().concat(" " + lItemGroup.get(k).getItemGroupName().concat(" " + lItem.get(l).getItemName())));
                                lEmptyField.add(itemID);
                            }
                        }
                    }
                }
            case "StudyEventForm":
                for (int i = index; i < lTargetStudy.size(); i++) {
                    StudyEventForm studyEventForm = (StudyEventForm) lTargetStudy.get(i);
                    List<ItemGroup> lItemGroup = studyEventForm.getlItemGroup();
                    for (int j = 0; j < lItemGroup.size(); j++) {
                        List<Item> lItem = lItemGroup.get(j).getlItem();
                        for (int k = 0; k < lItem.size(); k++) {
                            String itemID = pathNode.concat(" " + studyEventForm.getFormName().concat(" " + lItemGroup.get(j).getItemGroupName().concat(" " + lItem.get(k).getItemName())));
                            lEmptyField.add(itemID);
                        }
                    }
                }
            case "ItemGroup":
                for (int i = index; i < lTargetStudy.size(); i++) {
                    ItemGroup itemGroup = (ItemGroup) lTargetStudy.get(i);
                    List<Item> lItem = itemGroup.getlItem();
                    for (int j = 0; j < lItem.size(); j++) {
                        String itemID = pathNode.concat(" " + itemGroup.getItemGroupName().concat(" " + lItem.get(j).getItemName()));
                        lEmptyField.add(itemID);
                    }
                }
            case "Item":
                for (int i = index; i < lTargetStudy.size(); i++) {
                    Item item = (Item) lTargetStudy.get(i);
                    String itemID = pathNode.concat(" " + item.getItemName());
                    lEmptyField.add(itemID);
                }
        }
        
    }
    
}

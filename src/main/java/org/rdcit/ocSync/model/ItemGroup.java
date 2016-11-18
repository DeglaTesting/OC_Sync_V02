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
public class ItemGroup {

    String itemGroupOID;
    String itemGroupName;
    List<Item> lItem;

    public ItemGroup(String itemGroupOID) {
        this.itemGroupOID = itemGroupOID;
        lItem =  new ArrayList();
    }

    public String getItemGroupOID() {
        return itemGroupOID;
    }

    public void setItemGroupOID(String itemGroupOID) {
        this.itemGroupOID = itemGroupOID;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public List<Item> getlItem() {
        return lItem;
    }

    public void setlItem(List<Item> lItem) {
        this.lItem = lItem;
    }
    
    public void addItem(Item item){
        this.lItem.add(item);
    }

    @Override
    public String toString() {
        return "ItemGroup{" + "itemGroupOID=" + itemGroupOID + ", itemGroupName=" + itemGroupName + '}';
    }

}

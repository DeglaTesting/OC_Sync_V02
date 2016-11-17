/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.model;

/**
 *
 * @author sa841
 */
public class Item {
    String itemOID;
    String itemID;
    String itemName;

    public Item(String itemOID, String itemID, String itemName) {
        this.itemOID = itemOID;
        this.itemID = itemID;
        this.itemName = itemName;
    }

    public Item(String itemOID) {
        this.itemOID = itemOID;
    }


    public String getItemOID() {
        return itemOID;
    }

    public void setItemOID(String itemOID) {
        this.itemOID = itemOID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

}

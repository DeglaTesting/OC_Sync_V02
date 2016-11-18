/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.rdcit.ocSync.controller.CollectingMetaData;

/**
 *
 * @author sa841
 */
@ManagedBean(name = "EmptyFieldView")
public class EmptyFieldView implements Serializable {

    public List<String> lEmptyField;
    @ManagedProperty(value = "#{CollectingMetaData}")
    CollectingMetaData collectingMetaData;

    @PostConstruct
    public void init() {
        lEmptyField = new ArrayList();
        lEmptyField = collectingMetaData.getlEmptyField();
    }

    public List<String> getlEmptyField() {
        return lEmptyField;
    }

    public void setlEmptyField(List<String> lEmptyField) {
        this.lEmptyField = lEmptyField;
    }

    public CollectingMetaData getCollectingMetaData() {
        return collectingMetaData;
    }

    public void setCollectingMetaData(CollectingMetaData collectingMetaData) {
        this.collectingMetaData = collectingMetaData;
    }
   

}

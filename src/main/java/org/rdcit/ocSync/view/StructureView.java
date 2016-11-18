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
import org.rdcit.ocSync.model.StructureModel;
import org.rdcit.ocSync.controller.CollectingMetaData;

/**
 *
 * @author sa841
 */
@ManagedBean(name = "StructureView")
public class StructureView implements Serializable {

    public List<StructureModel> lstructureModel;
    @ManagedProperty(value = "#{CollectingMetaData}")
    CollectingMetaData collectingMetaData;

    public CollectingMetaData getCollectingMetaData() {
        return collectingMetaData;
    }

    public void setCollectingMetaData(CollectingMetaData collectingMetaData) {
        this.collectingMetaData = collectingMetaData;
    }

    @PostConstruct
    public void init() {
        lstructureModel = new ArrayList();
        lstructureModel = collectingMetaData.getlStructureModel();
    }

    public List<StructureModel> getlstructureModel() {
        return lstructureModel;
    }

    public void setlstructureModel(List<StructureModel> lstructureModel) {
        this.lstructureModel = lstructureModel;
    }

}

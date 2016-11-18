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
@ManagedBean(name = "TargetStudyMetaData")
public class TargetStudyMetaData {

    Study study;

    public TargetStudyMetaData(Study study) {
        this.study = study;
    }

   
}

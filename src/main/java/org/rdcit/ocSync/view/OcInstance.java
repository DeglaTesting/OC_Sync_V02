/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.view;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author sa841
 */
@ManagedBean( name="OcInstance")
public class OcInstance implements Serializable {
    private static final long serialVersionUID = 1L; 
    private String instance;

    public String getInstance() {
        System.out.println("");
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
    
    
}

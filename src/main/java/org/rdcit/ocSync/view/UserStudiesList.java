/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.view;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.rdcit.ocSync.model.Study;

/**
 *
 * @author sa841
 */
@ManagedBean(name = "UserStudiesList")
public class UserStudiesList {

    private MenuModel lUserStudy;

    @PostConstruct
    public void init() {
        lUserStudy = new DefaultMenuModel();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        List<Study> luserStudy = (List<Study>) facesContext.getExternalContext().getSessionMap().get("studyUserList");
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Your Studies");

        for (int i = 0; i < luserStudy.size(); i++) {
            DefaultMenuItem item = new DefaultMenuItem(luserStudy.get(i).getStudy_name());
            item.setCommand("#{TargetStudyMetaData.getStudyMetaData()}");
            item.setIcon("ui-icon-document");
            firstSubmenu.addElement(item);
            lUserStudy.addElement(firstSubmenu);
        }
    }

    public MenuModel getModel() {
        return lUserStudy;
    }

    public void setModel(MenuModel model) {
        this.lUserStudy = model;
    }

}
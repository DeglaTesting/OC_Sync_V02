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
public class User {

    String user_name;
    String user_id;
    String password;
    List<Study> lStudy;

    public User(String user_name, String user_id, String password) {
        this.user_name = user_name;
        this.user_id = user_id;
        this.password = password;
        lStudy = new ArrayList();
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Study> getlStudy() {
        return lStudy;
    }

    public void setlStudy(List<Study> lStudy) {
        this.lStudy = lStudy;
    }

    public void addStudy(Study study) {
        this.lStudy.add(study);
    }

    public void print() {
        System.out.println("User name " + this.user_name + " user id " + this.user_id + " user password " + this.password + " has " +this.lStudy.size() + " Study(ies)." );
    }
}

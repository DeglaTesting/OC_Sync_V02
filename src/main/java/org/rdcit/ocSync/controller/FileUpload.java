/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.rdcit.ocSync.model.StudyEvent;

/**
 *
 * @author sa841
 */
@ManagedBean(name = "FileUpload")
@ViewScoped
public class FileUpload {

    public String sourcefilePath;
    private final String destination = "C:\\Users\\sa841\\Documents\\";
    List<StudyEvent> lSourceStudyEvent;

    public void uploadSourceFile(FileUploadEvent event) {
        try {
            lSourceStudyEvent = new ArrayList();
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            sourcefilePath = destination + event.getFile().getFileName();
            UploadedFile.sourceUploadedFile = new File(sourcefilePath);
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            StudyMetaData studyMetaData = new StudyMetaData(); 
            lSourceStudyEvent = studyMetaData.getStudyMetaDataFromFile(UploadedFile.sourceUploadedFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void copyFile(String fileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(destination + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<StudyEvent> getlSourceStudyEvent() {
        return lSourceStudyEvent;
    }

    public void setlSourceStudyEvent(List<StudyEvent> lSourceStudyEvent) {
        this.lSourceStudyEvent = lSourceStudyEvent;
    }

}

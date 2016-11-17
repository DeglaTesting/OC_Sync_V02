/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;

import java.io.File;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author sa841
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@ManagedBean(name = "UploadedFile")
public class UploadedFile {
    public static File sourceUploadedFile;
   
    public static File getSourceUploadedFile() {
        return sourceUploadedFile;
    }

    public static void setSourceUploadedFile(File sourceUploadedFile) {
        UploadedFile.sourceUploadedFile = sourceUploadedFile;
    }
}

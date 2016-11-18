/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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

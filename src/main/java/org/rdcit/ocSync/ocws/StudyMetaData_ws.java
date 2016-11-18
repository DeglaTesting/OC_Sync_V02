/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.ocSync.ocws;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.rdcit.ocSync.controller.CollectingStudyEvents;
import org.rdcit.ocSync.model.User;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author sa841
 */
@ManagedBean(name = "StudyMetaData_ws")
public class StudyMetaData_ws {

    public StudyMetaData_ws() {

    }

    private SOAPMessage createSOAPRequest() throws Exception {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        String userPassword = user.getPassword();
        String userName = user.getUser_name();
        String study_u_p_id = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("Study_upID");
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "https://openclinica-testing.medschl.cam.ac.uk:443/OCplay-ws/ws/studySubject/v1";

        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addAttribute(new QName("xmlns:v1"), "http://openclinica.org/ws/study/v1");
        envelope.addAttribute(new QName("xmlns:bean"), "http://openclinica.org/ws/beans");
        envelope.addAttribute(new QName("xmlns:soap"), "http://www.w3.org/2003/05/soap-envelope/");
        envelope.addAttribute(new QName("soap:encodingStyle"), "http://www.w3.org/2003/05/soap-encoding");

        SOAPHeader header = envelope.getHeader();
        SOAPBody body = envelope.getBody();

        SOAPElement security = header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        security.addAttribute(new QName("SOAP-ENV:mustUnderstand"), "1");
        SOAPElement usernameToken = security.addChildElement("UsernameToken", "wsse");
        usernameToken.addAttribute(new QName("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        usernameToken.addAttribute(new QName("wsu:Id"), "UsernameToken-27777511");
        SOAPElement username = usernameToken.addChildElement("Username", "wsse");
        username.addTextNode(userName);
        SOAPElement password = usernameToken.addChildElement("Password", "wsse");
        password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
        password.addTextNode(userPassword);
        SOAPElement getMetadataRequest = body.addChildElement("getMetadataRequest", "v1");
        SOAPElement studyMetadata = getMetadataRequest.addChildElement("studyMetadata", "v1");
        SOAPElement identifier = studyMetadata.addChildElement("identifier", "bean");
        identifier.addTextNode(study_u_p_id);
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI + "create");
        soapMessage.saveChanges();
        return soapMessage;
    }

    public void getStudyMetaData() {
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = "https://openclinica-testing.medschl.cam.ac.uk/OCplay-ws/ws/study/v1/studyWsdl.wsdl";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
            NodeList nlODM = soapResponse.getSOAPBody().getElementsByTagName("odm");
            Node nODM = nlODM.item(0);
            NodeList nlMetaData = nODM.getChildNodes();
            StringToDocument stringToDocument = new StringToDocument(nlMetaData.item(0).getNodeValue());
            CollectingStudyEvents collectingStudyEvents = new CollectingStudyEvents();
            collectingStudyEvents.collectingStudyEvents(stringToDocument.document);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

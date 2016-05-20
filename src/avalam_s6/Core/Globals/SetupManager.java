/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.Globals;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author sazeratj
 */
public class SetupManager {
    private static Document aDoc;
    
    public static void load() {
        try {
            File xmlFile = new File("./ressources/config/config.xml");
            SetupManager.aDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);		
            //optional, but recommended by W3C
            aDoc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println("Error - "+LanguageManager.class.toString());
            Logger.getLogger(LanguageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        LanguageManager.setLanguage(SetupManager.getElement("Langue"));
        DefaultLevelGenerator.generate();
    }
    
    public static String getElement(String s) {
        return (aDoc.getElementsByTagName(s).item(0).getTextContent());
    }
    
    public static void setElement(String name, String value) {
        aDoc.getElementsByTagName(name).item(0).setTextContent(value);
        try {
            SetupManager.save();
        } catch (TransformerException ex) {
            Logger.getLogger(SetupManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (name.equals("Langue")) {
            LanguageManager.setLanguage(value);
        }
    }
    
    private static void save() throws TransformerConfigurationException, TransformerException {
        // write the content into xml file
         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         DOMSource source = new DOMSource(aDoc);
         StreamResult result = new StreamResult(new File("./ressources/config/config.xml"));
         transformer.transform(source, result);
    }
}

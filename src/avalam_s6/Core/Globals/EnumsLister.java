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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Read data contained into Data/Enums.xml
 * @author Team 7
 */
public class EnumsLister {

    private static Document aDoc;

    /**
     * Initialize the parser
     */
    public static void init() {
        try {
            File xmlFile = new File("./ressources/Data/Enums.xml");
            EnumsLister.aDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
            //optional, but recommended by W3C
            aDoc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println("Error - " + EnumsLister.class.toString());
            Logger.getLogger(EnumsLister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the element contained into an xml tag
     * @param s Name of the xml tag
     * @return Content of the xml tag
     */
    public static String getElement(String s) {
        return (aDoc.getElementsByTagName(s).item(0).getTextContent());
    }

    /**
     * Get the elements contained into an xml tag
     * @param s name of the xml tag
     * @return List of the childrens's content
     */
    public static String[] getChildrensOf(String s) {
        if (aDoc.getElementsByTagName(s).item(0).hasChildNodes()) {
            NodeList lChilds = aDoc.getElementsByTagName(s).item(0).getChildNodes();
            int lSize = 0;
            for (int i = 0; i < lChilds.getLength(); i++) {
                if (lChilds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    lSize++;
                }
            }
            String[] st = new String[lSize];
            int lPassage = 0;
            for (int i = 0; i < lChilds.getLength(); i++) {
                if (lChilds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    st[lPassage] = lChilds.item(i).getTextContent();
                    lPassage++;
                }
            }
            return st;
        } else {
            return null;
        }
    }

    /**
     * Get the element's tag name contained into an xml tag
     * @param s name of the xml tag
     * @return List of the childrens's name
     */
    public static String[] getChildrensNameOf(String s) {
        if (aDoc.getElementsByTagName(s).item(0).hasChildNodes()) {
            NodeList lChilds = aDoc.getElementsByTagName(s).item(0).getChildNodes();
            int lSize = 0;
            for (int i = 0; i < lChilds.getLength(); i++) {
                if (lChilds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    lSize++;
                }
            }
            String[] st = new String[lSize];
            int lPassage = 0;
            for (int i = 0; i < lChilds.getLength(); i++) {
                if (lChilds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    st[lPassage] = lChilds.item(i).getNodeName();
                    lPassage++;
                }
            }
            return st;
        } else {
            return null;
        }
    }
}

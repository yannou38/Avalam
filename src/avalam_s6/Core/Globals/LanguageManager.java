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
 *
 * @author sazeratj
 */
public class LanguageManager {

    private static String aCurrentLanguage;
    private static String aCurrentShortcut;
    private static Document aDoc;

    public static void setLanguage(String s) {
        LanguageManager.aCurrentLanguage = s;
        LanguageManager.applyLanguage();
    }

    private static void applyLanguage() {
        try {
            File xmlFile = new File("./ressources/Languages/" + LanguageManager.aCurrentLanguage + "_Translations.xml");
            LanguageManager.aDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
            //optional, but recommended by W3C
            aDoc.getDocumentElement().normalize();
            aCurrentShortcut = aDoc.getDocumentElement().getAttribute("id");
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println("Error - " + LanguageManager.class.toString());
            Logger.getLogger(LanguageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getElement(String s) {
        return (aDoc.getElementsByTagName(s).item(0).getTextContent());
    }

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

    public static String getFolderShortcut() {
        return LanguageManager.aCurrentShortcut;
    }

}

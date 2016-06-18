/* 
 * Copyright (C) 2016 Yann Ducruy <yann.ducruy@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package avalam.Core.Globals;

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
 * Global Dictionnary that translates through xml files the words for every class.
 * Class that displays labels calls LanguageManager and 'asks' for a translation into the current language.
 * @author Team 7
 */
public class LanguageManager {

    private static String aCurrentLanguage;
    private static String aCurrentShortcut;
    private static Document aDoc;

    /**
     * Setter
     * @param s Language asked by the user. 
     */
    public static void setLanguage(String s) {
        LanguageManager.aCurrentLanguage = s;
        LanguageManager.applyLanguage();
    }

    /**
     * Load the dictionnary of the current language into a local Document (quicker acces).
     * This will enable the class to modify the dictionnary locally then overright the existing one.
     */
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

    /**
     * Getter.
     * Example : getElement("Oui") return "Oui" if current language is french or "Yes" if it's in english ...
     * @param s Word's name into xml files (French tags ... 'Cause we're french :D).
     * @return The word's translation.
     */
    public static String getElement(String s) {
        return (aDoc.getElementsByTagName(s).item(0).getTextContent());
    }

    /**
     * Getter
     * @param s name of the xml tag
     * @return List of the children's content
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
     * Getter
     * @param s name of the xml tag
     * @return List of the children's name
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

    /**
     * Getter
     * @return Language Shortcut
     */
    public static String getFolderShortcut() {
        return LanguageManager.aCurrentShortcut;
    }

}

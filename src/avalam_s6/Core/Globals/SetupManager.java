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
 * General Manager that edit every setup.
 * Every class trying to change any setup will asks/tells it to the SetupManager.
 * Example : To set FullScreen on, asks SetupManager.setElement("FullScreen","Oui") (Oui means Yes).
 * @author team 7
 */
public class SetupManager {

    private static Document aDoc;

    /**
     * Load last user's setup (or default one if config file get deleted).
     * Parameters are load locally to get a quicker access and to override file if required.
     * Generate every file required, Load LanguageManager and SoundEngine.
     */
    public static void load() {
        ConfigGenerator.generate();
        try {
            File xmlFile = new File("./ressources/config/config.xml");
            SetupManager.aDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
            //optional, but recommended by W3C
            aDoc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println("Error - " + LanguageManager.class.toString());
            Logger.getLogger(LanguageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        LanguageManager.setLanguage(SetupManager.getElement("Langue"));
        DefaultLevelGenerator.generate();
        SaveSlotsGenerator.generate();
        SoundEngine.init();
    }

    /**
     * Save local setups into the config.xml
     * @throws TransformerConfigurationException Basic exception for Transformer
     * @throws TransformerException Basic exception for Transformer
     */
    private static void save() throws TransformerConfigurationException, TransformerException {
        // write the content into xml file
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(aDoc);
        StreamResult result = new StreamResult(new File("./ressources/config/config.xml"));
        transformer.transform(source, result);
    }

    /**
     * Getter.
     * Example : LanguageManager.get("Langue") return the current language (saved as shortcut).
     * @param s Setup asked
     * @return State of the Setup
     */
    public static String getElement(String s) {
        return (aDoc.getElementsByTagName(s).item(0).getTextContent());
    }

    /**
     * Setter
     * @param name Setup Name
     * @param value Setup Value
     */
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
}

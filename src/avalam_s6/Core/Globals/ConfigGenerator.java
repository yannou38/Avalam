/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.Globals;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sazeratj
 */
public class ConfigGenerator {

    public static void generate() {
        if (!Files.exists(Paths.get("./ressources/config/config.xml"))) {
            try (PrintWriter writer = new PrintWriter("./ressources/config/config.xml", "UTF-8")) {
                writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<config>\n\t<Langue>fr</Langue>\n\t<FullScreen>Oui</FullScreen>\n\t<Theme>Default</Theme>\n\t<Son>Non</Son>\n</config>");
                writer.close();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                System.out.println("Error - " + ConfigGenerator.class.toString());
                Logger.getLogger(ConfigGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

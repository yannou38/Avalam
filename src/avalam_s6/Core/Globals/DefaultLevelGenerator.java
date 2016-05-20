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
public class DefaultLevelGenerator {
    public static void generate() {
        String lContent = "RR1ARRRRRR1A1ARRRRRA1A1A1RRR1A1A1A1A1A1A0A1A1A1A1A1A1RRR1A1A1ARRRRRA1A1RRRRRRA1RR";
        if (! Files.exists(Paths.get("./ressources/Levels/default"))) {
            try (PrintWriter writer = new PrintWriter("./ressources/Levels/default", "UTF-8")) {
                writer.print(lContent);
                writer.close();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                System.out.println("Error - " + DefaultLevelGenerator.class.toString());
                Logger.getLogger(DefaultLevelGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

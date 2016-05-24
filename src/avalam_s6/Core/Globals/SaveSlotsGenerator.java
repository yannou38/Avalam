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

/**
 *
 * @author sazeratj
 */
public class SaveSlotsGenerator {
    public static void generate() {
        for (int i=1;i<6;i++) {
            File lFile = new File("./ressources/Saves/slot_"+i);
            if (!lFile.exists()) {
                try {
                    lFile.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(SaveSlotsGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

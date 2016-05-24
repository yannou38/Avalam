/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.File_IO;

import avalam_s6.Core.Local_Avalam_Game;
import avalam_s6.GUI.Main_Frame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sazeratj
 */
public class SaveParser_Reader {

    private Local_Avalam_Game aGame;
    private final String aPath;

    public SaveParser_Reader(Main_Frame pMainFrame, String pName) {
        this.aPath = "./ressources/Saves/" + pName;
        this.load();
    }

    private void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(aPath));
            /*this.aDate = br.readLine();
            this.aJ1 = br.readLine();
            this.aJ2 = br.readLine();
            br.readLine(); // Current Plyaer
            br.readLine(); // NB Turns
            this.aGrid = br.readLine();*/
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveParser_Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Local_Avalam_Game generateGame() {
        return null;
    }
}

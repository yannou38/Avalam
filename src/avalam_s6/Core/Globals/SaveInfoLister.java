/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.Globals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sazeratj
 */
public class SaveInfoLister {
    private final String aFilePath;
    private String aDate, aJ1, aJ2, aGrid;
    
    public SaveInfoLister(String s) {
        this.aFilePath = "./ressources/Saves/"+s;
        this.load();
    }
    
    private void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(aFilePath));
            this.aDate = br.readLine();
            this.aJ1 = br.readLine();
            this.aJ2 = br.readLine();
            br.readLine(); // Current Plyaer
            br.readLine(); // NB Turns
            this.aGrid = br.readLine();
            br.close();
            this.cleanStrings();
        } catch (IOException ex) {
            Logger.getLogger(SaveInfoLister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cleanStrings() {
        this.aDate = this.aDate.substring(this.aDate.lastIndexOf("[Date] ")+7);
        this.aDate = this.aDate.replaceAll("(\r\n|\n)", "");
        this.aJ1 = this.aJ1.substring(this.aJ1.lastIndexOf("|")+2);
        this.aJ1 = this.aJ1.replaceAll("(\r\n|\n)", "");
        this.aJ2 = this.aJ2.substring(this.aJ2.lastIndexOf("|")+2);
        this.aJ2 = this.aJ2.replaceAll("(\r\n|\n)", "");
        this.aGrid = this.aGrid.substring(this.aGrid.lastIndexOf("[GName] ")+8);
        this.aGrid = this.aGrid.replaceAll("(\r\n|\n)", "");
    }

    public String getDate() {
        return aDate;
    }

    public String getPlayer1() {
        return aJ1;
    }

    public String getPlayer2() {
        return aJ2;
    }

    public String getGrid() {
        return aGrid;
    }
    
    
    
}

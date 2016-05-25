/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.File_IO;

import avalam_s6.Core.Local_Avalam_Game;
import avalam_s6.Core.Move;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sazeratj
 */
public class SaveParser_Writer {

    private final Local_Avalam_Game aGame;
    private final String aPath;

    public SaveParser_Writer(Local_Avalam_Game pGame, String pName) {
        this.aGame = pGame;
        this.aPath = "./ressources/Saves/" + pName;
    }

    public void save() {
        try {
            File lFile = new File(aPath);
            if (!lFile.exists()) {
                lFile.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(lFile));
            /* Date */
            SimpleDateFormat lDateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
            bw.write("[Date] " + lDateFormat.format(new Date()) + "\n");
            /* Players */
            bw.write("[Player_1] " + this.aGame.getPlayers()[0].getClass().getSimpleName() + " | " + aGame.getPlayers()[0].getColor().name() + " | " + this.aGame.getPlayers()[0].getName() + "\n");
            bw.write("[Player_2] " + this.aGame.getPlayers()[1].getClass().getSimpleName() + " | " + aGame.getPlayers()[1].getColor().name() + " | " + this.aGame.getPlayers()[1].getName() + "\n");
            /* Current Player */
            bw.write("[Current] " + this.aGame.getCurrent() + "\n");
            bw.write("[Turns] " + this.aGame.getTurns() + "\n");
            /* Grid */
            bw.write("[GName] " + this.aGame.getGrid().getName() + "\n");
            /* Redo */
            bw.write("[Histo] " + this.aGame.getHistory().size() + "\n");
            for (Move m : this.aGame.getHistory()) {
                bw.write(m + "\n");
            }
            /* Undo */
            bw.write("[Cancel] " + this.aGame.getCancelled_moves().size() + "\n");
            for (Move m : this.aGame.getCancelled_moves()) {
                bw.write(m + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("Error - " + SaveParser_Writer.class.getName());
            Logger.getLogger(SaveParser_Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

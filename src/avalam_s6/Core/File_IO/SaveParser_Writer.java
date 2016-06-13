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
 * Export a Game into a SaveFile.
 * @author Team 7
 */
public class SaveParser_Writer {

    private final Local_Avalam_Game aGame;
    private final String aPath;

    /**
     * Constructor
     * @param pGame The Game to save
     * @param pName The Name of the Save File
     */
    public SaveParser_Writer(Local_Avalam_Game pGame, String pName) {
        this.aGame = pGame;
        this.aPath = "./ressources/Saves/" + pName;
    }

    /**
     * Save the Game
     */
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

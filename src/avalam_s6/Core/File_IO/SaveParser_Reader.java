/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.File_IO;

import avalam_s6.Core.Globals.AvalamColor;
import avalam_s6.Core.Grid;
import avalam_s6.Core.Local_Avalam_Game;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;
import avalam_s6.GUI.GuiManager_INTERFACE;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.Player.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sazeratj
 */
public class SaveParser_Reader {

    private final String aPath;
    
    /* -- Attributes to create a LAG -- */
    private GuiManager_INTERFACE aMainFrame;
    private Grid aGrid;
    private Player aPlayer1;
    private Player aPlayer2;
    private Stack<Move> aUndo;
    private Stack<Move> aRedo;
    private int aCurrentPlayer;
    private int aTurns;
    /* -------------------------------- */
    
    public SaveParser_Reader(Main_Frame pMainFrame, String pName) {
        this.aPath = "./ressources/Saves/" + pName;
        this.aMainFrame = pMainFrame;
        this.load();
    }

    private void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(aPath));
            /* DATE */
            br.readLine(); // NON TRAITE
            /* Player 1 */
            String s1 = br.readLine();
            String s1Class = s1.substring(s1.indexOf("[Player_1] ") + 11,s1.indexOf("|")-1);
            String s1Color = s1.substring(s1.indexOf("|")+2,s1.lastIndexOf("|")-1);
            String s1Name = s1.substring(s1.lastIndexOf("|")+2);
            /* Player 2 */
            String s2 = br.readLine();
            String s2Class = s2.substring(s2.indexOf("[Player_2] ") + 11,s2.indexOf("|")-1);
            String s2Color = s2.substring(s2.indexOf("|")+2,s2.lastIndexOf("|")-1);
            String s2Name = s2.substring(s2.lastIndexOf("|")+2);
            /* Creating Players */
            try {
                Class lClass1 = Class.forName("avalam_s6.Player."+s1Class);
                Class lClass2 = Class.forName("avalam_s6.Player."+s2Class);
                Constructor lConst1 = lClass1.getConstructor(String.class,AvalamColor.class,Owner.class);
                Constructor lConst2 = lClass2.getConstructor(String.class,AvalamColor.class,Owner.class);
                this.aPlayer1 = (Player) lConst1.newInstance(new Object[] {s1Name,AvalamColor.valueOf(s1Color),Owner.PLAYER_1});
                this.aPlayer2 = (Player) lConst2.newInstance(new Object[] {s2Name,AvalamColor.valueOf(s2Color),Owner.PLAYER_2});
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(SaveParser_Reader.class.getName()).log(Level.SEVERE, null, ex);
            }       
            /* Current */
            String lCurrent = br.readLine();
            lCurrent = lCurrent.substring(lCurrent.indexOf("[Current] ")+10);
            this.aCurrentPlayer = Integer.parseInt(lCurrent);
            
            //System.out.println(this.aPlayer1.getName()+" vs "+this.aPlayer2.getName());
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveParser_Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Local_Avalam_Game generateGame() {
        return new Local_Avalam_Game(aMainFrame,aGrid,aPlayer1,aPlayer2,aUndo,aRedo,aCurrentPlayer,aTurns);
    }
}

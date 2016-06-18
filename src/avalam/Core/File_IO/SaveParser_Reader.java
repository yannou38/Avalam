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
package avalam.Core.File_IO;

import avalam.Core.Coordinate;
import avalam.Core.Globals.AvalamColor;
import avalam.Core.Grid;
import avalam.Core.Move;
import avalam.Core.Owner;
import avalam.Exceptions.GridCharException;
import avalam.Exceptions.GridSizeException;
import avalam.GUI.GuiManager_INTERFACE;
import avalam.Player.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * File Parser reading saves and creating Game's attributes.
 * @author Team 7
 */
public class SaveParser_Reader {

    private final String aPath;
    
    /* -- Attributes to create a LAG -- */
    private final GuiManager_INTERFACE aMainFrame;
    private Grid aGrid; private String aGridName;
    private Player aPlayer1;
    private Player aPlayer2;
    private Stack<Move> aUndo;
    private Stack<Move> aRedo;
    private int aCurrentPlayer;
    private int aTurns;
    /* -------------------------------- */
    
    /**
     * Constructor
     * @param pMainFrame MainFrame (required to build a Game)
     * @param pName Name of the Save
     */
    public SaveParser_Reader(GuiManager_INTERFACE pMainFrame, String pName) {
        this.aPath = "./ressources/Saves/" + pName;
        this.aMainFrame = pMainFrame;
        this.load();
    }

@SuppressWarnings("unchecked")
    /**
     * Save into attributes ever game's parameters
     */
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
                Class lClass1 = Class.forName("avalam.Player."+s1Class);
                Class lClass2 = Class.forName("avalam.Player."+s2Class);
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
            /* Turns */
            String lTurns = br.readLine();
            lTurns = lTurns.substring(lTurns.indexOf("[Turns] ")+8);
            this.aTurns = Integer.parseInt(lTurns);
            /* Grid Name */
            String lGName = br.readLine();
            this.aGridName = lGName.substring(lGName.indexOf("[GName] ")+8);
            /* Undo */
            String lHisto = br.readLine();
            int lHistoSize = Integer.parseInt(lHisto.substring(lHisto.indexOf("[Histo] ")+8));
            this.aUndo = new Stack<>();
            for (int i=0;i<lHistoSize;i++) {
                String lMoveStr = br.readLine();
                Coordinate src = new Coordinate(Integer.parseInt(lMoveStr.substring(lMoveStr.indexOf("(")+1,lMoveStr.indexOf("(")+2)),Integer.parseInt(lMoveStr.substring(lMoveStr.indexOf(")")-1,lMoveStr.indexOf(")"))));
                Coordinate dst = new Coordinate(Integer.parseInt(lMoveStr.substring(lMoveStr.lastIndexOf("(")+1,lMoveStr.lastIndexOf("(")+2)),Integer.parseInt(lMoveStr.substring(lMoveStr.lastIndexOf(")")-1,lMoveStr.lastIndexOf(")"))));
                int hSrc = Integer.parseInt(lMoveStr.substring(lMoveStr.indexOf(")")+2,lMoveStr.indexOf(")")+3));
                int hDst = Integer.parseInt(lMoveStr.substring(lMoveStr.lastIndexOf(")")+2,lMoveStr.lastIndexOf(")")+3));
                if (this.aPlayer1.getName().equals(lMoveStr.substring(lMoveStr.lastIndexOf("|")+2))) {
                    this.aUndo.add( new Move(src,hSrc,dst,hDst,this.aPlayer1) );
                } else if (this.aPlayer2.getName().equals(lMoveStr.substring(lMoveStr.lastIndexOf("|")+2))){
                    this.aUndo.add( new Move(src,hSrc,dst,hDst,this.aPlayer2) );
                }
            }
            /* Redo */
            String lredo = br.readLine();
            int lredoSize = Integer.parseInt(lredo.substring(lredo.indexOf("[Cancel] ")+9));
            this.aRedo = new Stack<>();
            for (int i=0;i<lredoSize;i++) {
                String lMoveStr = br.readLine();
                Coordinate src = new Coordinate(Integer.parseInt(lMoveStr.substring(lMoveStr.indexOf("(")+1,lMoveStr.indexOf("(")+2)),Integer.parseInt(lMoveStr.substring(lMoveStr.indexOf(")")-1,lMoveStr.indexOf(")"))));
                Coordinate dst = new Coordinate(Integer.parseInt(lMoveStr.substring(lMoveStr.lastIndexOf("(")+1,lMoveStr.lastIndexOf("(")+2)),Integer.parseInt(lMoveStr.substring(lMoveStr.lastIndexOf(")")-1,lMoveStr.lastIndexOf(")"))));
                int hSrc = Integer.parseInt(lMoveStr.substring(lMoveStr.indexOf(")")+2,lMoveStr.indexOf(")")+3));
                int hDst = Integer.parseInt(lMoveStr.substring(lMoveStr.lastIndexOf(")")+2,lMoveStr.lastIndexOf(")")+3));
                if (this.aPlayer1.getName().equals(lMoveStr.substring(lMoveStr.lastIndexOf("|")+2))) {
                    Move m = new Move(src,hSrc,dst,hDst,this.aPlayer1);
                    System.out.println(m);
                    this.aRedo.add( m );
                } else if (this.aPlayer2.getName().equals(lMoveStr.substring(lMoveStr.lastIndexOf("|")+2))){
                    this.aRedo.add( new Move(src,hSrc,dst,hDst,this.aPlayer2) );
                }
            }
            /* Creation de la grille */
            try {
                Level_Parser myParser = new Level_Parser(this.aGridName);
                this.aGrid = new Grid(myParser.readLevel(), this.aGridName);
                for (Move m : this.aUndo) {
                    this.aGrid.moveCell(m.getC_src(), m.getC_dst());
                }
            } catch (GridSizeException | GridCharException ex) {
                Logger.getLogger(SaveParser_Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveParser_Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Getter
     * @return The MainFrame
     */
    public GuiManager_INTERFACE getaMainFrame() {
        return aMainFrame;
    }

    /**
     * Getter
     * @return The Game's Grid
     */
    public Grid getaGrid() {
        return aGrid;
    }

    /**
     * Getter
     * @return The Game's Player 1
     */
    public Player getaPlayer1() {
        return aPlayer1;
    }

    /**
     * Getter
     * @return The Game's player 2 
     */
    public Player getaPlayer2() {
        return aPlayer2;
    }

    /**
     * Getter
     * @return The Undo History 
     */
    public Stack<Move> getaUndo() {
        return aUndo;
    }

    /**
     * Getter
     * @return the Redo Hitory 
     */
    public Stack<Move> getaRedo() {
        return aRedo;
    }

    /**
     * Getter
     * @return The current player's position into Game's players table.
     */
    public int getaCurrentPlayer() {
        return aCurrentPlayer;
    }

    /**
     * Getter
     * @return The Amount of turns ever played 
     */
    public int getaTurns() {
        return aTurns;
    }
    
    
}

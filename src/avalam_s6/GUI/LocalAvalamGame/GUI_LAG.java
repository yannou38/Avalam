/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.LocalAvalamGame;

import avalam_s6.Core.File_IO.Level_Parser;
import avalam_s6.Core.*;
import avalam_s6.Exceptions.GridSizeException;
import avalam_s6.GUI.GUI_INTERFACE;
import avalam_s6.Player.AIPlayerRandom;
import avalam_s6.Player.ControlledPlayer;
import avalam_s6.Player.Player;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sazeratj
 */
public class GUI_LAG implements GUI_INTERFACE, Runnable{
    private Game_INTERFACE game;
    
    /**
     * Constructor.
     */
    public GUI_LAG() {
    }
    
    @Override
    public void render() {
        System.out.println("rendered");
    }

     @Override
    public void run() {
        try {
            Player p1 = new ControlledPlayer("Jon Doe",Color.WHITE,Owner.PLAYER_1);
            Player p2 = new AIPlayerRandom("Bot_Frank",Color.BLACK,Owner.PLAYER_2);
            Level_Parser myParser = new Level_Parser("default");
            Grid g = new Grid(myParser.readLevel()); // IOException | GridSizeException | NumberFormatException
            this.game = new Local_Avalam_Game(g, p1, p2, this); // GridSizeException
        } catch (IOException|GridSizeException|NumberFormatException ex) {
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.game.getTimer().start();
    }
}

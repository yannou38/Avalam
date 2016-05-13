/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import avalam_s6.Core.*;
import avalam_s6.Player.AIPlayerRandom;
import avalam_s6.Player.ControlledPlayer;
import avalam_s6.Player.Player;
import java.awt.Color;

/**
 *
 * @author sazeratj
 */
public class LAG_GUI implements GUI_INTERFACE, Runnable{
    private Game_INTERFACE game;
    
    /**
     * Constructor.
     */
    public LAG_GUI() {
    }
    
    @Override
    public void render() {
        System.out.println("rendered");
    }

     @Override
    public void run() {
        Player p1 = new ControlledPlayer("Jon Doe",Color.WHITE);
        Player p2 = new AIPlayerRandom("Bot_Frank",Color.BLACK);
        Grid g = new Grid("000023000023232000232323200323232300032313230003232323002323232000232320000320000");
        this.game = new Local_Avalam_Game(g, p1, p2, this);
        this.game.getTimer().start();
    }

    @Override
    public void enableUndo(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public void enableRedo(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

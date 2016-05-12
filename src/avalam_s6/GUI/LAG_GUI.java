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
public class LAG_GUI implements GUI, Runnable{
    private Game game;
    
    public LAG_GUI() {
    }
    
    @Override
    public void render() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public void run() {
        Player p1 = new ControlledPlayer("Jon Doe",Color.WHITE);
        Player p2 = new AIPlayerRandom("Bot_Frank",Color.BLACK);
        Grid g = new Grid("");
        this.game = new Local_Avalam_Game(g, p1, p2);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6;

import avalam_s6.Core.*;
import avalam_s6.Player.*;
import java.awt.Color;
import javax.swing.SwingUtilities;
/**
 *
 * @author TheDoctor
 */
public class Avalam_S6 implements Runnable{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Avalam_S6());        
    }

    @Override
    public void run() {
        Player p1 = new ControlledPlayer("Jon Doe",Color.WHITE);
        Player p2 = new AIPlayerRandom("Bot_Frank",Color.BLACK);
        Grid g = new Grid("");
        Local_Avalam_Game lag = new Local_Avalam_Game(g, p1, p2);
    }
    
    
    
}

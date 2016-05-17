/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.FinalScreen;

import avalam_s6.Player.Player_INTERFACE;
import java.awt.Image;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sazeratj
 */
public class GUI_FinalScreen extends JPanel {
    public String theme;
    private Image background;
    private JLabel victoryText;
    private Player_INTERFACE winner;
    
    public GUI_FinalScreen() {
        this("Default");
    }
    
    public GUI_FinalScreen(String theme) {
        this.theme = theme;
        this.winner = null;
    }
    
    public void init() {
        try {
            background = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/main/main_bg.png"));
        } catch (Exception ex) {
            Logger.getLogger(GUI_FinalScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        victoryText = new JLabel();
    }
    
    public void setWinner(Player_INTERFACE p) {
        this.winner = p;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.FinalScreen;

import avalam_s6.Player.Player_INTERFACE;
import java.awt.Graphics;
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
    
    public GUI_FinalScreen() {
        this("Default");
    }
    
    public GUI_FinalScreen(String theme) {
        this.theme = theme;
        this.init();
    }
    
    private void init() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/main/main_bg.png"));
        } catch (Exception ex) {
            Logger.getLogger(GUI_FinalScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.victoryText = new JLabel();
        this.add(this.victoryText);
    }
    
    public void setWinner(String p) {
        this.victoryText.setText(p+" a gagné!");        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
        
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.FinalScreen;

import avalam_s6.GUI.HomePage.HomePageListener;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sazeratj
 */
public class GUI_FinalScreen extends JPanel {
    public String theme;
    private Image background, homeI;
    private JLabel victoryText;
    private JButton home;
    
    public GUI_FinalScreen() {
        this("Default");
    }
    
    public GUI_FinalScreen(String theme) {
        this.theme = theme;
        this.victoryText = new JLabel();
        this.initComponents();
    }
    
    private void initComponents() {
        try {
            /* Chargement de la police */
            try {
                Font localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + this.theme + "/font/Gamaliel.otf"));
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
                /* Application de la police */
                this.victoryText.setFont(localFont.deriveFont(2*30f)); /* On peut appliquer un ratio a la police (ici 2) */
                /* Aggrandissement des bordures */
                this.victoryText.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
            } catch (IOException|FontFormatException ex) {
                Logger.getLogger(GUI_FinalScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            /* Chargement des images // du theme */
            this.background = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/final/background.jpg"));
            this.homeI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/final/home.png"));
        } catch (Exception ex) {
            Logger.getLogger(GUI_FinalScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        this.home = new JButton(new ImageIcon(this.homeI));
        this.home.setBorder(BorderFactory.createEmptyBorder());
        this.home.setContentAreaFilled(false);
        this.home.setFocusPainted(false);
        //home.addMouseListener(/*new HomePageListener("quit",theme)*/);
        
        //this.setLayout(null);
        this.add(this.victoryText);
        this.add(this.home);
    }
    
    public void setWinner(String p) {
        this.victoryText.setText(p+" a gagné!");        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.FinalScreen;

import avalam_s6.Core.Grid;
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
    public JLabel victoryText;
    public JButton home;
    private Grid finalGrid;
    
    public GUI_FinalScreen(String theme) {
        this.theme = theme;
        this.victoryText = new JLabel();
        this.finalGrid = null;
        this.initComponents();
    }
    
    private void initComponents() {
        try {
            Font localFont = new Font("Arial",Font.PLAIN,60);
            /* Chargement de la police */
            try {
                localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + this.theme + "/font/Gamaliel.otf"));
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
                /* Aggrandissement des bordures */
                this.victoryText.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
            } catch (IOException|FontFormatException ex) {
                throw(ex);
            }
            /* Chargement des images // du theme */
            this.background = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/final/background.jpg"));
            this.homeI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/final/home.png"));
            /* Application Police */
            this.victoryText.setFont(localFont.deriveFont(2*30f)); /* On peut appliquer un ratio a la police (ici 2) */
        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - "+GUI_FinalScreen.class.toString());
            Logger.getLogger(GUI_FinalScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        this.home = new JButton(new ImageIcon(this.homeI));
        this.home.setBorder(BorderFactory.createEmptyBorder());
        this.home.setContentAreaFilled(false);
        this.home.setFocusPainted(false);
        home.addMouseListener(new Final_MouseListener(theme));
        
        this.setLayout(null);
        this.add(this.victoryText);
        this.add(this.home);
        this.addComponentListener(new Final_AdapterListener(this));
    }
    
    public void setGrid(Grid g) {
        this.finalGrid = g;
    }
    
    public void setWinner(String p) {
        this.victoryText.setText(p+" a gagné!");        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
    }
}


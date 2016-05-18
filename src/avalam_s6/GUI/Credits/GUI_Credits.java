/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Credits;

import java.awt.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author ducruyy
 */
public class GUI_Credits extends JPanel {
    JButton retour;
    private Image returnI,background;
    String theme;
    
    
    public GUI_Credits() {
        this("Default");
    }

    public GUI_Credits(String theme) {
        this.theme = theme;
        this.initComponents();
    }

    private void initComponents() {
        try {
            background = ImageIO.read(new File("./ressources/Themes/" + theme + "/credits/background.png"));
            returnI = ImageIO.read(new File("./ressources/Themes/" + theme + "/credits/home.png"));
        } catch (Exception ex) {
            Logger.getLogger(GUI_Credits.class.getName()).log(Level.SEVERE, null, ex);
        }


        retour = new JButton(new ImageIcon(returnI));
        retour.setBorder(BorderFactory.createEmptyBorder());
        retour.setContentAreaFilled(false);
        retour.setFocusPainted(false);
        retour.addMouseListener(new CreditsListener("home",theme,0));

        this.setLayout(null);
        this.add(retour);    
        this.addComponentListener(new CreditsAdapterListener(this));
        
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
        
    }


    
}

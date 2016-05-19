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
    private JButton retour;
    private Image returnI,background;
    private String theme;
    
    
    public GUI_Credits(String theme) {
        this.theme = theme;
        this.initComponents();
    }

    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/credits/background.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/credits/home.png"));
        } catch (Exception ex) {
            System.out.println("Error - "+GUI_Credits.class.toString());
            Logger.getLogger(GUI_Credits.class.getName()).log(Level.SEVERE, null, ex);
        }


        this.retour = new JButton(new ImageIcon(this.returnI));
        this.retour.setBorder(BorderFactory.createEmptyBorder());
        this.retour.setContentAreaFilled(false);
        this.retour.setFocusPainted(false);
        this.retour.addMouseListener(new CreditsListener("home",this.theme,0));

        this.setLayout(null);
        this.add(this.retour);    
        this.addComponentListener(new CreditsAdapterListener(this));
        
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.background, 0, 0, this.getWidth(),this.getHeight(),null);
        
    }

    public JButton getRetour() {
        return this.retour;
    }  
    
    
}

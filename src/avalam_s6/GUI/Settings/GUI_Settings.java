/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Settings;

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
public class GUI_Settings extends JPanel {
    private Image applyI,background,creditsI,returnI,leftI,rightI;
    public JButton apply,retour,credits; 
    
    
    public String theme;

    public GUI_Settings() {
        this("Default");
    }

    public GUI_Settings(String theme) {
        this.theme = theme;
        initComponents();
    }
    
    private void initComponents() {

        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + theme + "/options/background.png"));
            this.applyI = ImageIO.read(new File("./ressources/Themes/" + theme + "/options/apply.png"));
            this.creditsI = ImageIO.read(new File("./ressources/Themes/" + theme + "/options/credits.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + theme + "/options/home.png"));
            this.leftI = ImageIO.read(new File("./ressources/Themes/" + theme + "/options/left.png"));
            this.rightI = ImageIO.read(new File("./ressources/Themes/" + theme + "/options/right.png"));
        } catch (Exception ex) {
            Logger.getLogger(GUI_Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.apply = new JButton(new ImageIcon(applyI));        
        this.apply.setBorder(BorderFactory.createEmptyBorder());
        this.apply.setContentAreaFilled(false);
        this.apply.setFocusPainted(false);
        this.apply.addMouseListener(new SettingsListener("apply",theme));
        
        this.retour = new JButton(new ImageIcon(returnI));        
        this.retour.setBorder(BorderFactory.createEmptyBorder());
        this.retour.setContentAreaFilled(false);
        this.retour.setFocusPainted(false);
        this.retour.addMouseListener(new SettingsListener("home",theme));
        
        this.credits = new JButton(new ImageIcon(creditsI));
        this.credits.setBorder(BorderFactory.createEmptyBorder());
        this.credits.setContentAreaFilled(false);
        this.credits.setFocusPainted(false);
        this.credits.addMouseListener(new SettingsListener("credits",theme));
        
        
        this.add(apply);
        this.add(retour);
        this.add(credits);
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
        
    }
}

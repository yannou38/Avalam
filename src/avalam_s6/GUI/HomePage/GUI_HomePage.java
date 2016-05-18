/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.HomePage;

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
/*
 * This class contain the home page with the avalam logo, and the diverse buttons to create a game,*
 * access options, check rules & tutorial, etc
 */
public class GUI_HomePage extends JPanel {

    private JButton quick, play, settings, rules, tuto, exit;
    private Image background, quickI, playI, settingsI, rulesI, tutoI, exitI;
    private String theme;

    public GUI_HomePage(String theme) {
        this.theme = theme;
        initComponents();
    }

    private void initComponents() {

        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/main/main_bg.png"));
            this.playI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/main/customgame.png"));
            this.tutoI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/main/tuto.png"));
            this.quickI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/main/quickgame.png"));
            this.settingsI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/main/options.png"));
            this.rulesI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/main/rules.png"));
            this.exitI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/main/quit.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_HomePage.class.toString());
            Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.quick = new JButton(new ImageIcon(this.quickI));
        this.quick.setBorder(BorderFactory.createEmptyBorder());
        this.quick.setContentAreaFilled(false);
        this.quick.setFocusPainted(false);
        this.quick.addMouseListener(new HomePageListener("quickgame", this.theme));

        this.play = new JButton(new ImageIcon(this.playI));
        this.play.setBorder(BorderFactory.createEmptyBorder());
        this.play.setContentAreaFilled(false);
        this.play.setFocusPainted(false);
        this.play.addMouseListener(new HomePageListener("customgame", this.theme));

        this.rules = new JButton(new ImageIcon(this.rulesI));
        this.rules.setBorder(BorderFactory.createEmptyBorder());
        this.rules.setContentAreaFilled(false);
        this.rules.setFocusPainted(false);
        this.rules.addMouseListener(new HomePageListener("rules", this.theme));

        this.tuto = new JButton(new ImageIcon(this.tutoI));
        this.tuto.setBorder(BorderFactory.createEmptyBorder());
        this.tuto.setContentAreaFilled(false);
        this.tuto.setFocusPainted(false);
        this.tuto.addMouseListener(new HomePageListener("tuto", this.theme));

        this.settings = new JButton(new ImageIcon(this.settingsI));
        this.settings.setBorder(BorderFactory.createEmptyBorder());
        this.settings.setContentAreaFilled(false);
        this.settings.setFocusPainted(false);
        this.settings.addMouseListener(new HomePageListener("options", this.theme));

        this.exit = new JButton(new ImageIcon(this.exitI));
        this.exit.setBorder(BorderFactory.createEmptyBorder());
        this.exit.setContentAreaFilled(false);
        this.exit.setFocusPainted(false);
        this.exit.addMouseListener(new HomePageListener("quit", this.theme));

        this.setLayout(null);
        this.add(this.quick);
        this.add(this.play);
        this.add(this.rules);
        this.add(this.tuto);
        this.add(this.settings);
        this.add(this.exit);
        this.addComponentListener(new HomePageAdapterListener(this));

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);

    }

    public JButton getQuick() {
        return this.quick;
    }

    public JButton getPlay() {
        return this.play;
    }

    public JButton getSettings() {
        return this.settings;
    }

    public JButton getRules() {
        return this.rules;
    }

    public JButton getTuto() {
        return this.tuto;
    }

    public JButton getExit() {
        return this.exit;
    }
    
    
}

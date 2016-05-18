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
    public JButton quick, play, settings, rules, tuto, exit;
    private Image background, quickI, playI, settingsI, rulesI, tutoI, exitI;
    public String theme;

    public GUI_HomePage() {
        this("Default");
    }

    public GUI_HomePage(String theme) {
        this.theme = theme;
        initComponents();
    }

    private void initComponents() {

        try {
            background = ImageIO.read(new File("./ressources/Themes/" + theme + "/main/main_bg.png"));
            playI = ImageIO.read(new File("./ressources/Themes/" + theme + "/main/customgame.png"));
            tutoI = ImageIO.read(new File("./ressources/Themes/" + theme + "/main/tuto.png"));
            quickI = ImageIO.read(new File("./ressources/Themes/" + theme + "/main/quickgame.png"));
            settingsI = ImageIO.read(new File("./ressources/Themes/" + theme + "/main/options.png"));
            rulesI = ImageIO.read(new File("./ressources/Themes/" + theme + "/main/rules.png"));
            exitI = ImageIO.read(new File("./ressources/Themes/" + theme + "/main/quit.png"));
        } catch (Exception ex) {
            System.out.println("Error - "+GUI_HomePage.class.toString());
            Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

        quick = new JButton(new ImageIcon(quickI));
        quick.setBorder(BorderFactory.createEmptyBorder());
        quick.setContentAreaFilled(false);
        quick.setFocusPainted(false);
        quick.addMouseListener(new HomePageListener("quickgame",theme));

        play = new JButton(new ImageIcon(playI));
        play.setBorder(BorderFactory.createEmptyBorder());
        play.setContentAreaFilled(false);
        play.setFocusPainted(false);
        play.addMouseListener(new HomePageListener("customgame",theme));

        rules = new JButton(new ImageIcon(rulesI));
        rules.setBorder(BorderFactory.createEmptyBorder());
        rules.setContentAreaFilled(false);
        rules.setFocusPainted(false);
        rules.addMouseListener(new HomePageListener("rules",theme));

        tuto = new JButton(new ImageIcon(tutoI));
        tuto.setBorder(BorderFactory.createEmptyBorder());
        tuto.setContentAreaFilled(false);
        tuto.setFocusPainted(false);
        tuto.addMouseListener(new HomePageListener("tuto",theme));

        settings = new JButton(new ImageIcon(settingsI));
        settings.setBorder(BorderFactory.createEmptyBorder());
        settings.setContentAreaFilled(false);
        settings.setFocusPainted(false);
        settings.addMouseListener(new HomePageListener("options",theme));

        exit = new JButton(new ImageIcon(exitI));
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.setContentAreaFilled(false);
        exit.setFocusPainted(false);
        exit.addMouseListener(new HomePageListener("quit",theme));

        this.setLayout(null);
        this.add(quick);
        this.add(play);
        this.add(rules);
        this.add(tuto);
        this.add(settings);
        this.add(exit);        
        this.addComponentListener(new HomePageAdapterListener(this));
        
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.HomePage;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Gui_INTERFACE;
import avalam_s6.GUI.Main_Frame;
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
public class GUI_HomePage extends JPanel implements Gui_INTERFACE {

    private JButton quick, play, settings, rules, exit, load;
    private Image background, quickI, playI, settingsI, rulesI, loadI, exitI;

    public GUI_HomePage() {
        initComponents();
    }

    private void initComponents() {

        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/main/main_bg.png"));
            this.playI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme")+ "/main/customgame.png"));
            this.quickI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/main/quickgame.png"));
            this.settingsI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/main/options.png"));
            this.rulesI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/main/rules.png"));
            this.exitI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/main/quit.png"));
            this.loadI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/main/load.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_HomePage.class.toString());
            Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.quick = new JButton(new ImageIcon(this.quickI));
        this.quick.setBorder(BorderFactory.createEmptyBorder());
        this.quick.setContentAreaFilled(false);
        this.quick.setFocusPainted(false);
        this.quick.addMouseListener(new HomePageListener("quickgame"));

        this.play = new JButton(new ImageIcon(this.playI));
        this.play.setBorder(BorderFactory.createEmptyBorder());
        this.play.setContentAreaFilled(false);
        this.play.setFocusPainted(false);
        this.play.addMouseListener(new HomePageListener("customgame"));

        this.rules = new JButton(new ImageIcon(this.rulesI));
        this.rules.setBorder(BorderFactory.createEmptyBorder());
        this.rules.setContentAreaFilled(false);
        this.rules.setFocusPainted(false);
        this.rules.addMouseListener(new HomePageListener("rules"));
        
        this.load = new JButton(new ImageIcon(this.loadI));
        this.load.setBorder(BorderFactory.createEmptyBorder());
        this.load.setContentAreaFilled(false);
        this.load.setFocusPainted(false);
        this.load.addMouseListener(new HomePageListener("load"));

        this.settings = new JButton(new ImageIcon(this.settingsI));
        this.settings.setBorder(BorderFactory.createEmptyBorder());
        this.settings.setContentAreaFilled(false);
        this.settings.setFocusPainted(false);
        this.settings.addMouseListener(new HomePageListener("options"));

        this.exit = new JButton(new ImageIcon(this.exitI));
        this.exit.setBorder(BorderFactory.createEmptyBorder());
        this.exit.setContentAreaFilled(false);
        this.exit.setFocusPainted(false);
        this.exit.addMouseListener(new HomePageListener("quit"));

        this.setLayout(null);
        this.add(this.quick);
        this.add(this.play);
        this.add(this.rules);
        this.add(this.settings);
        this.add(this.exit);
        this.add(load);
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

    public JButton getExit() {
        return this.exit;
    }

    public JButton getLoad() {
        return load;
    }

    @Override
    public void back() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());
        mainFrame.dispose();
    }
    
    
    
    
}

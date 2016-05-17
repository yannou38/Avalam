/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.HomePage;

import avalam_s6.GUI.Main_Frame;
import java.awt.*;
import java.io.File;
import static java.lang.Math.floor;
import static java.lang.Math.round;
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

    Main_Frame parent;
    JButton quick, play, settings, rules, tuto, exit;
    private Image background, quickI, playI, settingsI, rulesI, tutoI, exitI;
    String theme;

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
            Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

        quick = new JButton(new ImageIcon(quickI));
        quick.setBorder(BorderFactory.createEmptyBorder());
        quick.setContentAreaFilled(false);
        quick.addMouseListener(new HomePageListener("quickgame",theme));

        play = new JButton(new ImageIcon(playI));
        play.setBorder(BorderFactory.createEmptyBorder());
        play.setContentAreaFilled(false);
        play.addMouseListener(new HomePageListener("customgame",theme));

        rules = new JButton(new ImageIcon(rulesI));
        rules.setBorder(BorderFactory.createEmptyBorder());
        rules.setContentAreaFilled(false);
        rules.addMouseListener(new HomePageListener("rules",theme));

        tuto = new JButton(new ImageIcon(tutoI));
        tuto.setBorder(BorderFactory.createEmptyBorder());
        tuto.setContentAreaFilled(false);
        tuto.addMouseListener(new HomePageListener("tuto",theme));

        settings = new JButton(new ImageIcon(settingsI));
        settings.setBorder(BorderFactory.createEmptyBorder());
        settings.setContentAreaFilled(false);
        settings.addMouseListener(new HomePageListener("options",theme));

        exit = new JButton(new ImageIcon(exitI));
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.setContentAreaFilled(false);
        exit.addMouseListener(new HomePageListener("quit",theme));

        this.setLayout(null);
        Insets insets = this.getInsets();

        add(quick);
        Dimension size = quick.getPreferredSize();
        quick.setBounds(302 + insets.left, 302 + insets.top, size.width, size.height);

        add(play);
        size = play.getPreferredSize();
        play.setBounds(302 + insets.left, 402 + insets.top, size.width, size.height);

        add(rules);
        size = rules.getPreferredSize();
        rules.setBounds(302 + insets.left, 502 + insets.top, size.width, size.height);

        add(tuto);
        size = tuto.getPreferredSize();
        tuto.setBounds(529 + insets.left, 502 + insets.top, size.width, size.height);

        add(settings);
        size = settings.getPreferredSize();
        settings.setBounds(755 + insets.left, 502 + insets.top, size.width, size.height);

        add(exit);
        size = exit.getPreferredSize();
        exit.setBounds(1107 + insets.left, 615 + insets.top, size.width, size.height);
        
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
        Insets insets = this.getInsets();
        //1280*720 => taille de base
        double ratioW = (double)this.getWidth()/(double)1280;
        double ratioH = (double)this.getHeight()/(double)720;
        
        
        
        /*Dimension size = quick.getPreferredSize();
        System.out.println(ratioW +" "+ratioH);
        quick.setBounds((int)round((302 + insets.left)*ratioW), ((int)round((302 + insets.top)*ratioH)), size.width, size.height);
        quick.setSize((int)round(quick.getWidth()*ratioW), (int)round(quick.getHeight()*ratioH));*/
                
        
        
        /*size = play.getPreferredSize();
        play.setBounds(302 + insets.left, 402 + insets.top, size.width, size.height);
        size = rules.getPreferredSize();
        rules.setBounds(302 + insets.left, 502 + insets.top, size.width, size.height);
        size = tuto.getPreferredSize();
        tuto.setBounds(529 + insets.left, 502 + insets.top, size.width, size.height);
        size = settings.getPreferredSize();
        settings.setBounds(755 + insets.left, 502 + insets.top, size.width, size.height);
        size = exit.getPreferredSize();
        exit.setBounds(1107 + insets.left, 615 + insets.top, size.width, size.height);*/
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.NewGame;

import avalam_s6.GUI.HomePage.GUI_HomePage;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author ducruyy
 */
public class GUI_NewGame extends JPanel {
    JButton player, prec, sup, load, retour, start;
    private Image background, iaEasyI, iaMidI, iaHardI, loadI, playerI, precI, returnI, startI, supI;
    String theme;
    
    
    public GUI_NewGame() {
        this("Default");
    }

    public GUI_NewGame(String theme) {
        this.theme = theme;
        this.initComponents();
    }

    private void initComponents() {
        try {
            background = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/background.png"));
            iaEasyI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/ia_easy.png"));
            iaHardI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/ia_hard.png"));
            iaMidI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/ia_mid.png"));
            loadI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/load.png"));
            playerI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/player.png"));
            precI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/prec.png"));
            returnI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/return.png"));
            supI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/sup.png"));
            startI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/start.png"));
        } catch (Exception ex) {
            Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

        player = new JButton(new ImageIcon(playerI));
        player.setBorder(BorderFactory.createEmptyBorder());
        player.setContentAreaFilled(false);
        player.setFocusPainted(false);
        //player.addMouseListener(new HomePageListener("quickgame",theme));

        prec = new JButton(new ImageIcon(precI));
        prec.setBorder(BorderFactory.createEmptyBorder());
        prec.setContentAreaFilled(false);
        prec.setFocusPainted(false);
        //prec.addMouseListener(new HomePageListener("customgame",theme));

        sup = new JButton(new ImageIcon(supI));
        sup.setBorder(BorderFactory.createEmptyBorder());
        sup.setContentAreaFilled(false);
        sup.setFocusPainted(false);
        //rules.addMouseListener(new HomePageListener("rules",theme));

        load = new JButton(new ImageIcon(loadI));
        load.setBorder(BorderFactory.createEmptyBorder());
        load.setContentAreaFilled(false);
        load.setFocusPainted(false);
        //tuto.addMouseListener(new HomePageListener("tuto",theme));

        retour = new JButton(new ImageIcon(returnI));
        retour.setBorder(BorderFactory.createEmptyBorder());
        retour.setContentAreaFilled(false);
        retour.setFocusPainted(false);
        //settings.addMouseListener(new HomePageListener("options",theme));

        start = new JButton(new ImageIcon(startI));
        start.setBorder(BorderFactory.createEmptyBorder());
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        //exit.addMouseListener(new HomePageListener("quit",theme));

        this.setLayout(null);
        this.add(player);
        this.add(prec);
        this.add(sup);
        this.add(load);
        this.add(retour);
        this.add(start);        
        //this.addComponentListener(new HomePageAdapterListener(this));
        
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
        
    }


    
}

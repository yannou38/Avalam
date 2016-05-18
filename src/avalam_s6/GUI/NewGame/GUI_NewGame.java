/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.NewGame;

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
public class GUI_NewGame extends JPanel {
    JButton player1, prec1, sup1, player2, prec2, sup2, load, retour, start;
    private Image background, iaEasyI, iaMidI, iaHardI, loadI, player1I, prec1I, sup1I, player2I, prec2I, sup2I, returnI, startI;
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
            player1I = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/player.png"));
            prec1I = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/prec.png"));
            sup1I = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/sup.png"));
            player2I = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/player.png"));
            prec2I = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/prec.png"));
            sup2I = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/sup.png"));
            returnI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/return.png"));
            startI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/start.png"));
        } catch (Exception ex) {
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        player1 = new JButton(new ImageIcon(player1I));
        player1.setBorder(BorderFactory.createEmptyBorder());
        player1.setContentAreaFilled(false);
        player1.setFocusPainted(false);
        player1.addMouseListener(new NewGameListener("player1",theme));

        prec1 = new JButton(new ImageIcon(prec1I));
        prec1.setBorder(BorderFactory.createEmptyBorder());
        prec1.setContentAreaFilled(false);
        prec1.setFocusPainted(false);
        prec1.addMouseListener(new NewGameListener("prec1",theme));

        sup1 = new JButton(new ImageIcon(sup1I));
        sup1.setBorder(BorderFactory.createEmptyBorder());
        sup1.setContentAreaFilled(false);
        sup1.setFocusPainted(false);
        sup1.addMouseListener(new NewGameListener("sup1",theme));
        

        player2 = new JButton(new ImageIcon(player2I));
        player2.setBorder(BorderFactory.createEmptyBorder());
        player2.setContentAreaFilled(false);
        player2.setFocusPainted(false);
        player2.addMouseListener(new NewGameListener("player2",theme));

        prec2 = new JButton(new ImageIcon(prec2I));
        prec2.setBorder(BorderFactory.createEmptyBorder());
        prec2.setContentAreaFilled(false);
        prec2.setFocusPainted(false);
        prec2.addMouseListener(new NewGameListener("prec2",theme));

        sup2 = new JButton(new ImageIcon(sup2I));
        sup2.setBorder(BorderFactory.createEmptyBorder());
        sup2.setContentAreaFilled(false);
        sup2.setFocusPainted(false);
        sup2.addMouseListener(new NewGameListener("sup2",theme));  
        

        load = new JButton(new ImageIcon(loadI));
        load.setBorder(BorderFactory.createEmptyBorder());
        load.setContentAreaFilled(false);
        load.setFocusPainted(false);
        load.addMouseListener(new NewGameListener("load",theme));

        retour = new JButton(new ImageIcon(returnI));
        retour.setBorder(BorderFactory.createEmptyBorder());
        retour.setContentAreaFilled(false);
        retour.setFocusPainted(false);
        retour.addMouseListener(new NewGameListener("retour",theme));

        start = new JButton(new ImageIcon(startI));
        start.setBorder(BorderFactory.createEmptyBorder());
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        start.addMouseListener(new NewGameListener("start",theme));

        this.setLayout(null);
        this.add(player1);
        this.add(prec1);
        this.add(sup1);
        this.add(player2);
        this.add(prec2);
        this.add(sup2);
        this.add(load);
        this.add(retour);
        this.add(start);        
        this.addComponentListener(new NewGameAdapterListener(this));
        
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(),this.getHeight(),null);
        
    }


    
}

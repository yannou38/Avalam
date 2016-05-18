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
    JButton player1, prec1, sup1, player2, prec2, sup2, aie1, aim1, aih1, aie2, aim2, aih2, load, retour, start;
    private Image background, aieI, aimI, aihI, loadI, playerI, precI, supI, returnI, startI;
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
            aieI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/ia_easy.png"));
            aimI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/ia_mid.png"));
            aihI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/ia_hard.png"));
            loadI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/load.png"));
            playerI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/player.png"));
            precI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/prec.png"));
            supI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/sup.png"));
            returnI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/return.png"));
            startI = ImageIO.read(new File("./ressources/Themes/" + theme + "/playerselect/start.png"));
        } catch (Exception ex) {
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        player1 = new JButton(new ImageIcon(playerI));
        player1.setBorder(BorderFactory.createEmptyBorder());
        player1.setContentAreaFilled(false);
        player1.setFocusPainted(false);
        player1.addMouseListener(new NewGameListener("player",theme,1));
        
        aie1 = new JButton(new ImageIcon(aieI));
        aie1.setBorder(BorderFactory.createEmptyBorder());
        aie1.setContentAreaFilled(false);
        aie1.setFocusPainted(false);
        aie1.addMouseListener(new NewGameListener("player",theme,1));
        
        aim1 = new JButton(new ImageIcon(aimI));
        aim1.setBorder(BorderFactory.createEmptyBorder());
        aim1.setContentAreaFilled(false);
        aim1.setFocusPainted(false);
        aim1.addMouseListener(new NewGameListener("player",theme,1));        
         
        aih1 = new JButton(new ImageIcon(aihI));
        aih1.setBorder(BorderFactory.createEmptyBorder());
        aih1.setContentAreaFilled(false);
        aih1.setFocusPainted(false);
        aih1.addMouseListener(new NewGameListener("player",theme,1));             

        prec1 = new JButton(new ImageIcon(precI));
        prec1.setBorder(BorderFactory.createEmptyBorder());
        prec1.setContentAreaFilled(false);
        prec1.setFocusPainted(false);
        prec1.addMouseListener(new NewGameListener("prec",theme,1));

        sup1 = new JButton(new ImageIcon(supI));
        sup1.setBorder(BorderFactory.createEmptyBorder());
        sup1.setContentAreaFilled(false);
        sup1.setFocusPainted(false);
        sup1.addMouseListener(new NewGameListener("sup",theme,1));
        

        player2 = new JButton(new ImageIcon(playerI));
        player2.setBorder(BorderFactory.createEmptyBorder());
        player2.setContentAreaFilled(false);
        player2.setFocusPainted(false);
        player2.addMouseListener(new NewGameListener("player",theme,2));

        aie2 = new JButton(new ImageIcon(aieI));
        aie2.setBorder(BorderFactory.createEmptyBorder());
        aie2.setContentAreaFilled(false);
        aie2.setFocusPainted(false);
        aie2.addMouseListener(new NewGameListener("player",theme,2));

        aim2 = new JButton(new ImageIcon(aimI));
        aim2.setBorder(BorderFactory.createEmptyBorder());
        aim2.setContentAreaFilled(false);
        aim2.setFocusPainted(false);
        aim2.addMouseListener(new NewGameListener("player",theme,2));        
         
        aih2 = new JButton(new ImageIcon(aihI));
        aih2.setBorder(BorderFactory.createEmptyBorder());
        aih2.setContentAreaFilled(false);
        aih2.setFocusPainted(false);
        aih2.addMouseListener(new NewGameListener("player",theme,2));     
        
        prec2 = new JButton(new ImageIcon(precI));
        prec2.setBorder(BorderFactory.createEmptyBorder());
        prec2.setContentAreaFilled(false);
        prec2.setFocusPainted(false);
        prec2.addMouseListener(new NewGameListener("prec",theme,2));

        sup2 = new JButton(new ImageIcon(supI));
        sup2.setBorder(BorderFactory.createEmptyBorder());
        sup2.setContentAreaFilled(false);
        sup2.setFocusPainted(false);
        sup2.addMouseListener(new NewGameListener("sup",theme,2));  
        

        load = new JButton(new ImageIcon(loadI));
        load.setBorder(BorderFactory.createEmptyBorder());
        load.setContentAreaFilled(false);
        load.setFocusPainted(false);
        load.addMouseListener(new NewGameListener("load",theme,0));

        retour = new JButton(new ImageIcon(returnI));
        retour.setBorder(BorderFactory.createEmptyBorder());
        retour.setContentAreaFilled(false);
        retour.setFocusPainted(false);
        retour.addMouseListener(new NewGameListener("return",theme,0));

        start = new JButton(new ImageIcon(startI));
        start.setBorder(BorderFactory.createEmptyBorder());
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        start.addMouseListener(new NewGameListener("start",theme,0));

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

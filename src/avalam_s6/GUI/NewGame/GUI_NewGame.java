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
    private JButton player1, prec1, sup1, player2, prec2, sup2, aie1, aim1, aih1, aie2, aim2, aih2, load, retour, start;
    private Image background, aieI, aimI, aihI, loadI, playerI, precI, supI, returnI, startI;
    private String theme;
    
    public GUI_NewGame(String theme) {
        this.theme = theme;
        this.initComponents();
    }

    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/background.png"));
            this.aieI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/ia_easy.png"));
            this.aimI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/ia_mid.png"));
            this.aihI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/ia_hard.png"));
            this.loadI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/load.png"));
            this.playerI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/player.png"));
            this.precI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/prec.png"));
            this.supI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/sup.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/return.png"));
            this.startI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/start.png"));
        } catch (Exception ex) {
            System.out.println("Error - "+GUI_NewGame.class.toString());
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.player1 = new JButton(new ImageIcon(this.playerI));
        this.player1.setBorder(BorderFactory.createEmptyBorder());
        this.player1.setContentAreaFilled(false);
        this.player1.setFocusPainted(false);
        this.player1.addMouseListener(new NewGameListener("player",this.theme,1));
        
        this.aie1 = new JButton(new ImageIcon(this.aieI));
        this.aie1.setBorder(BorderFactory.createEmptyBorder());
        this.aie1.setContentAreaFilled(false);
        this.aie1.setFocusPainted(false);
        this.aie1.addMouseListener(new NewGameListener("player",this.theme,1));
        
        this.aim1 = new JButton(new ImageIcon(this.aimI));
        this.aim1.setBorder(BorderFactory.createEmptyBorder());
        this.aim1.setContentAreaFilled(false);
        this.aim1.setFocusPainted(false);
        this.aim1.addMouseListener(new NewGameListener("player",this.theme,1));        
         
        this.aih1 = new JButton(new ImageIcon(this.aihI));
        this.aih1.setBorder(BorderFactory.createEmptyBorder());
        this.aih1.setContentAreaFilled(false);
        this.aih1.setFocusPainted(false);
        this.aih1.addMouseListener(new NewGameListener("player",this.theme,1));             

        this.prec1 = new JButton(new ImageIcon(this.precI));
        this.prec1.setBorder(BorderFactory.createEmptyBorder());
        this.prec1.setContentAreaFilled(false);
        this.prec1.setFocusPainted(false);
        this.prec1.addMouseListener(new NewGameListener("prec",this.theme,1));

        this.sup1 = new JButton(new ImageIcon(this.supI));
        this.sup1.setBorder(BorderFactory.createEmptyBorder());
        this.sup1.setContentAreaFilled(false);
        this.sup1.setFocusPainted(false);
        this.sup1.addMouseListener(new NewGameListener("sup",this.theme,1));
        

        this.player2 = new JButton(new ImageIcon(this.playerI));
        this.player2.setBorder(BorderFactory.createEmptyBorder());
        this.player2.setContentAreaFilled(false);
        this.player2.setFocusPainted(false);
        this.player2.addMouseListener(new NewGameListener("player",this.theme,2));

        this.aie2 = new JButton(new ImageIcon(this.aieI));
        this.aie2.setBorder(BorderFactory.createEmptyBorder());
        this.aie2.setContentAreaFilled(false);
        this.aie2.setFocusPainted(false);
        this.aie2.addMouseListener(new NewGameListener("player",this.theme,2));

        this.aim2 = new JButton(new ImageIcon(this.aimI));
        this.aim2.setBorder(BorderFactory.createEmptyBorder());
        this.aim2.setContentAreaFilled(false);
        this.aim2.setFocusPainted(false);
        this.aim2.addMouseListener(new NewGameListener("player",this.theme,2));        
         
        this.aih2 = new JButton(new ImageIcon(this.aihI));
        this.aih2.setBorder(BorderFactory.createEmptyBorder());
        this.aih2.setContentAreaFilled(false);
        this.aih2.setFocusPainted(false);
        this.aih2.addMouseListener(new NewGameListener("player",this.theme,2));     
        
        this.prec2 = new JButton(new ImageIcon(this.precI));
        this.prec2.setBorder(BorderFactory.createEmptyBorder());
        this.prec2.setContentAreaFilled(false);
        this.prec2.setFocusPainted(false);
        this.prec2.addMouseListener(new NewGameListener("prec",this.theme,2));

        this.sup2 = new JButton(new ImageIcon(this.supI));
        this.sup2.setBorder(BorderFactory.createEmptyBorder());
        this.sup2.setContentAreaFilled(false);
        this.sup2.setFocusPainted(false);
        this.sup2.addMouseListener(new NewGameListener("sup",this.theme,2));  
        

        this.load = new JButton(new ImageIcon(this.loadI));
        this.load.setBorder(BorderFactory.createEmptyBorder());
        this.load.setContentAreaFilled(false);
        this.load.setFocusPainted(false);
        this.load.addMouseListener(new NewGameListener("load",this.theme,0));

        this.retour = new JButton(new ImageIcon(this.returnI));
        this.retour.setBorder(BorderFactory.createEmptyBorder());
        this.retour.setContentAreaFilled(false);
        this.retour.setFocusPainted(false);
        this.retour.addMouseListener(new NewGameListener("return",this.theme,0));

        this.start = new JButton(new ImageIcon(this.startI));
        this.start.setBorder(BorderFactory.createEmptyBorder());
        this.start.setContentAreaFilled(false);
        this.start.setFocusPainted(false);
        this.start.addMouseListener(new NewGameListener("start",this.theme,0));

        this.setLayout(null);
        this.add(this.player1);
        this.add(this.prec1);
        this.add(this.sup1);
        this.add(this.player2);
        this.add(this.prec2);
        this.add(this.sup2);
        this.add(this.load);
        this.add(this.retour);
        this.add(this.start);        
        this.addComponentListener(new NewGameAdapterListener(this));
        
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.background, 0, 0, this.getWidth(),this.getHeight(),null);
    }

    public JButton getPlayer1() {
        return this.player1;
    }

    public JButton getPrec1() {
        return this.prec1;
    }

    public JButton getSup1() {
        return this.sup1;
    }

    public JButton getPlayer2() {
        return this.player2;
    }

    public JButton getPrec2() {
        return this.prec2;
    }

    public JButton getSup2() {
        return this.sup2;
    }

    public JButton getAie1() {
        return this.aie1;
    }

    public JButton getAim1() {
        return this.aim1;
    }

    public JButton getAih1() {
        return this.aih1;
    }

    public JButton getAie2() {
        return this.aie2;
    }

    public JButton getAim2() {
        return this.aim2;
    }

    public JButton getAih2() {
        return this.aih2;
    }

    public JButton getLoad() {
        return this.load;
    }

    public JButton getRetour() {
        return this.retour;
    }

    public JButton getStart() {
        return this.start;
    }

    
    
}

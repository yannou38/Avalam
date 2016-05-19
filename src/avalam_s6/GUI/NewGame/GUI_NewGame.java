/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.NewGame;

import avalam_s6.Core.Globals.LanguageManager;
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

    private JButton prec1, sup1, prec2, sup2, retour, start;
    private Image background, precI, supI, returnI, startI;
    private Image player;
    private final String theme;

    private String[] AIlist;
    private Image[] AIimgs;
    private int AIlistsize;
    private JButton p1button, p2button;
    private int p1select, p2select;

    private NewGameAdapterListener listener;
    private Boolean callResize;

    public GUI_NewGame(String theme) {
        this.theme = theme;
        this.callResize = false;
        this.listener = new NewGameAdapterListener(this);
        this.initComponents();
        this.initLabels();
    }

    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/background.png"));
            this.precI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/prec.png"));
            this.supI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/sup.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/home.png"));
            this.startI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/start.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_NewGame.class.toString());
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.prec1 = new JButton(new ImageIcon(this.precI));
        this.prec1.setBorder(BorderFactory.createEmptyBorder());
        this.prec1.setContentAreaFilled(false);
        this.prec1.setFocusPainted(false);
        this.prec1.addMouseListener(new NewGameListener("prec", this.theme, 1, this));

        this.sup1 = new JButton(new ImageIcon(this.supI));
        this.sup1.setBorder(BorderFactory.createEmptyBorder());
        this.sup1.setContentAreaFilled(false);
        this.sup1.setFocusPainted(false);
        this.sup1.addMouseListener(new NewGameListener("sup", this.theme, 1, this));

        this.prec2 = new JButton(new ImageIcon(this.precI));
        this.prec2.setBorder(BorderFactory.createEmptyBorder());
        this.prec2.setContentAreaFilled(false);
        this.prec2.setFocusPainted(false);
        this.prec2.addMouseListener(new NewGameListener("prec", this.theme, 2, this));

        this.sup2 = new JButton(new ImageIcon(this.supI));
        this.sup2.setBorder(BorderFactory.createEmptyBorder());
        this.sup2.setContentAreaFilled(false);
        this.sup2.setFocusPainted(false);
        this.sup2.addMouseListener(new NewGameListener("sup", this.theme, 2, this));

        this.retour = new JButton(new ImageIcon(this.returnI));
        this.retour.setBorder(BorderFactory.createEmptyBorder());
        this.retour.setContentAreaFilled(false);
        this.retour.setFocusPainted(false);
        this.retour.addMouseListener(new NewGameListener("home", this.theme, 0, this));

        this.start = new JButton(new ImageIcon(this.startI));
        this.start.setBorder(BorderFactory.createEmptyBorder());
        this.start.setContentAreaFilled(false);
        this.start.setFocusPainted(false);
        this.start.addMouseListener(new NewGameListener("start", this.theme, 0, this));

        this.setLayout(null);
        this.add(this.prec1);
        this.add(this.sup1);
        this.add(this.prec2);
        this.add(this.sup2);
        this.add(this.retour);
        this.add(this.start);
        this.addComponentListener(this.listener);

    }

    private void initLabels() {
        this.AIlist = LanguageManager.getChildrensOf("IAImages");
        this.AIlistsize = this.AIlist.length;
        this.AIimgs = new Image[this.AIlistsize];
        for (int i = 0; i < this.AIlistsize; i++) {
            try {
                this.AIimgs[i] = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/" + this.AIlist[i] + ".png"));
            } catch (Exception ex) {
                System.out.println("Error - " + GUI_NewGame.class.toString());
                Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        this.p1button = new JButton(new ImageIcon(this.AIimgs[0]));
        this.p2button = new JButton(new ImageIcon(this.AIimgs[0]));
        
        this.add(this.p1button);
        this.add(this.p2button);

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);
        if (this.callResize == true) {
            this.listener.componentResized(null);
            this.callResize = false;
        }
    }

    public JButton getRetour() {
        return retour;
    }

    public JButton getStart() {
        return start;
    }

    public JButton getLeftP1() {
        return this.prec1;
    }

    public JButton getRightP1() {
        return this.sup1;
    }

    public JButton getLeftP2() {
        return this.prec2;
    }

    public JButton getRightP2() {
        return this.sup2;
    }

    public void leftAI(int numplayer) {
        if (numplayer == 1) {
            this.p1select = (this.p1select - 1);
            if (this.p1select == -1) {
                this.p1select = this.AIlistsize - 1;
            }
            this.p1button.setIcon(new ImageIcon(this.AIimgs[this.p1select]));
            this.callResize = true;
        } else {
            this.p2select = (this.p2select - 1);
            if (this.p2select == -1) {
                this.p2select = this.AIlistsize - 1;
            }
            this.p2button.setIcon(new ImageIcon(this.AIimgs[this.p2select]));
            this.callResize = true;
        }
    }

    public void rightAI(int numplayer) {
        if (numplayer == 1) {
            this.p1select = (this.p1select + 1) % this.AIlistsize;
            this.p1button.setIcon(new ImageIcon(this.AIimgs[this.p1select]));
            this.callResize = true;
        } else {
            this.p2select = (this.p2select + 1) % this.AIlistsize;
            this.p2button.setIcon(new ImageIcon(this.AIimgs[this.p2select]));
            this.callResize = true;
        }
    }

    public JButton getP1button() {
        return p1button;
    }

    public JButton getP2button() {
        return p2button;
    }

    public JButton getPrec1() {
        return prec1;
    }

    public JButton getSup1() {
        return sup1;
    }

    public JButton getPrec2() {
        return prec2;
    }

    public JButton getSup2() {
        return sup2;
    }

}

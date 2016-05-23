/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.NewGame;

import avalam_s6.Core.Globals.LanguageManager;
import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Gui_INTERFACE;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author ducruyy
 */
public class GUI_NewGame extends JPanel implements Gui_INTERFACE {

    private JButton prec1, sup1, prec2, sup2, retour, start;
    private Image background, precI, supI, returnI, startI;

    private String[] AIlist;
    private Image[] AIimgs;
    private int AIlistsize;
    private JButton p1button, p2button;
    private int p1select, p2select;

    private String[] ColorList;
    private Image[] ColorImgs;
    private int ColorListSize;
    private JButton p1color, p2color;
    private int p1colorselect, p2colorselect;

    private final NewGameAdapterListener listener;
    private Boolean callResize;
    private JButton preccolor1, preccolor2, supcolor1, supcolor2;
    private JTextField name1;
    private JTextField name2;

    public GUI_NewGame() {
        this.callResize = false;
        this.listener = new NewGameAdapterListener(this);
        this.initComponents();
    }

    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/playerselect/background.png"));
            this.precI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/playerselect/prec.png"));
            this.supI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/playerselect/sup.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/playerselect/home.png"));
            this.startI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/playerselect/start.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_NewGame.class.toString());
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.prec1 = new JButton(new ImageIcon(this.precI));
        this.prec1.setBorder(BorderFactory.createEmptyBorder());
        this.prec1.setContentAreaFilled(false);
        this.prec1.setFocusPainted(false);
        this.prec1.addMouseListener(new NewGameListener("prec", 1, this, "precia"));

        this.sup1 = new JButton(new ImageIcon(this.supI));
        this.sup1.setBorder(BorderFactory.createEmptyBorder());
        this.sup1.setContentAreaFilled(false);
        this.sup1.setFocusPainted(false);
        this.sup1.addMouseListener(new NewGameListener("sup", 1, this, "supia"));

        this.prec2 = new JButton(new ImageIcon(this.precI));
        this.prec2.setBorder(BorderFactory.createEmptyBorder());
        this.prec2.setContentAreaFilled(false);
        this.prec2.setFocusPainted(false);
        this.prec2.addMouseListener(new NewGameListener("prec", 2, this, "precia"));

        this.sup2 = new JButton(new ImageIcon(this.supI));
        this.sup2.setBorder(BorderFactory.createEmptyBorder());
        this.sup2.setContentAreaFilled(false);
        this.sup2.setFocusPainted(false);
        this.sup2.addMouseListener(new NewGameListener("sup", 2, this, "supia"));

        this.retour = new JButton(new ImageIcon(this.returnI));
        this.retour.setBorder(BorderFactory.createEmptyBorder());
        this.retour.setContentAreaFilled(false);
        this.retour.setFocusPainted(false);
        this.retour.addMouseListener(new NewGameListener("home", 0, this, null));

        this.start = new JButton(new ImageIcon(this.startI));
        this.start.setBorder(BorderFactory.createEmptyBorder());
        this.start.setContentAreaFilled(false);
        this.start.setFocusPainted(false);
        this.start.addMouseListener(new NewGameListener("start", 0, this, null));

        this.setLayout(null);
        this.add(this.prec1);
        this.add(this.sup1);
        this.add(this.prec2);
        this.add(this.sup2);
        this.add(this.retour);
        this.add(this.start);
        this.addComponentListener(this.listener);

        this.AIlist = LanguageManager.getChildrensOf("IAImages");
        this.AIlistsize = this.AIlist.length;
        this.AIimgs = new Image[this.AIlistsize];
        for (int i = 0; i < this.AIlistsize; i++) {
            try {
                this.AIimgs[i] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/playerselect/" + this.AIlist[i] + ".png"));
            } catch (Exception ex) {
                System.out.println("Error - " + GUI_NewGame.class.toString());
                Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.p1button = new JButton(new ImageIcon(this.AIimgs[0]));
        this.p1button.setBorder(BorderFactory.createEmptyBorder());
        this.p1button.setContentAreaFilled(false);
        this.p1button.setFocusPainted(false);
        this.p2button = new JButton(new ImageIcon(this.AIimgs[0]));
        this.p2button.setBorder(BorderFactory.createEmptyBorder());
        this.p2button.setContentAreaFilled(false);
        this.p2button.setFocusPainted(false);

        this.add(this.p1button);
        this.add(this.p2button);

        this.ColorList = LanguageManager.getChildrensOf("Colors");
        this.ColorListSize = this.ColorList.length;
        this.ColorImgs = new Image[this.ColorListSize];
        for (int i = 0; i < this.ColorListSize; i++) {
            try {
                this.ColorImgs[i] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/playerselect/" + this.ColorList[i] + ".png"));
            } catch (Exception ex) {
                System.out.println("Error - " + GUI_NewGame.class.toString());
                Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.p1color = new JButton(new ImageIcon(this.ColorImgs[0]));
        this.p1color.setBorder(BorderFactory.createEmptyBorder());
        this.p1color.setContentAreaFilled(false);
        this.p1color.setFocusPainted(false);
        this.p1colorselect = 0;
        this.p2color = new JButton(new ImageIcon(this.ColorImgs[1]));
        this.p2color.setBorder(BorderFactory.createEmptyBorder());
        this.p2color.setContentAreaFilled(false);
        this.p2color.setFocusPainted(false);
        this.p2colorselect = 1;

        this.preccolor1 = new JButton(new ImageIcon(this.precI));
        this.preccolor1.setBorder(BorderFactory.createEmptyBorder());
        this.preccolor1.setContentAreaFilled(false);
        this.preccolor1.setFocusPainted(false);
        this.preccolor1.addMouseListener(new NewGameListener("prec", 1, this, "preccolor"));

        this.supcolor1 = new JButton(new ImageIcon(this.supI));
        this.supcolor1.setBorder(BorderFactory.createEmptyBorder());
        this.supcolor1.setContentAreaFilled(false);
        this.supcolor1.setFocusPainted(false);
        this.supcolor1.addMouseListener(new NewGameListener("sup", 1, this, "supcolor"));

        this.preccolor2 = new JButton(new ImageIcon(this.precI));
        this.preccolor2.setBorder(BorderFactory.createEmptyBorder());
        this.preccolor2.setContentAreaFilled(false);
        this.preccolor2.setFocusPainted(false);
        this.preccolor2.addMouseListener(new NewGameListener("prec", 2, this, "preccolor"));

        this.supcolor2 = new JButton(new ImageIcon(this.supI));
        this.supcolor2.setBorder(BorderFactory.createEmptyBorder());
        this.supcolor2.setContentAreaFilled(false);
        this.supcolor2.setFocusPainted(false);
        this.supcolor2.addMouseListener(new NewGameListener("sup", 2, this, "supcolor"));

        Font localFont = new Font("Arial", Font.PLAIN, 60);
        
            /* Chargement de la police */
        try {
            localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/font/Gamaliel.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);                
        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - " + GUI_NewGame.class.toString());
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.name1 = new JTextField();
        this.name1.setFont(localFont.deriveFont(2 * 25f));
        this.name1.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        this.name1.setSize(150,50);
        this.name1.setText("Player 1's name");
        
        this.name2 = new JTextField();
        this.name2.setFont(localFont.deriveFont(2 * 25f));
        this.name2.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        this.name2.setSize(150,50);
        this.name2.setText("Player 2's name");
        
        this.add(this.p1color);
        this.add(this.p2color);
        this.add(this.preccolor1);
        this.add(this.preccolor2);
        this.add(this.supcolor1);
        this.add(this.supcolor2);
        this.add(this.name1);
        this.add(this.name2);

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
        return this.retour;
    }

    public JButton getStart() {
        return this.start;
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

    public JButton getPreccolor1() {
        return this.preccolor1;
    }

    public JButton getPreccolor2() {
        return this.preccolor2;
    }

    public JButton getSupcolor1() {
        return this.supcolor1;
    }

    public JButton getSupcolor2() {
        return this.supcolor2;
    }
    
    public JTextField getName1() {
        return this.name1;
    }
    
    public JTextField getName2() {
        return this.name2;
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

    public JButton getP1color() {
        return p1color;
    }

    public JButton getP2color() {
        return p2color;
    }

    void leftColorAI(int numplayer) {
        if (numplayer == 1) {
            this.p1colorselect = (this.p1colorselect - 1);
            if (this.p1colorselect == -1) {
                this.p1colorselect = this.ColorListSize - 1;
            }
            //les deux joueurs ne peuvent pas avoir la même couleur
            if (this.p1colorselect == this.p2colorselect) {
                this.p1colorselect = (this.p1colorselect - 1);
                if (this.p1colorselect == -1) {
                    this.p1colorselect = this.ColorListSize - 1;
                }
            }
            this.p1color.setIcon(new ImageIcon(this.ColorImgs[this.p1colorselect]));
            this.callResize = true;
        } else {
            this.p2colorselect = (this.p2colorselect - 1);
            if (this.p2colorselect == -1) {
                this.p2colorselect = this.ColorListSize - 1;
            }
            //les deux joueurs ne peuvent pas avoir la même couleur
            if (this.p1colorselect == this.p2colorselect) {
                this.p2colorselect = (this.p2colorselect - 1);
                if (this.p2colorselect == -1) {
                    this.p2colorselect = this.ColorListSize - 1;
                }
            }
            this.p2color.setIcon(new ImageIcon(this.ColorImgs[this.p2colorselect]));
            this.callResize = true;
        }
    }

    void rightColorAI(int numplayer) {
        if (numplayer == 1) {
            this.p1colorselect = (this.p1colorselect + 1) % this.ColorListSize;
            //les deux joueurs ne peuvent pas avoir la même couleur
            if (this.p1colorselect == this.p2colorselect) {
                this.p1colorselect = (this.p1colorselect + 1) % this.ColorListSize;
            }
            this.p1color.setIcon(new ImageIcon(this.ColorImgs[this.p1colorselect]));
            this.callResize = true;
        } else {
            this.p2colorselect = (this.p2colorselect + 1) % this.ColorListSize;
            //les deux joueurs ne peuvent pas avoir la même couleur
            if (this.p1colorselect == this.p2colorselect) {
                this.p2colorselect = (this.p2colorselect + 1) % this.ColorListSize;
            }
            this.p2color.setIcon(new ImageIcon(this.ColorImgs[this.p2colorselect]));
            this.callResize = true;
        }
    }

    String[] loadP1Settings() {
        String[] p1 = new String[2];
        p1[0] = this.ColorList[this.p1colorselect];
        p1[1] = this.AIlist[this.p1select];
        return p1;
    }

    String[] loadP2Settings() {
        String[] p2 = new String[2];
        p2[0] = this.ColorList[this.p2colorselect];
        p2[1] = this.AIlist[this.p2select];
        return p2;
    }

    @Override
    public void back() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());
        mainFrame.setwState(WindowState.MAIN);
    }

}

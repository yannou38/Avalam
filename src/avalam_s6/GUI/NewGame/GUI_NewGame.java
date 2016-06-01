/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.NewGame;

import avalam_s6.Core.Cell;
import avalam_s6.Core.CellState;
import avalam_s6.Core.Coordinate;
import avalam_s6.Core.File_IO.Level_Parser;
import avalam_s6.Core.Globals.EnumsLister;
import avalam_s6.Core.Globals.Input;
import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.Core.Grid;
import avalam_s6.Exceptions.GridCharException;
import avalam_s6.Exceptions.GridSizeException;
import avalam_s6.GUI.Gui_INTERFACE;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.round;
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

    /* -- GRID -- */
    private JButton[][] buttonmap;
    private ImageIcon wh, bl, em, re;
    private Image[] pawnList;
    private String[] gridNameList;
    private int selectedGrid;
    private Grid[] gridList;

    public GUI_NewGame() {
        this.callResize = false;
        this.listener = new NewGameAdapterListener(this);
        this.buttonmap = new JButton[9][9];
        this.initComponents();
    }

    private void initComponents() {
        try {
            // BackGround
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/playerselect/background.png"));
            // Buttons
            this.precI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/playerselect/prec.png"));
            this.supI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/playerselect/sup.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/playerselect/home.png"));
            this.startI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/playerselect/start.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_NewGame.class.toString());
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Coordinate c = new Coordinate(j, i);
                JButton b = new JButton();
                b.setBorder(BorderFactory.createEmptyBorder());
                b.setContentAreaFilled(false);
                b.setHorizontalTextPosition(JButton.CENTER);
                b.setVerticalTextPosition(JButton.CENTER);
                this.buttonmap[j][i] = b;
                //b.setOpaque(false);
                this.add(b);
            }
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

        // AI List
        this.AIlist = EnumsLister.getChildrensOf("IAImages");
        this.AIlistsize = this.AIlist.length;
        this.AIimgs = new Image[this.AIlistsize];
        for (int i = 0; i < this.AIlistsize; i++) {
            try {
                this.AIimgs[i] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/playerselect/" + this.AIlist[i] + ".png"));
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

        // Color List
        this.ColorList = EnumsLister.getChildrensOf("Colors");
        this.ColorListSize = this.ColorList.length;
        this.ColorImgs = new Image[this.ColorListSize];
        for (int i = 0; i < this.ColorListSize; i++) {
            try {
                this.ColorImgs[i] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/playerselect/" + this.ColorList[i] + ".png"));
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

        // Grid
        this.selectedGrid = 0;
        this.gridNameList = new String[1];
        this.gridNameList[0] = "default";

        this.gridList = new Grid[gridNameList.length];
        try {
            for (int i = 0; i < gridNameList.length; i++) {
                Level_Parser lParser = new Level_Parser(gridNameList[i]);
                gridList[i] = new Grid(lParser.readLevel(), gridNameList[i]);
            }
        } catch (IOException | GridSizeException | GridCharException ex) {
            System.out.println("Error - " + GUI_NewGame.class.toString());
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Grid Content
        this.pawnList = new Image[ColorList.length + 2];
        try {
            this.pawnList[0] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/empty.png"));
            this.pawnList[1] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/restricted.png"));
            for (int i = 0; i < ColorList.length; i++) {
                this.pawnList[2 + i] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + ColorList[i] + ".png"));;
            }
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_NewGame.class.toString());
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        this.name1.setEditable(true);
        this.name1.setFont(localFont.deriveFont(45f));
        this.name1.setBorder(BorderFactory.createEmptyBorder());
        this.name1.setSize(150, 50);
        this.name1.setText("Name_1");
        this.name1.setOpaque(false);

        this.name2 = new JTextField();
        this.name2.setFont(localFont.deriveFont(45f));
        this.name2.setBorder(BorderFactory.createEmptyBorder());
        this.name2.setSize(150, 50);
        this.name2.setText("Name_2");
        this.name2.setOpaque(false);

        this.add(this.p1color);
        this.add(this.p2color);
        this.add(this.preccolor1);
        this.add(this.preccolor2);
        this.add(this.supcolor1);
        this.add(this.supcolor2);
        this.add(this.name1);
        this.add(this.name2);

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

    public String[] loadP1Settings() {
        String[] p1 = new String[3];
        p1[0] = this.AIlist[this.p1select];
        p1[1] = this.name1.getText();
        p1[2] = this.ColorList[this.p1colorselect];
        return p1;
    }

    public String[] loadP2Settings() {
        String[] p2 = new String[3];
        p2[0] = this.AIlist[this.p2select];
        p2[1] = this.name2.getText();
        p2[2] = this.ColorList[this.p2colorselect];
        return p2;
    }

    public String loadGridName() {
        return this.gridNameList[this.selectedGrid];
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

    @Override
    public void paintComponent(Graphics g) {
        // Affichage Background
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);
        // Calcul des ratios
        double ratioW = (double) this.getWidth() / (double) 1920;
        double ratioH = (double) this.getHeight() / (double) 1080;
        // Resize au besoin
        if (this.callResize) {
            wh = new ImageIcon(this.pawnList[2 + this.p1colorselect].getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            bl = new ImageIcon(this.pawnList[2 + this.p2colorselect].getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            em = new ImageIcon(this.pawnList[0].getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            re = new ImageIcon(this.pawnList[1].getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
        }
        Grid gr = this.gridList[this.selectedGrid];
        Coordinate c = new Coordinate();
        for (int i = 0; i < gr.getWidth(); i++) {
            for (int j = 0; j < gr.getHeight(); j++) {
                c.setX(i);
                c.setY(j);
                Cell ce = gr.getCellAt(c);
                switch (ce.getOwner()) {
                    case PLAYER_1:
                        this.buttonmap[i][j].setIcon(wh);
                        this.buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                        break;
                    case PLAYER_2:
                        this.buttonmap[i][j].setIcon(bl);
                        this.buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                        break;
                    case NO_OWNER:
                        if (gr.getCellAt(c).getState().getValue() == CellState.RESTRICTED.getValue()) {
                            this.buttonmap[i][j].setIcon(re);
                        } else {
                            this.buttonmap[i][j].setIcon(em);
                            this.buttonmap[i][j].setText("");
                        }
                        break;
                }
                this.buttonmap[i][j].setBounds((int) round((660 + i * 66) * ratioW), ((int) round((260 + j * 66) * ratioH)), (int) round(66 * ratioW), (int) round(66 * ratioH));
                this.buttonmap[i][j].setSize((int) round(66 * ratioW), (int) round(66 * ratioH));
                this.buttonmap[i][j].setOpaque(false);
            }
        }
        // Stopper resize au besoin
        if (this.callResize == true) {
            this.listener.componentResized(null);
            this.callResize = false;
        }
    }

    @Override
    public void back() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());
        mainFrame.setwState(WindowState.MAIN);
    }

    @Override
    public void callResize() {
        this.callResize = true;
    }

}

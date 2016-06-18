/* 
 * Copyright (C) 2016 Yann Ducruy <yann.ducruy@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package avalam.GUI.NewGame;

import avalam.Core.Cell;
import avalam.Core.CellState;
import avalam.Core.Coordinate;
import avalam.Core.File_IO.Level_Parser;
import avalam.Core.Globals.EnumsLister;
import avalam.Core.Globals.GridLister;
import avalam.Core.Globals.SetupManager;
import avalam.Core.Grid;
import avalam.Exceptions.GridCharException;
import avalam.Exceptions.GridSizeException;
import avalam.GUI.Gui_INTERFACE;
import avalam.GUI.Main_Frame;
import avalam.GUI.WindowState;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.round;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Custom game window
 * @author Team 7
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
    private JLabel grilleName;

    /* -- GRID -- */
    private JButton[][] buttonmap;
    private ImageIcon wh, bl, em, re;
    private Image[] pawnList;
    private String[] gridNameList;
    private int selectedGrid;
    private Grid[] gridList;
    private JButton supgrille;
    private JButton precgrille;

    /**
     * Constructor
     */
    public GUI_NewGame() {
        this.callResize = false;
        this.listener = new NewGameAdapterListener(this);
        this.buttonmap = new JButton[9][9];
        this.initComponents();
    }

    /**
     * Initialises all the components of the window,
     * Loads the images and buttons.
     */
    private void initComponents() {
        try {
            // BackGround
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/playerselect/background.png"));
            // Buttons
            this.precI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/playerselect/prec.png"));
            this.supI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/playerselect/sup.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/playerselect/home.png"));
            this.startI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/playerselect/start.png"));
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
                b.setFont(b.getFont().deriveFont(1*15f));
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
                this.AIimgs[i] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/playerselect/" + this.AIlist[i] + ".png"));
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
                this.ColorImgs[i] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/playerselect/" + this.ColorList[i] + ".png"));
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
        this.gridNameList = GridLister.listGrids();
        for(int i=0;i<this.gridNameList.length;i++){
            if(this.gridNameList[i].equals("default")){
                this.selectedGrid = i;
            }
        }
        this.gridList = new Grid[gridNameList.length];
        try {
            for (int i = 0; i < gridNameList.length; i++) {
                Level_Parser lParser = new Level_Parser(this.gridNameList[i]);
                this.gridList[i] = new Grid(lParser.readLevel(), this.gridNameList[i]);
            }
        } catch (IOException | GridSizeException | GridCharException ex) {
            System.out.println("Error - " + GUI_NewGame.class.toString());
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Grid Content
        this.pawnList = new Image[ColorList.length + 2];
        try {
            this.pawnList[0] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/empty.png"));
            this.pawnList[1] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/restricted.png"));
            for (int i = 0; i < ColorList.length; i++) {
                this.pawnList[2 + i] = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/" + ColorList[i] + ".png"));;
            }
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_NewGame.class.toString());
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        Font localFont = new Font("Arial", Font.PLAIN, 60);

        /* Chargement de la police */
        try {
            localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/font/Gamaliel.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - " + GUI_NewGame.class.toString());
            Logger.getLogger(GUI_NewGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.name1 = new JTextField();
        this.name1.setEditable(true);
        this.name1.setFont(localFont.deriveFont(45f));
        this.name1.setBorder(BorderFactory.createEmptyBorder());
        this.name1.setText("Name 1");
        this.name1.setOpaque(false);

        this.name2 = new JTextField();
        this.name2.setFont(localFont.deriveFont(45f));
        this.name2.setBorder(BorderFactory.createEmptyBorder());
        this.name2.setText("Name 2");
        this.name2.setOpaque(false);
        
        this.grilleName = new JLabel(this.gridNameList[this.selectedGrid]);
        this.grilleName.setHorizontalAlignment(JLabel.CENTER);
        this.grilleName.setBorder(BorderFactory.createEmptyBorder());
        this.grilleName.setFont(localFont.deriveFont(1 * 30f));
        this.add(this.grilleName);
        
        this.precgrille = new JButton(new ImageIcon(this.precI));
        this.precgrille.setBorder(BorderFactory.createEmptyBorder());
        this.precgrille.setContentAreaFilled(false);
        this.precgrille.setFocusPainted(false);
        this.precgrille.addMouseListener(new NewGameListener("prec", 0, this, "precgrille"));

        this.supgrille = new JButton(new ImageIcon(this.supI));
        this.supgrille.setBorder(BorderFactory.createEmptyBorder());
        this.supgrille.setContentAreaFilled(false);
        this.supgrille.setFocusPainted(false);
        this.supgrille.addMouseListener(new NewGameListener("sup", 0, this, "supgrille"));

        this.add(this.p1color);
        this.add(this.p2color);
        this.add(this.preccolor1);
        this.add(this.preccolor2);
        this.add(this.supcolor1);
        this.add(this.supcolor2);
        this.add(this.supgrille);
        this.add(this.precgrille);
        this.add(this.name1);
        this.add(this.name2);

    }

    /**
     * Decrements the color when user clicked on the left arrow
     * @param numplayer the number of the player affected by the change
     */
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

    /**
     * Increments the color when user clicked on the right arrow
     * @param numplayer the number of the player affected by the change
     */
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

    /**
     * Loads the settings of the player 1.
     * @return a string array containing all the settings for player 1.
     */
    public String[] loadP1Settings() {
        String[] p1 = new String[3];
        p1[0] = this.AIlist[this.p1select];
        p1[1] = this.name1.getText();
        p1[2] = this.ColorList[this.p1colorselect];
        return p1;
    }

    /**
     * Loads the settings of the player 2.
     * @return a string array containing all the settings for player 2.
     */
    public String[] loadP2Settings() {
        String[] p2 = new String[3];
        p2[0] = this.AIlist[this.p2select];
        p2[1] = this.name2.getText();
        p2[2] = this.ColorList[this.p2colorselect];
        return p2;
    }

    /**
     * Loads the name of the selected grid.
     * @return 
     */
    public String loadGridName() {
        return this.gridNameList[this.selectedGrid];
    }

    /**
     * Changes the value of the AI/PLAYER field when the left arrow is clicked
     * @param numplayer the number of the player affected by the change
     */
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

    /**
     * Changes the value of the AI/PLAYER field when the right arrow is clicked
     * @param numplayer the number of the player affected by the change
     */
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
    
    

    /**
     * Handles a click on the left arrow on the Grid section
     */
    public void leftGrille() {
        this.selectedGrid = (this.selectedGrid - 1);
            if (this.selectedGrid == -1) {
                this.selectedGrid = this.gridList.length - 1;
            }
            this.grilleName.setText(this.gridNameList[this.selectedGrid]);
            this.callResize = true;
    }

    /**
     * Handles a click on the right arrow on the Grid section
     */
    public void rightGrille() {
            this.selectedGrid = (this.selectedGrid + 1) % this.gridList.length;
            this.grilleName.setText(this.gridNameList[this.selectedGrid]);
            this.callResize = true;
    }

    /**
     * Getter
     * @return the return button
     */
    public JButton getRetour() {
        return this.retour;
    }

    /**
     * Getter
     * @return the name of the grid (in JLabel)
     */
    public JLabel getGrilleName() {
        return grilleName;
    }

    /**
     * Getter
     * @return the next grid
     */
    public JButton getSupgrille() {
        return supgrille;
    }

    /**
     * Getter
     * @return the previous grid
     */
    public JButton getPrecgrille() {
        return precgrille;
    }
    
    /**
     * Getter
     * @return the start button
     */
    public JButton getStart() {
        return this.start;
    }

    /**
     * Getter
     * @return the left button for the difficulty select of player 1 
     */
    public JButton getLeftP1() {
        return this.prec1;
    }

    /**
     * Getter
     * @return the right button for the difficulty select of player 1 
     */
    public JButton getRightP1() {
        return this.sup1;
    }

    /**
     * Getter
     * @return the left button for the difficulty select of player 2 
     */
    public JButton getLeftP2() {
        return this.prec2;
    }

    /**
     * Getter
     * @return the right button for the difficulty select of player 1 
     */
    public JButton getRightP2() {
        return this.sup2;
    }

    /**
     * Getter
     * @return the left button for the color select of player 1 
     */
    public JButton getPreccolor1() {
        return this.preccolor1;
    }

    /**
     * Getter
     * @return the left button for the color select of player 2
     */
    public JButton getPreccolor2() {
        return this.preccolor2;
    }

    /**
     * Getter
     * @return the right button for the color select of player 1 
     */
    public JButton getSupcolor1() {
        return this.supcolor1;
    }

    /**
     * Getter
     * @return the right button for the color select of player 2 
     */
    public JButton getSupcolor2() {
        return this.supcolor2;
    }

    /**
     * Getter
     * @return the name of the first player 
     */
    public JTextField getName1() {
        return this.name1;
    }

    /**
     * Getter
     * @return the name of the second player
     */
    public JTextField getName2() {
        return this.name2;
    }

    /**
     * Getter
     * @return the button of the first player  (humain, easyai, mediumai, etc)
     */
    public JButton getP1button() {
        return p1button;
    }

    /**
     * Getter
     * @return the button of the second player (humain, easyai, mediumai, etc)
     */
    public JButton getP2button() {
        return p2button;
    }

    /**
     * Getter
     * @return the image of the button of previous player type for the first player
     */
    public JButton getPrec1() {
        return prec1;
    }
    /**
     * Getter
     * @return the image of the button of next player type for the first player
     */
    public JButton getSup1() {
        return sup1;
    }
    /**
     * Getter
     * @return the image of the button of previous player type for the second player
     */
    public JButton getPrec2() {
        return prec2;
    }
    /**
     * Getter
     * @return the image of the button of next player type for the second player
     */
    public JButton getSup2() {
        return sup2;
    }
    /**
     * Getter
     * @return the image of the button of the first player color
     */
    public JButton getP1color() {
        return p1color;
    }
    /**
     * Getter
     * @return the image of the button of the second player color
     */
    public JButton getP2color() {
        return p2color;
    }
    /**
     * Getter
     * @return the image of the selected grid
     */
    public int getSelectedGrid() {
        return selectedGrid;
    }
    /**
     * Getter
     * @return the selection of the first player
     */
    public int getP1select() {
        return p1select;
    }
    /**
     * Getter
     * @return the selection of the second player
     */
    public int getP2select() {
        return p2select;
    }
    /**
     * Getter
     * @return the color of the first player
     */
    public int getP1colorselect() {
        return p1colorselect;
    }
    /**
     * Getter
     * @return the color of the second player
     */
    public int getP2colorselect() {
        return p2colorselect;
    }
    /**
     * Getter
     * @return the previous player type
     */
    public Image getPrecI() {
        return precI;
    }
    /**
     * Getter
     * @return the next player type
     */
    public Image getSupI() {
        return supI;
    }
    /**
     * Getter
     * @return the image of the return button
     */
    public Image getReturnI() {
        return returnI;
    }
    /**
     * Getter
     * @return the image of the start button
     */
    public Image getStartI() {
        return startI;
    }
    /**
     * Getter
     * @return the image of the AI type
     */
    public Image[] getAIimgs() {
        return AIimgs;
    }
    /**
     * Getter
     * @return the image of the color of the player
     */
    public Image[] getColorImgs() {
        return ColorImgs;
    }
    /**
     * Getter
     * @return the list of pawn on the game area
     */
    public Image[] getPawnList() {
        return pawnList;
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
            wh = new ImageIcon(this.pawnList[2 + this.p1colorselect].getScaledInstance(((int) round(55 * ratioW)), ((int) round(55 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            bl = new ImageIcon(this.pawnList[2 + this.p2colorselect].getScaledInstance(((int) round(55 * ratioW)), ((int) round(55 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            em = new ImageIcon(this.pawnList[0].getScaledInstance(((int) round(55 * ratioW)), ((int) round(55 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            re = new ImageIcon(this.pawnList[1].getScaledInstance(((int) round(55 * ratioW)), ((int) round(55 * ratioH)), java.awt.Image.SCALE_SMOOTH));
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
                        if (this.ColorList[this.p1colorselect].equals("black")
                                || this.ColorList[this.p1colorselect].equals("purple")
                                || this.ColorList[this.p1colorselect].equals("blue")
                        ) {
                           this.buttonmap[i][j].setForeground(Color.WHITE); 
                        } else {
                            this.buttonmap[i][j].setForeground(Color.BLACK);
                        }
                        break;
                    case PLAYER_2:
                        this.buttonmap[i][j].setIcon(bl);
                        this.buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                        if (this.ColorList[this.p2colorselect].equals("black")
                                || this.ColorList[this.p2colorselect].equals("purple")
                                || this.ColorList[this.p2colorselect].equals("blue")
                        ) {
                           this.buttonmap[i][j].setForeground(Color.WHITE); 
                        } else {
                            this.buttonmap[i][j].setForeground(Color.BLACK);
                        }
                        break;
                    case NO_OWNER:
                        if (gr.getCellAt(c).getState().getValue() == CellState.RESTRICTED.getValue()) {
                            this.buttonmap[i][j].setIcon(re);
                            this.buttonmap[i][j].setText("");
                        } else {
                            this.buttonmap[i][j].setIcon(em);
                            this.buttonmap[i][j].setText("");
                        }
                        break;
                }
                this.buttonmap[i][j].setBounds((int) round((710 + i * 55) * ratioW), ((int) round((345 + j * 55) * ratioH)), (int) round(55 * ratioW), (int) round(55 * ratioH));
                this.buttonmap[i][j].setSize((int) round(55 * ratioW), (int) round(55 * ratioH));
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.LocalAvalamGame;

import avalam_s6.Core.Globals.Input;
import avalam_s6.Core.File_IO.Level_Parser;
import avalam_s6.Core.*;
import avalam_s6.Core.File_IO.SaveParser_Reader;
import avalam_s6.Core.File_IO.SaveParser_Writer;
import avalam_s6.Core.Globals.AvalamColor;
import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.Core.Globals.SoundEngine;
import avalam_s6.Exceptions.GridCharException;
import avalam_s6.Exceptions.GridSizeException;
import avalam_s6.GUI.GuiManager_INTERFACE;
import avalam_s6.GUI.Gui_INTERFACE;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import avalam_s6.Player.*;
import java.awt.*;
import java.io.*;
import static java.lang.Math.round;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author sazeratj
 */
public class GUI_LAG extends JPanel implements Gui_INTERFACE {

    private Game_INTERFACE game;
    private JButton undoB, redoB, retourB, saveB, playB, gauche, droite, fullscreenB, helpB, muteB;
    private Image background, cancel, fullscreen, mute, unmute, help, player_playing, player_waiting, play, pause, redo, retour, save, board, black, white, empty, restricted, w_selected, b_selected, w_possible, b_possible, iaSource, w_iaDest, b_iaDest;
    private final JButton[][] buttonmap;
    private boolean callResize;
    private final LAG_AdapterListener listener;
    private JLabel titre;
    private final Font font;
    private int currentTurn;

    private JLabel p1name, p2name, p1score, p2score, p1color, p2color;

    ImageIcon wh, bl, em, re, wsel, bsel, wpos, bpos, iaSrc, wIADst, bIADst;
    Coordinate IASrc, IADst;

    /**
     * Constructor.
     */
    public GUI_LAG() {
        this.callResize = false;
        this.listener = new LAG_AdapterListener(this);
        this.buttonmap = new JButton[9][9];
        this.currentTurn = 0;
        Font localFont = new Font("Arial", Font.PLAIN, 60);
        try {
            localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/font/Gamaliel.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - " + GUI_LAG.class.toString());
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.font = localFont;
        this.initComponents();
    }

    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/background.png"));
            this.cancel = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/cancel.png"));
            this.player_playing = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/player_playing.png"));
            this.player_waiting = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/player_waiting.png"));
            this.restricted = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/restricted.png"));
            this.redo = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/redo.png"));
            this.retour = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/home.png"));
            this.play = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/play.png"));
            this.pause = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/pause.png"));
            this.save = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/save.png"));
            this.fullscreen = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/fullscreen.png"));
            this.help = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/help.png"));
            this.mute = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/mute.png"));
            this.unmute = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/unmute.png"));
            this.initPawnColors(AvalamColor.WHITE, AvalamColor.BLACK);
            this.empty = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/empty.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_LAG.class.toString());
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageIcon base = new ImageIcon(this.empty);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Coordinate c = new Coordinate(j, i);
                JButton b = new JButton(base);
                b.setBorder(BorderFactory.createEmptyBorder());
                b.setContentAreaFilled(false);
                b.addMouseListener(new LAG_MouseListener(c));
                b.setHorizontalTextPosition(JButton.CENTER);
                b.setVerticalTextPosition(JButton.CENTER);
                b.setFont(b.getFont().deriveFont(1*25f));
                this.buttonmap[j][i] = b;
                //b.setOpaque(false);
                this.add(b);
            }
        }
        this.setLayout(null);

        this.p1name = new JLabel();
        this.p1name.setHorizontalAlignment(JTextField.CENTER);
        this.p1name.setBorder(BorderFactory.createEmptyBorder());
        this.p1name.setFont(this.font.deriveFont(1 * 30f));
        this.add(this.p1name);

        this.p2name = new JLabel();
        this.p2name.setHorizontalAlignment(JTextField.CENTER);
        this.p2name.setBorder(BorderFactory.createEmptyBorder());
        this.p2name.setFont(this.font.deriveFont(1 * 30f));
        this.add(this.p2name);

        this.p1score = new JLabel("Score : 0");
        this.p1score.setHorizontalAlignment(JTextField.CENTER);
        this.p1score.setBorder(BorderFactory.createEmptyBorder());
        this.p1score.setFont(this.font.deriveFont(1 * 30f));
        this.add(this.p1score);

        this.p2score = new JLabel("Score : 0");
        this.p2score.setHorizontalAlignment(JTextField.CENTER);
        this.p2score.setBorder(BorderFactory.createEmptyBorder());
        this.p2score.setFont(this.font.deriveFont(1 * 30f));
        this.add(this.p2score);

        this.p1color = new JLabel();
        this.p1color.setHorizontalAlignment(JTextField.CENTER);
        this.p1color.setBorder(BorderFactory.createEmptyBorder());
        this.add(this.p1color);

        this.p2color = new JLabel();
        this.p2color.setHorizontalAlignment(JTextField.CENTER);
        this.p2color.setBorder(BorderFactory.createEmptyBorder());
        this.add(this.p2color);

        this.titre = new JLabel("test d'un titre long");
        this.titre.setHorizontalAlignment(JTextField.CENTER);
        this.titre.setBorder(BorderFactory.createEmptyBorder());
        this.titre.setFont(this.font.deriveFont(1 * 30f));
        this.add(this.titre);

        this.undoB = new JButton(new ImageIcon(this.cancel));
        this.undoB.setBorder(BorderFactory.createEmptyBorder());
        this.undoB.setContentAreaFilled(false);
        this.undoB.setFocusPainted(false);
        this.undoB.addMouseListener(new LAG_UI_MouseListener("cancel", this));
        this.add(this.undoB);

        this.retourB = new JButton(new ImageIcon(this.retour));
        this.retourB.setBorder(BorderFactory.createEmptyBorder());
        this.retourB.setContentAreaFilled(false);
        this.retourB.setFocusPainted(false);
        this.retourB.addMouseListener(new LAG_UI_MouseListener("home", this));
        this.add(this.retourB);

        this.redoB = new JButton(new ImageIcon(this.redo));
        this.redoB.setBorder(BorderFactory.createEmptyBorder());
        this.redoB.setContentAreaFilled(false);
        this.redoB.setFocusPainted(false);
        this.redoB.addMouseListener(new LAG_UI_MouseListener("redo", this));
        this.add(this.redoB);

        this.saveB = new JButton(new ImageIcon(this.save));
        this.saveB.setBorder(BorderFactory.createEmptyBorder());
        this.saveB.setContentAreaFilled(false);
        this.saveB.setFocusPainted(false);
        this.saveB.addMouseListener(new LAG_UI_MouseListener("save", this));
        this.add(this.saveB);

        this.playB = new JButton(new ImageIcon(this.pause));
        this.playB.setBorder(BorderFactory.createEmptyBorder());
        this.playB.setContentAreaFilled(false);
        this.playB.setFocusPainted(false);
        this.playB.addMouseListener(new LAG_UI_MouseListener("play", this));
        this.add(this.playB);

        this.fullscreenB = new JButton(new ImageIcon(this.fullscreen));
        this.fullscreenB.setBorder(BorderFactory.createEmptyBorder());
        this.fullscreenB.setContentAreaFilled(false);
        this.fullscreenB.setFocusPainted(false);
        this.fullscreenB.addMouseListener(new LAG_UI_MouseListener("fullscreen", this));
        this.add(this.fullscreenB);

        this.muteB = new JButton(new ImageIcon(this.mute));
        this.muteB.setBorder(BorderFactory.createEmptyBorder());
        this.muteB.setContentAreaFilled(false);
        this.muteB.setFocusPainted(false);
        this.muteB.addMouseListener(new LAG_UI_MouseListener("mute", this));
        this.add(this.muteB);

        this.helpB = new JButton(new ImageIcon(this.help));
        this.helpB.setBorder(BorderFactory.createEmptyBorder());
        this.helpB.setContentAreaFilled(false);
        this.helpB.setFocusPainted(false);
        this.helpB.addMouseListener(new LAG_UI_MouseListener("help", this));
        this.add(this.helpB);

        this.gauche = new JButton(new ImageIcon(this.player_playing));
        this.gauche.setBorder(BorderFactory.createEmptyBorder());
        this.gauche.setContentAreaFilled(false);
        this.gauche.setFocusPainted(false);
        this.add(this.gauche);

        this.droite = new JButton(new ImageIcon(this.player_waiting));
        this.droite.setBorder(BorderFactory.createEmptyBorder());
        this.droite.setContentAreaFilled(false);
        this.droite.setFocusPainted(false);
        this.add(this.droite);

        this.addComponentListener(this.listener);
    }

    public void initPawnColors(AvalamColor pWhite, AvalamColor pBlack) {
        try {

            this.white = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + pWhite.getValue() + ".png"));
            this.black = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + pBlack.getValue() + ".png"));
            this.w_selected = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + pWhite.getValue() + "_selected.png"));
            this.b_selected = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + pBlack.getValue() + "_selected.png"));
            this.w_possible = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + pWhite.getValue() + "_possible.png"));
            this.b_possible = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + pBlack.getValue() + "_possible.png"));
            this.iaSource = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/empty_ia.png"));
            this.w_iaDest = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + pWhite.getValue() + "_ia.png"));
            this.b_iaDest = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + pBlack.getValue() + "_ia.png"));
            
        } catch (IOException ex) {
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initPlayerInfos(Player p1, Player p2) {
        //System.out.println("truc");
        this.p1name.setText(p1.getName());
        this.p2name.setText(p2.getName());
        this.p1color.setIcon(new ImageIcon(this.white));
        this.p2color.setIcon(new ImageIcon(this.black));
    }

    public void initGame(GuiManager_INTERFACE pGui) {
        try {
            this.game = new Local_Avalam_Game((Main_Frame) pGui); // GridSizeException
            this.initPawnColors(AvalamColor.WHITE, AvalamColor.BLACK);
            this.initPlayerInfos(((Local_Avalam_Game) this.game).getPlayers()[0], ((Local_Avalam_Game) this.game).getPlayers()[1]);
        } catch (IOException | GridSizeException | GridCharException ex) {
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    public void initGame(GuiManager_INTERFACE pGui, String pClassP1, String pNameP1, String pColorP1, String pClassP2, String pNameP2, String pColorP2, String pGridName) {
        try {
            Class lClass1 = Class.forName("avalam_s6.Player." + pClassP1);
            Class lClass2 = Class.forName("avalam_s6.Player." + pClassP2);
            Constructor lConst1 = lClass1.getConstructor(String.class, AvalamColor.class, Owner.class);
            Constructor lConst2 = lClass2.getConstructor(String.class, AvalamColor.class, Owner.class);
            Player p1 = (Player) lConst1.newInstance(new Object[]{pNameP1, AvalamColor.valueOf(pColorP1.toUpperCase()), Owner.PLAYER_1});
            Player p2 = (Player) lConst2.newInstance(new Object[]{pNameP2, AvalamColor.valueOf(pColorP2.toUpperCase()), Owner.PLAYER_2});
            Level_Parser myParser = new Level_Parser(pGridName);
            Grid g = new Grid(myParser.readLevel(), pGridName); // IOException | GridSizeException | NumberFormatException
            this.game = new Local_Avalam_Game((Main_Frame) pGui, g, p1, p2, new Stack<>(), new Stack<>(), 0, 0); // GridSizeException
            this.initPawnColors(p1.getColor(), p2.getColor());
            this.initPlayerInfos(p1, p2);
        } catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | GridCharException | IOException | GridSizeException ex) {
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateLastIaMove() {
        Move x = ((Local_Avalam_Game)this.game).getLastIaMove();
        if (x != null) {
            this.IASrc = x.getC_src();
            this.IADst = x.getC_dst();
        } else {
            this.IASrc = null;
            this.IADst = null;
        }
    }

    public void deleteGame() {
        this.game.clean();
        this.game = null;
        System.gc();
    }

    public void start() {
        this.game.getTimer().start();
        ((Local_Avalam_Game)this.game).updateTitle();
    }

    public void stop() {
        this.game.getTimer().stop();
    }

    public void save(String pSlotName) {
        SaveParser_Writer myParser = new SaveParser_Writer((Local_Avalam_Game) this.game, pSlotName);
        myParser.save();
    }

    public void load(GuiManager_INTERFACE mFrame, String pSlotName) {
        //Container mainFrame = this.getParent().getParent().getParent().getParent();
        SaveParser_Reader lParser = new SaveParser_Reader((Main_Frame) mFrame, pSlotName);
        this.game = new Local_Avalam_Game((Main_Frame) mFrame, lParser.getaGrid(), lParser.getaPlayer1(), lParser.getaPlayer2(), lParser.getaUndo(), lParser.getaRedo(), lParser.getaCurrentPlayer(), lParser.getaTurns());
        ((Local_Avalam_Game)this.game).updateTitle();
    }

    public JButton getUndoB() {
        return this.undoB;
    }

    public JButton getRedoB() {
        return this.redoB;
    }

    public JButton getRetourB() {
        return this.retourB;
    }

    public JLabel getP1name() {
        return p1name;
    }

    public JLabel getP2name() {
        return p2name;
    }

    public JLabel getP1score() {
        return p1score;
    }

    public JLabel getP2score() {
        return p2score;
    }

    public JLabel getP1color() {
        return p1color;
    }

    public JLabel getP2color() {
        return p2color;
    }

    public JButton getSaveB() {
        return this.saveB;
    }

    public Game_INTERFACE getGame() {
        return this.game;
    }

    public JButton[][] getButtonmap() {
        return buttonmap;
    }

    public Image getBlack() {
        return black;
    }

    public Image getWhite() {
        return white;
    }

    public JLabel getTitre() {
        return titre;
    }

    public JButton getPlayB() {
        return playB;
    }

    public Image getPlay() {
        return play;
    }

    public Image getPause() {
        return pause;
    }

    public Image getEmpty() {
        return empty;
    }

    public Image getRestricted() {
        return restricted;
    }

    public Image getCancel() {
        return cancel;
    }

    public Image getRedo() {
        return redo;
    }

    public Image getRetour() {
        return retour;
    }

    public Image getSave() {
        return save;
    }

    public JButton getGauche() {
        return gauche;
    }

    public JButton getDroite() {
        return droite;
    }

    public Image getPlayer_playing() {
        return player_playing;
    }

    public Image getPlayer_waiting() {
        return player_waiting;
    }

    public JButton getFullscreenB() {
        return fullscreenB;
    }

    public JButton getHelpB() {
        return helpB;
    }

    public JButton getMuteB() {
        return muteB;
    }

    public Image getFullscreen() {
        return fullscreen;
    }

    public Image getMute() {
        return mute;
    }

    public Image getUnMute() {
        return unmute;
    }
    public Image getHelp() {
        return help;
    }

    public void setTitle(String s) {
        this.titre.setText(s);
    }

    public Font getLabelFont() {
        return this.font;
    }

    @Override
    public void back() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());
        this.stop();
        mainFrame.setwState(WindowState.MAIN);
    }

    @Override
    public void callResize() {
        this.callResize = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        this.updateLastIaMove();
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);
        double ratioW = (double) this.getWidth() / (double) 1920;
        double ratioH = (double) this.getHeight() / (double) 1080;

        /* --- GRILLE --- */
        Grid gr = this.game.getGrid();
        Coordinate c = new Coordinate();
        if (this.callResize) {
            wh = new ImageIcon(this.white.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            bl = new ImageIcon(this.black.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            em = new ImageIcon(this.empty.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            re = new ImageIcon(this.restricted.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            wsel = new ImageIcon(this.w_selected.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            bsel = new ImageIcon(this.b_selected.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            wpos = new ImageIcon(this.w_possible.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            bpos = new ImageIcon(this.b_possible.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            iaSrc = new ImageIcon(this.iaSource.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            wIADst = new ImageIcon(this.w_iaDest.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            bIADst = new ImageIcon(this.b_iaDest.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH));
            this.p1color.setIcon(wh);
            this.p2color.setIcon(bl);

        }
        if (this.currentTurn != ((Local_Avalam_Game) this.getGame()).getTurns()) {

            if (((Local_Avalam_Game) this.getGame()).getTurns() % 2 == 0) {
                this.gauche.setIcon(new ImageIcon(this.player_playing.getScaledInstance(((int) round(284 * ratioW)), ((int) round(671 * ratioH)), java.awt.Image.SCALE_SMOOTH)));
                this.droite.setIcon(new ImageIcon(this.player_waiting.getScaledInstance(((int) round(284 * ratioW)), ((int) round(671 * ratioH)), java.awt.Image.SCALE_SMOOTH)));
            } else {
                this.gauche.setIcon(new ImageIcon(this.player_waiting.getScaledInstance(((int) round(284 * ratioW)), ((int) round(671 * ratioH)), java.awt.Image.SCALE_SMOOTH)));
                this.droite.setIcon(new ImageIcon(this.player_playing.getScaledInstance(((int) round(284 * ratioW)), ((int) round(671 * ratioH)), java.awt.Image.SCALE_SMOOTH)));
            }

            this.updateScore();
            this.currentTurn = ((Local_Avalam_Game) this.getGame()).getTurns();

        }

        for (int i = 0; i < gr.getWidth(); i++) {
            for (int j = 0; j < gr.getHeight(); j++) {
                c.setX(i);
                c.setY(j);
                Cell ce = gr.getCellAt(c);
                if (Input.isButtonClicked()) {
                    int m1 = Math.abs(c.getX() - Input.getMouseSrcPosition().getX());
                    int m2 = Math.abs(c.getY() - Input.getMouseSrcPosition().getY());
                    if (m1 <= 1 && m2 <= 1 && (gr.canStack(ce, gr.getCellAt(Input.getMouseSrcPosition())) || (m1 == 0 && m2 == 0))) {
                        switch (ce.getOwner()) {
                            case PLAYER_1:
                                if (m1 == 0 && m2 == 0) {
                                    this.buttonmap[i][j].setIcon(wsel);
                                } else {
                                    this.buttonmap[i][j].setIcon(wpos);
                                }
                                this.buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                                break;
                            case PLAYER_2:
                                if (m1 == 0 && m2 == 0) {
                                    this.buttonmap[i][j].setIcon(bsel);
                                } else {
                                    this.buttonmap[i][j].setIcon(bpos);
                                }
                                this.buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                                break;
                            case NO_OWNER:
                                if (gr.getCellAt(c).getState().getValue() == CellState.RESTRICTED.getValue()) {
                                    this.buttonmap[i][j].setOpaque(false);
                                } else {
                                    this.buttonmap[i][j].setIcon(em);
                                    this.buttonmap[i][j].setText("");
                                }
                                break;
                        }
                    }
                } else {
                    switch (ce.getOwner()) {
                        case PLAYER_1:
                            this.buttonmap[i][j].setIcon(wh);
                            this.buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                            if (((Local_Avalam_Game)this.game).getPlayers()[0].getColor().getValue().equals("black")
                                || ((Local_Avalam_Game)this.game).getPlayers()[0].getColor().getValue().equals("purple")
                                || ((Local_Avalam_Game)this.game).getPlayers()[0].getColor().getValue().equals("blue")
                            ) {
                               this.buttonmap[i][j].setForeground(Color.WHITE); 
                            } else {
                                this.buttonmap[i][j].setForeground(Color.BLACK);
                            }
                            break;
                        case PLAYER_2:
                            this.buttonmap[i][j].setIcon(bl);
                            this.buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                            if (((Local_Avalam_Game)this.game).getPlayers()[1].getColor().getValue().equals("black")
                                || ((Local_Avalam_Game)this.game).getPlayers()[1].getColor().getValue().equals("purple")
                                || ((Local_Avalam_Game)this.game).getPlayers()[1].getColor().getValue().equals("blue")
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
                }
                this.buttonmap[i][j].setBounds((int) round((660 + i * 66) * ratioW), ((int) round((260 + j * 66) * ratioH)), (int) round(66 * ratioW), (int) round(66 * ratioH));
                this.buttonmap[i][j].setSize((int) round(66 * ratioW), (int) round(66 * ratioH));
                this.buttonmap[i][j].setOpaque(false);
            }
        }
        if (IASrc != null) {
            
        }
        if (IADst != null) {
            
        }
        /* -- BOUTON PAUSE -- */
        Image newimg;
        if (((Local_Avalam_Game) this.game).isPaused()) {
            newimg = getPlay().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        } else {
            newimg = getPause().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        }
        getPlayB().setIcon(new ImageIcon(newimg));
        
        /* -- BOUTON MUTE -- */
        Image soundImg;
        if (SoundEngine.isMuted()){
            soundImg = this.getUnMute().getScaledInstance((int) round(80 * ratioW), (int) round(80 * ratioH), java.awt.Image.SCALE_SMOOTH);
        } else {
            soundImg = this.getMute().getScaledInstance((int) round(80 * ratioW), (int) round(80 * ratioH), java.awt.Image.SCALE_SMOOTH);
        }
        getMuteB().setIcon(new ImageIcon(soundImg));
        /* -- Resize -- */
        if (this.callResize) {
            this.listener.componentResized(null);
            this.callResize = false;
        }
    }

    private void updateScore() {
        this.p1score.setText("Score : " + ((Local_Avalam_Game) this.game).getScore(1));
        this.p2score.setText("Score : " + ((Local_Avalam_Game) this.game).getScore(2));
    }

}

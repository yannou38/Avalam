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
package avalam.GUI.LocalAvalamGame;

import avalam.Core.Grid;
import avalam.Core.CellState;
import avalam.Core.Owner;
import avalam.Core.Local_Avalam_Game;
import avalam.Core.Coordinate;
import avalam.Core.Game_INTERFACE;
import avalam.Core.Cell;
import avalam.Core.Move;
import avalam.Core.Globals.Input;
import avalam.Core.File_IO.Level_Parser;
import avalam.Core.File_IO.SaveParser_Reader;
import avalam.Core.File_IO.SaveParser_Writer;
import avalam.Core.Globals.AvalamColor;
import avalam.Core.Globals.SetupManager;
import avalam.Exceptions.GridCharException;
import avalam.Exceptions.GridSizeException;
import avalam.GUI.GuiManager_INTERFACE;
import avalam.GUI.Gui_INTERFACE;
import avalam.GUI.Main_Frame;
import avalam.GUI.WindowState;
import avalam.Player.*;
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
 * The game board panel.
 *
 * @author Team 7
 */
public class GUI_LAG extends JPanel implements Gui_INTERFACE {

    private Game_INTERFACE game;
    private JButton undoB, redoB, retourB, saveB, playB, gauche, droite, fullscreenB, helpB, muteB;
    private Image background, cancel, fullscreen, mute, unmute, help, player_playing, player_waiting, play, pause, redo, retour, save, board, black, white, empty, restricted, w_selected, b_selected, w_possible, b_possible, iaSource, w_iaDest, b_iaDest;
    private final JButton[][] buttonmap;
    private boolean callResize, callPause, callMute;
    private final LAG_AdapterListener listener;
    private JLabel titre;
    private final Font font;
    private int currentTurn;
    private JLabel p1name, p2name, p1score, p2score, p1color, p2color;

    ImageIcon wh, bl, em, re, wsel, bsel, wpos, bpos, iaSrc, wIADst, bIADst;
    Coordinate IASrc, IADst;
    Coordinate hintSrc, hintDst;

    /**
     * Constructor.
     */
    public GUI_LAG() {
        this.callResize = false;
        this.callPause = false;
        this.callMute = false;
        this.listener = new LAG_AdapterListener(this);
        this.buttonmap = new JButton[9][9];
        this.currentTurn = 0;
        Font localFont = new Font("Arial", Font.PLAIN, 60);
        try {
            localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/font/Gamaliel.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - " + GUI_LAG.class.toString());
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.font = localFont;
        this.setHint(null);
        this.initComponents();
    }

    /**
     * Initialize the Components.
     */
    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/background.png"));
            this.cancel = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/cancel.png"));
            this.player_playing = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/player_playing.png"));
            this.player_waiting = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/player_waiting.png"));
            this.restricted = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/restricted.png"));
            this.redo = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/redo.png"));
            this.retour = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/home.png"));
            this.play = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/play.png"));
            this.pause = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/pause.png"));
            this.save = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/save.png"));
            this.fullscreen = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/fullscreen.png"));
            this.help = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/help.png"));
            this.mute = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/mute.png"));
            this.unmute = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/unmute.png"));
            this.initPawnColors(AvalamColor.WHITE, AvalamColor.BLACK);
            this.empty = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/empty.png"));
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
                b.setFont(b.getFont().deriveFont(1 * 25f));
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
        this.undoB.setEnabled(false);
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
        this.redoB.setEnabled(false);
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

        if (SetupManager.getElement("Son").equals("Non")) {
            this.mute = this.unmute;
        }
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

    /**
     * Load the pawn colors based on AvalamColor Enum.
     *
     * @param pWhite the first player color
     * @param pBlack the second player color
     */
    public void initPawnColors(AvalamColor pWhite, AvalamColor pBlack) {
        try {

            this.white = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/" + pWhite.getValue() + ".png"));
            this.black = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/" + pBlack.getValue() + ".png"));
            this.w_selected = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/" + pWhite.getValue() + "_selected.png"));
            this.b_selected = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/" + pBlack.getValue() + "_selected.png"));
            this.w_possible = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/" + pWhite.getValue() + "_possible.png"));
            this.b_possible = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/" + pBlack.getValue() + "_possible.png"));
            this.iaSource = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/empty_ia.png"));
            this.w_iaDest = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/" + pWhite.getValue() + "_ia.png"));
            this.b_iaDest = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/board/" + pBlack.getValue() + "_ia.png"));

        } catch (IOException ex) {
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initialize the players infos on the left and right side of the GUI
     *
     * @param p1 the first player Object
     * @param p2 the second player Object
     */
    private void initPlayerInfos(Player p1, Player p2) {
        //System.out.println("truc");
        if (p1.isAI()) {
            this.p1name.setText(p1.getName() + " (AI)");
        } else {
            this.p1name.setText(p1.getName());
        }
        if (p2.isAI()) {
            this.p2name.setText(p2.getName() + " (AI)");
        } else {
            this.p2name.setText(p2.getName());
        }
        this.p1color.setIcon(new ImageIcon(this.white));
        this.p2color.setIcon(new ImageIcon(this.black));
    }

    /**
     * Initialize a standard game with set parameters.
     *
     * @param pGui the main frame interface
     */
    public void initGame(GuiManager_INTERFACE pGui) {
        try {
            this.game = new Local_Avalam_Game((Main_Frame) pGui); // GridSizeException
            this.initPawnColors(AvalamColor.WHITE, AvalamColor.BLACK);
            this.initPlayerInfos(((Local_Avalam_Game) this.game).getPlayers()[0], ((Local_Avalam_Game) this.game).getPlayers()[1]);
        } catch (IOException | GridSizeException | GridCharException ex) {
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initialize a custom game with custom parameters.
     *
     * @param pGui the main frame interface
     * @param pClassP1 the first player class name
     * @param pNameP1 the first player name
     * @param pColorP1 the first player color
     * @param pClassP2 the second player class name
     * @param pNameP2 the second player name
     * @param pColorP2 the second player color
     * @param pGridName the grid filename
     */
    @SuppressWarnings("unchecked")
    public void initGame(GuiManager_INTERFACE pGui, String pClassP1, String pNameP1, String pColorP1, String pClassP2, String pNameP2, String pColorP2, String pGridName) {
        try {
            Class lClass1 = Class.forName("avalam.Player." + pClassP1);
            Class lClass2 = Class.forName("avalam.Player." + pClassP2);
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

    /**
     * Update the last move made by the IA.
     */
    public void updateLastIaMove() {
        Move x = ((Local_Avalam_Game) this.game).getLastIaMove();
        if (x != null) {
            this.IASrc = x.getC_src();
            this.IADst = x.getC_dst();
        } else {
            this.IASrc = null;
            this.IADst = null;
        }
    }

    /**
     * Delete the actual game.
     */
    public void deleteGame() {
        this.game.clean();
        this.game = null;
        System.gc();
    }

    /**
     * Start the game timer.
     */
    public void start() {
        this.game.getTimer().start();
        ((Local_Avalam_Game) this.game).updateTitle();
    }

    /**
     * Stop the game timer.
     */
    public void stop() {
        this.game.getTimer().stop();
    }

    /**
     * Save the current game in a file
     *
     * @param pSlotName the file name (do not provide path)
     */
    public void save(String pSlotName) {
        SaveParser_Writer myParser = new SaveParser_Writer((Local_Avalam_Game) this.game, pSlotName);
        myParser.save();
    }

    /**
     * Load the current game from a file
     *
     * @param mFrame the main frame
     * @param pSlotName the file name (do not provide path)
     */
    public void load(GuiManager_INTERFACE mFrame, String pSlotName) {
        SaveParser_Reader lParser = new SaveParser_Reader((Main_Frame) mFrame, pSlotName);
        this.game = new Local_Avalam_Game((Main_Frame) mFrame, lParser.getaGrid(), lParser.getaPlayer1(), lParser.getaPlayer2(), lParser.getaUndo(), lParser.getaRedo(), lParser.getaCurrentPlayer(), lParser.getaTurns());
        ((Local_Avalam_Game) this.game).updateTitle();
    }

    /**
     * Getter.
     *
     * @return the undo button
     */
    public JButton getUndoB() {
        return this.undoB;
    }

    /**
     * Getter.
     *
     * @return the redo button
     */
    public JButton getRedoB() {
        return this.redoB;
    }

    /**
     * Getter.
     *
     * @return the return button
     */
    public JButton getRetourB() {
        return this.retourB;
    }

    /**
     * Getter.
     *
     * @return the first player name label
     */
    public JLabel getP1name() {
        return p1name;
    }

    /**
     * Getter.
     *
     * @return the second player name label
     */
    public JLabel getP2name() {
        return p2name;
    }

    /**
     * Getter.
     *
     * @return the first player score label
     */
    public JLabel getP1score() {
        return p1score;
    }

    /**
     * Getter.
     *
     * @return the second player score label
     */
    public JLabel getP2score() {
        return p2score;
    }

    /**
     * Getter.
     *
     * @return the first player color label
     */
    public JLabel getP1color() {
        return p1color;
    }

    /**
     * Getter.
     *
     * @return the second player color label
     */
    public JLabel getP2color() {
        return p2color;
    }

    /**
     * Getter.
     *
     * @return the save button
     */
    public JButton getSaveB() {
        return this.saveB;
    }

    /**
     * Getter.
     *
     * @return the game object interface
     */
    public Game_INTERFACE getGame() {
        return this.game;
    }

    /**
     * Getter.
     *
     * @return the button map (AKA grid)
     */
    public JButton[][] getButtonmap() {
        return buttonmap;
    }

    /**
     * Getter.
     *
     * @return the second player color image
     */
    public Image getBlack() {
        return black;
    }

    /**
     * Getter.
     *
     * @return the first player color image
     */
    public Image getWhite() {
        return white;
    }

    /**
     * Getter.
     *
     * @return the title label
     */
    public JLabel getTitre() {
        return titre;
    }

    /**
     * Getter.
     *
     * @return the play/pause button
     */
    public JButton getPlayB() {
        return playB;
    }

    /**
     * Getter.
     *
     * @return the play image
     */
    public Image getPlay() {
        return play;
    }

    /**
     * Getter.
     *
     * @return the pause image
     */
    public Image getPause() {
        return pause;
    }

    /**
     * Getter.
     *
     * @return the empty tile image
     */
    public Image getEmpty() {
        return empty;
    }

    /**
     * Getter.
     *
     * @return the restricted tile image
     */
    public Image getRestricted() {
        return restricted;
    }

    /**
     * Getter.
     *
     * @return the undo image
     */
    public Image getCancel() {
        return cancel;
    }

    /**
     * Getter.
     *
     * @return the redo image
     */
    public Image getRedo() {
        return redo;
    }

    /**
     * Getter.
     *
     * @return the return image
     */
    public Image getRetour() {
        return retour;
    }

    /**
     * Getter.
     *
     * @return the save image
     */
    public Image getSave() {
        return save;
    }

    /**
     * Getter.
     *
     * @return the left area button
     */
    public JButton getGauche() {
        return gauche;
    }

    /**
     * Getter.
     *
     * @return the right area button
     */
    public JButton getDroite() {
        return droite;
    }

    /**
     * Getter.
     *
     * @return the area image of the player actually playing
     */
    public Image getPlayer_playing() {
        return player_playing;
    }

    /**
     * Getter.
     *
     * @return the area image of the player currently waiting
     */
    public Image getPlayer_waiting() {
        return player_waiting;
    }

    /**
     * Getter.
     *
     * @return the fullscreen toogle button
     */
    public JButton getFullscreenB() {
        return fullscreenB;
    }

    /**
     * Getter.
     *
     * @return the 'suggest a move' button
     */
    public JButton getHelpB() {
        return helpB;
    }

    /**
     * Getter.
     *
     * @return the toogle mute button
     */
    public JButton getMuteB() {
        return muteB;
    }

    /**
     * Getter.
     *
     * @return the fullscreen toogle image
     */
    public Image getFullscreen() {
        return fullscreen;
    }

    /**
     * Getter.
     *
     * @return the mute image
     */
    public Image getMute() {
        return mute;
    }

    /**
     * Getter.
     *
     * @return the unmute image
     */
    public Image getUnMute() {
        return unmute;
    }

    /**
     * Getter.
     *
     * @return the help image
     */
    public Image getHelp() {
        return help;
    }

    /**
     * Set the title text.
     *
     * @param s the title text
     */
    public void setTitle(String s) {
        this.titre.setText(s);
    }

    /**
     * Set the hint display.
     *
     * @param m the move to display
     */
    public void setHint(Move m) {
        if (m == null) {
            hintSrc = null;
            hintDst = null;
        } else {
            hintSrc = m.getC_src();
            hintDst = m.getC_dst();
        }
    }

    /**
     * Getter.
     *
     * @return the custom font
     */
    public Font getLabelFont() {
        return this.font;
    }

    /**
     * Set the callpause boolean.
     *
     * @param val the boolan to set
     */
    public void setCallPause(boolean val) {
        this.callPause = val;
    }

    /**
     * Set the mute boolean.
     *
     * @param val the boolean to set
     */
    public void setMuteCall(boolean val) {
        this.callMute = val;
    }

    /**
     * Go back to homepage.
     */
    @Override
    public void back() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());
        this.stop();
        mainFrame.setwState(WindowState.MAIN);
    }

    /**
     * Call the resizing function ONCE in the repaint().
     */
    @Override
    public void callResize() {
        this.callResize = true;
    }

    /**
     * Override of the Strandard paintComponent() function.
     *
     * @param g the graphics object
     */
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
                            if (((Local_Avalam_Game) this.game).getPlayers()[0].getColor().getValue().equals("black")
                                    || ((Local_Avalam_Game) this.game).getPlayers()[0].getColor().getValue().equals("purple")
                                    || ((Local_Avalam_Game) this.game).getPlayers()[0].getColor().getValue().equals("blue")) {
                                this.buttonmap[i][j].setForeground(Color.WHITE);
                            } else {
                                this.buttonmap[i][j].setForeground(Color.BLACK);
                            }
                            break;
                        case PLAYER_2:
                            this.buttonmap[i][j].setIcon(bl);
                            this.buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                            if (((Local_Avalam_Game) this.game).getPlayers()[1].getColor().getValue().equals("black")
                                    || ((Local_Avalam_Game) this.game).getPlayers()[1].getColor().getValue().equals("purple")
                                    || ((Local_Avalam_Game) this.game).getPlayers()[1].getColor().getValue().equals("blue")) {
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
        /* Move IA */
        if (IASrc != null && !IASrc.equals(Input.getMouseSrcPosition())) {
            this.buttonmap[IASrc.getX()][IASrc.getY()].setIcon(iaSrc);
        }
        if (IADst != null && !IADst.equals(Input.getMouseSrcPosition())) {
            if (gr.getCellAt(IADst).getOwner().getValue() == Owner.PLAYER_1.getValue()) {
                this.buttonmap[IADst.getX()][IADst.getY()].setIcon(wIADst);
                this.buttonmap[IADst.getX()][IADst.getY()].setText(Integer.toString(gr.getCellAt(IADst).getSize()));
                if (((Local_Avalam_Game) this.game).getPlayers()[0].getColor().getValue().equals("black")
                        || ((Local_Avalam_Game) this.game).getPlayers()[0].getColor().getValue().equals("purple")
                        || ((Local_Avalam_Game) this.game).getPlayers()[0].getColor().getValue().equals("blue")) {
                    this.buttonmap[IADst.getX()][IADst.getY()].setForeground(Color.WHITE);
                } else {
                    this.buttonmap[IADst.getX()][IADst.getY()].setForeground(Color.BLACK);
                }
            } else {
                this.buttonmap[IADst.getX()][IADst.getY()].setIcon(bIADst);
                this.buttonmap[IADst.getX()][IADst.getY()].setText(Integer.toString(gr.getCellAt(IADst).getSize()));
                if (((Local_Avalam_Game) this.game).getPlayers()[1].getColor().getValue().equals("black")
                        || ((Local_Avalam_Game) this.game).getPlayers()[1].getColor().getValue().equals("purple")
                        || ((Local_Avalam_Game) this.game).getPlayers()[1].getColor().getValue().equals("blue")) {
                    this.buttonmap[IADst.getX()][IADst.getY()].setForeground(Color.WHITE);
                } else {
                    this.buttonmap[IADst.getX()][IADst.getY()].setForeground(Color.BLACK);
                }
            }
        }
        /* Hint */
        if (hintSrc != null && (Math.abs(hintSrc.getX() - Input.getMouseSrcPosition().getX()) > 1 || Math.abs(hintSrc.getY() - Input.getMouseSrcPosition().getY()) > 1)) {
            if (gr.getCellAt(hintSrc).getOwner().getValue() == Owner.PLAYER_1.getValue()) {
                this.buttonmap[hintSrc.getX()][hintSrc.getY()].setIcon(wsel);
            } else {
                this.buttonmap[hintSrc.getX()][hintSrc.getY()].setIcon(bsel);
            }
        }
        if (hintDst != null && (Math.abs(hintDst.getX() - Input.getMouseSrcPosition().getX()) > 1 || Math.abs(hintDst.getY() - Input.getMouseSrcPosition().getY()) > 1)) {
            if (gr.getCellAt(hintDst).getOwner().getValue() == Owner.PLAYER_1.getValue()) {
                this.buttonmap[hintDst.getX()][hintDst.getY()].setIcon(wIADst);
            } else {
                this.buttonmap[hintDst.getX()][hintDst.getY()].setIcon(bIADst);
            }
        }
        if (hintSrc != null && hintDst != null && hintSrc.getX() == Input.getMouseSrcPosition().getX() && hintSrc.getY() == Input.getMouseSrcPosition().getY() && (Math.abs(hintDst.getX() - Input.getMouseDestPosition().getX()) <= 1 || Math.abs(hintDst.getY() - Input.getMouseDestPosition().getY()) > 1)) {
            if (gr.getCellAt(hintDst).getOwner().getValue() == Owner.PLAYER_1.getValue()) {
                this.buttonmap[hintDst.getX()][hintDst.getY()].setIcon(wIADst);
            } else {
                this.buttonmap[hintDst.getX()][hintDst.getY()].setIcon(bIADst);
            }
        }
        /* -- BOUTON PAUSE -- */
        Image newimg;
        if (this.callPause) {
            newimg = getPlay().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
            getPlayB().setIcon(new ImageIcon(newimg));
            this.callPause = false;
        }
        /* -- BOUTON MUTE -- */
        if (this.callMute) {
            newimg = this.getMute().getScaledInstance((int) round(80 * ratioW), (int) round(80 * ratioH), java.awt.Image.SCALE_SMOOTH);
            getMuteB().setIcon(new ImageIcon(newimg));
            this.callMute = false;
        }
        /* undo redo */

        this.undoB.setEnabled(!((Local_Avalam_Game) this.game).getHistory().isEmpty());
        this.redoB.setEnabled(!((Local_Avalam_Game) this.game).getCancelled_moves().isEmpty());

        /* -- Resize -- */
        if (this.callResize) {
            this.listener.componentResized(null);
            this.callResize = false;
        }
    }

    /**
     * Update the score info on both player areas.
     */
    private void updateScore() {
        this.p1score.setText("Score : " + ((Local_Avalam_Game) this.game).getScore(1));
        this.p2score.setText("Score : " + ((Local_Avalam_Game) this.game).getScore(2));
    }

    /**
     * Set the play button image.
     *
     * @param icon the new image
     */
    void setPlay(Image icon) {
        this.play = icon;
    }

    /**
     * Set the mute button image.
     *
     * @param icon the new image
     */
    void setMute(Image icon) {
        this.mute = icon;
    }

}

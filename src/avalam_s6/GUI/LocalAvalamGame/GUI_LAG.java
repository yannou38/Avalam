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
import avalam_s6.Exceptions.GridCharException;
import avalam_s6.Exceptions.GridSizeException;
import avalam_s6.GUI.GuiManager_INTERFACE;
import avalam_s6.GUI.Gui_INTERFACE;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import avalam_s6.Player.*;
import java.awt.*;
import java.io.*;
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
    private final boolean player1IsPlaying;
    private JPanel grille;
    private JButton undoB, redoB, retourB, saveB;
    private Image background, cancel, player_playing, player_waiting, redo, retour, save, board, black, white, empty, restricted, w_selected, b_selected, w_possible, b_possible;
    private final JButton[][] buttonmap;
    private boolean callResize;
    private final LAG_AdapterListener listener;

    /**
     * Constructor.
     */
    public GUI_LAG() {
        this.callResize = false;
        this.listener = new LAG_AdapterListener(this);
        this.buttonmap = new JButton[9][9];
        this.initComponents();
        this.player1IsPlaying = true;
    }

    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/background.png"));
            this.cancel = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/cancel.png"));
            this.player_playing = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/player_playing.png"));
            this.player_waiting = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/player_waiting.png"));
            this.restricted = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/restricted.png"));
            this.redo = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/redo.png"));
            this.retour = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/return.png"));
            this.save = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/save.png"));
            this.board = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/board.png"));
            this.black = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/black.png"));
            this.white = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/white.png"));
            this.empty = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/empty.png"));
            this.w_selected = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/white_selected.png"));
            this.b_selected = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/black_selected.png"));
            this.w_possible = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/white_possible.png"));
            this.b_possible = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/black_possible.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_LAG.class.toString());
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageIcon base = new ImageIcon(this.empty);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setLayout(new GridLayout(9, 9, 2, 2));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Coordinate c = new Coordinate(j, i);
                JButton b = new JButton(base);
                b.setBorder(BorderFactory.createEmptyBorder());
                b.setContentAreaFilled(false);
                b.addMouseListener(new LAG_MouseListener(c));
                b.setHorizontalTextPosition(JButton.CENTER);
                b.setVerticalTextPosition(JButton.CENTER);
                this.buttonmap[j][i] = b;
                b.setOpaque(false);
                panel.add(b);
            }
        }
        this.setLayout(null);
        this.grille = panel;
        this.grille.setOpaque(false);
        this.add(panel);

        this.undoB = new JButton(new ImageIcon(this.cancel));
        this.undoB.setBorder(BorderFactory.createEmptyBorder());
        this.undoB.setContentAreaFilled(false);
        this.undoB.setFocusPainted(false);
        this.undoB.addMouseListener(new LAG_UI_MouseListener("cancel"));
        this.add(this.undoB);

        this.retourB = new JButton(new ImageIcon(this.retour));
        this.retourB.setBorder(BorderFactory.createEmptyBorder());
        this.retourB.setContentAreaFilled(false);
        this.retourB.setFocusPainted(false);
        this.retourB.addMouseListener(new LAG_UI_MouseListener("return"));
        this.add(this.retourB);

        this.redoB = new JButton(new ImageIcon(this.redo));
        this.redoB.setBorder(BorderFactory.createEmptyBorder());
        this.redoB.setContentAreaFilled(false);
        this.redoB.setFocusPainted(false);
        this.redoB.addMouseListener(new LAG_UI_MouseListener("redo"));
        this.add(this.redoB);

        this.saveB = new JButton(new ImageIcon(this.save));
        this.saveB.setBorder(BorderFactory.createEmptyBorder());
        this.saveB.setContentAreaFilled(false);
        this.saveB.setFocusPainted(false);
        this.saveB.addMouseListener(new LAG_UI_MouseListener("save"));
        this.add(this.saveB);

        this.addComponentListener(this.listener);
    }

    public void initGame(GuiManager_INTERFACE pGui) {
        try {
            this.game = new Local_Avalam_Game((Main_Frame) pGui); // GridSizeException
        } catch (IOException | GridSizeException | GridCharException ex) {
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initGame(GuiManager_INTERFACE pGui, String[] player1, String[] player2) {
        try {

            AvalamColor c1 = AvalamColor.valueOf(player1[0].toUpperCase());
            AvalamColor c2 = AvalamColor.valueOf(player2[0].toUpperCase());

            Player p1 = new ControlledPlayer(player1[2], c1, Owner.PLAYER_1);
            switch (player1[1]) {
                case "player":
                    p1 = new ControlledPlayer(player1[2], c1, Owner.PLAYER_1);
                    break;
                case "ia_easy":
                    p1 = new AIPlayerEasy(player1[2], c1, Owner.PLAYER_1);
                    break;
                case "ia_mid":
                    p1 = new AIPlayerMedium(player1[2], c1, Owner.PLAYER_1);
                    break;
                case "ia_hard":
                    p1 = new AIPlayerHard(player1[2], c1, Owner.PLAYER_1);
                    break;
                case "ia_exp":
                    p1 = new AIPlayerMedium(player1[2], c1, Owner.PLAYER_1);
                    break;
                case "ia_leg":
                    p1 = new AIPlayerMedium(player1[2], c1, Owner.PLAYER_1);
                    break;
            }
            Player p2 = new ControlledPlayer(player2[2], c2, Owner.PLAYER_2);
            switch (player2[1]) {
                case "player":
                    p2 = new ControlledPlayer(player2[2], c2, Owner.PLAYER_2);
                    break;
                case "ia_easy":
                    p2 = new AIPlayerEasy(player2[2], c2, Owner.PLAYER_2);
                    break;
                case "ia_mid":
                    p2 = new AIPlayerMedium(player2[2], c2, Owner.PLAYER_2);
                    break;
                case "ia_hard":
                    p2 = new AIPlayerHard(player2[2], c2, Owner.PLAYER_2);
                    break;
                case "ia_exp":
                    p2 = new AIPlayerMedium(player2[2], c2, Owner.PLAYER_2);
                    break;
                case "ia_leg":
                    p2 = new AIPlayerMedium(player2[2], c2, Owner.PLAYER_2);
                    break;
            }
            String gName = "default";
            Level_Parser myParser = new Level_Parser(gName);
            Grid g = new Grid(myParser.readLevel(), gName); // IOException | GridSizeException | NumberFormatException
            this.game = new Local_Avalam_Game((Main_Frame) pGui, g, p1, p2, new Stack<>(), new Stack<>(), 0, 0); // GridSizeException
        } catch (IOException | GridSizeException | GridCharException ex) {
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteGame(){
        this.game.clean();
        this.game=null;
        System.gc();
    }

    public void start() {
        this.game.getTimer().start();
    }

    public void stop() {
        this.game.getTimer().stop();
    }

    public void save(String pSlotName) {
        SaveParser_Writer myParser = new SaveParser_Writer((Local_Avalam_Game) this.game, pSlotName);
        myParser.save();
    }

    public void load(GuiManager_INTERFACE mFrame,String pSlotName) {
        //Container mainFrame = this.getParent().getParent().getParent().getParent();
        SaveParser_Reader lParser = new SaveParser_Reader((Main_Frame)mFrame, pSlotName);
        this.game = new Local_Avalam_Game((Main_Frame)mFrame,lParser.getaGrid(),lParser.getaPlayer1(),lParser.getaPlayer2(),lParser.getaUndo(),lParser.getaRedo(),lParser.getaCurrentPlayer(),lParser.getaTurns());
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

    public JButton getSaveB() {
        return this.saveB;
    }

    public JPanel getGrille() {
        return this.grille;
    }

    public Game_INTERFACE getGame() {
        return this.game;
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
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);

        int scaleW = this.getWidth() / 8;
        int scaleH = 2 * (this.getHeight() / 3);
        if (this.player1IsPlaying) {
            g.drawImage(this.player_playing, 3, this.getHeight() / 5, scaleW, scaleH, null);
            g.drawImage(this.player_waiting, this.getWidth() - (scaleW + 3), this.getHeight() / 5, scaleW, scaleH, null);
        } else {
            g.drawImage(this.player_waiting, 3, this.getHeight() / 5, scaleW, scaleH, null);
            g.drawImage(this.player_playing, this.getWidth() - (scaleW + 3), this.getHeight() / 5, scaleW, scaleH, null);
        }
        int bScaleW = 2 * (this.getWidth() / 3);
        int bScaleH = 2 * (this.getHeight() / 3);
        g.drawImage(this.board, (this.getWidth() / 2) - (bScaleW / 2), this.getHeight() / 5, bScaleW, bScaleH, null);
        Grid gr = this.game.getGrid();
        Coordinate c = new Coordinate();
        ImageIcon wh = new ImageIcon(this.white);
        ImageIcon bl = new ImageIcon(this.black);
        ImageIcon em = new ImageIcon(this.empty);
        ImageIcon re = new ImageIcon(this.restricted);
        ImageIcon wsel = new ImageIcon(this.w_selected);
        ImageIcon bsel = new ImageIcon(this.b_selected);
        ImageIcon wpos = new ImageIcon(this.w_possible);
        ImageIcon bpos = new ImageIcon(this.b_possible);
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
                                    this.buttonmap[i][j].setOpaque(false);//setIcon(re);
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
                }
                this.buttonmap[i][j].setOpaque(false);
            }
        }
        if (this.callResize) {
            this.listener.componentResized(null);
            this.callResize = false;
        }
    }
}

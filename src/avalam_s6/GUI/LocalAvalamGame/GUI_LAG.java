/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.LocalAvalamGame;

import avalam_s6.Core.File_IO.Level_Parser;
import avalam_s6.Core.*;
import avalam_s6.Exceptions.GridSizeException;
import avalam_s6.GUI.HomePage.GUI_HomePage;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.Player.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.floor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author sazeratj
 */
public class GUI_LAG extends JPanel {

    private Game_INTERFACE game;
    String theme;
    boolean player1IsPlaying;

    private Image background, cancel, player_playing, player_waiting, redo, retour, save, board, black, white, empty;
    JButton[][] buttonmap = new JButton[9][9];

    /**
     * Constructor.
     */
    public GUI_LAG() {
        this("Default");
    }

    public GUI_LAG(String theme) {
        this.theme = theme;
        this.initComponents();
        this.player1IsPlaying = true;
    }

    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/background.png"));
            this.cancel = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/cancel.png"));
            this.player_playing = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/player_playing.png"));
            this.player_waiting = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/player_waiting.png"));
            this.redo = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/redo.png"));
            this.retour = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/return.png"));
            this.save = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/save.png"));
            this.board = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/board.png"));
            this.black = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/black.png"));
            this.white = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/white.png"));
            this.empty = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/empty.png"));
        } catch (Exception ex) {
            Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        Coordinate c = new Coordinate();
        ImageIcon base = new ImageIcon(empty);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setLayout(new GridLayout(9, 9, 4, 4));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                c.setX(i);
                c.setY(j);
                JButton b = new JButton(base);
                b.setBorder(BorderFactory.createEmptyBorder());
                b.setContentAreaFilled(false);
                b.addMouseListener(new LAG_MouseListener(c));
                b.setHorizontalTextPosition(JButton.CENTER);
                b.setVerticalTextPosition(JButton.CENTER);
                buttonmap[i][j] = b;
                panel.add(b);
            }
        }
        this.add(panel);
    }

    public void initGame() {
        try {
            Player p1 = new AIPlayerEasy("Jon Doe", Color.WHITE, Owner.PLAYER_1);
            Player p2 = new AIPlayerEasy("Bot_Frank", Color.BLACK, Owner.PLAYER_2);
            Level_Parser myParser = new Level_Parser("default");
            Grid g = new Grid(myParser.readLevel()); // IOException | GridSizeException | NumberFormatException
            Container mainFrame = this.getParent().getParent().getParent().getParent();
            System.out.println(mainFrame.toString());
            this.game = new Local_Avalam_Game(g, p1, p2, (Main_Frame) mainFrame); // GridSizeException
            Input.setInputGame(game);
        } catch (IOException | GridSizeException | NumberFormatException ex) {
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        this.game.getTimer().start();
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
        /*g.setColor(Color.blue);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());*/

        Grid gr = this.game.getGrid();
        Coordinate c = new Coordinate();
        ImageIcon wh = new ImageIcon(this.white);
        ImageIcon bl = new ImageIcon(this.black);
        ImageIcon em = new ImageIcon(this.empty);
        for (int i = 0; i < gr.getWidth(); i++) {
            for (int j = 0; j < gr.getHeight(); j++) {
                c.setX(i);
                c.setY(j);
                Cell ce = gr.getCellAt(c);
                switch (ce.getOwner()) {
                    case PLAYER_1:
                        buttonmap[i][j].setIcon(wh);
                        buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                        
                        //g.drawImage(this.white, i * 60, j * 60, null);
                        break;
                    case PLAYER_2: 
                        buttonmap[i][j].setIcon(bl);
                        buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                        //g.drawImage(this.black, i * 60, j * 60, null);

                        break;
                    case NO_OWNER:
                        buttonmap[i][j].setIcon(em);
                        buttonmap[i][j].setText("");
                        //g.drawImage(this.empty, i * 60, j * 60, null);

                        break;

                }
            }
        }
    }
}

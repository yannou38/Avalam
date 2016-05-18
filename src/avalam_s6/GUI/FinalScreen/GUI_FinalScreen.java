/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.FinalScreen;

import avalam_s6.Core.Cell;
import avalam_s6.Core.CellState;
import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Grid;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sazeratj
 */
public class GUI_FinalScreen extends JPanel {

    public String theme;
    private Image background, homeI, black, white, empty;
    public JLabel victoryText;
    public JButton home;
    public JPanel grille;

    private Grid finalGrid;
    JButton[][] buttonmap;

    public GUI_FinalScreen(String theme) {
        this.theme = theme;
        this.victoryText = new JLabel();
        this.finalGrid = null;
        this.buttonmap = new JButton[9][9];
        this.initComponents();
    }

    private void initComponents() {
        try {
            Font localFont = new Font("Arial", Font.PLAIN, 60);
            /* Chargement de la police */
            try {
                localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + this.theme + "/font/Gamaliel.otf"));
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
                /* Aggrandissement des bordures */
                this.victoryText.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
            } catch (IOException | FontFormatException ex) {
                throw (ex);
            }
            /* Chargement des images // du theme */
            this.background = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/final/background.jpg"));
            this.homeI = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/final/home.png"));
            this.black = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/board/black.png"));
            this.white = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/board/white.png"));
            this.empty = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/board/empty.png"));
            /* Application Police */
            this.victoryText.setFont(localFont.deriveFont(2 * 30f)); /* On peut appliquer un ratio a la police (ici 2) */

        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - " + GUI_FinalScreen.class.toString());
            Logger.getLogger(GUI_FinalScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageIcon base = new ImageIcon(this.empty);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setLayout(new GridLayout(9, 9, 2, 2));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JButton b = new JButton(base);
                b.setBorder(BorderFactory.createEmptyBorder());
                b.setContentAreaFilled(false);
                b.setHorizontalTextPosition(JButton.CENTER);
                b.setVerticalTextPosition(JButton.CENTER);
                this.buttonmap[j][i] = b;
                panel.add(b);
            }
        }
        this.grille = panel;
        this.add(this.grille);

        this.home = new JButton(new ImageIcon(this.homeI));
        this.home.setBorder(BorderFactory.createEmptyBorder());
        this.home.setContentAreaFilled(false);
        this.home.setFocusPainted(false);
        this.home.addMouseListener(new Final_MouseListener(this.theme));

        this.setLayout(null);
        this.add(this.victoryText);
        this.add(this.home);
        this.addComponentListener(new Final_AdapterListener(this));
    }

    public void setGrid(Grid g) {
        this.finalGrid = g;
    }

    public void setWinner(String p) {
        this.victoryText.setText(p + " a gagné!");
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);

        Grid gr = this.finalGrid;
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
                        this.buttonmap[i][j].setIcon(wh);
                        this.buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                        break;
                    case PLAYER_2:
                        this.buttonmap[i][j].setIcon(bl);
                        this.buttonmap[i][j].setText(Integer.toString(gr.getCellAt(c).getSize()));
                        break;
                    case NO_OWNER:
                        if (gr.getCellAt(c).getState() == CellState.RESTRICTED) {
                            this.buttonmap[i][j].setIcon(null);
                        } else {
                            this.buttonmap[i][j].setIcon(em);
                            this.buttonmap[i][j].setText("");
                        }
                        break;

                }
            }
        }

    }
}

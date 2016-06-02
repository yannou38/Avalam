/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.NewGame;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.HomePage.GUI_HomePage;
import avalam_s6.GUI.Main_Frame;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import static java.lang.Math.round;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Listener for the NewGame window
 * @author Team 7
 */
public class NewGameListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;
    private int playernum;
    private GUI_NewGame page;
    private final String type;

    /**
     * Constructor
     * @param buttonname
     * @param playernumber
     * @param page
     * @param type 
     */
    public NewGameListener(String buttonname, int playernumber, GUI_NewGame page, String type) {
        this.name = buttonname;
        this.playernum = playernumber;
        this.page = page;
        this.type = type;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/playerselect/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/playerselect/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + NewGameListener.class.toString());
            Logger.getLogger(NewGameListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_NewGame newGame = ((GUI_NewGame) source.getParent());
        Main_Frame mainFrame = ((Main_Frame) newGame.getParent().getParent().getParent().getParent());
        switch (this.name) {
            case "start":
                String[] p1 = this.page.loadP1Settings();
                String[] p2 = this.page.loadP2Settings();
                String gridName = this.page.loadGridName();
                ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));
                mainFrame.initGame(p1, p2, gridName);
                mainFrame.startGame();
                break;
            case "home":
                ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));
                newGame.back();
                break;
            case "sup":
                switch (this.type) {
                    case "supcolor":
                        this.page.rightColorAI(playernum);
                        break;
                    case "supia":
                        this.page.rightAI(playernum);
                        break;
                    case "supgrille":
                        this.page.rightGrille();
                        break;
                }
                break;
            case "prec":
                switch (this.type) {
                    case "preccolor":
                        this.page.leftColorAI(playernum);
                        break;
                    case "precia":
                        this.page.leftAI(playernum);
                        break;
                    case "precgrille":
                        this.page.leftGrille();
                        break;
                }
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //replace the icon with another
        double ratioW = (double) this.page.getWidth() / (double) 1920;
        double ratioH = (double) this.page.getHeight() / (double) 1080;
        Image newimg = this.icon.getScaledInstance(((int) round(icon.getWidth(null) * ratioW)), ((int) round(icon.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        double ratioW = (double) this.page.getWidth() / (double) 1920;
        double ratioH = (double) this.page.getHeight() / (double) 1080;
        Image newimg = this.iconbase.getScaledInstance(((int) round(iconbase.getWidth(null) * ratioW)), ((int) round(iconbase.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

}

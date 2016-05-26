/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.NewGame;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author loic
 */
public class NewGameListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;
    private int playernum;
    private GUI_NewGame page;
    private final String type;

    public NewGameListener(String buttonname, int playernumber, GUI_NewGame page, String type) {
        this.name = buttonname;
        this.playernum = playernumber;
        this.page = page;
        this.type = type;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/playerselect/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/playerselect/" + this.name + ".png"));
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
        ((JButton) e.getSource()).setIcon(new ImageIcon(this.icon));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));
    }

}

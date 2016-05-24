/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.LocalAvalamGame;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.Core.Local_Avalam_Game;
import avalam_s6.GUI.HomePage.GUI_HomePage;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author ducruyy
 */
public class LAG_UI_MouseListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;

    public LAG_UI_MouseListener(String buttonname) {
        this.name = buttonname;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/board/" + this.name + ".png"));
        } catch (Exception ex) {
            Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_LAG lag = ((GUI_LAG) source.getParent());
        Main_Frame mainFrame = ((Main_Frame) lag.getParent().getParent().getParent().getParent());
        Local_Avalam_Game game = ((Local_Avalam_Game) lag.getGame());
        switch (this.name) {
            case "return":
                lag.back();
                break;
            case "redo":
                if (game.getCancelled_moves().size() > 0) {
                    game.redo();
                    game.changeNbTurns(1);
                    if (!lag.getUndoB().isEnabled()) {
                        lag.getUndoB().setEnabled(true);
                    }
                    if (game.getCancelled_moves().isEmpty()) {
                        lag.getRedoB().setEnabled(false);
                    } else if (game.getCurrentPlayer().isAI()) {
                        game.redo();
                        game.changeNbTurns(1);
                    }
                }
                break;
            case "cancel":
                if (game.getHistory().size() > 0) {
                    game.undo();
                    game.changeNbTurns(-1);
                    if (!lag.getRedoB().isEnabled()) {
                        lag.getRedoB().setEnabled(true);
                    }
                    if (game.getHistory().isEmpty()) {
                        lag.getUndoB().setEnabled(false);
                    } else if (game.getCurrentPlayer().isAI()) {
                        game.undo();
                        game.changeNbTurns(-1);
                    }
                }
                break;
            case "save":
                mainFrame.setwState(WindowState.SAVE);
                break;
        }
        ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));

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

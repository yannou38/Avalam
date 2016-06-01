/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.LocalAvalamGame;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.Core.Globals.SoundEngine;
import avalam_s6.Core.Local_Avalam_Game;
import avalam_s6.GUI.HomePage.GUI_HomePage;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import static java.lang.Math.round;
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
    private GUI_LAG page;

    public LAG_UI_MouseListener(String buttonname, GUI_LAG page) {
        this.name = buttonname;
        this.page = page;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + this.name + ".png"));
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
            case "home":
                lag.back();
                break;
            case "redo":
                if (game.getCancelled_moves().size() > 0) { // REDO Possible
                    game.redo();
                    game.changeNbTurns(1); // Action Redo
                    if (!lag.getUndoB().isEnabled()) { // Affiche UNDO Possible
                        lag.getUndoB().setEnabled(true);
                    }
                    if (game.getCancelled_moves().isEmpty()) { // Retire REDO au besoin
                        lag.getRedoB().setEnabled(false);
                    } else if (!game.isPaused() && game.getCurrentPlayer().isAI()) { // Redo encore si ennemi = IA et le jeu n'est pas en pause
                        game.redo();
                        game.changeNbTurns(1);
                    }
                }
                game.updateTitle();
                break;
            case "cancel":
                if (game.getHistory().size() > 0) { // Undo Possible
                    game.undo();
                    game.changeNbTurns(-1); // Action Undo
                    if (!lag.getRedoB().isEnabled()) {  // Affichage Undo
                        lag.getRedoB().setEnabled(true);
                    }
                    if (game.getHistory().isEmpty()) {  // Retire Undo si besoin
                        lag.getUndoB().setEnabled(false);
                    } else if (!game.isPaused() && game.getCurrentPlayer().isAI()) { // Undo encore si on joue contre une IA
                        game.undo();
                        game.changeNbTurns(-1);
                    }
                }
                game.updateTitle();
                break;
            case "save":
                mainFrame.setwState(WindowState.SAVE);
                break;
            case "fullscreen":
                mainFrame.toggleWRM();
                break;
            case "mute":
                SoundEngine.toggleMute();
                break;
            case "help":
                lag.setHint(game.getHint());
                break;
            case "play":
                game.togglePause();
                this.page.repaint();
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
        JButton source = (JButton) e.getSource();
        GUI_LAG lag = ((GUI_LAG) source.getParent());
        double ratioW = (double) lag.getWidth() / (double) 1920;
        double ratioH = (double) lag.getHeight() / (double) 1080;
        Image newimg = this.icon.getScaledInstance(((int) round(icon.getWidth(null) * ratioW)), ((int) round(icon.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));

        if (this.name.equals("play")) {
            if (((Local_Avalam_Game) this.page.getGame()).isPaused()) {
                newimg = this.page.getPlay().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
            } else {
                newimg = this.page.getPause().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
            }
            this.page.getPlayB().setIcon(new ImageIcon(newimg));

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        JButton source = (JButton) e.getSource();
        GUI_LAG lag = ((GUI_LAG) source.getParent());
        double ratioW = (double) lag.getWidth() / (double) 1920;
        double ratioH = (double) lag.getHeight() / (double) 1080;
        Image newimg = this.iconbase.getScaledInstance(((int) round(iconbase.getWidth(null) * ratioW)), ((int) round(iconbase.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));

        if (this.name.equals("play")) {
            if (((Local_Avalam_Game) this.page.getGame()).isPaused()) {
                newimg = this.page.getPlay().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
            } else {
                newimg = this.page.getPause().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
            }
            this.page.getPlayB().setIcon(new ImageIcon(newimg));

        }
    }

}

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
import java.io.IOException;
import static java.lang.Math.round;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Listener on the board panel.
 *
 * @author Team 7
 */
public class LAG_UI_MouseListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;
    private GUI_LAG page;

    /**
     * Constructor
     *
     * @param buttonname the button name, also used for image loading
     * @param page the board panel
     */
    public LAG_UI_MouseListener(String buttonname, GUI_LAG page) {
        this.name = buttonname;
        this.page = page;
        if ("play".equals(this.name)) {
            try {
                this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/pause_h.png"));
                this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/pause.png"));
            } catch (Exception ex) {
                Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("mute".equals(this.name)) {
            String muteS = SetupManager.getElement("Son").equals("Non") ? "unmute" : "mute";
            try {
                this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + muteS + "_h.png"));
                this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + muteS + ".png"));
            } catch (Exception ex) {
                Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + this.name + "_h.png"));
                this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/" + this.name + ".png"));
            } catch (Exception ex) {
                Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Handler for the mouse clicked event.
     *
     * @param e the mouse event
     */
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
                try {
                    if (SoundEngine.isMuted()) {
                        this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/unmute_h.png"));
                        this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/unmute.png"));
                    } else {
                        this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/mute_h.png"));
                        this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/mute.png"));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LAG_UI_MouseListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.page.setMuteCall(true);
                this.page.setMute(this.icon);
                break;
            case "help":
                lag.setHint(game.getHint());
                break;
            case "play":
                game.togglePause();
                try {
                    if (((Local_Avalam_Game) this.page.getGame()).isPaused()) {
                        this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/play_h.png"));
                        this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/play.png"));
                    } else {
                        this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/pause_h.png"));
                        this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/board/pause.png"));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LAG_UI_MouseListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.page.setCallPause(true);
                this.page.setPlay(this.icon);

                this.page.repaint();

                break;
        }

    }

    /**
     * Do nothing.
     *
     * @param e the mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Do nothing.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Handler for hovering on the button.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        //replace the icon with another

        if (this.name.equals("play")) {
            this.page.setPlay(this.icon);
        }

        if (this.name.equals("mute")) {
            this.page.setMute(this.icon);
        }

        JButton source = (JButton) e.getSource();
        GUI_LAG lag = ((GUI_LAG) source.getParent());
        double ratioW = (double) lag.getWidth() / (double) 1920;
        double ratioH = (double) lag.getHeight() / (double) 1080;
        Image newimg = this.icon.getScaledInstance(((int) round(icon.getWidth(null) * ratioW)), ((int) round(icon.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

    /**
     * Handler for hovering on the button.
     *
     * @param e the mouse event
     */
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
            this.page.setPlay(this.iconbase);
        }

        if (this.name.equals("mute")) {
            this.page.setMute(this.iconbase);
        }
    }

}

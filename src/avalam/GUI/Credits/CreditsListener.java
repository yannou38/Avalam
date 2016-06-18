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
package avalam.GUI.Credits;

import avalam.Core.Globals.SetupManager;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import static java.lang.Math.round;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Listener on the credits panel.
 *
 * @author Team 7
 */
public class CreditsListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;

    /**
     * Contructor.
     *
     * @param buttonname the button name. Also used for image loading.
     */
    public CreditsListener(String buttonname) {
        this.name = buttonname;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/credits/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/credits/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + CreditsListener.class.toString());
            Logger.getLogger(CreditsListener.class.getName()).log(Level.SEVERE, null, ex);
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
        GUI_Credits credits = ((GUI_Credits) source.getParent());
        credits.back();
        ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));
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
        JButton source = (JButton) e.getSource();
        GUI_Credits credits = ((GUI_Credits) source.getParent());
        double ratioW = (double) credits.getWidth() / (double) 1920;
        double ratioH = (double) credits.getHeight() / (double) 1080;
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
        JButton source = (JButton) e.getSource();
        GUI_Credits credits = ((GUI_Credits) source.getParent());
        double ratioW = (double) credits.getWidth() / (double) 1920;
        double ratioH = (double) credits.getHeight() / (double) 1080;
        Image newimg = this.iconbase.getScaledInstance(((int) round(iconbase.getWidth(null) * ratioW)), ((int) round(iconbase.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

}

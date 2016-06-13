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
package avalam_s6.GUI.Rules;

import avalam_s6.Core.Globals.SetupManager;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import static java.lang.Math.round;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Listener of the Rules Page
 * @author Team 7
 */
public class RulesListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;

    /**
     * Constructor
     * @param buttonname Name of the button on which action is provided.
     */
    public RulesListener(String buttonname) {
        this.name = buttonname;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/rules/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/rules/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + RulesListener.class.toString());
            Logger.getLogger(RulesListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Rules rules = ((GUI_Rules) source.getParent());
        rules.back();
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
        JButton source = (JButton) e.getSource();
        GUI_Rules rules = ((GUI_Rules) source.getParent());
        double ratioW = (double) rules.getWidth() / (double) 1920;
        double ratioH = (double) rules.getHeight() / (double) 1080;
        Image newimg = this.icon.getScaledInstance(((int) round(icon.getWidth(null) * ratioW)), ((int) round(icon.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        JButton source = (JButton) e.getSource();
        GUI_Rules rules = ((GUI_Rules) source.getParent());
        double ratioW = (double) rules.getWidth() / (double) 1920;
        double ratioH = (double) rules.getHeight() / (double) 1080;
        Image newimg = this.iconbase.getScaledInstance(((int) round(iconbase.getWidth(null) * ratioW)), ((int) round(iconbase.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

}

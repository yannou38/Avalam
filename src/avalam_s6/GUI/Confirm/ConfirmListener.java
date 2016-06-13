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
package avalam_s6.GUI.Confirm;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Credits.CreditsListener;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import static java.lang.Math.round;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Listener of the confirm page.
 * @author Team 7
 */
public class ConfirmListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;

    /**
     * Constructor
     * @param buttonname the name of the button to listen 
     */
    public ConfirmListener(String buttonname) {
        this.name = buttonname;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/confirm/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/confirm/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + CreditsListener.class.toString());
            Logger.getLogger(CreditsListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Confirm page = ((GUI_Confirm) source.getParent());
        switch (this.name) {
            case "yes":
                page.doAction();
                break;
            case "no":
                page.cancelAction();
                break;
        }
        double ratioW = (double) page.getWidth() / (double) 1920;
        double ratioH = (double) page.getHeight() / (double) 1080;
        Image newimg = this.iconbase.getScaledInstance(((int) round(iconbase.getWidth(null) * ratioW)), ((int) round(iconbase.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        source.setIcon(new ImageIcon(newimg));
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Confirm page = ((GUI_Confirm) source.getParent());
        double ratioW = (double) page.getWidth() / (double) 1920;
        double ratioH = (double) page.getHeight() / (double) 1080;
        Image newimg = this.icon.getScaledInstance(((int) round(icon.getWidth(null) * ratioW)), ((int) round(icon.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Confirm page = ((GUI_Confirm) source.getParent());
        double ratioW = (double) page.getWidth() / (double) 1920;
        double ratioH = (double) page.getHeight() / (double) 1080;
        Image newimg = this.iconbase.getScaledInstance(((int) round(iconbase.getWidth(null) * ratioW)), ((int) round(iconbase.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

}

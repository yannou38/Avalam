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
package avalam_s6.GUI.HomePage;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import static java.lang.Math.round;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Team 7
 */
public class HomePageListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;

    /**
     * Constructor
     * @param buttonname is a string to determine which button is focused
     */
    public HomePageListener(String buttonname) {
        this.name = buttonname;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/main/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/main/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + HomePageListener.class.toString());
            Logger.getLogger(HomePageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_HomePage homePage = ((GUI_HomePage) source.getParent());
        Main_Frame mainFrame = ((Main_Frame) homePage.getParent().getParent().getParent().getParent());
        switch (this.name) {
            case "quit":
                mainFrame.setConfirmQuitter();
                mainFrame.setwState(WindowState.YESNO);                
                break;
            case "quickgame":
                mainFrame.initGame();
                mainFrame.startGame();
                break;
            case "customgame":
                mainFrame.setwState(WindowState.PLAYERSELECT);
                break;
            case "options":
                mainFrame.setwState(WindowState.SETTINGS);
                break;
            case "load":
                mainFrame.setwState(WindowState.LOAD);
                break;
            case "rules":
                mainFrame.setwState(WindowState.RULES);
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
        JButton source = (JButton) e.getSource();
        GUI_HomePage homePage = ((GUI_HomePage) source.getParent());
        double ratioW = (double) homePage.getWidth() / (double) 1920;
        double ratioH = (double) homePage.getHeight() / (double) 1080;
        Image newimg = this.icon.getScaledInstance(((int) round(icon.getWidth(null) * ratioW)), ((int) round(icon.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        JButton source = (JButton) e.getSource();
        GUI_HomePage homePage = ((GUI_HomePage) source.getParent());
        double ratioW = (double) homePage.getWidth() / (double) 1920;
        double ratioH = (double) homePage.getHeight() / (double) 1080;
        Image newimg = this.iconbase.getScaledInstance(((int) round(iconbase.getWidth(null) * ratioW)), ((int) round(iconbase.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

}

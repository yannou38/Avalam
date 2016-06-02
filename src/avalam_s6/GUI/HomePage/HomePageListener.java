/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author ducruyy
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
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/main/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/main/" + this.name + ".png"));
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

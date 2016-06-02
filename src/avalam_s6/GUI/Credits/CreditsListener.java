/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Credits;

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
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/credits/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/credits/" + this.name + ".png"));
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

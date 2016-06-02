/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Credits;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

/**
 * Listener that place and resize everything in the panel.
 *
 * @author Team 7
 */
public class CreditsAdapterListener implements ComponentListener {

    private final GUI_Credits cr;

    /**
     * Constructor.
     *
     * @param credits the credits panel
     */
    public CreditsAdapterListener(GUI_Credits credits) {
        this.cr = credits;
    }

    /**
     * Resize and place the component where they should be.
     *
     * @param e the component event
     */
    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.cr.getInsets();
        Image newimg;
        //1280*720 => taille de base
        double ratioW = (double) this.cr.getWidth() / (double) 1920;
        double ratioH = (double) this.cr.getHeight() / (double) 1080;

        newimg = this.cr.getReturnI().getScaledInstance(((int) round(263 * ratioW)), ((int) round(123 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.cr.getRetour().setIcon(new ImageIcon(newimg));
        this.cr.getRetour().setBounds((int) round((20 + insets.left) * ratioW), ((int) round((930 + insets.top) * ratioH)), (int) round(263 * ratioW), (int) round(123 * ratioH));
        this.cr.getRetour().setSize((int) round(263 * ratioW), (int) round(123 * ratioH));
    }

    /**
     * Do nothing.
     *
     * @param e the component event
     */
    @Override
    public void componentMoved(ComponentEvent e) {
    }

    /**
     * Do nothing.
     *
     * @param e the component event
     */
    @Override
    public void componentShown(ComponentEvent e) {
    }

    /**
     * Do nothing.
     *
     * @param e the component event
     */
    @Override
    public void componentHidden(ComponentEvent e) {
    }

}

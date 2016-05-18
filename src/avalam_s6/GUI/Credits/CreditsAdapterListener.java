/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Credits;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.*;
import static java.lang.Math.round;

/**
 *
 * @author loic
 */
public class CreditsAdapterListener implements ComponentListener {

    GUI_Credits cr;

    public CreditsAdapterListener(GUI_Credits credits) {
        this.cr = credits;
    }

    //JButton player, prec, sup, load, retour, start;
    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = cr.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) cr.getWidth() / (double) 1280;
        double ratioH = (double) cr.getHeight() / (double) 720;
        Dimension size = cr.retour.getPreferredSize();
        cr.retour.setBounds((int) round((20 + insets.left) * ratioW), ((int) round((625 + insets.top) * ratioH)), size.width, size.height);
        cr.retour.setSize((int) round(cr.retour.getWidth() * ratioW), (int) round(cr.retour.getHeight() * ratioH));
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

}

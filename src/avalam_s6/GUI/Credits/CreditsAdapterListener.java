/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Credits;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

/**
 *
 * @author loic
 */
public class CreditsAdapterListener implements ComponentListener {

    private final GUI_Credits cr;

    public CreditsAdapterListener(GUI_Credits credits) {
        this.cr = credits;
    }

    //JButton player, prec, sup, load, retour, start;
    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.cr.getInsets();
        Image newimg;
        //1280*720 => taille de base
        double ratioW = (double) this.cr.getWidth() / (double) 1920;
        double ratioH = (double) this.cr.getHeight() / (double) 1080;
        
        newimg = this.cr.getReturnI().getScaledInstance(((int) round(263 * ratioW)), ((int) round(123 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.cr.getRetour().setIcon(new ImageIcon(newimg));
        this.cr.getRetour().setBounds((int) round((20 + insets.left) * ratioW), ((int) round((930 + insets.top) * ratioH)), (int) round(263 * ratioW), (int) round(123*ratioH));
        this.cr.getRetour().setSize((int) round(263 * ratioW), (int) round(123 * ratioH));    
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

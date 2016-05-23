/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Rules;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.*;
import static java.lang.Math.round;

/**
 *
 * @author loic
 */
public class RulesAdapterListener implements ComponentListener {

    private final GUI_Rules cr;

    public RulesAdapterListener(GUI_Rules credits) {
        this.cr = credits;
    }

    //JButton player, prec, sup, load, retour, start;
    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.cr.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) this.cr.getWidth() / (double) 1280;
        double ratioH = (double) this.cr.getHeight() / (double) 720;
        Dimension size = this.cr.getRetour().getPreferredSize();
        this.cr.getRetour().setBounds((int) round((20 + insets.left) * ratioW), ((int) round((625 + insets.top) * ratioH)), size.width, size.height);
        this.cr.getRetour().setSize((int) round(this.cr.getRetour().getWidth() * ratioW), (int) round(this.cr.getRetour().getHeight() * ratioH));
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

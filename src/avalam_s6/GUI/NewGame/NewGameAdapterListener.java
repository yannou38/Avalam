/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.NewGame;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.*;
import static java.lang.Math.round;

/**
 *
 * @author loic
 */
public class NewGameAdapterListener implements ComponentListener {

    private GUI_NewGame ng;

    public NewGameAdapterListener(GUI_NewGame newgame) {
        this.ng = newgame;
    }

    //JButton player, prec, sup, load, retour, start;
    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.ng.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) this.ng.getWidth() / (double) 1280;
        double ratioH = (double) this.ng.getHeight() / (double) 720;
        Dimension size = this.ng.getRetour().getPreferredSize();
        this.ng.getRetour().setBounds((int) round((3 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getRetour().setSize((int) round(this.ng.getRetour().getWidth() * ratioW), (int) round(this.ng.getRetour().getHeight() * ratioH));

        size = this.ng.getStart().getPreferredSize();
        this.ng.getStart().setBounds((int) round((290 + insets.left) * ratioW), ((int) round((600 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getStart().setSize((int) round(this.ng.getStart().getWidth() * ratioW), (int) round(this.ng.getStart().getHeight() * ratioH));
        
        size = this.ng.getP1button().getPreferredSize();
        this.ng.getP1button().setBounds((int) round((290 + insets.left) * ratioW), ((int) round((300 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getP1button().setSize((int) round(this.ng.getP1button().getWidth() * ratioW), (int) round(this.ng.getP1button().getHeight() * ratioH));
        
        size = this.ng.getP2button().getPreferredSize();
        this.ng.getP2button().setBounds((int) round((590 + insets.left) * ratioW), ((int) round((300 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getP2button().setSize((int) round(this.ng.getP2button().getWidth() * ratioW), (int) round(this.ng.getP2button().getHeight() * ratioH));
        
        
        size = this.ng.getPrec1().getPreferredSize();
        this.ng.getPrec1().setBounds((int) round((290 + insets.left) * ratioW), ((int) round((300 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPrec1().setSize((int) round(this.ng.getPrec1().getWidth() * ratioW), (int) round(this.ng.getPrec1().getHeight() * ratioH));
        size = this.ng.getSup1().getPreferredSize();
        this.ng.getSup1().setBounds((int) round((350 + insets.left) * ratioW), ((int) round((300 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSup1().setSize((int) round(this.ng.getSup1().getWidth() * ratioW), (int) round(this.ng.getSup1().getHeight() * ratioH));
        
        
        size = this.ng.getPrec2().getPreferredSize();
        this.ng.getPrec2().setBounds((int) round((590 + insets.left) * ratioW), ((int) round((300 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPrec2().setSize((int) round(this.ng.getPrec2().getWidth() * ratioW), (int) round(this.ng.getPrec2().getHeight() * ratioH));
        size = this.ng.getSup2().getPreferredSize();
        this.ng.getSup2().setBounds((int) round((650 + insets.left) * ratioW), ((int) round((300 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSup2().setSize((int) round(this.ng.getSup2().getWidth() * ratioW), (int) round(this.ng.getSup2().getHeight() * ratioH));
        
        
                
                
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

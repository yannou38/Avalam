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
        Dimension size = this.ng.getPlayer1().getPreferredSize();
        this.ng.getPlayer1().setBounds((int) round((300 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPlayer1().setSize((int) round(this.ng.getPlayer1().getWidth() * ratioW), (int) round(this.ng.getPlayer1().getHeight() * ratioH));
        
        this.ng.getAie1().setBounds((int) round((300 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getAie1().setSize((int) round(this.ng.getAie1().getWidth() * ratioW), (int) round(this.ng.getAie1().getHeight() * ratioH));
        
        this.ng.getAim1().setBounds((int) round((300 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getAim1().setSize((int) round(this.ng.getAim1().getWidth() * ratioW), (int) round(this.ng.getAim1().getHeight() * ratioH));
        
        this.ng.getAih1().setBounds((int) round((300 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getAih1().setSize((int) round(this.ng.getAih1().getWidth() * ratioW), (int) round(this.ng.getAih1().getHeight() * ratioH));

        size = this.ng.getPrec1().getPreferredSize();
        this.ng.getPrec1().setBounds((int) round((250 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPrec1().setSize((int) round(this.ng.getPrec1().getWidth() * ratioW), (int) round(this.ng.getPrec1().getHeight() * ratioH));

        size = this.ng.getSup1().getPreferredSize();
        this.ng.getSup1().setBounds((int) round((470 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSup1().setSize((int) round(this.ng.getSup1().getWidth() * ratioW), (int) round(this.ng.getSup1().getHeight() * ratioH));
        
        
        size = this.ng.getPlayer2().getPreferredSize();
        this.ng.getPlayer2().setBounds((int) round((760 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPlayer2().setSize((int) round(this.ng.getPlayer2().getWidth() * ratioW), (int) round(this.ng.getPlayer2().getHeight() * ratioH));
        
        this.ng.getAie2().setBounds((int) round((300 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getAie2().setSize((int) round(this.ng.getAie2().getWidth() * ratioW), (int) round(this.ng.getAie2().getHeight() * ratioH));
        
        this.ng.getAim2().setBounds((int) round((300 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getAim2().setSize((int) round(this.ng.getAim2().getWidth() * ratioW), (int) round(this.ng.getAim2().getHeight() * ratioH));
        
        this.ng.getAih2().setBounds((int) round((300 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getAih2().setSize((int) round(this.ng.getAih2().getWidth() * ratioW), (int) round(this.ng.getAih2().getHeight() * ratioH));

        size = this.ng.getPrec2().getPreferredSize();
        this.ng.getPrec2().setBounds((int) round((710 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPrec2().setSize((int) round(this.ng.getPrec2().getWidth() * ratioW), (int) round(this.ng.getPrec2().getHeight() * ratioH));

        size = this.ng.getSup2().getPreferredSize();
        this.ng.getSup2().setBounds((int) round((930 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSup2().setSize((int) round(this.ng.getSup2().getWidth() * ratioW), (int) round(this.ng.getSup2().getHeight() * ratioH));
        

        size = this.ng.getLoad().getPreferredSize();
        this.ng.getLoad().setBounds((int) round((1102 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getLoad().setSize((int) round(this.ng.getLoad().getWidth() * ratioW), (int) round(this.ng.getLoad().getHeight() * ratioH));

        size = this.ng.getRetour().getPreferredSize();
        this.ng.getRetour().setBounds((int) round((3 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getRetour().setSize((int) round(this.ng.getRetour().getWidth() * ratioW), (int) round(this.ng.getRetour().getHeight() * ratioH));

        size = this.ng.getStart().getPreferredSize();
        this.ng.getStart().setBounds((int) round((290 + insets.left) * ratioW), ((int) round((600 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getStart().setSize((int) round(this.ng.getStart().getWidth() * ratioW), (int) round(this.ng.getStart().getHeight() * ratioH));
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

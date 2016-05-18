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

    GUI_NewGame ng;

    public NewGameAdapterListener(GUI_NewGame newgame) {
        this.ng = newgame;
    }

    //JButton player, prec, sup, load, retour, start;
    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = ng.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) ng.getWidth() / (double) 1280;
        double ratioH = (double) ng.getHeight() / (double) 720;
        Dimension size = ng.player1.getPreferredSize();
        ng.player1.setBounds((int) round((300 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        ng.player1.setSize((int) round(ng.player1.getWidth() * ratioW), (int) round(ng.player1.getHeight() * ratioH));

        size = ng.prec1.getPreferredSize();
        ng.prec1.setBounds((int) round((250 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        ng.prec1.setSize((int) round(ng.prec1.getWidth() * ratioW), (int) round(ng.prec1.getHeight() * ratioH));

        size = ng.sup1.getPreferredSize();
        ng.sup1.setBounds((int) round((470 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        ng.sup1.setSize((int) round(ng.sup1.getWidth() * ratioW), (int) round(ng.sup1.getHeight() * ratioH));
        
        
        size = ng.player2.getPreferredSize();
        ng.player2.setBounds((int) round((760 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        ng.player2.setSize((int) round(ng.player2.getWidth() * ratioW), (int) round(ng.player2.getHeight() * ratioH));

        size = ng.prec2.getPreferredSize();
        ng.prec2.setBounds((int) round((710 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        ng.prec2.setSize((int) round(ng.prec2.getWidth() * ratioW), (int) round(ng.prec2.getHeight() * ratioH));

        size = ng.sup2.getPreferredSize();
        ng.sup2.setBounds((int) round((930 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        ng.sup2.setSize((int) round(ng.sup2.getWidth() * ratioW), (int) round(ng.sup2.getHeight() * ratioH));
        

        size = ng.load.getPreferredSize();
        ng.load.setBounds((int) round((1102 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        ng.load.setSize((int) round(ng.load.getWidth() * ratioW), (int) round(ng.load.getHeight() * ratioH));

        size = ng.retour.getPreferredSize();
        ng.retour.setBounds((int) round((3 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        ng.retour.setSize((int) round(ng.retour.getWidth() * ratioW), (int) round(ng.retour.getHeight() * ratioH));

        size = ng.start.getPreferredSize();
        ng.start.setBounds((int) round((290 + insets.left) * ratioW), ((int) round((600 + insets.top) * ratioH)), size.width, size.height);
        ng.start.setSize((int) round(ng.start.getWidth() * ratioW), (int) round(ng.start.getHeight() * ratioH));
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

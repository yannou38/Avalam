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
        //1920-1080 => taille de base
        double ratioW = (double) this.ng.getWidth() / (double) 1920;
        double ratioH = (double) this.ng.getHeight() / (double) 1080;
        Dimension size = this.ng.getRetour().getPreferredSize();
        this.ng.getRetour().setBounds((int) round((25 + insets.left) * ratioW), ((int) round((934 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getRetour().setSize((int) round(this.ng.getRetour().getWidth() * ratioW), (int) round(this.ng.getRetour().getHeight() * ratioH));

        size = this.ng.getStart().getPreferredSize();
        this.ng.getStart().setBounds((int) round((449 + insets.left) * ratioW), ((int) round((904 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getStart().setSize((int) round(this.ng.getStart().getWidth() * ratioW), (int) round(this.ng.getStart().getHeight() * ratioH));

        size = this.ng.getPreccolor1().getPreferredSize();
        this.ng.getPreccolor1().setBounds((int) round((15 + insets.left) * ratioW), ((int) round((389 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPreccolor1().setSize((int) round(this.ng.getPreccolor1().getWidth() * ratioW), (int) round(this.ng.getPreccolor1().getHeight() * ratioH));
        size = this.ng.getP1color().getPreferredSize();
        this.ng.getP1color().setBounds((int) round((129 + insets.left) * ratioW), ((int) round((394 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getP1color().setSize((int) round(this.ng.getP1color().getWidth() * ratioW), (int) round(this.ng.getP1color().getHeight() * ratioH));
        size = this.ng.getSupcolor1().getPreferredSize();
        this.ng.getSupcolor1().setBounds((int) round((536 + insets.left) * ratioW), ((int) round((387 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSupcolor1().setSize((int) round(this.ng.getSupcolor1().getWidth() * ratioW), (int) round(this.ng.getSupcolor1().getHeight() * ratioH));

        size = this.ng.getPreccolor2().getPreferredSize();
        this.ng.getPreccolor2().setBounds((int) round((1267 + insets.left) * ratioW), ((int) round((389 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPreccolor2().setSize((int) round(this.ng.getPreccolor2().getWidth() * ratioW), (int) round(this.ng.getPreccolor2().getHeight() * ratioH));
        size = this.ng.getP2color().getPreferredSize();
        this.ng.getP2color().setBounds((int) round((1381 + insets.left) * ratioW), ((int) round((394 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getP2color().setSize((int) round(this.ng.getP2color().getWidth() * ratioW), (int) round(this.ng.getP2color().getHeight() * ratioH));
        size = this.ng.getSupcolor2().getPreferredSize();
        this.ng.getSupcolor2().setBounds((int) round((1788 + insets.left) * ratioW), ((int) round((387 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSupcolor2().setSize((int) round(this.ng.getSupcolor2().getWidth() * ratioW), (int) round(this.ng.getSupcolor2().getHeight() * ratioH));

        size = this.ng.getPrec1().getPreferredSize();
        this.ng.getPrec1().setBounds((int) round((15 + insets.left) * ratioW), ((int) round((577 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPrec1().setSize((int) round(this.ng.getPrec1().getWidth() * ratioW), (int) round(this.ng.getPrec1().getHeight() * ratioH));
        size = this.ng.getP1button().getPreferredSize();
        this.ng.getP1button().setBounds((int) round((129 + insets.left) * ratioW), ((int) round((581 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getP1button().setSize((int) round(this.ng.getP1button().getWidth() * ratioW), (int) round(this.ng.getP1button().getHeight() * ratioH));
        size = this.ng.getSup1().getPreferredSize();
        this.ng.getSup1().setBounds((int) round((536 + insets.left) * ratioW), ((int) round((575 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSup1().setSize((int) round(this.ng.getSup1().getWidth() * ratioW), (int) round(this.ng.getSup1().getHeight() * ratioH));

        size = this.ng.getPrec2().getPreferredSize();
        this.ng.getPrec2().setBounds((int) round((1267 + insets.left) * ratioW), ((int) round((577 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPrec2().setSize((int) round(this.ng.getPrec2().getWidth() * ratioW), (int) round(this.ng.getPrec2().getHeight() * ratioH));
        size = this.ng.getP2button().getPreferredSize();
        this.ng.getP2button().setBounds((int) round((1381 + insets.left) * ratioW), ((int) round((581 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getP2button().setSize((int) round(this.ng.getP2button().getWidth() * ratioW), (int) round(this.ng.getP2button().getHeight() * ratioH));
        size = this.ng.getSup2().getPreferredSize();
        this.ng.getSup2().setBounds((int) round((1788 + insets.left) * ratioW), ((int) round((575 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSup2().setSize((int) round(this.ng.getSup2().getWidth() * ratioW), (int) round(this.ng.getSup2().getHeight() * ratioH));

        size = this.ng.getName1().getPreferredSize();
        this.ng.getName1().setBounds((int) round((180 + insets.left) * ratioW), ((int) round((776 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getName1().setSize((int) round(this.ng.getName1().getWidth() * ratioW), (int) round(this.ng.getName1().getHeight() * ratioH));
        size = this.ng.getName2().getPreferredSize();
        this.ng.getName2().setBounds((int) round((1432 + insets.left) * ratioW), ((int) round((776 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getName2().setSize((int) round(this.ng.getName2().getWidth() * ratioW), (int) round(this.ng.getName2().getHeight() * ratioH));

        size = this.ng.getPrecgrille().getPreferredSize();
        this.ng.getPrecgrille().setBounds((int) round((670 + insets.left) * ratioW), ((int) round((229 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPrecgrille().setSize((int) round(this.ng.getPrecgrille().getWidth() * ratioW), (int) round(this.ng.getPrecgrille().getHeight() * ratioH));
        
        size = this.ng.getSupgrille().getPreferredSize();
        this.ng.getSupgrille().setBounds((int) round((1156 + insets.left) * ratioW), ((int) round((229 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSupgrille().setSize((int) round(this.ng.getSupgrille().getWidth() * ratioW), (int) round(this.ng.getSupgrille().getHeight() * ratioH));
        
        size = this.ng.getGrilleName().getPreferredSize();
        this.ng.getGrilleName().setBounds((int) round((769 + insets.left) * ratioW), ((int) round((229 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getGrilleName().setSize((int) round(384 * ratioW), (int) round(100 * ratioH));
        
        this.ng.callResize();
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

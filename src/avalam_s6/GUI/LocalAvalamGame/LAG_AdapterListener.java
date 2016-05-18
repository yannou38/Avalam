/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.LocalAvalamGame;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import static java.lang.Math.round;

/**
 *
 * @author ducruyy
 */
public class LAG_AdapterListener implements ComponentListener {

    private GUI_LAG page;

    public LAG_AdapterListener(GUI_LAG page) {
        this.page = page;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        
        Insets insets = page.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) page.getWidth() / (double) 1280;
        double ratioH = (double) page.getHeight() / (double) 720;
        Dimension size = page.grille.getPreferredSize();
        page.grille.setBounds((int) round((427 + insets.left) * ratioW), ((int) round((161 + insets.top) * ratioH)), size.width, size.height);
        page.grille.setSize((int) round(page.grille.getWidth() * ratioW), (int) round(page.grille.getHeight() * ratioH));
        
        
        size = page.undoB.getPreferredSize();
        page.undoB.setBounds((int) round((400 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        page.undoB.setSize((int) round(page.undoB.getWidth() * ratioW), (int) round(page.undoB.getHeight() * ratioH));
        
        
        size = page.redoB.getPreferredSize();
        page.redoB.setBounds((int) round((705 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        page.redoB.setSize((int) round(page.redoB.getWidth() * ratioW), (int) round(page.redoB.getHeight() * ratioH));
        
        size = page.saveB.getPreferredSize();
        page.saveB.setBounds((int) round((1101 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        page.saveB.setSize((int) round(page.saveB.getWidth() * ratioW), (int) round(page.saveB.getHeight() * ratioH));
        
        size = page.retourB.getPreferredSize();
        page.retourB.setBounds((int) round((3 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        page.retourB.setSize((int) round(page.retourB.getWidth() * ratioW), (int) round(page.retourB.getHeight() * ratioH));
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

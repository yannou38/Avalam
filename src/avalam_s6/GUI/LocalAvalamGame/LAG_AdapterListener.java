/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.LocalAvalamGame;

import avalam_s6.GUI.HomePage.GUI_HomePage;
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

    GUI_LAG page;

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.HomePage;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.*;
import static java.lang.Math.round;

/**
 *
 * @author ducruyy
 */
public class HomePageAdapterListener implements ComponentListener {

    GUI_HomePage hm;

    public HomePageAdapterListener(GUI_HomePage homepage) {
        this.hm = homepage;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = hm.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) hm.getWidth() / (double) 1280;
        double ratioH = (double) hm.getHeight() / (double) 720;

        //System.out.println(ratioW +" "+ratioH);
        Dimension size = hm.quick.getPreferredSize();
        hm.quick.setBounds((int) round((302 + insets.left) * ratioW), ((int) round((302 + insets.top) * ratioH)), size.width, size.height);
        hm.quick.setSize((int) round(hm.quick.getWidth() * ratioW), (int) round(hm.quick.getHeight() * ratioH));

        size = hm.play.getPreferredSize();
        hm.play.setBounds((int) round((302 + insets.left) * ratioW), ((int) round((402 + insets.top) * ratioH)), size.width, size.height);
        hm.play.setSize((int) round(hm.play.getWidth() * ratioW), (int) round(hm.play.getHeight() * ratioH));

        size = hm.rules.getPreferredSize();
        hm.rules.setBounds((int) round((302 + insets.left) * ratioW), ((int) round((502 + insets.top) * ratioH)), size.width, size.height);
        hm.rules.setSize((int) round(hm.rules.getWidth() * ratioW), (int) round(hm.rules.getHeight() * ratioH));

        size = hm.tuto.getPreferredSize();
        hm.tuto.setBounds((int) round((529 + insets.left) * ratioW), ((int) round((502 + insets.top) * ratioH)), size.width, size.height);
        hm.tuto.setSize((int) round(hm.tuto.getWidth() * ratioW), (int) round(hm.tuto.getHeight() * ratioH));

        size = hm.settings.getPreferredSize();
        hm.settings.setBounds((int) round((755 + insets.left) * ratioW), ((int) round((502 + insets.top) * ratioH)), size.width, size.height);
        hm.settings.setSize((int) round(hm.settings.getWidth() * ratioW), (int) round(hm.settings.getHeight() * ratioH));

        size = hm.exit.getPreferredSize();
        hm.exit.setBounds((int) round((1107 + insets.left) * ratioW), ((int) round((615 + insets.top) * ratioH)), size.width, size.height);
        hm.exit.setSize((int) round(hm.exit.getWidth() * ratioW), (int) round(hm.exit.getHeight() * ratioH));
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

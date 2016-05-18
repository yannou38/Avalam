/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.FinalScreen;

import avalam_s6.GUI.LocalAvalamGame.GUI_LAG;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import static java.lang.Math.round;

/**
 *
 * @author sazeratj
 */
public class Final_AdapterListener implements ComponentListener {
    private GUI_FinalScreen page;

    public Final_AdapterListener(GUI_FinalScreen page) {
        this.page = page;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = page.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) page.getWidth() / (double) 1280;
        double ratioH = (double) page.getHeight() / (double) 720;
        Dimension size = page.home.getPreferredSize();
        page.home.setBounds((int) round((3 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        page.home.setSize((int) round(page.home.getWidth() * ratioW), (int) round(page.home.getHeight() * ratioH));
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
}

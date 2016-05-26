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
        double ratioW = (double) page.getWidth() / (double) 1920;
        double ratioH = (double) page.getHeight() / (double) 1080;
        Dimension size = page.getGrille().getPreferredSize();
        page.getGrille().setBounds((int) round((680 + insets.left) * ratioW), ((int) round((275 + insets.top) * ratioH)), size.width, size.height);
        page.getGrille().setSize((int) round(page.getGrille().getWidth() * ratioW*2/3), (int) round(page.getGrille().getHeight() * ratioH*2/3));

        size = page.getUndoB().getPreferredSize();
        page.getUndoB().setBounds((int) round((360 + insets.left) * ratioW), ((int) round((505 + insets.top) * ratioH)), size.width, size.height);
        page.getUndoB().setSize((int) round(page.getUndoB().getWidth() * ratioW), (int) round(page.getUndoB().getHeight() * ratioH));

        size = page.getRedoB().getPreferredSize();
        page.getRedoB().setBounds((int) round((1313 + insets.left) * ratioW), ((int) round((505 + insets.top) * ratioH)), size.width, size.height);
        page.getRedoB().setSize((int) round(page.getRedoB().getWidth() * ratioW), (int) round(page.getRedoB().getHeight() * ratioH));

        size = page.getSaveB().getPreferredSize();
        page.getSaveB().setBounds((int) round((1640 + insets.left) * ratioW), ((int) round((930 + insets.top) * ratioH)), size.width, size.height);
        page.getSaveB().setSize((int) round(page.getSaveB().getWidth() * ratioW), (int) round(page.getSaveB().getHeight() * ratioH));

        size = page.getRetourB().getPreferredSize();
        page.getRetourB().setBounds((int) round((25 + insets.left) * ratioW), ((int) round((933 + insets.top) * ratioH)), size.width, size.height);
        page.getRetourB().setSize((int) round(page.getRetourB().getWidth() * ratioW), (int) round(page.getRetourB().getHeight() * ratioH));
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

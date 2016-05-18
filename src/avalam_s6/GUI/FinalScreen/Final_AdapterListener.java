/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.FinalScreen;

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
    private final GUI_FinalScreen page;

    public Final_AdapterListener(GUI_FinalScreen page) {
        this.page = page;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.page.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) this.page.getWidth() / (double) 1280;
        double ratioH = (double) this.page.getHeight() / (double) 720;
        Dimension size = this.page.getHome().getPreferredSize();
        this.page.getHome().setBounds((int) round((3 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        this.page.getHome().setSize((int) round(this.page.getHome().getWidth() * ratioW), (int) round(this.page.getHome().getHeight() * ratioH));
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
}

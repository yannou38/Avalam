/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Load;

import avalam_s6.GUI.Save.*;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import static java.lang.Math.round;

/**
 *
 * @author dupageuyy
 */
public class LoadAdapterListener implements ComponentListener {

    private GUI_Load page;

    public LoadAdapterListener(GUI_Load page) {
        this.page = page;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.page.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) this.page.getWidth() / (double) 1280;
        double ratioH = (double) this.page.getHeight() / (double) 720;

        Dimension size = this.page.getHomereturn().getPreferredSize();
        this.page.getHomereturn().setBounds((int) round((250 + insets.left) * ratioW), ((int) round((300 + insets.top) * ratioH)), size.width, size.height);
        this.page.getHomereturn().setSize((int) round(this.page.getHomereturn().getWidth() * ratioW), (int) round(this.page.getHomereturn().getHeight() * ratioH));

        size = this.page.getSaveload().getPreferredSize();
        this.page.getSaveload().setBounds((int) round((250 + insets.left) * ratioW), ((int) round((500 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSaveload().setSize((int) round(this.page.getSaveload().getWidth() * ratioW), (int) round(this.page.getSaveload().getHeight() * ratioH));

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

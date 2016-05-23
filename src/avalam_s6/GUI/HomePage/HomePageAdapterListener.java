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

    private GUI_HomePage hm;

    public HomePageAdapterListener(GUI_HomePage homepage) {
        this.hm = homepage;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.hm.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) this.hm.getWidth() / (double) 1920;
        double ratioH = (double) this.hm.getHeight() / (double) 1080;
        Dimension size = this.hm.getQuick().getPreferredSize();
        this.hm.getQuick().setBounds((int) round((603 + insets.left) * ratioW), ((int) round((473 + insets.top) * ratioH)), size.width, size.height);
        this.hm.getQuick().setSize((int) round(this.hm.getQuick().getWidth() * ratioW), (int) round(this.hm.getQuick().getHeight() * ratioH));

        size = this.hm.getPlay().getPreferredSize();
        this.hm.getPlay().setBounds((int) round((603 + insets.left) * ratioW), ((int) round((623 + insets.top) * ratioH)), size.width, size.height);
        this.hm.getPlay().setSize((int) round(this.hm.getPlay().getWidth() * ratioW), (int) round(this.hm.getPlay().getHeight() * ratioH));

        size = this.hm.getRules().getPreferredSize();
        this.hm.getRules().setBounds((int) round((513 + insets.left) * ratioW), ((int) round((773 + insets.top) * ratioH)), size.width, size.height);
        this.hm.getRules().setSize((int) round(this.hm.getRules().getWidth() * ratioW), (int) round(this.hm.getRules().getHeight() * ratioH));
        
        
        size = this.hm.getLoad().getPreferredSize();
        this.hm.getLoad().setBounds((int) round((852 + insets.left) * ratioW), ((int) round((773 + insets.top) * ratioH)), size.width, size.height);
        this.hm.getLoad().setSize((int) round(this.hm.getLoad().getWidth() * ratioW), (int) round(this.hm.getLoad().getHeight() * ratioH));


        size = this.hm.getSettings().getPreferredSize();
        this.hm.getSettings().setBounds((int) round((1180 + insets.left) * ratioW), ((int) round((773 + insets.top) * ratioH)), size.width, size.height);
        this.hm.getSettings().setSize((int) round(this.hm.getSettings().getWidth() * ratioW), (int) round(this.hm.getSettings().getHeight() * ratioH));

        size = this.hm.getExit().getPreferredSize();
        this.hm.getExit().setBounds((int) round((1721 + insets.left) * ratioW), ((int) round((1000 + insets.top) * ratioH)), size.width, size.height);
        this.hm.getExit().setSize((int) round(this.hm.getExit().getWidth() * ratioW), (int) round(this.hm.getExit().getHeight() * ratioH));
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

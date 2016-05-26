/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.HomePage;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

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
        Image newimg;
        Dimension size;
        //1280*720 => taille de base
        double ratioW = (double) this.hm.getWidth() / (double) 1920;
        double ratioH = (double) this.hm.getHeight() / (double) 1080;
        
        newimg = this.hm.getQuickI().getScaledInstance(((int) round(674 * ratioW)), ((int) round(94 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.hm.getQuick().setIcon(new ImageIcon(newimg));
        this.hm.getQuick().setBounds((int) round((453 + insets.left) * ratioW), ((int) round((453 + insets.top) * ratioH)), (int) round(1015 * ratioW), (int) round(150*ratioH));
        this.hm.getQuick().setSize((int) round(1015 * ratioW), (int) round(150 * ratioH));
        
        newimg = this.hm.getPlayI().getScaledInstance(((int) round(674 * ratioW)), ((int) round(94 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.hm.getPlay().setIcon(new ImageIcon(newimg));
        this.hm.getPlay().setBounds((int) round((453 + insets.left) * ratioW), ((int) round((603 + insets.top) * ratioH)), (int) round(1015 * ratioW), (int) round(150*ratioH));
        this.hm.getPlay().setSize((int) round(1015 * ratioW), (int) round(150 * ratioH));
        
        newimg = this.hm.getRulesI().getScaledInstance(((int) round(222 * ratioW)), ((int) round(96 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.hm.getRules().setIcon(new ImageIcon(newimg));
        this.hm.getRules().setBounds((int) round((453 + insets.left) * ratioW), ((int) round((753 + insets.top) * ratioH)), (int) round(338 * ratioW), (int) round(150*ratioH));
        this.hm.getRules().setSize((int) round(338 * ratioW), (int) round(150 * ratioH));

        newimg = this.hm.getLoadI().getScaledInstance(((int) round(222 * ratioW)), ((int) round(96 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.hm.getLoad().setIcon(new ImageIcon(newimg));
        this.hm.getLoad().setBounds((int) round((792 + insets.left) * ratioW), ((int) round((753 + insets.top) * ratioH)), (int) round(338 * ratioW), (int) round(150*ratioH));
        this.hm.getLoad().setSize((int) round(338 * ratioW), (int) round(150 * ratioH));

        newimg = this.hm.getSettingsI().getScaledInstance(((int) round(222 * ratioW)), ((int) round(96 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.hm.getSettings().setIcon(new ImageIcon(newimg));
        this.hm.getSettings().setBounds((int) round((1130 + insets.left) * ratioW), ((int) round((753 + insets.top) * ratioH)), (int) round(338 * ratioW), (int) round(150*ratioH));
        this.hm.getSettings().setSize((int) round(338 * ratioW), (int) round(150 * ratioH));
        
        newimg = this.hm.getExitI().getScaledInstance(((int) round(131 * ratioW)), ((int) round(51 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.hm.getExit().setIcon(new ImageIcon(newimg));
        this.hm.getExit().setBounds((int) round((1688 + insets.left) * ratioW), ((int) round((988 + insets.top) * ratioH)), (int) round(200 * ratioW), (int) round(80*ratioH));
        this.hm.getExit().setSize((int) round(200 * ratioW), (int) round(80 * ratioH));
        
        /*size = this.hm.getExit().getPreferredSize();
        this.hm.getExit().setBounds((int) round((1688 + insets.left) * ratioW), ((int) round((988 + insets.top) * ratioH)), size.width * 3 / 2 + 2, size.height * 3 / 2 + 2);
        this.hm.getExit().setSize((int) round(this.hm.getExit().getWidth() * ratioW), (int) round(this.hm.getExit().getHeight() * ratioH));*/
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

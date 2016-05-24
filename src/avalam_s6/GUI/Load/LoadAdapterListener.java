/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Load;

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

    private final GUI_Load page;

    public LoadAdapterListener(GUI_Load page) {
        this.page = page;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.page.getInsets();
        double ratioW = (double) this.page.getWidth() / (double) 1920;
        double ratioH = (double) this.page.getHeight() / (double) 1080;

        Dimension size = this.page.getHomereturn().getPreferredSize();
        this.page.getHomereturn().setBounds((int) round((30 + insets.left) * ratioW), ((int) round((930 + insets.top) * ratioH)), size.width, size.height);
        this.page.getHomereturn().setSize((int) round(this.page.getHomereturn().getWidth() * ratioW), (int) round(this.page.getHomereturn().getHeight() * ratioH));

        size = this.page.getSaveload().getPreferredSize();
        this.page.getSaveload().setBounds((int) round((454 + insets.left) * ratioW), ((int) round((909 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSaveload().setSize((int) round(this.page.getSaveload().getWidth() * ratioW), (int) round(this.page.getSaveload().getHeight() * ratioH));

        size = this.page.getSlots(1).getPreferredSize();
        this.page.getSlots(1).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((220 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSlots(1).setSize((int) round(this.page.getSlots(1).getWidth() * ratioW), (int) round(this.page.getSlots(1).getHeight() * ratioH));
        
        size = this.page.getSlots(2).getPreferredSize();
        this.page.getSlots(2).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((325 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSlots(2).setSize((int) round(this.page.getSlots(2).getWidth() * ratioW), (int) round(this.page.getSlots(2).getHeight() * ratioH));
        
        size = this.page.getSlots(3).getPreferredSize();
        this.page.getSlots(3).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((430 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSlots(3).setSize((int) round(this.page.getSlots(3).getWidth() * ratioW), (int) round(this.page.getSlots(3).getHeight() * ratioH));
        
        size = this.page.getSlots(4).getPreferredSize();
        this.page.getSlots(4).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((536 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSlots(4).setSize((int) round(this.page.getSlots(4).getWidth() * ratioW), (int) round(this.page.getSlots(4).getHeight() * ratioH));
        
        size = this.page.getSlots(5).getPreferredSize();
        this.page.getSlots(5).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((642 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSlots(5).setSize((int) round(this.page.getSlots(5).getWidth() * ratioW), (int) round(this.page.getSlots(5).getHeight() * ratioH));
        
        size = this.page.getSlots(6).getPreferredSize();
        this.page.getSlots(6).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((748 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSlots(6).setSize((int) round(this.page.getSlots(6).getWidth() * ratioW), (int) round(this.page.getSlots(6).getHeight() * ratioH));
        
        size = this.page.getField().getPreferredSize();
        this.page.getField().setBounds((int) round((810 + insets.left) * ratioW), ((int) round((788 + insets.top) * ratioH)), size.width, size.height);
        this.page.getField().setSize((int) round(this.page.getField().getWidth() * ratioW), (int) round(this.page.getField().getHeight() * ratioH));

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Load;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

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
        Dimension size;
        
        Image newimg;

        newimg = this.page.getHomeReturnI().getScaledInstance((int) round(251 * ratioW), (int) round(111 * ratioH), java.awt.Image.SCALE_SMOOTH);
        this.page.getHomereturn().setIcon(new ImageIcon(newimg));
        this.page.getHomereturn().setBounds((int) round((30 + insets.left) * ratioW), ((int) round((930 + insets.top) * ratioH)), (int) round(251 * ratioW), (int) round(111 * ratioH));
        this.page.getHomereturn().setSize((int) round(251 * ratioW), (int) round(111 * ratioH));

        newimg = this.page.getLoadI().getScaledInstance((int) round(1012 * ratioW), (int) round(141 * ratioH), java.awt.Image.SCALE_SMOOTH);
        this.page.getSaveload().setIcon(new ImageIcon(newimg));
        this.page.getSaveload().setBounds((int) round((454 + insets.left) * ratioW), ((int) round((909 + insets.top) * ratioH)), (int) round(1012 * ratioW), (int) round(141 * ratioH));
        this.page.getSaveload().setSize((int) round(1012 * ratioW), (int) round(141 * ratioH));

        newimg = this.page.getSlotI().getScaledInstance((int) round(1200 * ratioW), (int) round(109 * ratioH), java.awt.Image.SCALE_SMOOTH);
        this.page.getSlots(1).setIcon(new ImageIcon(newimg));
        this.page.getSlots(1).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((220 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(1).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));
        
        this.page.getSlots(2).setIcon(new ImageIcon(newimg));
        this.page.getSlots(2).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((325 + insets.top) * ratioH)),(int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(2).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        this.page.getSlots(3).setIcon(new ImageIcon(newimg));
        this.page.getSlots(3).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((430 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(3).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        this.page.getSlots(4).setIcon(new ImageIcon(newimg));
        this.page.getSlots(4).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((536 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(4).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        this.page.getSlots(5).setIcon(new ImageIcon(newimg));
        this.page.getSlots(5).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((642 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(5).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        this.page.getSlots(6).setIcon(new ImageIcon(newimg));
        this.page.getSlots(6).setBounds((int) round((360 + insets.left) * ratioW), ((int) round((748 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(6).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        size = this.page.getField().getPreferredSize();
        this.page.getField().setBounds((int) round((810 + insets.left) * ratioW), ((int) round((788 + insets.top) * ratioH)), size.width, size.height);
        this.page.getField().setSize((int) round(this.page.getField().getWidth() * ratioW), (int) round(this.page.getField().getHeight() * ratioH));

        size = this.page.getSlotlabels(1).getPreferredSize();
        this.page.getSlotlabels(1).setBounds((int) round((((this.page.getWidth() / 2) - size.getWidth() / 2) + insets.left)), ((int) round((255 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSlotlabels(1).setSize((int) round(this.page.getSlotlabels(1).getWidth() * ratioW), (int) round(this.page.getSlotlabels(1).getHeight() * ratioH));

        size = this.page.getSlotlabels(2).getPreferredSize();
        this.page.getSlotlabels(2).setBounds((int) round((((this.page.getWidth() / 2) - size.getWidth() / 2) + insets.left)), ((int) round((360 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSlotlabels(2).setSize((int) round(this.page.getSlotlabels(2).getWidth() * ratioW), (int) round(this.page.getSlotlabels(2).getHeight() * ratioH));

        size = this.page.getSlotlabels(3).getPreferredSize();
        this.page.getSlotlabels(3).setBounds((int) round((((this.page.getWidth() / 2) - size.getWidth() / 2) + insets.left)), ((int) round((465 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSlotlabels(3).setSize((int) round(this.page.getSlotlabels(3).getWidth() * ratioW), (int) round(this.page.getSlotlabels(3).getHeight() * ratioH));

        size = this.page.getSlotlabels(4).getPreferredSize();
        this.page.getSlotlabels(4).setBounds((int) round((((this.page.getWidth() / 2) - size.getWidth() / 2) + insets.left)), ((int) round((571 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSlotlabels(4).setSize((int) round(this.page.getSlotlabels(4).getWidth() * ratioW), (int) round(this.page.getSlotlabels(4).getHeight() * ratioH));

        size = this.page.getSlotlabels(5).getPreferredSize();
        this.page.getSlotlabels(5).setBounds((int) round((((this.page.getWidth() / 2) - size.getWidth() / 2) + insets.left)), ((int) round((677 + insets.top) * ratioH)), size.width, size.height);
        this.page.getSlotlabels(5).setSize((int) round(this.page.getSlotlabels(5).getWidth() * ratioW), (int) round(this.page.getSlotlabels(5).getHeight() * ratioH));

    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
        this.page.loadSlotText();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

}

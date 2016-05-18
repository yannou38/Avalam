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
        this.page.home.setBounds((int) round((3 + insets.left) * ratioW), ((int) round((620 + insets.top) * ratioH)), size.width, size.height);
        this.page.home.setSize((int) round(this.page.home.getWidth() * ratioW), (int) round(this.page.home.getHeight() * ratioH));
        
        size = this.page.victoryText.getPreferredSize();
        this.page.victoryText.setBounds((int) round((500 + insets.left) * ratioW), ((int) round((50 + insets.top) * ratioH)), size.width, size.height);
        this.page.victoryText.setSize((int) round(this.page.victoryText.getWidth() * ratioW), (int) round(this.page.victoryText.getHeight() * ratioH));
        
        size = this.page.grille.getPreferredSize();
        this.page.grille.setBounds((int) round((450 + insets.left) * ratioW), ((int) round((150 + insets.top) * ratioH)), size.width, size.height);
        this.page.grille.setSize((int) round(this.page.grille.getWidth() * ratioW), (int) round(this.page.grille.getHeight() * ratioH));
        
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
}

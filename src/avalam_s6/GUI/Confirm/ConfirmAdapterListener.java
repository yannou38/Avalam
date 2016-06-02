/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Confirm;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

/**
 *
 * @author sazeratj
 */
public class ConfirmAdapterListener implements ComponentListener {
    private final GUI_Confirm co;

    public ConfirmAdapterListener(GUI_Confirm confirm) {
        this.co = confirm;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.co.getInsets();
        Image newimg;
        //1280*720 => taille de base
        double ratioW = (double) this.co.getWidth() / (double) 1920;
        double ratioH = (double) this.co.getHeight() / (double) 1080;
        
        
        this.co.getTitre().setBounds(0, (int) round(40 * ratioH), this.co.getWidth(), 90);
        this.co.getTitre().setSize(this.co.getWidth(), 90);
        
        newimg = this.co.getYesI().getScaledInstance(((int) round(252 * ratioW)), ((int) round(111 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.co.getYes().setIcon(new ImageIcon(newimg));
        this.co.getYes().setBounds((int) round((355 + insets.left) * ratioW), ((int) round((539 + insets.top) * ratioH)), (int) round(252 * ratioW), (int) round(111*ratioH));
        this.co.getYes().setSize((int) round(252 * ratioW), (int) round(111 * ratioH));
        
        newimg = this.co.getNoI().getScaledInstance(((int) round(252 * ratioW)), ((int) round(111 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.co.getNo().setIcon(new ImageIcon(newimg));
        this.co.getNo().setBounds((int) round((1297 + insets.left) * ratioW), ((int) round((539 + insets.top) * ratioH)), (int) round(252 * ratioW), (int) round(111*ratioH));
        this.co.getNo().setSize((int) round(252 * ratioW), (int) round(111 * ratioH));
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

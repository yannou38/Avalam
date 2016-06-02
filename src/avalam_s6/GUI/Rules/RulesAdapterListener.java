/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Rules;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

/**
 * Resizes Rules Page
 * @author Team 7
 */
public class RulesAdapterListener implements ComponentListener {

    private final GUI_Rules ru;

    /**
     * Constructor
     * @param credits The Rules Page
     */
    public RulesAdapterListener(GUI_Rules credits) {
        this.ru = credits;
    }

    //JButton player, prec, sup, load, retour, start;
    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.ru.getInsets();
        Image newimg;
        //1280*720 => taille de base
        double ratioW = (double) this.ru.getWidth() / (double) 1920;
        double ratioH = (double) this.ru.getHeight() / (double) 1080;
        
        newimg = this.ru.getReturnI().getScaledInstance(((int) round(263 * ratioW)), ((int) round(123 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ru.getRetour().setIcon(new ImageIcon(newimg));
        this.ru.getRetour().setBounds((int) round((20 + insets.left) * ratioW), ((int) round((930 + insets.top) * ratioH)), (int) round(263 * ratioW), (int) round(123*ratioH));
        this.ru.getRetour().setSize((int) round(263 * ratioW), (int) round(123 * ratioH));    
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

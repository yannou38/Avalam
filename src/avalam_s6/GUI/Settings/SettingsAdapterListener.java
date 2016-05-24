/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Settings;

import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.round;

/**
 *
 * @author ducruyy
 */
public class SettingsAdapterListener implements ComponentListener{

    private final GUI_Settings page;

    public SettingsAdapterListener(GUI_Settings settings) {
        this.page = settings;
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
        
        Insets insets = this.page.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) this.page.getWidth() / (double) 1920;
        double ratioH = (double) this.page.getHeight() / (double) 1080;        
        
        //boutons
        Dimension size = this.page.getRetour().getPreferredSize();
        this.page.getRetour().setBounds((int) round((20 + insets.left) * ratioW), ((int) round((935 + insets.top) * ratioH)), size.width, size.height);
        this.page.getRetour().setSize((int) round(this.page.getRetour().getWidth() * ratioW), (int) round(this.page.getRetour().getHeight() * ratioH));
        
        size = this.page.getApply().getPreferredSize();
        this.page.getApply().setBounds((int) round((860 + insets.left) * ratioW), ((int) round((935 + insets.top) * ratioH)), size.width, size.height);
        this.page.getApply().setSize((int) round(this.page.getApply().getWidth() * ratioW), (int) round(this.page.getApply().getHeight() * ratioH));
        
        size = this.page.getCredits().getPreferredSize();
        this.page.getCredits().setBounds((int) round((1640 + insets.left) * ratioW), ((int) round((935 + insets.top) * ratioH)), size.width, size.height);
        this.page.getCredits().setSize((int) round(this.page.getCredits().getWidth() * ratioW), (int) round(this.page.getCredits().getHeight() * ratioH));
        
        //labels
        size = this.page.getLabelLanguage().getPreferredSize();
        this.page.getLabelLanguage().setBounds((int) round((1050 + insets.left) * ratioW), ((int) round((220 + insets.top) * ratioH)), size.width*3/2, size.height*3/2);
        this.page.getLabelLanguage().setSize((int) round(this.page.getLabelLanguage().getWidth() * ratioW), (int) round(this.page.getLabelLanguage().getHeight() * ratioH));
        
        size = this.page.getLabelFS().getPreferredSize();
        this.page.getLabelFS().setBounds((int) round((1050 + insets.left) * ratioW), ((int) round((380 + insets.top) * ratioH)), size.width*3/2, size.height*3/2);
        this.page.getLabelFS().setSize((int) round(this.page.getLabelFS().getWidth() * ratioW), (int) round(this.page.getLabelFS().getHeight() * ratioH));        
        
        size = this.page.getLabelTheme().getPreferredSize();
        this.page.getLabelTheme().setBounds((int) round((1050 + insets.left) * ratioW), ((int) round((550 + insets.top) * ratioH)), size.width*3/2, size.height*3/2);
        this.page.getLabelTheme().setSize((int) round(this.page.getLabelTheme().getWidth() * ratioW), (int) round(this.page.getLabelTheme().getHeight() * ratioH));
        
        size = this.page.getLabelSound().getPreferredSize();
        this.page.getLabelSound().setBounds((int) round((1050 + insets.left) * ratioW), ((int) round((715 + insets.top) * ratioH)), size.width*3/2, size.height*3/2);
        this.page.getLabelSound().setSize((int) round(this.page.getLabelSound().getWidth() * ratioW), (int) round(this.page.getLabelSound().getHeight() * ratioH));
        
        //left & right
        size = this.page.getLeftLanguage().getPreferredSize();
        this.page.getLeftLanguage().setBounds((int) round((340 + insets.left) * ratioW), ((int) round((215 + insets.top) * ratioH)), size.width*3/2-20, size.height*3/2-20);
        this.page.getLeftLanguage().setSize((int) round(this.page.getLeftLanguage().getWidth() * ratioW), (int) round(this.page.getLeftLanguage().getHeight() * ratioH));
        
        size = this.page.getLeftFS().getPreferredSize();
        this.page.getLeftFS().setBounds((int) round((340 + insets.left) * ratioW), ((int) round((385 + insets.top) * ratioH)), size.width*3/2-20, size.height*3/2-20);
        this.page.getLeftFS().setSize((int) round(this.page.getLeftFS().getWidth() * ratioW), (int) round(this.page.getLeftFS().getHeight() * ratioH));        
        
        size = this.page.getLeftTheme().getPreferredSize();
        this.page.getLeftTheme().setBounds((int) round((340 + insets.left) * ratioW), ((int) round((550 + insets.top) * ratioH)), size.width*3/2-20, size.height*3/2-20);
        this.page.getLeftTheme().setSize((int) round(this.page.getLeftTheme().getWidth() * ratioW), (int) round(this.page.getLeftTheme().getHeight() * ratioH));
        
        size = this.page.getLeftSound().getPreferredSize();
        this.page.getLeftSound().setBounds((int) round((340 + insets.left) * ratioW), ((int) round((715 + insets.top) * ratioH)), size.width*3/2-20, size.height*3/2-20);
        this.page.getLeftSound().setSize((int) round(this.page.getLeftSound().getWidth() * ratioW), (int) round(this.page.getLeftSound().getHeight() * ratioH));
        
        
        size = this.page.getRightLanguage().getPreferredSize();
        this.page.getRightLanguage().setBounds((int) round((1510 + insets.left) * ratioW), ((int) round((215 + insets.top) * ratioH)), size.width*3/2-20, size.height*3/2-20);
        this.page.getRightLanguage().setSize((int) round(this.page.getRightLanguage().getWidth() * ratioW), (int) round(this.page.getRightLanguage().getHeight() * ratioH));
        
        size = this.page.getRightFS().getPreferredSize();
        this.page.getRightFS().setBounds((int) round((1510 + insets.left) * ratioW), ((int) round((385 + insets.top) * ratioH)), size.width*3/2-20, size.height*3/2-20);
        this.page.getRightFS().setSize((int) round(this.page.getRightFS().getWidth() * ratioW), (int) round(this.page.getRightFS().getHeight() * ratioH));        
        
        size = this.page.getRightTheme().getPreferredSize();
        this.page.getRightTheme().setBounds((int) round((1510 + insets.left) * ratioW), ((int) round((550 + insets.top) * ratioH)), size.width*3/2-20, size.height*3/2-20);
        this.page.getRightTheme().setSize((int) round(this.page.getRightTheme().getWidth() * ratioW), (int) round(this.page.getRightTheme().getHeight() * ratioH));
        
        size = this.page.getRightSound().getPreferredSize();
        this.page.getRightSound().setBounds((int) round((1510 + insets.left) * ratioW), ((int) round((715 + insets.top) * ratioH)), size.width*3/2-20, size.height*3/2-20);
        this.page.getRightSound().setSize((int) round(this.page.getRightSound().getWidth() * ratioW), (int) round(this.page.getRightSound().getHeight() * ratioH));
        
        
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
        this.page.initOptions();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Settings;

import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

/**
 * The resize system for the settings gui.
 * @author Team 7
 */
public class SettingsAdapterListener implements ComponentListener {

    private final GUI_Settings page;

    /**
     * Constructor.
     * @param settings the gui this listener is linked to.
     */
    public SettingsAdapterListener(GUI_Settings settings) {
        this.page = settings;
    }

    @Override
    public void componentResized(ComponentEvent e) {

        Dimension size;
        Image newimg;
        Insets insets = this.page.getInsets();
        //1280*720 => taille de base
        double ratioW = (double) this.page.getWidth() / (double) 1920;
        double ratioH = (double) this.page.getHeight() / (double) 1080;

        //boutons
        newimg = this.page.getRetourI().getScaledInstance((int) round(263 * ratioW), (int) round(123 * ratioH), java.awt.Image.SCALE_SMOOTH);
        this.page.getRetour().setIcon(new ImageIcon(newimg));
        this.page.getRetour().setBounds((int) round((20 + insets.left) * ratioW), ((int) round((935 + insets.top) * ratioH)), (int) round(263 * ratioW), (int) round(123 * ratioH));
        this.page.getRetour().setSize((int) round(263 * ratioW), (int) round(123 * ratioH));

        newimg = this.page.getApplyI().getScaledInstance((int) round(251 * ratioW), (int) round(111 * ratioH), java.awt.Image.SCALE_SMOOTH);
        this.page.getApply().setIcon(new ImageIcon(newimg));
        this.page.getApply().setBounds((int) round((860 + insets.left) * ratioW), ((int) round((935 + insets.top) * ratioH)), (int) round(251 * ratioW), (int) round(111 * ratioH));
        this.page.getApply().setSize((int) round(251 * ratioW), (int) round(111 * ratioH));

        newimg = this.page.getCreditsI().getScaledInstance((int) round(251 * ratioW), (int) round(111 * ratioH), java.awt.Image.SCALE_SMOOTH);
        this.page.getCredits().setIcon(new ImageIcon(newimg));
        this.page.getCredits().setBounds((int) round((1640 + insets.left) * ratioW), ((int) round((935 + insets.top) * ratioH)), (int) round(251 * ratioW), (int) round(111 * ratioH));
        this.page.getCredits().setSize((int) round(251 * ratioW), (int) round(111 * ratioH));

        //labels
        this.page.getLabelLanguage().setBounds((int) round((1050 + insets.left) * ratioW), ((int) round((220 + insets.top) * ratioH)), (int) round(640 * ratioW), (int) round(140 * ratioH));
        this.page.getLabelLanguage().setSize((int) round(640 * ratioW), (int) round(140 * ratioH));

        size = this.page.getLabelFS().getPreferredSize();
        this.page.getLabelFS().setBounds((int) round((1050 + insets.left) * ratioW), ((int) round((380 + insets.top) * ratioH)), (int) round(640 * ratioW), (int) round(140 * ratioH));
        this.page.getLabelFS().setSize((int) round(640 * ratioW), (int) round(140 * ratioH));

        size = this.page.getLabelTheme().getPreferredSize();
        this.page.getLabelTheme().setBounds((int) round((1050 + insets.left) * ratioW), ((int) round((550 + insets.top) * ratioH)), (int) round(640 * ratioW), (int) round(140 * ratioH));
        this.page.getLabelTheme().setSize((int) round(640 * ratioW), (int) round(140 * ratioH));

        size = this.page.getLabelSound().getPreferredSize();
        this.page.getLabelSound().setBounds((int) round((1050 + insets.left) * ratioW), ((int) round((715 + insets.top) * ratioH)), (int) round(640 * ratioW), (int) round(140 * ratioH));
        this.page.getLabelSound().setSize((int) round(640 * ratioW), (int) round(140 * ratioH));

        //left & right
        newimg = this.page.getLeftI().getScaledInstance((int) round(117 * ratioW), (int) round(117 * ratioH), java.awt.Image.SCALE_SMOOTH);
        this.page.getLeftLanguage().setIcon(new ImageIcon(newimg));
        this.page.getLeftLanguage().setBounds((int) round((350 + insets.left) * ratioW), ((int) round((235 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.page.getLeftLanguage().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));

        this.page.getLeftFS().setIcon(new ImageIcon(newimg));
        this.page.getLeftFS().setBounds((int) round((350 + insets.left) * ratioW), ((int) round((400 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.page.getLeftFS().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));

        this.page.getLeftTheme().setIcon(new ImageIcon(newimg));
        this.page.getLeftTheme().setBounds((int) round((350 + insets.left) * ratioW), ((int) round((565 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.page.getLeftTheme().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));

        this.page.getLeftSound().setIcon(new ImageIcon(newimg));
        this.page.getLeftSound().setBounds((int) round((350 + insets.left) * ratioW), ((int) round((730 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.page.getLeftSound().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));

        newimg = this.page.getRightI().getScaledInstance((int) round(117 * ratioW), (int) round(117 * ratioH), java.awt.Image.SCALE_SMOOTH);
        this.page.getRightLanguage().setIcon(new ImageIcon(newimg));
        this.page.getRightLanguage().setBounds((int) round((1525 + insets.left) * ratioW), ((int) round((235 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.page.getRightLanguage().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));

        this.page.getRightFS().setIcon(new ImageIcon(newimg));
        this.page.getRightFS().setBounds((int) round((1525 + insets.left) * ratioW), ((int) round((400 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.page.getRightFS().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));

        this.page.getRightTheme().setIcon(new ImageIcon(newimg));
        this.page.getRightTheme().setBounds((int) round((1525 + insets.left) * ratioW), ((int) round((565 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.page.getRightTheme().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));

        this.page.getRightSound().setIcon(new ImageIcon(newimg));
        this.page.getRightSound().setBounds((int) round((1525 + insets.left) * ratioW), ((int) round((730 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.page.getRightSound().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));

        this.page.callResize();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Settings;

import avalam_s6.Core.Globals.LanguageManager;
import avalam_s6.Core.Globals.SetupManager;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author ducruyy
 */
public class GUI_Settings extends JPanel {

    private Image applyI, background, creditsI, returnI, leftI, rightI;
    private JButton apply, retour, credits;
    private JButton leftLanguage, rightLanguage, leftFS, rightFS, leftTheme, rightTheme, leftSound, rightSound;

    private String[] language, fullScreen, Theme, Sound;
    private int languageSelected, fullScreenSelected, ThemeSelected, SoundSelected;
    private int languagesize, themesize;
    private JLabel LabelSound, LabelLanguage, LabelFS, LabelTheme;

    private SettingsAdapterListener listener;
    private Boolean callResize;

    public GUI_Settings() {
        this.callResize = false;
        this.listener = new SettingsAdapterListener(this);
        initOptions();
        initComponents();
    }

    private void initComponents() {

        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/options/background.png"));
            this.applyI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/options/apply.png"));
            this.creditsI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/options/credits.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/options/home.png"));
            this.leftI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/options/left.png"));
            this.rightI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/options/right.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_Settings.class.toString());
            Logger.getLogger(GUI_Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.apply = new JButton(new ImageIcon(this.applyI));
        this.apply.setBorder(BorderFactory.createEmptyBorder());
        this.apply.setContentAreaFilled(false);
        this.apply.setFocusPainted(false);
        this.apply.addMouseListener(new SettingsListener("apply",this));

        this.retour = new JButton(new ImageIcon(this.returnI));
        this.retour.setBorder(BorderFactory.createEmptyBorder());
        this.retour.setContentAreaFilled(false);
        this.retour.setFocusPainted(false);
        this.retour.addMouseListener(new SettingsListener("home",this));

        this.credits = new JButton(new ImageIcon(this.creditsI));
        this.credits.setBorder(BorderFactory.createEmptyBorder());
        this.credits.setContentAreaFilled(false);
        this.credits.setFocusPainted(false);
        this.credits.addMouseListener(new SettingsListener("credits",this));

        //private JButton leftLanguage, rightlanguage, laftFS, rightFS, leftTheme, rightTheme, leftSound, rightSound;
        this.leftLanguage = new JButton(new ImageIcon(this.leftI));
        this.leftLanguage.setBorder(BorderFactory.createEmptyBorder());
        this.leftLanguage.setContentAreaFilled(false);
        this.leftLanguage.setFocusPainted(false);
        this.leftLanguage.addMouseListener(new SettingsListener("left", "language", this));

        this.leftFS = new JButton(new ImageIcon(this.leftI));
        this.leftFS.setBorder(BorderFactory.createEmptyBorder());
        this.leftFS.setContentAreaFilled(false);
        this.leftFS.setFocusPainted(false);
        this.leftFS.addMouseListener(new SettingsListener("left", "fullscreen", this));

        this.leftTheme = new JButton(new ImageIcon(this.leftI));
        this.leftTheme.setBorder(BorderFactory.createEmptyBorder());
        this.leftTheme.setContentAreaFilled(false);
        this.leftTheme.setFocusPainted(false);
        this.leftTheme.addMouseListener(new SettingsListener("left", "theme", this));

        this.leftSound = new JButton(new ImageIcon(this.leftI));
        this.leftSound.setBorder(BorderFactory.createEmptyBorder());
        this.leftSound.setContentAreaFilled(false);
        this.leftSound.setFocusPainted(false);
        this.leftSound.addMouseListener(new SettingsListener("left", "sound", this));

        this.add(this.leftLanguage);
        this.add(this.leftFS);
        this.add(this.leftTheme);
        this.add(this.leftSound);

        this.rightLanguage = new JButton(new ImageIcon(this.rightI));
        this.rightLanguage.setBorder(BorderFactory.createEmptyBorder());
        this.rightLanguage.setContentAreaFilled(false);
        this.rightLanguage.setFocusPainted(false);
        this.rightLanguage.addMouseListener(new SettingsListener("right", "language", this));

        this.rightFS = new JButton(new ImageIcon(this.rightI));
        this.rightFS.setBorder(BorderFactory.createEmptyBorder());
        this.rightFS.setContentAreaFilled(false);
        this.rightFS.setFocusPainted(false);
        this.rightFS.addMouseListener(new SettingsListener("right", "fullscreen", this));

        this.rightTheme = new JButton(new ImageIcon(this.rightI));
        this.rightTheme.setBorder(BorderFactory.createEmptyBorder());
        this.rightTheme.setContentAreaFilled(false);
        this.rightTheme.setFocusPainted(false);
        this.rightTheme.addMouseListener(new SettingsListener("right", "theme", this));

        this.rightSound = new JButton(new ImageIcon(this.rightI));
        this.rightSound.setBorder(BorderFactory.createEmptyBorder());
        this.rightSound.setContentAreaFilled(false);
        this.rightSound.setFocusPainted(false);
        this.rightSound.addMouseListener(new SettingsListener("right", "sound", this));

        this.add(this.rightLanguage);
        this.add(this.rightFS);
        this.add(this.rightTheme);
        this.add(this.rightSound);

        this.add(this.apply);
        this.add(this.retour);
        this.add(this.credits);
        this.addComponentListener(listener);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);
        if (this.callResize == true) {
            this.listener.componentResized(null);
            this.callResize = false;
        }

    }

    public JButton getApply() {
        return this.apply;
    }

    public JButton getRetour() {
        return this.retour;
    }

    public JButton getCredits() {
        return this.credits;
    }

    private void initOptions() {
        String yes = LanguageManager.getElement("Oui");
        String no = LanguageManager.getElement("Non");

        String themeDefault = "Default";

        String[] temp = LanguageManager.getChildrensOf("Langue");
        this.languagesize = temp.length;
        this.language = new String[this.languagesize];
        this.language = temp;
        this.languageSelected = 0;

        this.fullScreen = new String[2];
        this.fullScreen[0] = yes;
        this.fullScreen[1] = no;
        this.fullScreenSelected = 1;

        this.Sound = new String[2];
        this.Sound[0] = yes;
        this.Sound[1] = no;
        this.SoundSelected = 0;

        this.themesize = 1;
        this.Theme = new String[this.themesize];
        this.Theme[0] = themeDefault;

        Font localFont = new Font("Arial", Font.PLAIN, 60);
        try {
            localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/font/Gamaliel.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - " + GUI_Settings.class.toString());
            Logger.getLogger(GUI_Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.LabelFS = new JLabel(this.fullScreen[this.fullScreenSelected]);
        this.LabelFS.setBorder(BorderFactory.createEmptyBorder());
        this.LabelFS.setFont(localFont.deriveFont(3 * 30f));
        this.add(this.LabelFS);

        this.LabelLanguage = new JLabel(this.language[this.languageSelected]);
        this.LabelLanguage.setBorder(BorderFactory.createEmptyBorder());
        this.LabelLanguage.setFont(localFont.deriveFont(3 * 30f));
        this.add(this.LabelLanguage);

        this.LabelSound = new JLabel(this.Sound[this.SoundSelected]);
        this.LabelSound.setBorder(BorderFactory.createEmptyBorder());
        this.LabelSound.setFont(localFont.deriveFont(3 * 30f));
        this.add(this.LabelSound);

        this.LabelTheme = new JLabel(this.Theme[this.ThemeSelected]);
        this.LabelTheme.setBorder(BorderFactory.createEmptyBorder());
        this.LabelTheme.setFont(localFont.deriveFont(3 * 30f));
        this.add(this.LabelTheme);

    }

    public JLabel getLabelSound() {
        return this.LabelSound;
    }

    public JLabel getLabelLanguage() {
        return this.LabelLanguage;
    }

    public JLabel getLabelFS() {
        return this.LabelFS;
    }

    public JLabel getLabelTheme() {
        return this.LabelTheme;
    }

    public JButton getLeftLanguage() {
        return this.leftLanguage;
    }

    public JButton getRightLanguage() {
        return this.rightLanguage;
    }

    public JButton getRightFS() {
        return this.rightFS;
    }

    public JButton getLeftTheme() {
        return this.leftTheme;
    }

    public JButton getRightTheme() {
        return this.rightTheme;
    }

    public JButton getLeftSound() {
        return this.leftSound;
    }

    public JButton getRightSound() {
        return this.rightSound;
    }

    public JButton getLeftFS() {
        return this.leftFS;
    }

    void leftLanguage() {
        //le 2 en hardcodé sera a changer :/
        this.languageSelected = (this.languageSelected - 1);
        if (this.languageSelected == -1) {
            this.languageSelected = 1;
        }
        this.LabelLanguage.setText(this.language[this.languageSelected]);
        this.callResize();
    }

    void leftFS() {
        //le 2 en hardcodé sera a changer :/
        this.fullScreenSelected = (this.fullScreenSelected - 1) % 2;
        if (this.fullScreenSelected == -1) {
            this.fullScreenSelected = 1;
        }
        this.LabelFS.setText(this.fullScreen[this.fullScreenSelected]);
        this.callResize();
    }

    void leftTheme() {
        //le 1 en hardcodé sera a changer :/
        this.ThemeSelected = (this.ThemeSelected - 1) % 1;
        if (this.ThemeSelected == -1) {
            this.ThemeSelected = 0;
        }
        this.LabelTheme.setText(this.Theme[this.ThemeSelected]);
        this.callResize();
    }

    void leftSound() {
        //le 2 en hardcodé sera a changer :/
        this.SoundSelected = (this.SoundSelected - 1) % 2;
        if (this.SoundSelected == -1) {
            this.SoundSelected = 1;
        }
        this.LabelSound.setText(this.Sound[this.SoundSelected]);
        this.callResize();
    }

    void rightLanguage() {
        //le 2 en hardcodé sera a changer :/
        this.languageSelected = (this.languageSelected + 1) % 2;
        this.LabelLanguage.setText(this.language[this.languageSelected]);
        this.callResize();
    }

    void rightFS() {
        //le 2 en hardcodé sera a changer :/
        this.fullScreenSelected = (this.fullScreenSelected + 1) % 2;
        this.LabelFS.setText(this.fullScreen[this.fullScreenSelected]);
        this.callResize();
    }

    void rightTheme() {
        //le 2 en hardcodé sera a changer :/
        this.ThemeSelected = (this.ThemeSelected + 1) % 1;
        this.LabelTheme.setText(this.Theme[this.ThemeSelected]);
        this.callResize();
    }

    void rightSound() {
        //le 2 en hardcodé sera a changer :/
        this.SoundSelected = (this.SoundSelected + 1) % 2;
        this.LabelSound.setText(this.Sound[this.SoundSelected]);
        this.callResize();
    }

    public void callResize() {
        this.callResize = true;
    }

}

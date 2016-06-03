/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Settings;

import avalam_s6.Core.Globals.LanguageManager;
import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.Core.Globals.ThemesLister;
import avalam_s6.GUI.Gui_INTERFACE;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The Settings gui.
 *
 * @author Team 7
 */
public class GUI_Settings extends JPanel implements Gui_INTERFACE {

    private Image applyI, background, creditsI, returnI, leftI, rightI;
    private JButton apply, retour, credits;
    private JButton leftLanguage, rightLanguage, leftFS, rightFS, leftTheme, rightTheme, leftSound, rightSound;

    private String[] language, fullScreen, Theme, Sound;
    private int currentLanguage, fullScreenSelected, ThemeSelected, SoundSelected;
    private JLabel LabelSound, LabelLanguage, LabelFS, LabelTheme;

    private final SettingsAdapterListener listener;
    private Boolean callResize;

    /**
     * Constructor.
     */
    public GUI_Settings() {
        this.callResize = false;
        this.listener = new SettingsAdapterListener(this);
        initOptions();
        initComponents();
    }

    /**
     * Inits the components that are not constructor dependent.
     */
    private void initComponents() {

        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/options/background.png"));
            this.applyI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/options/apply.png"));
            this.creditsI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/options/credits.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/options/home.png"));
            this.leftI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/options/left.png"));
            this.rightI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/options/right.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_Settings.class.toString());
            Logger.getLogger(GUI_Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.apply = new JButton(new ImageIcon(this.applyI));
        this.apply.setBorder(BorderFactory.createEmptyBorder());
        this.apply.setContentAreaFilled(false);
        this.apply.setFocusPainted(false);
        this.apply.addMouseListener(new SettingsListener("apply", this));

        this.retour = new JButton(new ImageIcon(this.returnI));
        this.retour.setBorder(BorderFactory.createEmptyBorder());
        this.retour.setContentAreaFilled(false);
        this.retour.setFocusPainted(false);
        this.retour.addMouseListener(new SettingsListener("home", this));

        this.credits = new JButton(new ImageIcon(this.creditsI));
        this.credits.setBorder(BorderFactory.createEmptyBorder());
        this.credits.setContentAreaFilled(false);
        this.credits.setFocusPainted(false);
        this.credits.addMouseListener(new SettingsListener("credits", this));

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

        Font localFont = new Font("Arial", Font.PLAIN, 60);
        try {
            localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/font/Gamaliel.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - " + GUI_Settings.class.toString());
            Logger.getLogger(GUI_Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.LabelFS = new JLabel(LanguageManager.getElement(this.fullScreen[this.fullScreenSelected]));
        this.LabelFS.setBorder(BorderFactory.createEmptyBorder());
        this.LabelFS.setFont(localFont.deriveFont(2 * 30f));
        this.add(this.LabelFS);

        this.LabelLanguage = new JLabel(LanguageManager.getElement(this.language[this.currentLanguage]));
        this.LabelLanguage.setBorder(BorderFactory.createEmptyBorder());
        this.LabelLanguage.setFont(localFont.deriveFont(2 * 30f));
        this.add(this.LabelLanguage);

        this.LabelSound = new JLabel(LanguageManager.getElement(this.Sound[this.SoundSelected]));
        this.LabelSound.setBorder(BorderFactory.createEmptyBorder());
        this.LabelSound.setFont(localFont.deriveFont(2 * 30f));
        this.add(this.LabelSound);

        this.LabelTheme = new JLabel(this.Theme[this.ThemeSelected]);
        this.LabelTheme.setBorder(BorderFactory.createEmptyBorder());
        this.LabelTheme.setFont(localFont.deriveFont(2 * 30f));
        this.add(this.LabelTheme);

        this.add(this.rightLanguage);
        this.add(this.rightFS);
        this.add(this.rightTheme);
        this.add(this.rightSound);

        this.add(this.apply);
        this.add(this.retour);
        this.add(this.credits);
        this.addComponentListener(listener);
    }

    /**
     * Gets the current options set in the config files.
     */
    public void initOptions() {

        this.language = LanguageManager.getChildrensNameOf("Langue");
        String[] languageshorts = LanguageManager.getChildrensNameOf("Langue");
        for (int x = 0; x < this.language.length; x++) {
            if (languageshorts[x].equals(SetupManager.getElement("Langue"))) {
                this.currentLanguage = x;
            }
        }

        this.fullScreen = new String[2];
        this.fullScreen[0] = "Oui";
        this.fullScreen[1] = "Non";
        this.fullScreenSelected = 1;
        if (SetupManager.getElement("FullScreen").equals("Oui")) {
            this.fullScreenSelected = 0;
        }

        this.Sound = new String[2];
        this.Sound[0] = "Oui";
        this.Sound[1] = "Non";
        this.SoundSelected = 1;
        if (SetupManager.getElement("Son").equals("Oui")) {
            this.SoundSelected = 0;
        }

        this.Theme = ThemesLister.listThemes();
        for (int x = 0; x < this.Theme.length; x++) {
            if (this.Theme[x].equals(SetupManager.getElement("Theme"))) {
                this.ThemeSelected = x;
            }
        }

        this.callResize();
    }

    /**
     * Change the labels' text (called when changes has been made).
     */
    public void retextLabels() {
        this.LabelFS.setText(LanguageManager.getElement(this.fullScreen[this.fullScreenSelected]));
        this.LabelTheme.setText(this.Theme[this.ThemeSelected]);
        this.LabelSound.setText(LanguageManager.getElement(this.Sound[this.SoundSelected]));
        this.LabelLanguage.setText(LanguageManager.getElement(this.language[this.currentLanguage]));
        this.callResize();
    }

    /**
     * Changes the language when the language's left arrow has been clicked.
     */
    public void leftLanguage() {
        this.currentLanguage = (this.currentLanguage - 1);
        if (this.currentLanguage == -1) {
            this.currentLanguage = this.language.length - 1;
        }
        this.LabelLanguage.setText(LanguageManager.getElement(this.language[this.currentLanguage]));
        this.callResize();
    }

    /**
     * Changes the resolution when the FullScreen left arrow has been clicked.
     */
    public void leftFS() {
        this.fullScreenSelected = (this.fullScreenSelected - 1);
        if (this.fullScreenSelected == -1) {
            this.fullScreenSelected = 1;
        }
        this.LabelFS.setText(LanguageManager.getElement(this.fullScreen[this.fullScreenSelected]));
        this.callResize();
    }

    /**
     * Changes the theme when the theme's left arrow has been clicked.
     */
    public void leftTheme() {
        this.ThemeSelected = (this.ThemeSelected - 1);
        if (this.ThemeSelected == -1) {
            this.ThemeSelected = this.Theme.length - 1;
        }
        this.LabelTheme.setText(this.Theme[this.ThemeSelected]);
        this.callResize();
    }

    /**
     * Mutes or unmutes the sound when the sound left arrow has been clicked.
     */
    public void leftSound() {
        this.SoundSelected = (this.SoundSelected - 1);
        if (this.SoundSelected == -1) {
            this.SoundSelected = 1;
        }
        this.LabelSound.setText(LanguageManager.getElement(this.Sound[this.SoundSelected]));
        this.callResize();
    }

    /**
     * Changes the language when the language's right arrow has been clicked.
     */
    public void rightLanguage() {
        this.currentLanguage = (this.currentLanguage + 1) % 2;
        this.LabelLanguage.setText(LanguageManager.getElement(this.language[this.currentLanguage]));
        this.callResize();
    }

    /**
     * Changes the resolution when the FullScreen right arrow has been clicked.
     */
    public void rightFS() {
        this.fullScreenSelected = (this.fullScreenSelected + 1) % 2;
        this.LabelFS.setText(LanguageManager.getElement(this.fullScreen[this.fullScreenSelected]));
        this.callResize();
    }

    /**
     * Changes the theme when the theme's right arrow has been clicked.
     */
    public void rightTheme() {
        this.ThemeSelected = (this.ThemeSelected + 1) % this.Theme.length;
        this.LabelTheme.setText(this.Theme[this.ThemeSelected]);
        this.callResize();
    }

    /**
     * Mutes or unmutes the sound when the sound left arrow has been clicked.
     */
    public void rightSound() {
        this.SoundSelected = (this.SoundSelected + 1) % 2;
        this.LabelSound.setText(LanguageManager.getElement(this.Sound[this.SoundSelected]));
        this.callResize();
    }

    /**
     * Gets the apply button.
     *
     * @return the apply button.
     */
    public JButton getApply() {
        return this.apply;
    }

    /**
     * Gets the return button.
     *
     * @return the return button.
     */
    public JButton getRetour() {
        return this.retour;
    }

    /**
     * Gets the credits button.
     *
     * @return the credits button.
     */
    public JButton getCredits() {
        return this.credits;
    }

    /**
     * Gets the Sound label.
     *
     * @return the sound label.
     */
    public JLabel getLabelSound() {
        return this.LabelSound;
    }

    /**
     * Gets the language label.
     *
     * @return the language label.
     */
    public JLabel getLabelLanguage() {
        return this.LabelLanguage;
    }

    /**
     * Gets Fullscreen label.
     *
     * @return the Fullscreen label.
     */
    public JLabel getLabelFS() {
        return this.LabelFS;
    }

    /**
     * Gets the theme label.
     *
     * @return the theme label.
     */
    public JLabel getLabelTheme() {
        return this.LabelTheme;
    }

    /**
     * Gets the left language arrow.
     *
     * @return the left language arrow.
     */
    public JButton getLeftLanguage() {
        return this.leftLanguage;
    }

    /**
     * Gets the right language arrow.
     *
     * @return the right language arrow.
     */
    public JButton getRightLanguage() {
        return this.rightLanguage;
    }

    /**
     * Gets the right Fullscreen arrow.
     *
     * @return the right Fullscreen arrow.
     */
    public JButton getRightFS() {
        return this.rightFS;
    }

    /**
     * Gets the left theme arrow.
     *
     * @return the left theme arrow.
     */
    public JButton getLeftTheme() {
        return this.leftTheme;
    }

    /**
     * Gets the right theme arrow.
     *
     * @return the right theme arrow.
     */
    public JButton getRightTheme() {
        return this.rightTheme;
    }

    /**
     * Gets the left sound arrow.
     *
     * @return the left sound arrow.
     */
    public JButton getLeftSound() {
        return this.leftSound;
    }

    /**
     * Gets the right sound arrow.
     *
     * @return the right sound arrow.
     */
    public JButton getRightSound() {
        return this.rightSound;
    }

    /**
     * Gets the left fullscreen arrow.
     *
     * @return the left fullscreen arrow.
     */
    public JButton getLeftFS() {
        return this.leftFS;
    }

    /**
     * Gets the selected language.
     *
     * @return the selected language.
     */
    public String getSelectedLanguage() {
        return this.language[this.currentLanguage];
    }

    /**
     * Gets the selected screen configuration.
     *
     * @return the selected screen configuration.
     */
    public String getSelectedFS() {
        return this.fullScreen[this.fullScreenSelected];

    }

    /**
     * Gets the selected theme.
     *
     * @return the selected theme.
     */
    public String getSelectedTheme() {
        return this.Theme[this.ThemeSelected];

    }

    /**
     * Gets the selected sound setting.
     *
     * @return the selected sound setting.
     */
    public String getSelectedSound() {
        return this.Sound[this.SoundSelected];

    }

    /**
     * Gets apply button image.
     *
     * @return the apply button image.
     */
    Image getApplyI() {
        return this.applyI;
    }

    /**
     * Gets the return button image.
     *
     * @return the return button image.
     */
    Image getRetourI() {
        return this.returnI;
    }

    /**
     * Gets the credits button image.
     *
     * @return the credits button image.
     */
    Image getCreditsI() {
        return this.creditsI;
    }

    /**
     * Gets the left arrow image.
     *
     * @return the left arrow image.
     */
    Image getLeftI() {
        return this.leftI;
    }

    /**
     * Gets the right arrow image.
     *
     * @return the right arrow image.
     */
    Image getRightI() {
        return this.rightI;
    }

    @Override
    public void back() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());

        mainFrame.setwState(WindowState.MAIN);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);
        if (this.callResize) {
            this.listener.componentResized(null);
            this.callResize = false;
        }

    }

    @Override
    public void callResize() {
        this.callResize = true;
    }
}

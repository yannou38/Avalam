/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Settings;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import static java.lang.Math.round;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The listener for user interactions with the settings gui.
 * @author Team 7
 */
public class SettingsListener implements MouseListener {

    private GUI_Settings page;
    private String name;
    private Image icon;
    private Image iconbase;
    private String relatedLabel;

    /**
     * Constructor.
     * @param buttonname the name of the observed button.
     * @param relatedLabel the label to wich the button is related.
     * @param page the gui this listener is linked to.
     */
    public SettingsListener(String buttonname, String relatedLabel, GUI_Settings page) {
        this.name = buttonname;
        this.page = page;
        this.relatedLabel = relatedLabel;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/options/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/options/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + SettingsListener.class.toString());
            Logger.getLogger(SettingsListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Constructor.
     * @param buttonname the name of the observed button.
     * @param page the gui this listener is linked to.
     */
    public SettingsListener(String buttonname, GUI_Settings page) {
        this(buttonname, null, page);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Settings homePage = ((GUI_Settings) source.getParent());
        Main_Frame mainFrame = ((Main_Frame) homePage.getParent().getParent().getParent().getParent());
        this.page.callResize();
        switch (this.name) {
            case "credits":
                ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));
                mainFrame.setwState(WindowState.ABOUT);
                break;
            case "home":
                ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));
                mainFrame.setwState(WindowState.MAIN);
                break;
            case "apply":
                ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));
                String Language = this.page.getSelectedLanguage();
                String FS = this.page.getSelectedFS();
                String Theme = this.page.getSelectedTheme();
                String Sound = this.page.getSelectedSound();
                mainFrame.changeSettings(Language, FS, Theme, Sound);
                mainFrame.setwState(WindowState.MAIN);
                break;
            case "left":
                switch (this.relatedLabel) {
                    case "language":
                        this.page.leftLanguage();
                        break;
                    case "fullscreen":
                        this.page.leftFS();

                        break;
                    case "theme":
                        this.page.leftTheme();

                        break;
                    case "sound":
                        this.page.leftSound();
                        break;
                }
                break;
            case "right":
                switch (this.relatedLabel) {
                    case "language":
                        this.page.rightLanguage();
                        break;
                    case "fullscreen":
                        this.page.rightFS();

                        break;
                    case "theme":
                        this.page.rightTheme();

                        break;
                    case "sound":
                        this.page.rightSound();
                        break;
                }
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //replace the icon with another
        double ratioW = (double) this.page.getWidth() / (double) 1920;
        double ratioH = (double) this.page.getHeight() / (double) 1080;
        Image newimg = this.icon.getScaledInstance(((int) round(icon.getWidth(null) * ratioW)), ((int) round(icon.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        double ratioW = (double) this.page.getWidth() / (double) 1920;
        double ratioH = (double) this.page.getHeight() / (double) 1080;
        Image newimg = this.iconbase.getScaledInstance(((int) round(iconbase.getWidth(null) * ratioW)), ((int) round(iconbase.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Settings;

import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author ducruyy
 */
public class SettingsListener implements MouseListener {

    private GUI_Settings page;
    private String name;
    private Image icon;
    private Image iconbase;
    private String theme;
    private String relatedLabel;

    public SettingsListener(String buttonname, String theme, String relatedLabel, GUI_Settings page) {
        this.name = buttonname;
        this.theme = theme;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/options/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/options/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + SettingsListener.class.toString());
            Logger.getLogger(SettingsListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.relatedLabel = relatedLabel;
        this.page=page;
    }

    public SettingsListener(String buttonname, String theme) {
        this(buttonname, theme, null, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Settings homePage = ((GUI_Settings) source.getParent());
        Main_Frame mainFrame = ((Main_Frame) homePage.getParent().getParent().getParent().getParent());
        switch (this.name) {
            case "credits":
                ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));
                mainFrame.setwState(WindowState.ABOUT);
                break;
            case "home":
                ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));
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
        ((JButton) e.getSource()).setIcon(new ImageIcon(this.icon));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));
    }

}

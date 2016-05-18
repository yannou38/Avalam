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

/**
 *
 * @author ducruyy
 */
public class SettingsListener implements MouseListener {

    String name;
    Image icon;
    Image iconbase;

    public SettingsListener(String buttonname, String theme) {
        this.name = buttonname;
        try {
            icon = ImageIO.read(new File("./ressources/Themes/" + theme + "/options/" + name + "_h.png"));
            iconbase = ImageIO.read(new File("./ressources/Themes/" + theme + "/options/" + name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - "+SettingsListener.class.toString());
            Logger.getLogger(SettingsListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Settings homePage = ((GUI_Settings)source.getParent());
        Main_Frame mainFrame = ((Main_Frame)homePage.getParent().getParent().getParent().getParent());
        switch (this.name){            
            case "credits" :
                mainFrame.setwState(WindowState.ABOUT);
                break;
        }
        ((JButton)e.getSource()).setIcon(new ImageIcon(this.iconbase));
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
        ((JButton)e.getSource()).setIcon(new ImageIcon(this.icon));        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        ((JButton)e.getSource()).setIcon(new ImageIcon(this.iconbase));
    }

}

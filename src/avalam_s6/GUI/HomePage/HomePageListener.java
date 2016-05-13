/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.HomePage;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author ducruyy
 */
public class HomePageListener implements MouseListener {

    String name;
    Image icon;
    Image iconbase;

    public HomePageListener(String buttonname, String theme) {
        this.name = buttonname;
        try {
            icon = ImageIO.read(new File("./ressources/Themes/" + theme + "/main/" + name + "_h.png"));
            iconbase = ImageIO.read(new File("./ressources/Themes/" + theme + "/main/" + name + ".png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
        ((JButton)e.getSource()).setIcon(new ImageIcon(icon));
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        ((JButton)e.getSource()).setIcon(new ImageIcon(iconbase));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Credits;

import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author loic
 */
public class CreditsListener implements MouseListener {

    String name;
    Image icon;
    Image iconbase;
    int playernum;
    
    public CreditsListener(String buttonname, String theme, int playernumber) {
        this.name = buttonname;
        this.playernum = playernumber;
        try {
            icon = ImageIO.read(new File("./ressources/Themes/" + theme + "/credits/" + name + "_h.png"));
            iconbase = ImageIO.read(new File("./ressources/Themes/" + theme + "/credits/" + name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - "+CreditsListener.class.toString());
            Logger.getLogger(CreditsListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Credits Credits = ((GUI_Credits)source.getParent());
        Main_Frame mainFrame = ((Main_Frame)Credits.getParent().getParent().getParent().getParent());
            mainFrame.setwState(WindowState.MAIN);   
        ((JButton)e.getSource()).setIcon(new ImageIcon(iconbase));
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

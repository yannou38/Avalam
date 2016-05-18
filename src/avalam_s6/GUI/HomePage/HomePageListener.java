/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.HomePage;

import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

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
        JButton source = (JButton) e.getSource();
        GUI_HomePage homePage = ((GUI_HomePage)source.getParent());
        Main_Frame mainFrame = ((Main_Frame)homePage.getParent().getParent().getParent().getParent());
        switch (this.name){
            case "quit" :
                mainFrame.dispose();
                break;
            case "quickgame" :                
                mainFrame.initGame();                
                break;                
            case "customgame" :
                mainFrame.setwState(WindowState.PLAYERSELECT);
                break;
        }
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

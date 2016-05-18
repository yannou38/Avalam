/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.FinalScreen;

import avalam_s6.GUI.HomePage.GUI_HomePage;
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
 * @author sazeratj
 */
public class Final_MouseListener implements MouseListener {
    private Image icon;
    private Image iconbase;
    
    public Final_MouseListener(String theme) {
        try {
            icon = ImageIO.read(new File("./ressources/Themes/" + theme + "/final/home_h.png"));
            iconbase = ImageIO.read(new File("./ressources/Themes/" + theme + "/final/home.png"));
        } catch (Exception ex) {
            System.out.println("Error - "+Final_MouseListener.class.toString());
            Logger.getLogger(Final_MouseListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_FinalScreen fs = ((GUI_FinalScreen)source.getParent());
        Main_Frame mainFrame = ((Main_Frame)fs.getParent().getParent().getParent().getParent());
        mainFrame.setwState(WindowState.MAIN);
        ((JButton) e.getSource()).setIcon(new ImageIcon(iconbase));
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        ((JButton) e.getSource()).setIcon(new ImageIcon(icon));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ((JButton) e.getSource()).setIcon(new ImageIcon(iconbase));
    }
    
}

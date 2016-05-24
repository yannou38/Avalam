/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Load;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Rules.RulesListener;
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
public class LoadListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;
    private GUI_Load page;

    public LoadListener(String buttonname) {
        this.name = buttonname;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/load/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/load/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + RulesListener.class.toString());
            Logger.getLogger(RulesListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Load Save = ((GUI_Load) source.getParent());
        switch (this.name) {            
            case "load":
                Save.getGame().load("slot_1");
                break;
            case "home":
                Save.back();
                break;
        }
        ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));
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

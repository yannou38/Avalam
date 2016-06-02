/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Confirm;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Credits.CreditsListener;
import avalam_s6.GUI.Gui_INTERFACE;
import avalam_s6.GUI.Main_Frame;
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
 *
 * @author sazeratj
 */
public class ConfirmListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;

    public ConfirmListener(String buttonname) {
        this.name = buttonname;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/confirm/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/confirm/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + CreditsListener.class.toString());
            Logger.getLogger(CreditsListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Confirm page = ((GUI_Confirm) source.getParent());
        switch (this.name) {
            case "yes":
                page.doAction();
                break;
            case "no":
                page.cancelAction();
                break;
        }
        source.setIcon(new ImageIcon(this.iconbase));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Confirm page = ((GUI_Confirm) source.getParent());
        double ratioW = (double) page.getWidth() / (double) 1920;
        double ratioH = (double) page.getHeight() / (double) 1080;
        Image newimg = this.icon.getScaledInstance(((int) round(icon.getWidth(null) * ratioW)), ((int) round(icon.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Confirm page = ((GUI_Confirm) source.getParent());
        double ratioW = (double) page.getWidth() / (double) 1920;
        double ratioH = (double) page.getHeight() / (double) 1080;
        Image newimg = this.iconbase.getScaledInstance(((int) round(iconbase.getWidth(null) * ratioW)), ((int) round(iconbase.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
        ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
    }

}

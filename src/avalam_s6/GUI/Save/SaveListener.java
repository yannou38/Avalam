/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Save;

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
public class SaveListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;
    private GUI_Save page;
    private int slotnumber;
    private Boolean ishoverable;

    public SaveListener(String buttonname, GUI_Save page, int number) {
        this.name = buttonname;
        this.page = page;
        this.slotnumber = number;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + RulesListener.class.toString());
            Logger.getLogger(RulesListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_Save Save = ((GUI_Save) source.getParent());
        switch (this.name) {
            case "save":
                Save.getGame().save("slot_"+this.page.getSlotnumber());
                break;
            case "return":
                Save.back();
                break;
            case "slot":
                Save.setSlotnumber(this.slotnumber);
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

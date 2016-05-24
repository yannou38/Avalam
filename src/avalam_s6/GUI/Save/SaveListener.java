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
import java.awt.image.BufferedImage;
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
    private BufferedImage iconselect;
    private Boolean isSelected;

    public SaveListener(String buttonname, GUI_Save page, int number) {
        this.name = buttonname;
        this.page = page;
        this.slotnumber = number;
        this.isSelected = false;
        try {
            if (this.name.equals("slot")) {
                this.iconselect = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/" + this.name + "_selected.png"));
            }
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
        switch (this.name) {
            case "save":
                if (this.page.getSlotnumber() == 0) {
                    //no slot selected, do nothing
                    break;
                } else if (this.page.getSlotnumber() == 6) {
                    this.page.getGame().save(this.page.getField().getText());
                } else {
                    this.page.getGame().save("slot_" + this.page.getSlotnumber());
                }
                this.page.back();
                source.setIcon(new ImageIcon(this.iconbase));
                break;
            case "return":
                this.page.back();
                source.setIcon(new ImageIcon(this.iconbase));
                break;
            case "slot":
                for (int i = 1; i < this.page.getSlotslistener().length + 1; i++) {
                    this.page.getSlotslistener(i).setIsSelected(false);
                    this.page.getSlots(i).setIcon(new ImageIcon(this.iconbase));

                }
                this.isSelected = true;
                if (this.slotnumber == 6) {
                    this.page.getField().setText("");
                    this.page.getField().requestFocus();

                }
                source.setIcon(new ImageIcon(this.iconselect));
                this.page.setSlotnumber(this.slotnumber);
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
        if (this.isSelected == false) {
            ((JButton) e.getSource()).setIcon(new ImageIcon(this.icon));

        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        if (this.isSelected == false) {
            ((JButton) e.getSource()).setIcon(new ImageIcon(this.iconbase));

        }
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

}

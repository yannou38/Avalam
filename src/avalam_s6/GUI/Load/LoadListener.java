/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Load;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Main_Frame;
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
    private Boolean isSelected;
    private int slotnumber;
    private Image iconselect;

    public LoadListener(String buttonname, GUI_Load page, int number) {
        this.name = buttonname;
        this.isSelected = false;
        this.page = page;
        this.slotnumber = number;
        try {
            if (this.name.equals("slot")) {
                this.iconselect = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/load/" + this.name + "_selected.png"));
            }
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/load/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/load/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + RulesListener.class.toString());
            Logger.getLogger(RulesListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        Main_Frame mFrame = ((Main_Frame) source.getParent().getParent().getParent().getParent().getParent());
        switch (this.name) {
            case "load":
                if (this.page.getSlotnumber() == 0) {
                    //no slot selected, do nothing
                    break;
                } else if (this.page.getSlotnumber() == 6) {
                    mFrame.load(this.page.getField().getText());
                } else {
                    mFrame.load("slot_" + this.page.getSlotnumber());
                }
                source.setIcon(new ImageIcon(this.iconbase));
                this.page.resetSlotSelection();
                mFrame.startGame();
                break;
            case "home":
                source.setIcon(new ImageIcon(this.iconbase));
                this.page.resetSlotSelection();
                this.page.back();
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

}

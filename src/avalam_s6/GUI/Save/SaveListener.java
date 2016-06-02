/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Save;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.Rules.RulesListener;
import avalam_s6.GUI.WindowState;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import static java.lang.Math.round;
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
                this.iconselect = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/save/" + this.name + "_selected.png"));
            }
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/save/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/save/" + this.name + ".png"));
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
            case "save":
                if (this.page.getSlotnumber() == 0) {
                    //no slot selected, do nothing
                    break;
                } else if (this.page.getSlotnumber() == 6) {
                    mFrame.setConfirmSauver(this.page.getField().getText());
                mFrame.setwState(WindowState.YESNO);
                } else {
                    mFrame.setConfirmSauver("slot_" + this.page.getSlotnumber());
                mFrame.setwState(WindowState.YESNO);
                }
                source.setIcon(new ImageIcon(this.iconbase));
                this.page.resetSlots();
                break;
            case "return":
                this.page.back();
                source.setIcon(new ImageIcon(this.iconbase));
                this.page.resetSlots();
                break;
            case "slot":
                double ratioW = (double) this.page.getWidth() / (double) 1920;
                double ratioH = (double) this.page.getHeight() / (double) 1080;
                Image newimg = this.iconbase.getScaledInstance(((int) round(iconbase.getWidth(null) * ratioW)), ((int) round(iconbase.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
                for (int i = 1; i < this.page.getSlotslistener().length + 1; i++) {
                    this.page.getSlotslistener(i).setIsSelected(false);
                    this.page.getSlots(i).setIcon(new ImageIcon(newimg));
                }
                this.isSelected = true;
                if (this.slotnumber == 6) {
                    this.page.getField().setText("");
                    this.page.getField().requestFocus();
                }

                newimg = this.iconselect.getScaledInstance(((int) round(iconselect.getWidth(null) * ratioW)), ((int) round(iconselect.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
                source.setIcon(new ImageIcon(newimg));
                this.page.setSlotnumber(this.slotnumber);
                break;
        }

    }

    public Image getIconbase() {
        return iconbase;
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
            double ratioW = (double) this.page.getWidth() / (double) 1920;
            double ratioH = (double) this.page.getHeight() / (double) 1080;
            Image newimg = this.icon.getScaledInstance(((int) round(icon.getWidth(null) * ratioW)), ((int) round(icon.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
            ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        if (this.isSelected == false) {
            double ratioW = (double) this.page.getWidth() / (double) 1920;
            double ratioH = (double) this.page.getHeight() / (double) 1080;
            Image newimg = this.iconbase.getScaledInstance(((int) round(iconbase.getWidth(null) * ratioW)), ((int) round(iconbase.getHeight(null) * ratioH)), java.awt.Image.SCALE_SMOOTH);
            ((JButton) e.getSource()).setIcon(new ImageIcon(newimg));
        }
    }
}

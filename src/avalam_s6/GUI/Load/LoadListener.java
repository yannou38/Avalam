/* 
 * Copyright (C) 2016 Yann Ducruy <yann.ducruy@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package avalam_s6.GUI.Load;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.Rules.RulesListener;
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
 * Listener for the loading window
 * @author Team 7
 */
public class LoadListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;
    private GUI_Load page;
    private Boolean isSelected;
    private int slotnumber;
    private Image iconselect;

    /**
     * Constructor
     * @param buttonname the name of the button to listen
     * @param page the page associated to this listener
     * @param number the number of the slot
     */
    public LoadListener(String buttonname, GUI_Load page, int number) {
        this.name = buttonname;
        this.isSelected = false;
        this.page = page;
        this.slotnumber = number;
        try {
            if (this.name.equals("slot")) {
                this.iconselect = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/load/" + this.name + "_selected.png"));
            }
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/load/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/load/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + RulesListener.class.toString());
            Logger.getLogger(RulesListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Setter
     * @param isSelected boolean to set if the slot is selected 
     */
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
                for (int i = 1; i < this.page.getSlotslistener().length + 1; i++) {
                    this.page.getSlotslistener(i).setIsSelected(false);
                    this.page.getSlots(i).setIcon(new ImageIcon(this.iconbase));
                }
                source.setIcon(new ImageIcon(this.iconbase));
                this.page.resetSlots();
                mFrame.startGame();
                break;
            case "home":
                source.setIcon(new ImageIcon(this.iconbase));
                this.page.resetSlots();
                this.page.back();
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

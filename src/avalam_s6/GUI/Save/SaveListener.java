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
 * The system for getting user input on the save page.
 * @author Team 7
 */
public class SaveListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;
    private GUI_Save page;
    private int slotnumber;
    private BufferedImage iconselect;
    private Boolean isSelected;

    /**
     * Constructor.
     * @param buttonname the name of the button.
     * @param page the Save_GUI this listener is linked to.
     * @param number the number of the selected slot.
     */
    public SaveListener(String buttonname, GUI_Save page, int number) {
        this.name = buttonname;
        this.page = page;
        this.slotnumber = number;
        this.isSelected = false;
        try {
            if (this.name.equals("slot")) {
                this.iconselect = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/save/" + this.name + "_selected.png"));
            }
            this.icon = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/save/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/save/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - " + RulesListener.class.toString());
            Logger.getLogger(RulesListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the state of the selected slot flag.
     * @param isSelected the new value of the flag.
     */
    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * Gets the non hovered image of the save button.
     * @return the non hovered image of the save button.
     */
    public Image getIconbase() {
        return iconbase;
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

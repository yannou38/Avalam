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

import avalam_s6.Core.Globals.LanguageManager;
import avalam_s6.GUI.Save.*;
import avalam_s6.Core.File_IO.SaveInfoLister;
import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Gui_INTERFACE;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Window to load a game.
 * @author Team 7
 */
public class GUI_Load extends JPanel implements Gui_INTERFACE {

    private JButton homereturn, saveload;
    private Image backgroundload, homeI, loadI, slot;
    private JTextField field;
    private final JButton[] slots;
    private final LoadListener[] slotslistener;
    private int slotnumber;
    private final LoadAdapterListener listener;
    private boolean callResize;
    private final JTextField[] slotlabels;
    private final Font font;

    /**
     * Constructor
     */
    public GUI_Load() {
        this.callResize = false;
        this.listener = new LoadAdapterListener(this);
        this.slotnumber = 0;
        this.slots = new JButton[6];
        this.slotslistener = new LoadListener[6];
        this.slotlabels = new JTextField[5];
        Font localFont = new Font("Arial", Font.PLAIN, 60);
        try {
            localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/font/Gamaliel.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - " + GUI_Save.class.toString());
            Logger.getLogger(GUI_Save.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.font = localFont;
        initComponents();
    }

    /**
     * Initialises all the components of the page,
     * Loads all the buttons and images.
     */
    private void initComponents() {
        try {
            this.backgroundload = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/load/background_load.png"));
            this.homeI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/load/home.png"));
            this.loadI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/load/load.png"));
            this.slot = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/load/slot.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_Load.class.toString());
            Logger.getLogger(GUI_Load.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.homereturn = new JButton(new ImageIcon(this.homeI));
        this.homereturn.setBorder(BorderFactory.createEmptyBorder());
        this.homereturn.setContentAreaFilled(false);
        this.homereturn.setFocusPainted(false);
        this.homereturn.addMouseListener(new LoadListener("home", this, 0));

        this.saveload = new JButton(new ImageIcon(this.loadI));
        this.saveload.setBorder(BorderFactory.createEmptyBorder());
        this.saveload.setContentAreaFilled(false);
        this.saveload.setFocusPainted(false);
        this.saveload.addMouseListener(new LoadListener("load", this, 0));

        for (int i = 0; i < this.slots.length; i++) {
            this.slots[i] = new JButton(new ImageIcon(slot));
            this.slots[i].setBorder(BorderFactory.createEmptyBorder());
            this.slots[i].setContentAreaFilled(false);
            this.slots[i].setFocusPainted(false);
            this.slotslistener[i] = new LoadListener("slot", this, i + 1);
            this.slots[i].addMouseListener(this.slotslistener[i]);
            this.add(this.slots[i]);
        }
        for (int i = 0; i < this.slotlabels.length; i++) {
            int j = i + 1;
            this.slotlabels[i] = new JTextField();
            this.slotlabels[i].setHorizontalAlignment(JLabel.CENTER);
            this.slotlabels[i].setFont(this.font.deriveFont(30f));
            this.slotlabels[i].setBorder(BorderFactory.createEmptyBorder());
            this.slotlabels[i].setText("Slot " + j + " : vide");
            this.slotlabels[i].setOpaque(false);
            this.slotlabels[i].setEditable(false);
            this.add(this.slotlabels[i]);
        }

        this.field = new JTextField();
        this.field.setHorizontalAlignment(JLabel.CENTER);
        this.field.setFont(this.font.deriveFont(30f));
        this.field.setBorder(BorderFactory.createEmptyBorder());
        this.field.setText("Nom de fichier");
        this.field.setOpaque(false);

        this.add(this.homereturn);
        this.add(this.saveload);
        this.add(this.field);
        this.addComponentListener(listener);
    }

    /**
     * Loads the text of a save slot.
     */
    public void loadSlotText() {
        SaveInfoLister sil;
        int j;
        for (int i = 0; i < this.slotlabels.length; i++) {
            j = i + 1;
            try {
                sil = new SaveInfoLister("slot_" + j);
                if (!sil.getEmptyslot()) {
                    sil = new SaveInfoLister("slot_" + j);
                    this.slotlabels[i].setFont(this.slotlabels[i].getFont().deriveFont(30f * this.getWidth() / 1920));
                    this.slotlabels[i].setText(sil.getDate() + "    " + sil.getPlayer1() + " VS " + sil.getPlayer2() + "    " + LanguageManager.getElement("Sur") + " " + sil.getGrid());
                } else {
                    this.slotlabels[i].setFont(this.slotlabels[i].getFont().deriveFont(30f * this.getWidth() / 1920));
                    this.slotlabels[i].setText("Slot " + j + " : vide");

                }

                this.field.setFont(this.font.deriveFont(45f * this.getWidth() / 1920));
                this.field.setText(this.field.getText());

            } catch (IOException ex) {
                //ne pas traiter
            }
        }
        this.callResize();
    }

    /**
     * Resets the slots to an unselected state.
     */
    public void resetSlots() {
        this.slotnumber = 0;
        for (int i = 0; i < this.slots.length; i++) {
            this.slotslistener[i].setIsSelected(false);
            this.slots[i].setIcon(new ImageIcon(this.slot));
        }
    }

    /**
     * Getter
     * @return the font of the window.
     */
    public Font getTextFont() {
        return font;
    }

    /**
     * Getter
     * @return the button to return home
     */
    public JButton getHomereturn() {
        return homereturn;
    }

    /**
     * Getter
     * @return load button
     */
    public JButton getSaveload() {
        return saveload;
    }

    /**
     * Getter
     * @param i the number of the desired slot
     * @return the button associated to a slot
     */
    public JButton getSlots(int i) {
        return this.slots[i - 1];
    }

    /**
     * Getter
     * @return the custom slot TextField
     */
    public JTextField getField() {
        return field;
    }

    /**
     * Getter
     * @return the slot listeners
     */
    public LoadListener[] getSlotslistener() {
        return slotslistener;
    }

    /**
     * Setter
     * @param slotnumber the number of the slot to set
     */
    public void setSlotnumber(int slotnumber) {
        this.slotnumber = slotnumber;
    }

    /**
     * Getter
     * @param i the desired slot
     * @return the listener of the desired slot
     */
    public LoadListener getSlotslistener(int i) {
        return slotslistener[i - 1];
    }

    /**
     * Getter
     * @return the number of the slot 
     */
    public int getSlotnumber() {
        return slotnumber;
    }

    /**
     * Getter
     * @param i the desired slot
     * @return the TextField associated to the desired slot
     */
    public JTextField getSlotlabels(int i) {
        return this.slotlabels[i - 1];
    }

    /**
     * Getter
     * @return the image of the return button
     */
    public Image getHomeReturnI() {
        return this.homeI;
    }

    /**
     * Getter
     * @return load image 
     */
    public Image getLoadI() {
        return this.loadI;
    }

    /**
     * Getter
     * @return slot image 
     */
    public Image getSlotI() {
        return this.slot;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.backgroundload, 0, 0, this.getWidth(), this.getHeight(), null);
        if (this.callResize) {
            this.listener.componentResized(null);
            this.callResize = false;
        }
    }

    @Override
    public void back() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());
        mainFrame.setwState(WindowState.MAIN);
    }

    @Override
    public void callResize() {
        this.callResize = true;
    }
}

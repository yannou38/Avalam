/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Save;

import avalam_s6.Core.Game_INTERFACE;
import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Gui_INTERFACE;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author ducruyy
 */
public class GUI_Save extends JPanel implements Gui_INTERFACE {

    private JButton homereturn, saveload;
    private JButton[] slots;
    private int slotnumber;
    private Image backgroundsave, saveI, returnI, slot;
    private final SaveAdapterListener listener;
    private Game_INTERFACE game;

    public GUI_Save() {
        this.listener = new SaveAdapterListener(this);
        this.game = null;
        this.slotnumber = 0;
        this.slots = new JButton[6];
        initComponents();
    }

    private void initComponents() {
        try {
            this.backgroundsave = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/background_save.png"));
            this.saveI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/save.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/return.png"));
            this.slot = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/slot.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_Save.class.toString());
            Logger.getLogger(GUI_Save.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.homereturn = new JButton(new ImageIcon(this.returnI));
        this.homereturn.setBorder(BorderFactory.createEmptyBorder());
        this.homereturn.setContentAreaFilled(false);
        this.homereturn.setFocusPainted(false);
        this.homereturn.addMouseListener(new SaveListener("return", this, 0));

        this.saveload = new JButton(new ImageIcon(this.saveI));
        this.saveload.setBorder(BorderFactory.createEmptyBorder());
        this.saveload.setContentAreaFilled(false);
        this.saveload.setFocusPainted(false);
        this.saveload.addMouseListener(new SaveListener("save", this, 0));

        for (int i = 0; i < this.slots.length; i++) {
            this.slots[i] = new JButton(new ImageIcon(slot));
            this.slots[i].setBorder(BorderFactory.createEmptyBorder());
            this.slots[i].setContentAreaFilled(false);
            this.slots[i].setFocusPainted(false);
            this.slots[i].addMouseListener(new SaveListener("slot", this, i + 1));
            this.add(this.slots[i]);
        }
        this.add(this.homereturn);
        this.add(this.saveload);
        this.addComponentListener(listener);
    }

    public void setGame(Game_INTERFACE game) {
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.backgroundsave, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public JButton getHomereturn() {
        return homereturn;
    }

    public JButton getSaveload() {
        return saveload;
    }

    @Override
    public void back() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());
        mainFrame.setwState(WindowState.BOARD);
    }

    public Game_INTERFACE getGame() {
        return game;
    }

    public int getSlotnumber() {
        return slotnumber;
    }

    public void setSlotnumber(int slotnumber) {
        this.slotnumber = slotnumber;
    }

    public JButton getSlots(int i) {
        return this.slots[i-1];
    }

}

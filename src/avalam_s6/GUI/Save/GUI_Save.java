/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Save;

import avalam_s6.Core.Globals.SaveInfoLister;
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
import javax.swing.SwingConstants;

/**
 *
 * @author ducruyy
 */
public class GUI_Save extends JPanel implements Gui_INTERFACE {

    private JButton homereturn, saveload;
    private JTextField field;
    private final JButton[] slots;
    private final SaveListener[] slotslistener;
    private int slotnumber;
    private Image backgroundsave, saveI, returnI, slot;
    private final SaveAdapterListener listener;
    private Boolean callResize;
    private final JLabel[] slotlabels;

    public GUI_Save() {
        this.listener = new SaveAdapterListener(this);
        this.callResize = false;
        this.slotnumber = 0;
        this.slots = new JButton[6];
        this.slotslistener = new SaveListener[6];
        this.slotlabels = new JLabel[5];
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

        Font localFont = new Font("Arial", Font.PLAIN, 60);
        try {
            localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/font/Gamaliel.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - " + GUI_Save.class.toString());
            Logger.getLogger(GUI_Save.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < this.slots.length; i++) {
            int j = i + 1;
            this.slots[i] = new JButton(new ImageIcon(this.slot));
            this.slots[i].setBorder(BorderFactory.createEmptyBorder());
            this.slots[i].setContentAreaFilled(false);
            this.slots[i].setFocusPainted(false);
            this.slotslistener[i] = new SaveListener("slot", this, i + 1);
            this.slots[i].addMouseListener(this.slotslistener[i]);
            this.add(this.slots[i]);
        }
        for (int i = 0; i < this.slotlabels.length; i++) {
            int j = i + 1;
            this.slotlabels[i] = new JLabel("Slot " + j);
            this.slotlabels[i].setBorder(BorderFactory.createEmptyBorder());
            this.slotlabels[i].setFont(localFont.deriveFont(1 * 30f));
            this.add(this.slotlabels[i]);
        }

        this.field = new JTextField();
        this.field.setFont(localFont.deriveFont(45f));
        this.field.setBorder(BorderFactory.createEmptyBorder());
        this.field.setText("Nom de fichier");
        this.field.setOpaque(false);

        this.add(this.homereturn);
        this.add(this.saveload);
        this.add(this.field);
        this.addComponentListener(listener);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.backgroundsave, 0, 0, this.getWidth(), this.getHeight(), null);
        if (this.callResize == true) {
            this.listener.componentResized(null);
            this.callResize = false;
        }
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

    public int getSlotnumber() {
        return slotnumber;
    }

    public void setSlotnumber(int slotnumber) {
        this.slotnumber = slotnumber;
    }

    public JButton getSlots(int i) {
        return this.slots[i - 1];
    }

    public SaveListener getSlotslistener(int i) {
        return slotslistener[i - 1];
    }

    public SaveListener[] getSlotslistener() {
        return slotslistener;
    }

    public JTextField getField() {
        return field;
    }

    @Override
    public void callResize() {
        this.callResize = true;
    }

    public JLabel getSlotlabels(int i) {
        return this.slotlabels[i - 1];
    }

    public void loadSlotText() {
        SaveInfoLister sil;
        int j;
        for (int i = 0; i < this.slotlabels.length; i++) {
            j = i + 1;
            try {
                sil = new SaveInfoLister("slot_" + j);
                if (!sil.getEmptyslot()) {
                    sil = new SaveInfoLister("slot_" + j);
                    this.slotlabels[i].setFont(this.slotlabels[i].getFont().deriveFont(1 * 30f));
                    this.slotlabels[i].setText(sil.getDate() + " " + sil.getPlayer1() + " VS " + sil.getPlayer2() + "  " + sil.getGrid());
                }

            } catch (IOException ex) {
                //ne pas traiter => on laisse le texte par défaut
            }
        }
        this.callResize();
    }
}

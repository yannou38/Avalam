/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Load;

import avalam_s6.GUI.Save.*;
import avalam_s6.Core.Game_INTERFACE;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ducruyy
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

    public GUI_Load() {
        this.callResize = false;
        this.listener = new LoadAdapterListener(this);
        this.slotnumber = 0;
        this.slots = new JButton[6];
        this.slotslistener = new LoadListener[6];
        initComponents();
    }

    private void initComponents() {
        try {
            this.backgroundload = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/load/background_load.png"));
            this.homeI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/load/home.png"));
            this.loadI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/load/load.png"));
            this.slot = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/load/slot.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_Load.class.toString());
            Logger.getLogger(GUI_Load.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.homereturn = new JButton(new ImageIcon(this.homeI));
        this.homereturn.setBorder(BorderFactory.createEmptyBorder());
        this.homereturn.setContentAreaFilled(false);
        this.homereturn.setFocusPainted(false);
        this.homereturn.addMouseListener(new LoadListener("home",this,0));

        this.saveload = new JButton(new ImageIcon(this.loadI));
        this.saveload.setBorder(BorderFactory.createEmptyBorder());
        this.saveload.setContentAreaFilled(false);
        this.saveload.setFocusPainted(false);
        this.saveload.addMouseListener(new LoadListener("load",this,0));
        
        Font localFont = new Font("Arial", Font.PLAIN, 60);
        try {
            localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/font/Gamaliel.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - " + GUI_Save.class.toString());
            Logger.getLogger(GUI_Save.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < this.slots.length; i++) {
            this.slots[i] = new JButton(new ImageIcon(slot));
            this.slots[i].setBorder(BorderFactory.createEmptyBorder());
            this.slots[i].setContentAreaFilled(false);
            this.slots[i].setFocusPainted(false);
            this.slotslistener[i] = new LoadListener("slot",this,i+1);
            this.slots[i].addMouseListener(this.slotslistener[i]);
            this.add(this.slots[i]);
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
        g.drawImage(this.backgroundload, 0, 0, this.getWidth(), this.getHeight(), null);
        if (this.callResize) {
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
        mainFrame.setwState(WindowState.MAIN);
    }

    @Override
    public void callResize() {
        this.callResize = true;
    }

    public JButton getSlots(int i) {
        return this.slots[i-1];
    }

    public JTextField getField() {
        return field;
    }

    public LoadListener[] getSlotslistener() {
        return slotslistener;
    }
    
    
    public void setSlotnumber(int slotnumber) {
        this.slotnumber = slotnumber;
    }

    public LoadListener getSlotslistener(int i) {
        return slotslistener[i - 1];
    }

    public int getSlotnumber() {
        return slotnumber;
    }
    
    

    
}

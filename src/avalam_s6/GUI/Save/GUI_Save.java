/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Save;

import avalam_s6.Core.Game_INTERFACE;
import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Gui_INTERFACE;
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
    private Image backgroundload, backgroundsave, homeI, loadI, saveI, returnI;
    private SaveState etat;
    private final SaveAdapterListener listener;
    
    private SaveListener saveloadL, homereturnL;

    private enum SaveState {

        SAVE(0),
        LOAD(1);
        
        private final int value;
        private SaveState(int value) {
            this.value = value;
        }
        public int getValue() {
            return this.value;
        }
    }

    public GUI_Save() {
        this.listener = new SaveAdapterListener(this);
        this.etat = SaveState.SAVE;
        saveloadL = new SaveListener("save");
        homereturnL = new SaveListener("load");
        initComponents();
    }

    private void initComponents() {
        try {
            this.backgroundload = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/background_load.png"));
            this.backgroundsave = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/background_save.png"));
            this.homeI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/home.png"));
            this.loadI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/load.png"));
            this.saveI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/save.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/save/return.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_Save.class.toString());
            Logger.getLogger(GUI_Save.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.homereturn = new JButton(new ImageIcon(this.returnI));        
        this.homereturn.setBorder(BorderFactory.createEmptyBorder());
        this.homereturn.setContentAreaFilled(false);
        this.homereturn.setFocusPainted(false);
        this.homereturn.addMouseListener(homereturnL);
        
        this.saveload = new JButton(new ImageIcon(this.saveI));        
        this.saveload.setBorder(BorderFactory.createEmptyBorder());
        this.saveload.setContentAreaFilled(false);
        this.saveload.setFocusPainted(false);
        this.saveload.addMouseListener(saveloadL);
        
        
        this.add(this.homereturn);
        this.add(this.saveload);
        this.addComponentListener(listener);
    }

    public void reload(int etat, Game_INTERFACE game) {
        //0 pour save, 1 pour load
        if (etat == 1) {
            this.etat = SaveState.LOAD;
            this.homereturn.setIcon(new ImageIcon(this.homeI));
            this.saveload.setIcon(new ImageIcon(this.loadI));
            this.saveload.removeMouseListener(saveloadL);
            saveloadL = new SaveListener("load");
            this.saveload.addMouseListener(this.saveloadL);
        } 
        else {
            this.etat = SaveState.SAVE;
            this.homereturn.setIcon(new ImageIcon(this.returnI));
            this.saveload.setIcon(new ImageIcon(this.saveI));
            this.saveload.removeMouseListener(saveloadL);
            saveloadL = new SaveListener("save");
            this.saveload.addMouseListener(this.saveloadL);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        if (this.etat == SaveState.SAVE) {
            g.drawImage(this.backgroundsave, 0, 0, this.getWidth(), this.getHeight(), null);
        } else {
            g.drawImage(this.backgroundload, 0, 0, this.getWidth(), this.getHeight(), null);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

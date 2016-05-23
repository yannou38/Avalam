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
public class GUI_Load extends JPanel implements Gui_INTERFACE {

    private JButton homereturn, saveload;
    private Image backgroundload, homeI, loadI;
    private final LoadAdapterListener listener;

    public GUI_Load() {
        this.listener = new LoadAdapterListener(this);
        initComponents();
    }

    private void initComponents() {
        try {
            this.backgroundload = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/load/background_load.png"));
            this.homeI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/load/home.png"));
            this.loadI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/load/load.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_Load.class.toString());
            Logger.getLogger(GUI_Load.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.homereturn = new JButton(new ImageIcon(this.homeI));
        this.homereturn.setBorder(BorderFactory.createEmptyBorder());
        this.homereturn.setContentAreaFilled(false);
        this.homereturn.setFocusPainted(false);
        this.homereturn.addMouseListener(new LoadListener("home"));

        this.saveload = new JButton(new ImageIcon(this.loadI));
        this.saveload.setBorder(BorderFactory.createEmptyBorder());
        this.saveload.setContentAreaFilled(false);
        this.saveload.setFocusPainted(false);
        this.saveload.addMouseListener(new LoadListener("load"));
        
        this.add(this.homereturn);
        this.add(this.saveload);
        this.addComponentListener(listener);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.backgroundload, 0, 0, this.getWidth(), this.getHeight(), null);
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

}

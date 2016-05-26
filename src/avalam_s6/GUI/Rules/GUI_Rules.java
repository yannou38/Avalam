/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Rules;

import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.GUI.Gui_INTERFACE;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author ducruyy
 */
public class GUI_Rules extends JPanel implements Gui_INTERFACE {

    private JButton retour;
    private Image returnI, background;
    private boolean callResize;
    private final RulesAdapterListener listener;

    public GUI_Rules() {
        this.callResize = false;
        this.listener = new RulesAdapterListener(this);
        this.initComponents();
    }

    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/rules/background.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/rules/home.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_Rules.class.toString());
            Logger.getLogger(GUI_Rules.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.retour = new JButton(new ImageIcon(this.returnI));
        this.retour.setBorder(BorderFactory.createEmptyBorder());
        this.retour.setContentAreaFilled(false);
        this.retour.setFocusPainted(false);
        this.retour.addMouseListener(new RulesListener("home"));

        this.setLayout(null);
        this.add(this.retour);
        this.addComponentListener(this.listener);

    }

    public JButton getRetour() {
        return this.retour;
    }

    public Image getReturnI() {
        return returnI;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);
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

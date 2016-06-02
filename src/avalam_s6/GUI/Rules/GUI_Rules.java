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
 * Interface showing rules of the game int othe current language.
 * @author Team 7
 */
public class GUI_Rules extends JPanel implements Gui_INTERFACE {

    private JButton retour;
    private Image returnI, background;
    private boolean callResize;
    private final RulesAdapterListener listener;

    /**
     * Constructor.
     */
    public GUI_Rules() {
        this.callResize = false;
        this.listener = new RulesAdapterListener(this);
        this.initComponents();
    }

    /**
     * Initialize content of the GUI_Rules.
     */
    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/rules/background.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/rules/home.png"));
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

    /**
     * Getter
     * @return HomePage Button
     */
    public JButton getRetour() {
        return this.retour;
    }

    /**
     * Getter
     * @return HomePage Button's Image
     */
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

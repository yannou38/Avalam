/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.Confirm;

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
 * @author sazeratj
 */
public class GUI_Confirm extends JPanel implements Gui_INTERFACE {

    private String prevWindow;
    private String title;
    private JButton yes, no;
    private boolean callResize;
    private final ConfirmAdapterListener listener;
    private Image yesI, noI, background;

    public GUI_Confirm() {
        this.prevWindow = "HomePage";
        this.title = "Do you really want to quit ?";

        this.callResize = false;
        this.listener = new ConfirmAdapterListener(this);
        this.initComponents();
    }

    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/confirm/confirm.png"));
            this.yesI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/confirm/yes.png"));
            this.noI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/confirm/no.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_Confirm.class.toString());
            Logger.getLogger(GUI_Confirm.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.yes = new JButton(new ImageIcon(this.yesI));
        this.yes.setBorder(BorderFactory.createEmptyBorder());
        this.yes.setContentAreaFilled(false);
        this.yes.setFocusPainted(false);
        this.yes.addMouseListener(new ConfirmListener("yes"));

        this.no = new JButton(new ImageIcon(this.noI));
        this.no.setBorder(BorderFactory.createEmptyBorder());
        this.no.setContentAreaFilled(false);
        this.no.setFocusPainted(false);
        this.no.addMouseListener(new ConfirmListener("no"));

        this.setLayout(null);
        this.add(this.yes);
        this.add(this.no);
        this.addComponentListener(this.listener);

    }

    void doAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void cancelAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setConfirmPage(String title, String page) {
        this.prevWindow = page;
        this.title = title;
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
        if (this.prevWindow.equals("HomePage")) {
            mainFrame.dispose();
        } else if (this.prevWindow.equals("Save")) {
            mainFrame.setwState(WindowState.SAVE);
        }
    }

    @Override
    public void callResize() {
        this.callResize = true;
    }

}

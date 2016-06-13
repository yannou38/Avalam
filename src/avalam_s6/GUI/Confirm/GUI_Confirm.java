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
package avalam_s6.GUI.Confirm;

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

/**
 * This class instanciates a confirmation page usefull during saves and quit.
 *
 * @author Team 7
 *
 */
public class GUI_Confirm extends JPanel implements Gui_INTERFACE {

    private String prevWindow;
    private JLabel titre;
    private String privatedata;
    private JButton yes, no;
    private boolean callResize;
    private final ConfirmAdapterListener listener;
    private Image yesI, noI, background;

    /**
     * Constructor
     */
    public GUI_Confirm() {
        this.prevWindow = "HomePage";

        this.callResize = false;
        this.listener = new ConfirmAdapterListener(this);
        this.initComponents();
    }

    /**
     * initialises all the components, loads all the buttons and images.
     */
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

        Font localFont = new Font("Arial", Font.PLAIN, 60);
        try {
            localFont = Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/font/Gamaliel.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(localFont);
        } catch (IOException | FontFormatException ex) {
            System.out.println("Error - " + GUI_Confirm.class.toString());
            Logger.getLogger(GUI_Confirm.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.titre = new JLabel();
        this.titre.setHorizontalAlignment(JLabel.CENTER);
        this.titre.setVerticalAlignment(JLabel.CENTER);
        this.titre.setBorder(BorderFactory.createEmptyBorder());
        this.titre.setFont(localFont.deriveFont(1 * 60f));
        this.add(this.titre);

        this.setLayout(null);
        this.add(this.yes);
        this.add(this.no);
        this.addComponentListener(this.listener);

    }

    /**
     * Executes the action that needed confirmation.
     */
    void doAction() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());
        if (this.prevWindow.equals("Save")) {
            mainFrame.save(this.privatedata);

        } else {
            mainFrame.dispose();
        }
        mainFrame.backWindow();

    }

    /**
     * Cancels the action that needed confirmation.
     */
    void cancelAction() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());
        if (this.prevWindow.equals("Save")) {
            mainFrame.setwState(WindowState.SAVE);

        } else {

            mainFrame.setwState(WindowState.MAIN);
        }
    }

    /**
     * Setter Sets the previous page (save or mainpage). Sets the title of this
     * window.
     *
     * @param title the title to set
     * @param page the previous page
     */
    public void setConfirmPage(String title, String page) {
        this.prevWindow = page;
        this.titre.setText(title);
    }

    /**
     * Setter Sets the save filename
     *
     * @param privatedata name of a save slot
     */
    public void setPrivatedata(String privatedata) {
        this.privatedata = privatedata;
    }

    /**
     * Getter
     *
     * @return yes button
     */
    public JButton getYes() {
        return yes;
    }

    /**
     * Getter
     *
     * @return no button
     */
    public JButton getNo() {
        return no;
    }

    /**
     * Getter
     *
     * @return yes image
     */
    public Image getYesI() {
        return yesI;
    }

    /**
     * Getter
     *
     * @return no image
     */
    public Image getNoI() {
        return noI;
    }

    /**
     * Getter
     *
     * @return title of the page
     */
    public JLabel getTitre() {
        return titre;
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
            mainFrame.setwState(WindowState.MAIN);
        } else if (this.prevWindow.equals("Save")) {
            mainFrame.setwState(WindowState.SAVE);
        }
    }

    @Override
    public void callResize() {
        this.callResize = true;
    }

}

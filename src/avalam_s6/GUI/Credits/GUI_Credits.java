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
package avalam_s6.GUI.Credits;

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
 * This Panel contain the credits, and a button to go back.
 *
 * @author Team 7
 */
public class GUI_Credits extends JPanel implements Gui_INTERFACE {

    private JButton retour;
    private Image returnI, background;
    private boolean callResize;
    private final CreditsAdapterListener listener;

    /**
     * Constructor.
     */
    public GUI_Credits() {
        this.callResize = false;
        this.listener = new CreditsAdapterListener(this);
        this.initComponents();
    }

    /**
     * Initialize the Components.
     */
    private void initComponents() {
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/credits/background.png"));
            this.returnI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Theme") + "/" + SetupManager.getElement("Langue") + "/credits/home.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_Credits.class.toString());
            Logger.getLogger(GUI_Credits.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.retour = new JButton(new ImageIcon(this.returnI));
        this.retour.setBorder(BorderFactory.createEmptyBorder());
        this.retour.setContentAreaFilled(false);
        this.retour.setFocusPainted(false);
        this.retour.addMouseListener(new CreditsListener("home"));

        this.setLayout(null);
        this.add(this.retour);
        this.addComponentListener(this.listener);

    }

    /**
     * Getter.
     *
     * @return The return button
     */
    public JButton getRetour() {
        return this.retour;
    }

    /**
     * Getter.
     *
     * @return The return image
     */
    public Image getReturnI() {
        return returnI;
    }

    /**
     * Override of the standard paintComponent() function.
     *
     * @param g The graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);
        if (this.callResize) {
            this.listener.componentResized(null);
            this.callResize = false;
        }
    }

    /**
     * Go back to the HomePage.
     */
    @Override
    public void back() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());
        mainFrame.setwState(WindowState.MAIN);
    }

    /**
     * Call the resize function once in the next repaint().
     */
    @Override
    public void callResize() {
        this.callResize = true;
    }

}

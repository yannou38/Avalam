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
package avalam.GUI.HomePage;

import avalam.Core.Globals.SetupManager;
import avalam.GUI.Gui_INTERFACE;
import avalam.GUI.Main_Frame;
import avalam.GUI.WindowState;
import java.awt.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class contain the home page with the avalam logo, and the diverse
 * buttons to create a game, access options, check rules & tutorial, etc
 *
 * @author Team 7
 */
public class GUI_HomePage extends JPanel implements Gui_INTERFACE {

    private JButton quick, play, settings, rules, exit, load;
    private Image background, quickI, playI, settingsI, rulesI, loadI, exitI;
    private boolean callResize;
    private final HomePageAdapterListener listener;

    /**
     * Constructor Initialises the page
     */
    public GUI_HomePage() {
        this.callResize = false;
        this.listener = new HomePageAdapterListener(this);
        initComponents();
    }

    /**
     * Initialises each component of the HomePage All images are loaded with
     * this function
     */
    private void initComponents() {

        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/main/main_bg.png"));
            this.playI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/main/customgame.png"));
            this.quickI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/main/quickgame.png"));
            this.settingsI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/main/options.png"));
            this.rulesI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/main/rules.png"));
            this.exitI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/main/quit.png"));
            this.loadI = ImageIO.read(new File("./ressources/Themes/" + SetupManager.getElement("Langue") + "/" + SetupManager.getElement("Theme") + "/main/load.png"));
        } catch (Exception ex) {
            System.out.println("Error - " + GUI_HomePage.class.toString());
            Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.quick = new JButton(new ImageIcon(this.quickI));
        this.quick.setBorder(BorderFactory.createEmptyBorder());
        this.quick.setContentAreaFilled(false);
        this.quick.setFocusPainted(false);
        this.quick.addMouseListener(new HomePageListener("quickgame"));

        this.play = new JButton(new ImageIcon(this.playI));
        this.play.setBorder(BorderFactory.createEmptyBorder());
        this.play.setContentAreaFilled(false);
        this.play.setFocusPainted(false);
        this.play.addMouseListener(new HomePageListener("customgame"));

        this.rules = new JButton(new ImageIcon(this.rulesI));
        this.rules.setBorder(BorderFactory.createEmptyBorder());
        this.rules.setContentAreaFilled(false);
        this.rules.setFocusPainted(false);
        this.rules.addMouseListener(new HomePageListener("rules"));

        this.load = new JButton(new ImageIcon(this.loadI));
        this.load.setBorder(BorderFactory.createEmptyBorder());
        this.load.setContentAreaFilled(false);
        this.load.setFocusPainted(false);
        this.load.addMouseListener(new HomePageListener("load"));

        this.settings = new JButton(new ImageIcon(this.settingsI));
        this.settings.setBorder(BorderFactory.createEmptyBorder());
        this.settings.setContentAreaFilled(false);
        this.settings.setFocusPainted(false);
        this.settings.addMouseListener(new HomePageListener("options"));

        this.exit = new JButton(new ImageIcon(this.exitI));
        this.exit.setBorder(BorderFactory.createEmptyBorder());
        this.exit.setContentAreaFilled(false);
        this.exit.setFocusPainted(false);
        this.exit.addMouseListener(new HomePageListener("quit"));

        this.setLayout(null);
        this.add(this.quick);
        this.add(this.play);
        this.add(this.rules);
        this.add(this.settings);
        this.add(this.exit);
        this.add(load);
        this.addComponentListener(this.listener);

    }

    /**
     * Getter
     *
     * @return QuickGame button
     */
    public JButton getQuick() {
        return this.quick;
    }

    /**
     * Getter
     *
     * @return CustomGame button
     */
    public JButton getPlay() {
        return this.play;
    }

    /**
     * Getter
     *
     * @return Settings button
     */
    public JButton getSettings() {
        return this.settings;
    }

    /**
     * Getter
     *
     * @return Rules button
     */
    public JButton getRules() {
        return this.rules;
    }

    /**
     * Getter
     *
     * @return Exit button
     */
    public JButton getExit() {
        return this.exit;
    }

    /**
     * Getter
     *
     * @return Return LoadGame button
     */
    public JButton getLoad() {
        return load;
    }

    /**
     * Getter
     *
     * @return QuickGame image
     */
    public Image getQuickI() {
        return quickI;
    }

    /**
     * Getter
     *
     * @return CustomGame image
     */
    public Image getPlayI() {
        return playI;
    }

    /**
     * Getter
     *
     * @return Settings image
     */
    public Image getSettingsI() {
        return settingsI;
    }

    /**
     * Getter
     *
     * @return Rules image
     */
    public Image getRulesI() {
        return rulesI;
    }

    /**
     * Getter
     *
     * @return LoadGame image
     */
    public Image getLoadI() {
        return loadI;
    }

    /**
     * Getter
     *
     * @return Exit image
     */
    public Image getExitI() {
        return exitI;
    }

    @Override
    public void back() {
        Main_Frame mainFrame = ((Main_Frame) this.getParent().getParent().getParent().getParent());
        mainFrame.setConfirmQuitter();
        mainFrame.setwState(WindowState.YESNO);
    }

    @Override
    public void callResize() {
        this.callResize = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);
        if (this.callResize) {
            this.listener.componentResized(null);
            this.callResize = false;
        }
    }

}

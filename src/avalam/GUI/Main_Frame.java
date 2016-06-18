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
package avalam.GUI;

import avalam.Core.Globals.EnumsLister;
import avalam.Core.Globals.LanguageManager;
import avalam.Core.Globals.SetupManager;
import avalam.Core.Globals.SoundEngine;
import avalam.GUI.Confirm.GUI_Confirm;
import avalam.GUI.HomePage.GUI_HomePage;
import avalam.GUI.LocalAvalamGame.GUI_LAG;
import avalam.GUI.NewGame.GUI_NewGame;
import avalam.GUI.Settings.GUI_Settings;
import avalam.GUI.Credits.GUI_Credits;
import avalam.GUI.Load.GUI_Load;
import avalam.GUI.Rules.GUI_Rules;
import avalam.GUI.Save.GUI_Save;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import static java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main class that manage and displays all the GUIs
 *
 * @author Team 7
 */
public class Main_Frame extends JFrame implements GuiManager_INTERFACE, Runnable {

    private WindowState wState;
    private JPanel[] panelList;

    /**
     * Mainframe constructor.
     */
    public Main_Frame() {
        SetupManager.load();
        EnumsLister.init();
        /* FUNCTION CALL */
        this.initFrame();
        /* ADD KB DISPATCHER */
        getCurrentKeyboardFocusManager().addKeyEventDispatcher(new CustomKeyboardDispatcher(this));
        SoundEngine.play("./ressources/Themes/"+SetupManager.getElement("Langue")+"/"+SetupManager.getElement("Theme")+"/sounds/game_ambient.wav");
        if (SetupManager.getElement("Son").equals("Non")) {
            SoundEngine.toggleMute();
        }
        this.addWindowFocusListener(new MainFrameListener(this));
    }
    
    public void resizeCurrent(){        
        ((Gui_INTERFACE) this.panelList[this.wState.getValue()]).callResize();
    }

    /**
     * Set the software to fullscreen or not, depending of the setup.
     */
    public void setRenderMode() {
        this.dispose();
        if (SetupManager.getElement("FullScreen").equals("Oui")) {
            this.setUndecorated(true);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        } else {
            this.setUndecorated(false);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
            this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        }
        this.pack();
        this.setMinimumSize(new Dimension(960, 540));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        //this.requestFocus();
    }

    /**
     * Change the fullscreen setup, then call setRenderMode().
     */
    public void toggleWRM() {
        if (SetupManager.getElement("FullScreen").equals("Oui")) {
            SetupManager.setElement("FullScreen", "Non");
        } else {
            SetupManager.setElement("FullScreen", "Oui");
        }
        this.setRenderMode();
        this.resetSettings();
    }

    /**
     * Toogle mute in the sound engine.
     */
    public void toggleMute() {
        SoundEngine.toggleMute();
        this.resetSettings();
    }

    /**
     * Change the setup labels to the actual setup.
     */
    private void resetSettings() {
        ((GUI_Settings) this.panelList[WindowState.SETTINGS.getValue()]).initOptions();
        ((GUI_Settings) this.panelList[WindowState.SETTINGS.getValue()]).retextLabels();
        ((GUI_Settings) this.panelList[WindowState.SETTINGS.getValue()]).callResize();
    }

    /**
     * Create the panels array and toogle fullscreen and sound based on setup.
     *
     */
    public void initFrame() {
        this.wState = WindowState.MAIN;
        if (this.panelList != null) {
            for (JPanel pElement : this.panelList) {
                pElement.setVisible(false);
            }
        }
        this.panelList = new JPanel[9];
        this.panelList[WindowState.MAIN.getValue()] = new GUI_HomePage();
        this.panelList[WindowState.BOARD.getValue()] = new GUI_LAG();
        this.panelList[WindowState.PLAYERSELECT.getValue()] = new GUI_NewGame();
        this.panelList[WindowState.SETTINGS.getValue()] = new GUI_Settings();
        this.panelList[WindowState.ABOUT.getValue()] = new GUI_Credits();
        this.panelList[WindowState.SAVE.getValue()] = new GUI_Save();
        this.panelList[WindowState.LOAD.getValue()] = new GUI_Load();
        this.panelList[WindowState.RULES.getValue()] = new GUI_Rules();
        this.panelList[WindowState.YESNO.getValue()] = new GUI_Confirm();
        for (JPanel pElement : this.panelList) {
            pElement.setVisible(false);
        }
        ((GUI_HomePage) this.panelList[WindowState.MAIN.getValue()]).callResize();
        this.setwState(WindowState.MAIN);
        this.setRenderMode();
        if ((SoundEngine.isMuted() && SetupManager.getElement("Son").equals("Oui")) || (!SoundEngine.isMuted() && SetupManager.getElement("Son").equals("Non"))) {
            SoundEngine.toggleMute();
        }
    }

    /**
     * Change the actual viewed panel.
     *
     * @param wState the panel you want to see (WindowState enum)
     */
    public void setwState(WindowState wState) {
        this.panelList[this.wState.getValue()].setVisible(false);
        this.remove(this.panelList[this.wState.getValue()]);
        this.wState = wState;
        this.panelList[this.wState.getValue()].setVisible(true);
        this.resizeCurrent();
        this.add(this.panelList[this.wState.getValue()]);

        this.setTitle("Avalam - " + this.wState.getName());
    }

    /**
     * Repaint the frame.
     */
    @Override
    public void render() {
        this.repaint();
    }

    /**
     * Run the frame.
     */
    @Override
    public void run() {
        this.setLocationRelativeTo(null);
        this.setRenderMode();
    }

    /**
     * Initialize a game with standard parameters.
     */
    public void initGame() {
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).initGame(this);
    }

    /**
     * Start the game timer and set the window to display the board.
     */
    public void startGame() {
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).start();
        this.setwState(WindowState.BOARD);
    }

    /**
     * Initialize the Game with custom parameters.
     *
     * @param p1 Player_1 [Class, Name, Color]
     * @param p2 Player_2 [Class, Name, Color]
     * @param gridName Name of the Grid
     */
    public void initGame(String[] p1, String[] p2, String gridName) {
        p1[0] = createClass(p1[0]);
        p2[0] = createClass(p2[0]);
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).initGame(this, p1[0], p1[1], p1[2], p2[0], p2[1], p2[2], gridName);
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).start();
        this.setwState(WindowState.BOARD);
    }

    /**
     * Return the AI/Player class corresponding to the param.
     *
     * @param xmlChoice The name picked in the xml file
     * @return The Class name of the Player/AI
     */
    public String createClass(String xmlChoice) {
        switch (xmlChoice) {
            case "player":
                return "ControlledPlayer";
            case "ia_easy":
                return "AIPlayerEasy";
            case "ia_mid":
                return "AIPlayerMedium";
            case "ia_hard":
                return "AIPlayerHard";
            case "ia_exp":
                return "AIPlayerHardAB";
            case "ia_leg":
                return "AIPlayerHardMC";
        }
        return null;
    }

    /**
     * Stop the game and change the panel title.
     *
     * @param s The panel title
     */
    public void setVictory(String s) {

        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).stop();
        this.setGameTitle(s);
    }

    /**
     * Change the title of the game panel.
     *
     * @param s The panel title
     */
    public void setGameTitle(String s) {
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).setTitle(s);
    }

    /**
     * Save the game in a file.
     *
     * @param pSlotName The file name (without path)
     */
    public void save(String pSlotName) {
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).save(pSlotName);
    }

    /**
     * Load the game from a file.
     *
     * @param pSlotName The file name (without path)
     */
    public void load(String pSlotName) {
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).load(this, pSlotName);
    }

    /**
     * Change the settings according to the params. Will reload all the panels.
     *
     * @param Language The new language
     * @param FS The fullscreen mode
     * @param Theme The new theme
     * @param Sound The sound mode
     */
    public void changeSettings(String Language, String FS, String Theme, String Sound) {
        SetupManager.setElement("Langue", Language);
        SetupManager.setElement("FullScreen", FS);
        SetupManager.setElement("Theme", Theme);
        SetupManager.setElement("Son", Sound);
        this.initFrame();
    }

    /**
     * Call the back() function of the current panel.
     */
    @Override
    public void backWindow() {
        ((Gui_INTERFACE) this.panelList[this.wState.getValue()]).back();
    }

    /**
     * Reset the hint displayed on the board.
     */
    public void resetHint() {
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).setHint(null);
    }

    /**
     * Change the confirm panel text fot exit confirmation.
     */
    public void setConfirmQuitter() {
        ((GUI_Confirm) this.panelList[WindowState.YESNO.getValue()]).setConfirmPage(LanguageManager.getElement("Quit"), "HomePage");
    }

    /**
     * Change the confirm panel text fot save confirmation.
     *
     * @param slot The file name to save
     */
    public void setConfirmSauver(String slot) {
        ((GUI_Confirm) this.panelList[WindowState.YESNO.getValue()]).setConfirmPage(LanguageManager.getElement("Save"), "Save");
        ((GUI_Confirm) this.panelList[WindowState.YESNO.getValue()]).setPrivatedata(slot);
    }
}

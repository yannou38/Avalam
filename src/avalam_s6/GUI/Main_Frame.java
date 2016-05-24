/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import avalam_s6.Core.Game_INTERFACE;
import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.Core.Globals.SoundEngine;
import avalam_s6.Core.Grid;
import avalam_s6.GUI.FinalScreen.GUI_FinalScreen;
import avalam_s6.GUI.HomePage.GUI_HomePage;
import avalam_s6.GUI.LocalAvalamGame.GUI_LAG;
import avalam_s6.GUI.NewGame.GUI_NewGame;
import avalam_s6.GUI.Settings.GUI_Settings;
import avalam_s6.GUI.Credits.GUI_Credits;
import avalam_s6.GUI.Load.GUI_Load;
import avalam_s6.GUI.Rules.GUI_Rules;
import avalam_s6.GUI.Save.GUI_Save;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import static java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author sazeratj
 */
public class Main_Frame extends JFrame implements GuiManager_INTERFACE, Runnable {

    private WindowState wState;
    private JPanel[] panelList;

    public Main_Frame() {
        SetupManager.load();
        /* FUNCTION CALL */
        this.initFrame(WindowState.MAIN);
        /* ADD KB DISPATCHER */
        getCurrentKeyboardFocusManager().addKeyEventDispatcher(new CustomKeyboardDispatcher(this));
    }

    public void setRenderMode() {
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
        if (SetupManager.getElement("FullScreen").equals("Oui")) {
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        } else {
            this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        }
        this.setMinimumSize(new Dimension(960, 540));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.requestFocus();
    }

    public void toggleWRM() {
        if (SetupManager.getElement("FullScreen").equals("Oui")) {
            SetupManager.setElement("FullScreen", "Non");
        } else {
            SetupManager.setElement("FullScreen", "Oui");
        }
        setRenderMode();
        
        resetSettings();
    }
    
    private void resetSettings() {
        this.panelList[WindowState.SETTINGS.getValue()].setVisible(false);
        this.remove(this.panelList[WindowState.SETTINGS.getValue()]);
        this.panelList[WindowState.SETTINGS.getValue()] = new GUI_Settings();
        
        this.panelList[WindowState.SETTINGS.getValue()].setVisible(this.wState.getValue() == WindowState.SETTINGS.getValue());
        
        ((Gui_INTERFACE)this.panelList[WindowState.SETTINGS.getValue()]).callResize();
        this.add(this.panelList[WindowState.SETTINGS.getValue()]);
    }

    public void initFrame(WindowState wState) {
        this.wState = wState;
        if (this.panelList != null) {
            for (JPanel pElement : this.panelList) {
                pElement.setVisible(false);
            }
        }
        this.panelList = new JPanel[9];
        this.panelList[WindowState.MAIN.getValue()] = new GUI_HomePage();
        this.panelList[WindowState.BOARD.getValue()] = new GUI_LAG();
        this.panelList[WindowState.VICTORY.getValue()] = new GUI_FinalScreen();
        this.panelList[WindowState.PLAYERSELECT.getValue()] = new GUI_NewGame();
        this.panelList[WindowState.SETTINGS.getValue()] = new GUI_Settings();
        this.panelList[WindowState.ABOUT.getValue()] = new GUI_Credits();
        this.panelList[WindowState.SAVE.getValue()] = new GUI_Save();
        this.panelList[WindowState.LOAD.getValue()] = new GUI_Load();
        this.panelList[WindowState.RULES.getValue()] = new GUI_Rules();
        for (JPanel pElement : this.panelList) {
            //this.add(pElement);
            pElement.setVisible(false);
        }
        this.setwState(WindowState.MAIN);
        this.setRenderMode();
    }

    public void setwState(WindowState wState) {
        this.panelList[this.wState.getValue()].setVisible(false);
        this.remove(this.panelList[this.wState.getValue()]);
        this.wState = wState;
        this.panelList[this.wState.getValue()].setVisible(true);
        ((Gui_INTERFACE)this.panelList[this.wState.getValue()]).callResize();
        this.add(this.panelList[this.wState.getValue()]);
    }

    @Override
    public void render() {
        this.repaint();
    }

    @Override
    public void run() {
        this.pack();
        this.setVisible(true);
    }

    public void initGame() {
        this.setwState(WindowState.BOARD);
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).initGame();
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).start();
    }

    public void initGame(String[] p1, String[] p2) {
        this.setwState(WindowState.BOARD);
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).initGame(p1, p2);
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).start();
    }

    public void setVictoryScreen(String p, Grid g) {
        ((GUI_FinalScreen) this.panelList[WindowState.VICTORY.getValue()]).setGrid(g);
        ((GUI_FinalScreen) this.panelList[WindowState.VICTORY.getValue()]).setWinningText(p);
        this.setwState(WindowState.VICTORY);
    }
    
    public void save(String pSlotName){
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).save(pSlotName);
    }
    
    public void load(String pSlotName) {
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).load(pSlotName);
    }

    public void changeSettings(String Language, String FS, String Theme, String Sound) {
        SetupManager.setElement("Langue", Language);
        SetupManager.setElement("FullScreen", FS);
        SetupManager.setElement("Theme", Theme);
        SetupManager.setElement("Son", Sound);
        this.initFrame(WindowState.SETTINGS);
    }

    @Override
    public void backWindow() {
        ((Gui_INTERFACE)this.panelList[this.wState.getValue()]).back();
    }
}

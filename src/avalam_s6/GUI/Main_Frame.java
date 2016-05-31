/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import avalam_s6.Core.Globals.EnumsLister;
import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.Core.Globals.SoundEngine;
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
        EnumsLister.init();
        /* FUNCTION CALL */
        this.initFrame(WindowState.MAIN);
        /* ADD KB DISPATCHER */
        getCurrentKeyboardFocusManager().addKeyEventDispatcher(new CustomKeyboardDispatcher(this));
    }

    public void setRenderMode() {
        if (SetupManager.getElement("FullScreen").equals("Oui")) {
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        } else {
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
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
        this.setRenderMode();
        this.resetSettings();
    }
    
    public void toggleMute() {
        SoundEngine.toggleMute();
        this.resetSettings();
        ((GUI_Settings) this.panelList[WindowState.SETTINGS.getValue()]).callResize();
    }
    
    private void resetSettings(){
        ((GUI_Settings) this.panelList[WindowState.SETTINGS.getValue()]).initOptions();
        ((GUI_Settings) this.panelList[WindowState.SETTINGS.getValue()]).retextLabels();
    }

    public void initFrame(WindowState wState) {
        this.wState = wState;
        if (this.panelList != null) {
            for (JPanel pElement : this.panelList) {
                pElement.setVisible(false);
            }
        }
        this.panelList = new JPanel[8];
        this.panelList[WindowState.MAIN.getValue()] = new GUI_HomePage();
        this.panelList[WindowState.BOARD.getValue()] = new GUI_LAG();
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
        ((GUI_HomePage) this.panelList[WindowState.MAIN.getValue()]).callResize();
        this.setwState(WindowState.MAIN);
        this.setRenderMode();                     
    }

    public void setwState(WindowState wState) {
        this.panelList[this.wState.getValue()].setVisible(false);
        this.remove(this.panelList[this.wState.getValue()]);
        this.wState = wState;
        this.panelList[this.wState.getValue()].setVisible(true);
        ((Gui_INTERFACE) this.panelList[this.wState.getValue()]).callResize();
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
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).initGame(this);
    }
    
    public void startGame() {
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).start();
        this.setwState(WindowState.BOARD);
    }

    /**
     * Initialize the Game with custom parameters
     * @param p1 Player_1 [Class, Name, Color]
     * @param p2 Player_2 [Class, Name, Color]
     * @param gridName  Name of the Grid
     */
    public void initGame(String[] p1, String[] p2, String gridName) {
        p1[0] = createClass(p1[0]);
        p2[0] = createClass(p2[0]);
        //System.out.println(p1[0]+" - "+p2[0]);
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).initGame(this,p1[0], p1[1], p1[2], p2[0], p2[1], p2[2], gridName);
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).start();
        this.setwState(WindowState.BOARD);
    }
    
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
                return "ControlledPlayer";
        }
        return null;
    }

    public void setVictory(String s) {
        
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).stop();
        this.setGameTitle(s);
        //((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).deleteGame();
        
        //((GUI_FinalScreen) this.panelList[WindowState.VICTORY.getValue()]).setGrid(g);
        //((GUI_FinalScreen) this.panelList[WindowState.VICTORY.getValue()]).setWinningText(p);
        //this.setwState(WindowState.VICTORY);
    }
    
    public void setGameTitle(String s) {
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).setTitle(s);
    }

    public void save(String pSlotName) {
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).save(pSlotName);
    }

    public void load(String pSlotName) {
        ((GUI_LAG) this.panelList[WindowState.BOARD.getValue()]).load(this,pSlotName);
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
        ((Gui_INTERFACE) this.panelList[this.wState.getValue()]).back();
    }
}

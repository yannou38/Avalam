/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import avalam_s6.Core.Grid;
import avalam_s6.GUI.FinalScreen.GUI_FinalScreen;
import avalam_s6.GUI.HomePage.GUI_HomePage;
import avalam_s6.GUI.LocalAvalamGame.GUI_LAG;
import avalam_s6.GUI.NewGame.GUI_NewGame;
import avalam_s6.GUI.Settings.GUI_Settings;
import avalam_s6.GUI.Credits.GUI_Credits;
import avalam_s6.Player.Player_INTERFACE;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import static java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author sazeratj
 */
public class Main_Frame extends JFrame implements GUI_INTERFACE, Runnable {
    WindowState wState;
    WindowRenderMode wrm;
    JPanel[] panelList;
    
    public Main_Frame() {
        this("Default",WindowRenderMode.WINDOWED);
    }
    
    public Main_Frame(String theme, WindowRenderMode renderMode) {
        /* UPDATE VARIABLES */
        this.panelList = new JPanel[6]; // TODO : add more JPanels.
        this.wState = WindowState.MAIN;
        this.wrm = renderMode;
        /* FUNCTION CALL */
        this.initFrame(theme);        
        this.setRenderMode(); 
        /* ADD KB DISPATCHER */
        getCurrentKeyboardFocusManager().addKeyEventDispatcher(new RenderKeyboardDispatcher(this));
    }
    
    public void setRenderMode() {
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
        if (this.wrm == WindowRenderMode.FULLSCREEN) {
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        } else {
            this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        }
        this.setMinimumSize(new Dimension(960, 540));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.requestFocus();
    }
    
    public void toggleWRM() {
        if (this.wrm == WindowRenderMode.FULLSCREEN) {
            this.wrm = WindowRenderMode.WINDOWED;
        } else {
            this.wrm = WindowRenderMode.FULLSCREEN;
        }
        setRenderMode();
    }
    
    public void initFrame(String theme) {
        this.wState = WindowState.MAIN;
        this.panelList[0] = new GUI_HomePage(theme);
        this.panelList[1] = new GUI_LAG(theme);
        this.panelList[2] = new GUI_FinalScreen(theme);
        this.panelList[3] = new GUI_NewGame(theme);
        this.panelList[4] = new GUI_Settings(theme);
        this.panelList[5] = new GUI_Credits(theme);
        for (JPanel pElement : this.panelList) {
            //this.add(pElement);
            pElement.setVisible(false);
        }
        this.setwState(WindowState.MAIN);
    }
    
    public void setwState(WindowState wState) {
        this.panelList[this.wState.getValue()].setVisible(false);
        this.remove(this.panelList[this.wState.getValue()]);
        this.wState = wState;
        this.panelList[this.wState.getValue()].setVisible(true);
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
    
    public void setVictoryScreen(String p, Grid g) {
        ((GUI_FinalScreen)this.panelList[WindowState.VICTORY.getValue()]).setGrid(g);
        ((GUI_FinalScreen)this.panelList[WindowState.VICTORY.getValue()]).setWinner(p);
        this.setwState(WindowState.VICTORY);
    }
}

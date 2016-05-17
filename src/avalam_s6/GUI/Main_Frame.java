/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import avalam_s6.GUI.HomePage.GUI_HomePage;
import avalam_s6.GUI.LocalAvalamGame.GUI_LAG;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author sazeratj
 */
public class Main_Frame extends JFrame implements GUI_INTERFACE, Runnable{
    WindowState wState;
    JPanel[] panelList;
    
    public Main_Frame() {
        this("Default",WindowRenderMode.WINDOWED);
    }
    
    public Main_Frame(String theme, WindowRenderMode renderMode) {
        this.panelList = new JPanel[2]; // TODO : add more JPanels.
        this.wState = WindowState.MAIN;
        this.initFrame(theme);
        this.setRenderMode(renderMode);  
        System.out.println(this.wState.getValue());
    }
    
    public void setRenderMode(WindowRenderMode r) {
        if (r == WindowRenderMode.FULLSCREEN)
            this.setUndecorated(true);
        else
            this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1280, 720));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public void initFrame(String theme) {
        this.wState = WindowState.MAIN;
        this.panelList[0] = new GUI_HomePage(theme);
        this.panelList[1] = new GUI_LAG(theme);
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
}
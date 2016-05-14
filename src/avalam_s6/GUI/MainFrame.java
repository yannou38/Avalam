/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import avalam_s6.GUI.HomePage.GUI_HomePage;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author sazeratj
 */
public class MainFrame extends JFrame implements GUI_INTERFACE, Runnable{
    WindowState wState;
    JPanel[] panelList;
    
    public MainFrame() {
        this("Default");
    }
    
    public MainFrame(String theme) {
        this.panelList = new JPanel[1]; // TODO : add more JPanels.
        this.initFrame(theme);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280, 720));
        setResizable(false);
    }
    
    public void initFrame(String theme) {
        this.panelList[0] = new GUI_HomePage(theme);
        for (JPanel pElement : this.panelList) {
            this.add(pElement);
        }
    }
    
    public void setwState(WindowState wState) {
        this.panelList[this.wState.getValue()].setVisible(false);
        this.wState = wState;
        this.panelList[this.wState.getValue()].setVisible(true);
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
}

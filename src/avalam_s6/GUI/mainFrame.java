/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import avalam_s6.GUI.HomePage.GUI_HomePage;
import javax.swing.JFrame;
/**
 *
 * @author sazeratj
 */
public class mainFrame extends JFrame implements GUI_INTERFACE, Runnable{
    WindowState wState;

    public mainFrame() {
        GUI_HomePage h = new GUI_HomePage();
        this.add(h);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
    }    

    @Override
    public void render() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        this.pack();
        this.setVisible(true);
    }
}

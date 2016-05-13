/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import javax.swing.JFrame;

/**
 *
 * @author ducruyy
 */
public class GUIFrame_testing_shouldprobberemoved extends JFrame{
    public GUIFrame_testing_shouldprobberemoved(){
        HomeGUI h = new HomeGUI();
        this.add(h);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
    }
}

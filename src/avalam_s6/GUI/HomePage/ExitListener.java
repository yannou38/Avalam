/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.HomePage;

import avalam_s6.GUI.MainFrame;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author TheDoctor
 */
class ExitListener implements ActionListener {

    private final MainFrame frame;
    
    public ExitListener(Container frame) {
        this.frame = (MainFrame) frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.dispose();
    }
    
}

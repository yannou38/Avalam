/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import javax.swing.*;

/**
 *
 * @author ducruyy
 */
public class GUI_testing_shouldberemovedaftertests implements Runnable{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUI_testing_shouldberemovedaftertests());
    }

    @Override
    public void run() {
        // Creation d'une fenetre
        GUIFrame_testing_shouldprobberemoved frame = new GUIFrame_testing_shouldprobberemoved();
        // On fixe la taille de la fenetre au minimum pour contenir tous les
        // composants
        frame.pack();
        frame.setVisible(true);
    }
}

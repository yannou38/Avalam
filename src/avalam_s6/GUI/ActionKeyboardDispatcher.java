/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

/**
 *
 * @author sazeratj
 */
public class ActionKeyboardDispatcher implements KeyEventDispatcher{

    Main_Frame mainframe;

    public ActionKeyboardDispatcher(Main_Frame mf) {
        this.mainframe = mf;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //this.mainframe.toggleWRM();
        }
        return true;
    }
    
}

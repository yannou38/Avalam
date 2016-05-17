/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import java.awt.Container;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_F11;

/**
 *
 * @author sazeratj
 */
public class RenderKeyboardDispatcher implements KeyEventDispatcher {
    
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == VK_F11) {
                Container o = (Container) e.getSource();
                while (o.getClass() != Main_Frame.class) {
                    //System.out.println(o.getClass());
                    o = o.getParent();
                }
                ((Main_Frame) o).toggleWRM();
            }
            return false;
        }
    }

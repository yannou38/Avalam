/* 
 * Copyright (C) 2016 Yann Ducruy <yann.ducruy@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package avalam_s6.GUI;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_F11;
import static java.awt.event.KeyEvent.VK_F6;

/**
 * Manage hotkeys in the software
 *
 * @author Team 7
 */
public class CustomKeyboardDispatcher implements KeyEventDispatcher {

    Main_Frame mainframe;

    /**
     * Constructor.
     *
     * @param mf the main frame
     */
    public CustomKeyboardDispatcher(Main_Frame mf) {
        this.mainframe = mf;
    }

    /**
     * Manage hotkeys. return true if the key stroke was used else return false.
     *
     * @param e The KeyEvent object
     * @return A boolean that say if the key stroke was catched and used.
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == VK_F11) {
            this.mainframe.toggleWRM();
        } else if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == VK_ESCAPE) {
            this.mainframe.backWindow();
        } else if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == VK_F6) {
            this.mainframe.toggleMute();
        } else {
            return false;
        }
        return true;
    }
}

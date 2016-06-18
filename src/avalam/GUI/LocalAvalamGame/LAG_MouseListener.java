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
package avalam.GUI.LocalAvalamGame;

import avalam.Core.Coordinate;
import avalam.Core.Globals.Input;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for the button map
 *
 * @author Team 7
 */
public class LAG_MouseListener implements MouseListener {

    private final Coordinate c;

    /**
     * Constructor
     *
     * @param c coordinate of the button in the buttonmap
     */
    public LAG_MouseListener(Coordinate c) {
        this.c = c;
    }

    /**
     * Save the location of the click
     *
     * @param e the mouse event (unused)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!Input.isButtonClicked()) {
            Input.setButtonClicked();
            Input.updateMouseSrcPosition(this.c);
        } else if ((Math.abs(c.getX() - Input.getMouseSrcPosition().getX()) <= 1) && (Math.abs(c.getY() - Input.getMouseSrcPosition().getY()) <= 1) && (!(c.getX() == Input.getMouseSrcPosition().getX() && c.getY() == Input.getMouseSrcPosition().getY()))) {
            Input.setButtonReleased();
            Input.updateMouseDestPosition(this.c);
        } else {
            Input.resetClick();
        }
    }

    /**
     * Do nothing.
     *
     * @param e the mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Do nothing.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Do nothing.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Do nothing.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

}

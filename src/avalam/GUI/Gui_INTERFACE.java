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
package avalam.GUI;

/**
 * Standard interface for all the GUIs
 *
 * @author Team 7
 */
public interface Gui_INTERFACE {

    /**
     * Return to the precedent page.
     */
    public void back();

    /**
     * Set a boolean to call the resizing function ONCE in the repaint()
     * function.
     */
    public void callResize();
}

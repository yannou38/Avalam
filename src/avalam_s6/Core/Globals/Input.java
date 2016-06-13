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
package avalam_s6.Core.Globals;

import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Game_INTERFACE;
import avalam_s6.Core.Owner;

/**
 * The Input Engine. Acts as an abstract layer for getting user's mouse inputs.
 * @author Team 7
 */
public class Input {

    private static Game_INTERFACE game;
    private static boolean mouseClicked = false;
    private static boolean hasClicked = false;
    private static Coordinate c_src = new Coordinate();
    private static Coordinate c_dest = new Coordinate();
    private static int src_size = -1;
    private static int dest_size = -1;

    /**
     * Tells if button has been clicked.
     *
     * @return true if button has been clicked.
     */
    public static boolean isButtonClicked() {
        return Input.mouseClicked;
    }

    /**
     * Set the position of the release.
     *
     * @param pos the position of the release.
     */
    public static void updateMouseDestPosition(Coordinate pos) {
        Input.c_dest = pos;
        if (pos.isValid() && Input.game.getGrid().getCellAt(pos).getOwner().getValue() != Owner.NO_OWNER.getValue()) {
            Input.dest_size = Input.game.getGrid().getCellAt(pos).getSize();
        } else {
            Input.hasClicked = false;
            Input.dest_size = -1;
        }
    }

    public static void resetClick() {
        Input.hasClicked = false;
        Input.c_src = new Coordinate();
        Input.mouseClicked = false;
    }

    /**
     * Set the position of the click.
     *
     * @param pos the position of the click.
     */
    public static void updateMouseSrcPosition(Coordinate pos) {
        Input.c_src = pos;
        if (pos.isValid() && Input.game.getGrid().getCellAt(pos).getOwner().getValue() != Owner.NO_OWNER.getValue()) {
            Input.src_size = Input.game.getGrid().getCellAt(pos).getSize();
        } else {
            Input.mouseClicked = false;
        }
    }

    /**
     * Tells if button has clicked AND released.
     *
     * @return true if button has been clicked and released.
     */
    public static boolean hasClicked() {
        return Input.hasClicked;
    }

    /**
     * Tells the Input class that the button has been clicked.
     */
    public static void setButtonClicked() {
        Input.mouseClicked = true;
    }

    /**
     * Tells the Input class that the button has been released.
     */
    public static void setButtonReleased() {
        Input.mouseClicked = false;
        Input.hasClicked = true;
    }

    /**
     * Get the position of the click.
     *
     * @return the position of the click.
     */
    public static Coordinate getMouseSrcPosition() {
        return Input.c_src;
    }

    /**
     * Get the height of the source tower.
     *
     * @return the size of the source tower.
     */
    public static int getSrcSize() {
        return Input.src_size;
    }

    /**
     * Get the position of the release.
     *
     * @return the position of the release.
     */
    public static Coordinate getMouseDestPosition() {
        return Input.c_dest;
    }

    /**
     * Get the height of the destination tower.
     *
     * @return the size of the destination tower.
     */
    public static int getDestSize() {
        return Input.dest_size;
    }

    /**
     * Link a game to the Input engine.
     * @param g the game to link to the Input Engine.
     */
    public static void setInputGame(Game_INTERFACE g) {
        Input.game = g;
    }

}

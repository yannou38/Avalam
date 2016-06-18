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
package avalam.Player;

import avalam.Core.Coordinate;
import avalam.Core.Globals.AvalamColor;
import avalam.Core.Globals.Input;
import avalam.Core.Move;
import avalam.Core.Owner;

/**
 *
 * @author Team 7
 */
public class ControlledPlayer extends Player {

    public ControlledPlayer(String n, AvalamColor c, Owner owner) {
        super(n, c, owner);
    }

    @Override
    public boolean isAI() {
        return false;
    }

    @Override
    public Move play() {
        if (Input.hasClicked()) {
            Coordinate src = Input.getMouseSrcPosition();
            Coordinate dest = Input.getMouseDestPosition();
            int srcSize = Input.getSrcSize();
            int destSize = Input.getDestSize();
            Move m = new Move(src, srcSize, dest, destSize, this);
            Input.resetClick();
            Input.updateMouseDestPosition(new Coordinate());
            Input.updateMouseSrcPosition(new Coordinate());
            return m;
        } else {
            return null;
        }
    }

}

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
package avalam_s6.Player;

import avalam_s6.Core.Globals.AvalamColor;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;

/**
 *
 * @author Team 7
 */
public class AIPlayerOnlyUndo extends AIPlayer {

    public AIPlayerOnlyUndo(String name, AvalamColor color, Owner owner) {
        super(name, color, owner);
    }

    @Override
    public Move play() {
        Move m = new Move(null, 1, null, 1, this);
        boolean test = true;
        while (test) {
            game.undo();
            game.redo();
        }
        return m;
    }

}

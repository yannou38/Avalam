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

import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;

/**
 *
 * @author Team 7
 */
public interface Player_INTERFACE {

    /**
     * Player play on a coordinate /!\ DON'T ASK A BOT FOR A PLAY THERE IS NO
     * POSSIBLE PLAY LEFT /!\
     *
     * @return the coordinate chose
     */
    public Move play();

    /**
     * Tells if the player is a bot.
     *
     * @return true if the player is an AI, false otherwise.
     */
    public boolean isAI();

    /**
     * getter
     * @return name of the player
     */
    public String getName();
    
    /**
     * getter
     * @return owner
     */
    public Owner getOwner();
}

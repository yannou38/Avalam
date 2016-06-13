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
public abstract class Player implements Player_INTERFACE {

    protected String name;
    protected AvalamColor color;
    protected Owner owner;

    public Player(String name, AvalamColor color, Owner owner) {
        this.name = name;
        this.color = color;
        this.owner = owner;
    }

    public AvalamColor getColor() {
        return this.color;
    }

    @Override
    public abstract Move play();

    @Override
    public abstract boolean isAI();

    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public Owner getOwner() {
        return this.owner;
    }
}

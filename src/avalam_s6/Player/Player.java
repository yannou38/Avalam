/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.Globals.AvalamColor;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;

/**
 *
 * @author TheDoctor
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

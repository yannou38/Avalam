/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Game_INTERFACE;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;
import java.awt.Color;

/**
 *
 * @author TheDoctor
 */
public abstract class Player implements Player_INTERFACE {
    protected String name;
    protected Color color;
    protected Owner owner;
    protected Game_INTERFACE game;
    
    public Player(String name, Color color, Owner owner) {
        this.name = name;
        this.color = color;
    }
    
    @Override
    public abstract Move play();
    
    @Override
    public abstract boolean isAI();

    public Color getColor() {
        return color;
    }
    
    @Override
    public void setGame(Game_INTERFACE game) {
        this.game = game;
    }
    
    
    
}

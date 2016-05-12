/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.Coordinate;
import java.awt.Color;

/**
 *
 * @author TheDoctor
 */
public abstract class Player {
    private String name;
    private Color color;
    
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }
    
    /**
     * Player play on a coordinate
     * @return the coordinate chose 
     */
    public abstract Coordinate play();

    /**
     * Tells if the player is a bot.
     * @return true if the player is an AI, false otherwise.
     */
    public abstract boolean isAI();
    
}

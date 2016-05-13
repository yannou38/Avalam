/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.*;
import java.awt.Color;

/**
 *
 * @author TheDoctor
 */
public abstract class AIPlayer extends Player{
    protected Game_INTERFACE game;
    
    /**
     * Constructor.
     * @param name The name of the player.
     * @param color The color of the player's pawns.
     */
    public AIPlayer(String name, Color color, Owner owner) {
        super(name,color,owner);
    }
    
    @Override
    public boolean isAI() {
        return true;
    }

    public void setGame(Game_INTERFACE game) {
        this.game = game;
    }
       
}

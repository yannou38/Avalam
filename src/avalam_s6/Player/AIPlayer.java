/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.Grid;
import avalam_s6.Core.Owner;
import java.awt.Color;

/**
 *
 * @author TheDoctor
 */
public abstract class AIPlayer extends Player{
    protected Grid grid;
    
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

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
       
}

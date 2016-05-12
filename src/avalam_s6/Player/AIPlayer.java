/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.Grid;
import java.awt.Color;

/**
 *
 * @author TheDoctor
 */
public abstract class AIPlayer extends Player{
    protected Grid grid;
    
    public AIPlayer(String name, Color color) {
        super(name,color);
    }
    
    @Override
    public boolean isAI() {
        return true;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
       
}

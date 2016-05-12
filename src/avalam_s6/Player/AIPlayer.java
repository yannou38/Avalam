/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.Grid;

/**
 *
 * @author TheDoctor
 */
public class AIPlayer implements Player{
    private Grid grid;
    
    public AIPlayer(Grid g) {
        this.grid = g;
    }
    
    @Override
    public boolean isAI() {
        return true;
    }
    
}

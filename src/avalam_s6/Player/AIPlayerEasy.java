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
 * @author Seawolf
 */
public class AIPlayerEasy extends AIPlayer{

    public AIPlayerEasy(String name, Color color, Owner owner) {
        super(name, color,owner);
    }

    @Override
    public Move play() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * @param a origin
     * @param b target
     * @return true if the move a on b would give us a 5 tour, b is opponent 
     */
    private boolean completeTourUs(Cell a, Cell b)
    {
        if (this.owner == a.getOwner())
        {
            
        }
        return false;
    }
    
}

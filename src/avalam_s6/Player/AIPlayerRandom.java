/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.*;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Seawolf
 */
public class AIPlayerRandom extends AIPlayer{
    
    public AIPlayerRandom(Grid grid) {
        super(grid);
    }

    @Override
    public Coordinate[] play() {
        ArrayList<Coordinate[]> mesCoups = new ArrayList<>();
        for (int i = 0; i < grid.getWidth(); i++)
        {
            /**
             *    1 2 3
             *    4 0 5
             *    6 7 8
             */   
            for (int j = 0; j < grid.getHeight(); j++)
            {
                Coordinate c0 = new Coordinate(j,i);
                if (c0.isValid() && grid.getCellAt(c0).getState() == State.TOWER)
                {
                    Coordinate c1 = new Coordinate(j-1,i-1);
                    Coordinate c2 = new Coordinate(j-1,i);
                    Coordinate c3 = new Coordinate(j-1,i+1);
                    Coordinate c4 = new Coordinate(j,i-1);
                    Coordinate c5 = new Coordinate(j,i+1);
                    Coordinate c6 = new Coordinate(j+1,i-1);
                    Coordinate c7 = new Coordinate(j+1,i);
                    Coordinate c8 = new Coordinate(j+1,i+1);
                    for (int c = 0; c <8;c++)
                    {
                        if (c1.isValid() && grid.getCellAt(c0).getState() == State.TOWER)
                        {
                            
                             //TODO : finir
                        }
                    }
                }
            }
        }
        Random r = new Random();
        return mesCoups.get(r.nextInt(mesCoups.size()));
    }
    
    
    
    
}

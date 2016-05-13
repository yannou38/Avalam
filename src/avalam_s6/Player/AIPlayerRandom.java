/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;


import avalam_s6.Core.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Seawolf
 */
public class AIPlayerRandom extends AIPlayer{

    public AIPlayerRandom(String name, Color color) {
        super(name, color);
    }
    
    @Override
    public Move play() {
        ArrayList<Move> mesCoups = new ArrayList<>();
        Coordinate[] tabCoord = new Coordinate[8];
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
                    tabCoord[0] = c1;
                    tabCoord[1] = c2;
                    tabCoord[2] = c3;
                    tabCoord[3] = c4;
                    tabCoord[4] = c5;
                    tabCoord[5] = c6;
                    tabCoord[6] = c7;
                    tabCoord[7] = c8;
                    for (int k = 0; k <8;k++)
                    {
                        if (tabCoord[k].isValid() && grid.getCellAt(tabCoord[k]).getState() == State.TOWER)
                        {
                            if(grid.moveCell(c8, c8))
                        }
                    }
                }
            }
        }
        Random r = new Random();
        return mesCoups.get(r.nextInt(mesCoups.size()));
    }
    
    
    
    
}

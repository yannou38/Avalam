/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.CellState;
import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Seawolf
 */
public class AIPlayerMedium extends AIPlayer{

    public AIPlayerMedium(String name, Color color, Owner owner) {
        super(name, color, owner);
    }

    @Override
   public Move play() {
        ArrayList<Move> mesCoups = new ArrayList<>();
        Coordinate[] tabCoord = new Coordinate[8];
        double maxvalue = 0;
        double value;
         for (int i = 0; i < this.game.getGrid().getWidth(); i++)
        {
            /**
             *    1 2 3
             *    4 0 5
             *    6 7 8
             */   
            for (int j = 0; j < this.game.getGrid().getHeight(); j++)
            {
                Coordinate c0 = new Coordinate(j,i);
                if (c0.isValid() && this.game.getGrid().getCellAt(c0).getState() == CellState.TOWER)
                {
                    Coordinate c1 = new Coordinate(j-1,i-1);
                    Coordinate c2 = new Coordinate(j,i-1);
                    Coordinate c3 = new Coordinate(j+1,i-1);
                    Coordinate c4 = new Coordinate(j-1,i);
                    Coordinate c5 = new Coordinate(j+1,i);
                    Coordinate c6 = new Coordinate(j-1,i+1);
                    Coordinate c7 = new Coordinate(j,i+1);
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
                        //un coup est possible
                        if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState() == CellState.TOWER)
                        {
                            if(this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0),this.game.getGrid().getCellAt(tabCoord[k])))
                            {
                               Move m = new Move(c0,this.game.getGrid().getCellAt(c0).getSize(),tabCoord[k],this.game.getGrid().getCellAt(tabCoord[k]).getSize(),this);
                               value = miniMax(m,4);
                               if (value>maxvalue)
                               {
                                   mesCoups.clear();
                                   mesCoups.add(m);
                               }
                               else if (value == maxvalue)
                               {
                                   mesCoups.add(m);
                               }
                               //sinon on ignore, le coups est moins bon
                            }
                        }
                    }
                }
            }
        }
        
        
        Random r = new Random();
        return mesCoups.get(r.nextInt(mesCoups.size()));
        
    }
   
   private double miniMax(Move move, int profondeur){
       
        Coordinate[] tabCoord = new Coordinate[8];
        double value = 0;
        for (int i = 0; i < this.game.getGrid().getWidth(); i++)
        {
            /**
             *    1 2 3
             *    4 0 5
             *    6 7 8
             */   
            for (int j = 0; j < this.game.getGrid().getHeight(); j++)
            {
                Coordinate c0 = new Coordinate(j,i);
                if (c0.isValid() && this.game.getGrid().getCellAt(c0).getState() == CellState.TOWER)
                {
                    Coordinate c1 = new Coordinate(j-1,i-1);
                    Coordinate c2 = new Coordinate(j,i-1);
                    Coordinate c3 = new Coordinate(j+1,i-1);
                    Coordinate c4 = new Coordinate(j-1,i);
                    Coordinate c5 = new Coordinate(j+1,i);
                    Coordinate c6 = new Coordinate(j-1,i+1);
                    Coordinate c7 = new Coordinate(j,i+1);
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
                        //un coup est possible
                        if (tabCoord[k].isValid() && this.game.getGrid().getCellAt(tabCoord[k]).getState() == CellState.TOWER)
                        {
                            if(this.game.getGrid().canStack(this.game.getGrid().getCellAt(c0),this.game.getGrid().getCellAt(tabCoord[k])))
                            {
                                Move m = new Move(c0,this.game.getGrid().getCellAt(c0).getSize(),tabCoord[k],this.game.getGrid().getCellAt(tabCoord[k]).getSize(),this);
                                //we gain a point for free, great !
                                if (completeTourUsVsOp(this.game.getGrid().getCellAt(c0),this.game.getGrid().getCellAt(tabCoord[k])) || createAloneUs(c0,tabCoord[k] )){
                                    value = 1;
                                }
                                //we can secure a point
                                else if (completeTourUs(this.game.getGrid().getCellAt(c0),this.game.getGrid().getCellAt(tabCoord[k]))){
                                     value = 0.66;
                                }
                                //whatever 
                                else if(completeTourOp(this.game.getGrid().getCellAt(c0),this.game.getGrid().getCellAt(tabCoord[k])) || createAloneOp(c0,tabCoord[k] )){
                                     value = 0;
                                }
                                else{
                                      value = 0.33;
                                }
                            }
                        }
                    }
                }
            }
        }
        return value;
   }
   
    
}

/*
 * DEBUG TOOL
 * DELETE IT BEFORE RELEASE
 */
package avalam_s6.Player;

import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;
import java.awt.Color;

/**
 *
 * @author Seawolf
 */
public class AIPlayerOnlyUndo extends AIPlayer{

    public AIPlayerOnlyUndo(String name, Color color, Owner owner) {
        super(name, color, owner);
    }

    @Override
    public Move play() {
        Move m = new Move(null,1,null,1,this);
        boolean lol = true;
        while(lol){
             game.undo();
             game.redo();
        }
       return m;
    }
    
}

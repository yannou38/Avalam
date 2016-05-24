/*
 * DEBUG TOOL
 * DELETE IT BEFORE RELEASE
 */
package avalam_s6.Player;

import avalam_s6.Core.Globals.AvalamColor;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;

/**
 *
 * @author Seawolf
 */
public class AIPlayerOnlyUndo extends AIPlayer {

    public AIPlayerOnlyUndo(String name, AvalamColor color, Owner owner) {
        super(name, color, owner);
    }

    @Override
    public Move play() {
        Move m = new Move(null, 1, null, 1, this);
        boolean lol = true;
        while (lol) {
            game.undo();
            game.redo();
        }
        return m;
    }

}

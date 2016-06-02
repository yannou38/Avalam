/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Globals.AvalamColor;
import avalam_s6.Core.Globals.Input;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;

/**
 *
 * @author Team 7
 */
public class ControlledPlayer extends Player {

    public ControlledPlayer(String n, AvalamColor c, Owner owner) {
        super(n, c, owner);
    }

    @Override
    public boolean isAI() {
        return false;
    }

    @Override
    public Move play() {
        if (Input.hasClicked()) {
            Coordinate src = Input.getMouseSrcPosition();
            Coordinate dest = Input.getMouseDestPosition();
            int srcSize = Input.getSrcSize();
            int destSize = Input.getDestSize();
            Move m = new Move(src, srcSize, dest, destSize, this);
            Input.resetClick();
            Input.updateMouseDestPosition(new Coordinate());
            Input.updateMouseSrcPosition(new Coordinate());
            return m;
        } else {
            return null;
        }
    }

}

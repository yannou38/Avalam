/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

/**
 * Define the owner of a cell/pawn.
 *
 * @author Team 7
 */
public enum Owner {
    PLAYER_1(1), //P1
    PLAYER_2(2), //P2
    NO_OWNER(3); //No Owner

    private final int value;

    /**
     * Constructor.
     * @param v the value of the owner.
     */
    private Owner(int v) {
        this.value = v;
    }

    /**
     * Gets the owner's value (player 1 = 1, player 2 = 2, no owner = 3).
     * @return the owner's value.
     */
    public int getValue() {
        return this.value;
    }
}

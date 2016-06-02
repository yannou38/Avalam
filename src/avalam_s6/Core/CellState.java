/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

/**
 * Define the state of the cell (tower, empty, restricted or full).
 *
 * @author Team 7
 */
public enum CellState {
    TOWER(2), // Tower 1-4
    EMPTY(1), // Empty Cell
    RESTRICTED(0), // Game Board
    FULL(3); // Tower 5

    private final int value;

    /**
     * Constructor.
     * @param v the value of the cell's state.
     */
    private CellState(int v) {
        this.value = v;
    }

    /**
     * Cell state value getter.
     * @return the value of the cell's state.
     */
    public int getValue() {
        return this.value;
    }
}

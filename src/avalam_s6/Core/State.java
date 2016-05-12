/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

/**
 * Define the state of the cell.
 * @author TheDoctor
 */
public enum State {
    TOWER, // Tower 1-4
    EMPTY, // Empty Cell
    RESTRICTED, // Game Board
    FULL; // Tower 5
}
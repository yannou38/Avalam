/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Exceptions;

/**
 * Exception - Grid contains an illegal character
 * @author Team 7
 */
public class GridCharException extends Exception {

    /**
     * Constructor
     * @param c Name of the invalid character
     * @param pos Position of the invalid character
     */
    public GridCharException(char c, int pos) {
        System.err.println("Grid contains an illegal character: " + c + " [" + pos + "]");
    }
}

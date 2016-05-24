/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Exceptions;

/**
 *
 * @author sazeratj
 */
public class GridCharException extends Exception {

    public GridCharException(char c, int pos) {
        System.err.println("Grid contains an illegal character: " + c + " [" + pos + "]");
    }
}

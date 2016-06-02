/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Exceptions;

/**
 * Exception - Grid is oversized or undersized.
 * Grid must be 9*9.
 * @author sazeratj
 */
public class GridSizeException extends Exception {

    public GridSizeException() {
        System.err.println("Error: Grid is undersized or oversized.");
        System.err.println("       Please, check that your grid contain exactly 81 characters (Read the Read_Me.txt file)");
    }
}

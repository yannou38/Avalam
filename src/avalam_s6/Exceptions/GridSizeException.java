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
public class GridSizeException extends Exception {
    public GridSizeException(int myCase) {
        if(myCase == 0) {
            System.out.println("Error: Grid is undersized or oversized.");
            System.out.println("       Please, check that your grid contain exactly 81 characters between 0 and 3 (Read the Read_Me.txt file)");
        } else {
            System.out.println("Error: Grid is undersized or oversized.");
            System.out.println("       It's a 3x3 grid, but only Tutorial grid is a 3x3.");
            System.out.println("       Please, check that your grid contain exactly 81 characters between 0 and 3 (Read the Read_Me.txt file)");
        }
    }
}

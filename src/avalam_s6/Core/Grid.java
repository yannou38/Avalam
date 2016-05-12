/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

/**
 *
 * @author TheDoctor
 */
public class Grid {
    private Cell[][] grille;
    
    //TODO/ Check length before calling constructor
    
    /**
     * Constructor
     * @param textgrid the text version of the grid. Size nust be 81.
     */
    public Grid(String textGrid) {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                this.grille[j][i] = new Cell(Integer.parseInt(""+textGrid.charAt(i*9+j)));
            }
        }
    }
}

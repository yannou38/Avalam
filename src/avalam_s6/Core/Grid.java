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
    
    //TODO: Check length before calling constructor
    
    /**
     * Constructor
     * @param textGrid the text version of the grid. SIZE MUST BE 81.
     */
    public Grid(String textGrid) {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                this.grille[j][i] = new Cell(Integer.parseInt(""+textGrid.charAt(i*9+j)));
            }
        }
    }
    
    /**
     * Getter
     * @return width 
     */
    public int getWidth() {
        return this.grille.length;
    }

    /**
     * Getter 
     * @return height 
     */
    public int getHeight() {
        return this.grille[0].length;
    }
    
    /**
     * Get the cell at a coordinate
     * @param c coordiante of the cell
     * @return cell at coordinate c
     */
    public Cell getCellAt(Coordinate c) {
        return this.grille[c.getX()][c.getY()];
    }
    
    //TODO: Check src is able to move on dst
    /**
     * Move cell src to cell dst. SRC MUST BE ABLE TO MOVE ON DST
     * @param src source cell
     * @param dst destination cell
     */
    public void moveCell(Coordinate src, Coordinate dst) {
        while(getCellAt(src).getState() != State.EMPTY) {
            getCellAt(dst).add(getCellAt(dst).remove());
        }
    }
    /**
     * Undo the move M
     * @param m the move to cancel
     */
    public void undoMove(Move m) {
        while(getCellAt(m.getC_src()).getSize() != m.getH_src()) { // While current src size is not old src size
            getCellAt(m.getC_src()).add(getCellAt(m.getC_dst()).removeAt(m.getH_dst()));
            /* As Current DST = Old DST + Old SRC we can not remove pawn from the top of the tower. */
        }
    }
    
}

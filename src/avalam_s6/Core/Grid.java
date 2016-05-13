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
    public Grid(String textGrid) throws NumberFormatException {
        grille = new Cell[9][9];
        if(textGrid.length() == 81) {
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    this.grille[j][i] = new Cell(Integer.parseInt(""+textGrid.charAt(i*9+j))-1);
                }
            }
        } else if (textGrid.length() == 9) {
           for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    this.grille[j][i] = new Cell(Integer.parseInt(""+textGrid.charAt(i*3+j))-1);
                }
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
            getCellAt(dst).add(getCellAt(src).removeAt(0));
        }
    }
    /**
     * Undo the move m.
     * @param m the move to cancel
     */
    public void undoMove(Move m) {
        while(getCellAt(m.getC_src()).getSize() != m.getH_src()) { // While current src size is not old src size
            getCellAt(m.getC_src()).add(getCellAt(m.getC_dst()).removeAt(m.getH_dst()));
            /* As Current DST = Old DST + Old SRC we can not remove pawn from the top of the tower. */
        }
    }

    /**
     * tells if two cells can be merged in one.
     * @param src the source cell
     * @param dst the destination cell
     * @return true if it is possible to add src on top of dst.
     */
    public boolean canStack(Cell src, Cell dst) {
        return (src.getSize()+dst.getSize() <= 5) && (src.getState() == State.TOWER) && ((dst.getState() == State.TOWER)|| dst.getState() == State.EMPTY);
    }
}
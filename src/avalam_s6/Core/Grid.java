/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

import avalam_s6.Exceptions.GridCharException;

/**
 *
 * @author TheDoctor
 */
public class Grid {

    private Cell[][] grille;
    private String gridName;

    //TODO: Check length before calling constructor
    /**
     * Constructor
     *
     * @param textGrid the text version of the grid. SIZE MUST BE 81.
     * @param name the name of the grid
     * @throws avalam_s6.Exceptions.GridCharException Illegal Character in the
     * grid
     */
    public Grid(String textGrid, String name) throws GridCharException {
        this.grille = new Cell[9][9];
        this.gridName = name;
        if (textGrid.length() == 81) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char c = textGrid.charAt(i * 9 + j);
                    switch (c) {
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                            this.grille[j][i] = new Cell(Integer.parseInt("" + c), 1);
                            break;
                        case 'A':
                            this.grille[j][i] = new Cell(1, 2);
                            break;
                        case 'B':
                            this.grille[j][i] = new Cell(2, 2);
                            break;
                        case 'C':
                            this.grille[j][i] = new Cell(3, 2);
                            break;
                        case 'D':
                            this.grille[j][i] = new Cell(4, 2);
                            break;
                        case 'E':
                            this.grille[j][i] = new Cell(5, 2);
                            break;
                        case '0':
                            this.grille[j][i] = new Cell(0, 0);
                            break;
                        case 'R':
                            this.grille[j][i] = new Cell(-1, 0);
                            break;
                        default:
                            throw new GridCharException(c, i * 9 + j);
                    }
                }
            }
        }
    }

    /**
     * tells if two cells can be merged in one.
     *
     * @param src the source cell
     * @param dst the destination cell
     * @return true if it is possible to add src on top of dst.
     */
    public boolean canStack(Cell src, Cell dst) {
        return (src.getSize() + dst.getSize() <= 5) && (src.getState().getValue() == CellState.TOWER.getValue())
                && ((dst.getState().getValue() == CellState.TOWER.getValue()));
    }

    //TODO: Check src is able to move on dst
    /**
     * Move cell src to cell dst. SRC MUST BE ABLE TO MOVE ON DST
     *
     * @param src source cell
     * @param dst destination cell
     */
    public void moveCell(Coordinate src, Coordinate dst) {
        while (getCellAt(src).getState() != CellState.EMPTY) {
            getCellAt(dst).add(getCellAt(src).removeAt(0));
        }
    }

    /**
     * Undo the move m.
     *
     * @param m the move to cancel
     */
    public void undoMove(Move m) {
        while (getCellAt(m.getC_src()).getSize() != m.getH_src()) { // While current src size is not old src size
            getCellAt(m.getC_src()).add(getCellAt(m.getC_dst()).removeAt(m.getH_dst()));
            /* As Current DST = Old DST + Old SRC we can not remove pawn from the top of the tower. */
        }
    }

    /**
     * Getter
     *
     * @return width
     */
    public int getWidth() {
        return this.grille.length;
    }

    /**
     * Getter
     *
     * @return height
     */
    public int getHeight() {
        return this.grille[0].length;
    }

    /**
     * Get the cell at a coordinate
     *
     * @param c coordiante of the cell
     * @return cell at coordinate c
     */
    public Cell getCellAt(Coordinate c) {
        return this.grille[c.getX()][c.getY()];
    }

    public String getName() {
        return this.gridName;
    }
}

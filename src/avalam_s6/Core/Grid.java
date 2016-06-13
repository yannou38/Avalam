/* 
 * Copyright (C) 2016 Yann Ducruy <yann.ducruy@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package avalam_s6.Core;

import avalam_s6.Exceptions.GridCharException;

/**
 * The grid system. Contains a table of Cells.
 * @author Team 7
 */
public class Grid {

    private Cell[][] grille;
    private String gridName;
    
    /**
     * Constructor.
     *
     * @param textGrid the text version of the grid. SIZE MUST BE 81.
     * @param name the name of the grid.
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
     * Tells if two cell's towers can be merged in one.
     *
     * @param src the source cell.
     * @param dst the destination cell.
     * @return true if it is possible to add src on top of dst.
     */
    public boolean canStack(Cell src, Cell dst) {
        return (src.getSize() + dst.getSize() <= 5) && (src.getState().getValue() == CellState.TOWER.getValue())
                && ((dst.getState().getValue() == CellState.TOWER.getValue()));
    }

    /**
     * Move cell src to cell dst. SRC MUST BE ABLE TO MOVE ON DST.
     *
     * @param src the source cell.
     * @param dst the destination cell.
     */
    public void moveCell(Coordinate src, Coordinate dst) {
        while (getCellAt(src).getState() != CellState.EMPTY) {
            getCellAt(dst).add(getCellAt(src).removeAt(0));
        }
    }

    /**
     * Undo the move m.
     *
     * @param m the move to cancel.
     */
    public void undoMove(Move m) {
        while (getCellAt(m.getC_src()).getSize() != m.getH_src()) { // While current src size is not old src size
            getCellAt(m.getC_src()).add(getCellAt(m.getC_dst()).removeAt(m.getH_dst()));
            /* As Current DST = Old DST + Old SRC we can not remove pawn from the top of the tower. */
        }
    }

    /**
     * Width's getter.
     *
     * @return the width of the grid.
     */
    public int getWidth() {
        return this.grille.length;
    }

    /**
     * Height's getter.
     *
     * @return the height of the grid.
     */
    public int getHeight() {
        return this.grille[0].length;
    }

    /**
     * Get the cell at the c coordinate.
     *
     * @param c coordinate of the cell to get.
     * @return the cell at coordinate c.
     */
    public Cell getCellAt(Coordinate c) {
        return this.grille[c.getX()][c.getY()];
    }

    /**
     * Grid's name getter.
     * @return the name of the grid.
     */
    public String getName() {
        return this.gridName;
    }
}

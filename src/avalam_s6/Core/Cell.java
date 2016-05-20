/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

import java.util.ArrayList;


/**
 *
 * @author TheDoctor
 */
public class Cell {
    private ArrayList<Owner> contenu;
    private CellState etat;
    
    public Cell(int size, int owner) {
        this.contenu = new ArrayList<>();
        if (owner == 0) {
            this.contenu.add(Owner.NO_OWNER);
            if(size == 0) {
                this.etat = CellState.EMPTY;
            } else {
                this.etat = CellState.RESTRICTED;
            }
        } else {
            for (int i=0;i<size;i++) {
               if(owner == 1) {
                   this.contenu.add(Owner.PLAYER_1);
               } else {
                   this.contenu.add(Owner.PLAYER_2);
               }
            }
            this.etat = CellState.TOWER;
        }
    }
    
    /**
     * Add a pawn on top of the tower
     * @param p the pawn added to the top of tower
     */
    public void add(Owner p) {
        if (this.contenu.size() < 5)
        {
            this.contenu.add(p);
            if (this.contenu.size() == 5)
                this.etat = CellState.FULL; // NORMAL
            if (this.contenu.size() == 1)
                this.etat = CellState.TOWER; // UNDO
        }
    }
    

    /**
     * Remove an element at I (Undo function can undo in the right order)
     * Towers are not shuffled by undoing.
     * @param i
     * @return 
     */
    public Owner removeAt(int i) {
        if (this.contenu.size() > 0)
        {
            if(this.contenu.size() == 5)
                this.etat = CellState.TOWER; 
            if(this.contenu.size() == 1)
                this.etat = CellState.EMPTY;
            return this.contenu.remove(i);
        }
        return null;
    }
    
    /**
     * Get the height of the tower
     * @return height of the tower
     */
    public int getSize() {
        return this.contenu.size();
    } 
    
    /**
     * Get the peek of the tower 
     * @return peek of tower 
     */
    public Owner getOwner() {
        if (! this.contenu.isEmpty())
            return this.contenu.get(getSize()-1);
        return Owner.NO_OWNER;
    }
    
    /**
     * Getter
     * @return state 
     */
    public CellState getState() {
        return etat;
    }
}

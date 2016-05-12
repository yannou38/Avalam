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
class Cell {
    private ArrayList<Pawn> contenu;
    private State etat;
    
    Cell(int owner) {
        contenu = new ArrayList<>();
        switch(owner) {
            case -1:
               etat = State.RESTRICTED;
               break;
            default:
            case 0:
                etat = State.EMPTY;
                break;
            case 1:
                contenu.add(Pawn.PLAYER_1);
                etat = State.TOWER;
            case 2:
                contenu.add(Pawn.PLAYER_2);
                etat = State.TOWER;                
        }
    }
    
    /**
     * Add a pawn on top of the tower
     * @param p the pawn added to the top of tower
     */
    public void ajouter(Pawn p) {
        contenu.add(p);
        if (contenu.size() == 5)
            etat = State.FULL;
    }
    
    /**
     * Remove last paww of tower. Modify the state adequately.
     * @return the pawn removed
     */
    public Pawn enlever() {
        if(contenu.size() == 1) 
            etat = State.EMPTY;
        return contenu.remove(contenu.size()-1);
    }
    
    /**
     * Get the height of the tower
     * @return height of the tower
     */
    public int getTaille() {
        return contenu.size();
    } 
    
    /**
     * Get the peek of the tower 
     * @return peek of tower 
     */
    public Pawn getOwner() {
        if (! contenu.isEmpty())
            return contenu.get(getTaille()-1);
        return Pawn.NO_OWNER;
    }
}

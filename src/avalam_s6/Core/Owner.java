/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

/**
 * Define the owner of the Pawn.
 * @author TheDoctor
 */
public enum Owner {
    PLAYER_1(1), //P1
    PLAYER_2(2), //P2
    NO_OWNER(3); //No Owner
    
    private final int value;
    
    private Owner(int v){
        this.value = v;
    }
    public int getValue(){
        return this.value;
    }
}

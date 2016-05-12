/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.Coordinate;
import java.awt.Color;

/**
 *
 * @author TheDoctor
 */
public class ControlledPlayer extends Player{

    public ControlledPlayer(String n, Color c) {
        super(n,c);
    }
    
    @Override
    public boolean isAI() {
        return false;
    }

    @Override
    public Coordinate play() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

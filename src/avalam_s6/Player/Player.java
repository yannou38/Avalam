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
public abstract class Player implements Player_INTERFACE {
    private String name;
    private Color color;
    
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }
    
    @Override
    public abstract Coordinate play();
    
    @Override
    public abstract boolean isAI();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.Globals;

/**
 * Color Enumeration used by the Avalam Game.
 * We didn't use standards colors here because it do not provide any getter by the name.
 * To save a player's color it requires the color name and the Default Java Colors do not allow it :)
 * @author Team 7
 */
public enum AvalamColor {
    WHITE("white"),
    BLACK("black"),
    BLUE("blue"),
    YELLOW("yellow"),
    RED("red"),
    GREEN("green"),
    PURPLE("purple"),
    CYAN("cyan");

    private final String value;

    /**
     * Constructor
     * @param v Value of the enumerate
     */
    private AvalamColor(String v) {
        this.value = v;
    }

    /**
     * Getter
     * @return The value of the enumerate 
     */
    public String getValue() {
        return this.value;
    }
}

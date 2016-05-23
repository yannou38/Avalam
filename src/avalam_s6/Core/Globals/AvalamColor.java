/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.Globals;

/**
 *
 * @author sazeratj
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
    
    private AvalamColor(String v){
        this.value = v;
    }
    public String getValue(){
        return this.value;
    }
}

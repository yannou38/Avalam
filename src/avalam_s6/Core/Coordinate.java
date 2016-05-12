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
public class Coordinate {
    private int x;
    private int y;
    
    public Coordinate(){
        this(-1, -1);
    }
    
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }                    
    
    @Override
    public String toString(){
        return "("+this.x+" "+this.y+")";
    }
    
    /**
     * Getter x
     * @return x
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * Getter y
     * @return y
     */
    public int getY(){
        return this.y;
    }
    
    /**
     * Setter x
     * @param x 
     */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     * Setter y
     * @param y 
     */
    public void setY(int y){
        this.y = y;
    }
    
    /**
     * Compare two Coordinates
     * @param r coordinate to compare
     * @return true if it's equal to r, false otherwise
     */
    public boolean equals(Coordinate r){
        return(this.x == r.getX() && this.y == r.getY());
    }
    
    /**
     * Test if a Coordinate is valid
     * @return true if the coordinate is valid, false otherwise
     */
    public boolean isValid(){
        return !(this.x < 0 || this.y < 0 || this.x > 8 || this.y > 8);
    }
}
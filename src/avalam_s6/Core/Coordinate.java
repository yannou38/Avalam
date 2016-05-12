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
    
    public float getX(){
        return this.x;
    }
    
    public float getY(){
        return this.y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public boolean equals(Coordinate r){
        return(this.x == r.getX() && this.y == r.getY());
    }
    
    public boolean isValid(){
        return !(this.x < 0 || this.y < 0);
    }
}
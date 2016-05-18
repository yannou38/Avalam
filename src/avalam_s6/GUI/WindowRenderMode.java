/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package avalam_s6.GUI;

/**
 * Define if the game must be displayed in Fullscreen or Windowed.
 * @author IQbrod
 */
public enum WindowRenderMode {
    FULLSCREEN(0),
    WINDOWED(1);
    
    private final int id;
    WindowRenderMode(int id) { this.id = id; }
    public int getValue() { return this.id; }
}

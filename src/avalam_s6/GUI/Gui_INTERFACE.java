/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

/**
 * Standard interface for all the GUIs
 *
 * @author Team 7
 */
public interface Gui_INTERFACE {

    /**
     * Return to the precedent page.
     */
    public void back();

    /**
     * Set a boolean to call the resizing function ONCE in the repaint()
     * function.
     */
    public void callResize();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

/**
 * Standard Interface for the MainFrame
 *
 * @author Team 7
 */
public interface GuiManager_INTERFACE {

    /**
     * Render the content of the current window.
     */
    public void render();

    /**
     * Call the back() function of the current panel.
     */
    public void backWindow();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;


/**
 *
 * @author TheDoctor
 */
public interface GUI_INTERFACE {            

    /**
     *  render the content of the current window.
     */
    public void render();

    /**
     * set the usability of the cancel button.
     * @param b tells if the cancel button should be usable.
     */
    public void enableUndo(boolean b);

    /**
     * set the usability of the redo button.
     * @param b tells if the redo button should be usable.
     */
    public void enableRedo(boolean b);
}

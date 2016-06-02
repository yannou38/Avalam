/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.LocalAvalamGame;

import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Globals.Input;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for the button map
 *
 * @author Team 7
 */
public class LAG_MouseListener implements MouseListener {

    private final Coordinate c;

    /**
     * Constructor
     *
     * @param c coordinate of the button in the buttonmap
     */
    public LAG_MouseListener(Coordinate c) {
        this.c = c;
    }

    /**
     * Save the location of the click
     *
     * @param e the mouse event (unused)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!Input.isButtonClicked()) {
            Input.setButtonClicked();
            Input.updateMouseSrcPosition(this.c);
        } else if ((Math.abs(c.getX() - Input.getMouseSrcPosition().getX()) <= 1) && (Math.abs(c.getY() - Input.getMouseSrcPosition().getY()) <= 1) && (!(c.getX() == Input.getMouseSrcPosition().getX() && c.getY() == Input.getMouseSrcPosition().getY()))) {
            Input.setButtonReleased();
            Input.updateMouseDestPosition(this.c);
        } else {
            Input.resetClick();
        }
    }

    /**
     * Do nothing.
     *
     * @param e the mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Do nothing.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Do nothing.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Do nothing.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

}

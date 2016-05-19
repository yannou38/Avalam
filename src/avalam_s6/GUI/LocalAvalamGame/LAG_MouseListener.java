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
 *
 * @author TheDoctor
 */
public class LAG_MouseListener implements MouseListener{

    private Coordinate c;
    
    public LAG_MouseListener(Coordinate c){
        this.c=c;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(!Input.isButtonClicked()) {
            Input.setButtonClicked();
            Input.updateMouseSrcPosition(this.c);
        } else {
            if ((Math.abs(c.getX() - Input.getMouseSrcPosition().getX()) <=1) && (Math.abs(c.getY() - Input.getMouseSrcPosition().getY()) <=1) && (!(c.getX() == Input.getMouseSrcPosition().getX() && c.getY() == Input.getMouseSrcPosition().getY()))) {
                Input.setButtonReleased();
                Input.updateMouseDestPosition(this.c);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}

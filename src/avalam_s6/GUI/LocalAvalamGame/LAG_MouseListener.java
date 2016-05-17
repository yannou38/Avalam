/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.LocalAvalamGame;

import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Input;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author TheDoctor
 */
public class LAG_MouseListener implements MouseListener{

    private int sizeOfCell = 100;
    @Override
    public void mouseClicked(MouseEvent e) {
        Input.setButtonClicked(); 
        Coordinate pos = new Coordinate(e.getX()%this.sizeOfCell,e.getY()%this.sizeOfCell);
        if(pos.isValid())
            Input.updateMouseSrcPosition(pos);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(Input.isButtonClicked()){
            Input.setButtonReleased();
            Coordinate pos = new Coordinate(e.getX()%this.sizeOfCell, e.getY()%this.sizeOfCell);
            if(pos.isValid())
                Input.updateMouseDestPosition(pos);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    public void setSizeOfCell(int size){
        this.sizeOfCell = size;
    }
}

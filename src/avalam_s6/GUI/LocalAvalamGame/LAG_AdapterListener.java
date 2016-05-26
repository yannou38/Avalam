/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.LocalAvalamGame;

import avalam_s6.Core.CellState;
import avalam_s6.Core.Coordinate;
import avalam_s6.Core.Owner;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

/**
 *
 * @author ducruyy
 */
public class LAG_AdapterListener implements ComponentListener {

    private final GUI_LAG page;

    public LAG_AdapterListener(GUI_LAG page) {
        this.page = page;
    }

    @Override
    public void componentResized(ComponentEvent e) {

        Insets insets = this.page.getInsets();
        Image newimg;
        //1280*720 => taille de base
        double ratioW = (double) this.page.getWidth() / (double) 1920;
        double ratioH = (double) this.page.getHeight() / (double) 1080;
        
        
        Dimension size = this.page.getTitre().getPreferredSize();
        this.page.getTitre().setBounds((int) round((420 + insets.left) * ratioW), ((int) round((50 + insets.top) * ratioH)), size.width, size.height);
        this.page.getTitre().setSize((int) round(this.page.getTitre().getWidth() * ratioW), (int) round(this.page.getTitre().getHeight() * ratioH));

        newimg = this.page.getCancel().getScaledInstance(((int) round(252 * ratioW)), ((int) round(111 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.page.getUndoB().setIcon(new ImageIcon(newimg));
        this.page.getUndoB().setBounds((int) round((360 + insets.left) * ratioW), ((int) round((505 + insets.top) * ratioH)), (int) round(252 * ratioW), (int) round(111 * ratioH));
        this.page.getUndoB().setSize((int) round(252 * ratioW), (int) round(111 * ratioH));
        
        newimg = this.page.getRedo().getScaledInstance(((int) round(252 * ratioW)), ((int) round(111 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.page.getRedoB().setIcon(new ImageIcon(newimg));
        this.page.getRedoB().setBounds((int) round((1313 + insets.left) * ratioW), ((int) round((505 + insets.top) * ratioH)), (int) round(252 * ratioW), (int) round(111 * ratioH));
        this.page.getRedoB().setSize((int) round(252 * ratioW), (int) round(111 * ratioH));
        
        newimg = this.page.getSave().getScaledInstance(((int) round(251 * ratioW)), ((int) round(111 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.page.getSaveB().setIcon(new ImageIcon(newimg));
        this.page.getSaveB().setBounds((int) round((1640 + insets.left) * ratioW), ((int) round((940 + insets.top) * ratioH)), (int) round(251 * ratioW), (int) round(111 * ratioH));
        this.page.getSaveB().setSize((int) round(251 * ratioW), (int) round(111 * ratioH));
        
        newimg = this.page.getRetour().getScaledInstance(((int) round(251 * ratioW)), ((int) round(111 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.page.getRetourB().setIcon(new ImageIcon(newimg));
        this.page.getRetourB().setBounds((int) round((25 + insets.left) * ratioW), ((int) round((940 + insets.top) * ratioH)), (int) round(251 * ratioW), (int) round(111 * ratioH));
        this.page.getRetourB().setSize((int) round(251 * ratioW), (int) round(111 * ratioH));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.page.getGame().getGrid().getCellAt(new Coordinate(i, j)).getOwner().getValue() == Owner.PLAYER_1.getValue()) {
                    newimg = this.page.getWhite().getScaledInstance(((int) round(30 * ratioW)), ((int) round(30 * ratioH)), java.awt.Image.SCALE_SMOOTH);
                } else if (this.page.getGame().getGrid().getCellAt(new Coordinate(i, j)).getOwner().getValue() == Owner.PLAYER_2.getValue()) {
                    newimg = this.page.getBlack().getScaledInstance(((int) round(03 * ratioW)), ((int) round(30 * ratioH)), java.awt.Image.SCALE_SMOOTH);
                } else {
                    if (this.page.getGame().getGrid().getCellAt(new Coordinate(i, j)).getState().getValue() == CellState.EMPTY.getValue()) {
                        newimg = this.page.getEmpty().getScaledInstance(((int) round(30 * ratioW)), ((int) round(30 * ratioH)), java.awt.Image.SCALE_SMOOTH);
                    } else {
                        newimg = this.page.getRestricted().getScaledInstance(((int) round(30 * ratioW)), ((int) round(30 * ratioH)), java.awt.Image.SCALE_SMOOTH);
                    }
                }
                this.page.getButtonmap()[i][j].setIcon(new ImageIcon(newimg));
                this.page.getButtonmap()[i][j].setBounds((int) round((680 + i * 61 + insets.left) * ratioW), ((int) round((275 + j * 61 + insets.top) * ratioH)), (int) round(61 * ratioW), (int) round(61 * ratioH));
                this.page.getButtonmap()[i][j].setSize((int) round(61 * ratioW), (int) round(61 * ratioH));

            }
        }

        /*
         newimg = this.hm.getQuickI().getScaledInstance(((int) round(674 * ratioW)), ((int) round(94 * ratioH)), java.awt.Image.SCALE_SMOOTH);
         this.hm.getQuick().setIcon(new ImageIcon(newimg));
         this.hm.getQuick().setBounds((int) round((453 + insets.left) * ratioW), ((int) round((453 + insets.top) * ratioH)), (int) round(1015 * ratioW), (int) round(150*ratioH));
         this.hm.getQuick().setSize((int) round(1015 * ratioW), (int) round(150 * ratioH));
         */
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

}

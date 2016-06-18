/* 
 * Copyright (C) 2016 Yann Ducruy <yann.ducruy@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package avalam.GUI.LocalAvalamGame;

import avalam.Core.CellState;
import avalam.Core.Coordinate;
import avalam.Core.Local_Avalam_Game;
import avalam.Core.Owner;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

/**
 * The board listener.
 *
 * @author Team 7
 */
public class LAG_AdapterListener implements ComponentListener {

    private final GUI_LAG page;

    /**
     * Constructor
     *
     * @param page the board GUI object
     */
    public LAG_AdapterListener(GUI_LAG page) {
        this.page = page;
    }

    /**
     * Resize and place the component where they should be.
     *
     * @param e the component event
     */
    @Override
    public void componentResized(ComponentEvent e) {

        Insets insets = this.page.getInsets();
        Image newimg;
        //1280*720 => taille de base
        double ratioW = (double) this.page.getWidth() / (double) 1920;
        double ratioH = (double) this.page.getHeight() / (double) 1080;
        this.page.getTitre().setBounds(0, (int) round(40 * ratioH), this.page.getWidth(), 50);
        this.page.getTitre().setSize(this.page.getWidth(), 50);

        newimg = this.page.getCancel().getScaledInstance(((int) round(252 * ratioW)), ((int) round(111 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.page.getUndoB().setIcon(new ImageIcon(newimg));
        this.page.getUndoB().setBounds((int) round((360 + insets.left) * ratioW), ((int) round((505 + insets.top) * ratioH)), (int) round(252 * ratioW), (int) round(111 * ratioH));
        this.page.getUndoB().setSize((int) round(252 * ratioW), (int) round(111 * ratioH));

        if (((Local_Avalam_Game) this.page.getGame()).getTurns() % 2 == 0) {
            newimg = this.page.getPlayer_playing().getScaledInstance(((int) round(284 * ratioW)), ((int) round(671 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        } else {
            newimg = this.page.getPlayer_waiting().getScaledInstance(((int) round(284 * ratioW)), ((int) round(671 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        }

        this.page.getGauche().setIcon(new ImageIcon(newimg));
        this.page.getGauche().setBounds((int) round((20 + insets.left) * ratioW), ((int) round((237 + insets.top) * ratioH)), (int) round(284 * ratioW), (int) round(671 * ratioH));
        this.page.getGauche().setSize((int) round(284 * ratioW), (int) round(671 * ratioH));

        if (((Local_Avalam_Game) this.page.getGame()).getTurns() % 2 == 0) {
            newimg = this.page.getPlayer_waiting().getScaledInstance(((int) round(284 * ratioW)), ((int) round(671 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        } else {
            newimg = this.page.getPlayer_playing().getScaledInstance(((int) round(284 * ratioW)), ((int) round(671 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        }

        this.page.getDroite().setIcon(new ImageIcon(newimg));
        this.page.getDroite().setBounds((int) round((1616 + insets.left) * ratioW), ((int) round((237 + insets.top) * ratioH)), (int) round(284 * ratioW), (int) round(671 * ratioH));
        this.page.getDroite().setSize((int) round(284 * ratioW), (int) round(671 * ratioH));

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

        if (((Local_Avalam_Game) this.page.getGame()).isPaused()) {
            newimg = this.page.getPlay().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        } else {
            newimg = this.page.getPause().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        }
        this.page.getPlayB().setIcon(new ImageIcon(newimg));
        this.page.getPlayB().setBounds((int) round((398 + insets.left) * ratioW), ((int) round((277 + insets.top) * ratioH)), (int) round(80 * ratioW), (int) round(80 * ratioH));
        this.page.getPlayB().setSize((int) round(80 * ratioW), (int) round(80 * ratioH));

        newimg = this.page.getFullscreen().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.page.getFullscreenB().setIcon(new ImageIcon(newimg));
        this.page.getFullscreenB().setBounds((int) round((1445 + insets.left) * ratioW), ((int) round((277 + insets.top) * ratioH)), (int) round(80 * ratioW), (int) round(80 * ratioH));
        this.page.getFullscreenB().setSize((int) round(80 * ratioW), (int) round(80 * ratioH));

        newimg = this.page.getMute().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.page.getMuteB().setIcon(new ImageIcon(newimg));
        this.page.getMuteB().setBounds((int) round((1342 + insets.left) * ratioW), ((int) round((277 + insets.top) * ratioH)), (int) round(80 * ratioW), (int) round(80 * ratioH));
        this.page.getMuteB().setSize((int) round(80 * ratioW), (int) round(80 * ratioH));

        newimg = this.page.getHelp().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.page.getHelpB().setIcon(new ImageIcon(newimg));
        this.page.getHelpB().setBounds((int) round((502 + insets.left) * ratioW), ((int) round((277 + insets.top) * ratioH)), (int) round(80 * ratioW), (int) round(80 * ratioH));
        this.page.getHelpB().setSize((int) round(80 * ratioW), (int) round(80 * ratioH));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.page.getGame().getGrid().getCellAt(new Coordinate(i, j)).getOwner().getValue() == Owner.PLAYER_1.getValue()) {
                    newimg = this.page.getWhite();
                } else if (this.page.getGame().getGrid().getCellAt(new Coordinate(i, j)).getOwner().getValue() == Owner.PLAYER_2.getValue()) {
                    newimg = this.page.getBlack();
                } else {
                    if (this.page.getGame().getGrid().getCellAt(new Coordinate(i, j)).getState().getValue() == CellState.EMPTY.getValue()) {
                        newimg = this.page.getEmpty();
                    } else {
                        newimg = this.page.getRestricted();
                    }
                }
                newimg = newimg.getScaledInstance(((int) round(66 * ratioW)), ((int) round(66 * ratioH)), java.awt.Image.SCALE_SMOOTH);
                this.page.getButtonmap()[i][j].setIcon(new ImageIcon(newimg));
                this.page.getButtonmap()[i][j].setBounds((int) round((660 + i * 66 + insets.left) * ratioW), ((int) round((260 + j * 66 + insets.top) * ratioH)), (int) round(66 * ratioW), (int) round(66 * ratioH));
                this.page.getButtonmap()[i][j].setSize((int) round(66 * ratioW), (int) round(66 * ratioH));

            }
        }

        this.page.getP1name().setBounds((int) round((20 + insets.left) * ratioW), ((int) round((257 + insets.top) * ratioH)), (int) round(284 * ratioW), 50);
        this.page.getP1name().setSize((int) round(284 * ratioW), 50);

        this.page.getP2name().setBounds((int) round((1616 + insets.left) * ratioW), ((int) round((257 + insets.top) * ratioH)), (int) round(284 * ratioW), 50);
        this.page.getP2name().setSize((int) round(284 * ratioW), 50);

        this.page.getP1score().setBounds((int) round((20 + insets.left) * ratioW), ((int) round((457 + insets.top) * ratioH)), (int) round(284 * ratioW), 50);
        this.page.getP1score().setSize((int) round(284 * ratioW), 50);

        this.page.getP2score().setBounds((int) round((1616 + insets.left) * ratioW), ((int) round((457 + insets.top) * ratioH)), (int) round(284 * ratioW), 50);
        this.page.getP2score().setSize((int) round(284 * ratioW), 50);

        newimg = this.page.getWhite().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.page.getP1color().setIcon(new ImageIcon(newimg));
        this.page.getP1color().setBounds((int) round((122 + insets.left) * ratioW), ((int) round((367 + insets.top) * ratioH)), (int) round(80 * ratioW), (int) round(80 * ratioH));
        this.page.getP1color().setSize((int) round(80 * ratioW), (int) round(80 * ratioH));

        newimg = this.page.getBlack().getScaledInstance(((int) round(80 * ratioW)), ((int) round(80 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.page.getP2color().setIcon(new ImageIcon(newimg));
        this.page.getP2color().setBounds((int) round((1718 + insets.left) * ratioW), ((int) round((367 + insets.top) * ratioH)), (int) round(80 * ratioW), (int) round(80 * ratioH));
        this.page.getP2color().setSize((int) round(80 * ratioW), (int) round(80 * ratioH));

        this.page.callResize();
    }

    /**
     * Do nothing.
     *
     * @param e the component event
     */
    @Override
    public void componentMoved(ComponentEvent e) {
    }

    /**
     * Do nothing.
     *
     * @param e the component event
     */
    @Override
    public void componentShown(ComponentEvent e) {
    }

    /**
     * Do nothing.
     *
     * @param e the component event
     */
    @Override
    public void componentHidden(ComponentEvent e) {
    }

}

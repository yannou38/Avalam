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
package avalam.GUI.NewGame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

/**
 * This class resizes the NewGame window.
 * @author Team 7
 */
public class NewGameAdapterListener implements ComponentListener {

    private final GUI_NewGame ng;

    public NewGameAdapterListener(GUI_NewGame newgame) {
        this.ng = newgame;
    }

    //JButton player, prec, sup, load, retour, start;
    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.ng.getInsets();
        Image newimg;
        Dimension size;
        
        //1920-1080 => taille de base
        double ratioW = (double) this.ng.getWidth() / (double) 1920;
        double ratioH = (double) this.ng.getHeight() / (double) 1080;
        
        newimg = this.ng.getReturnI().getScaledInstance(((int) round(263 * ratioW)), ((int) round(123 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getRetour().setIcon(new ImageIcon(newimg));
        this.ng.getRetour().setBounds((int) round((25 + insets.left) * ratioW), ((int) round((934 + insets.top) * ratioH)), (int) round(263 * ratioW), (int) round(123 * ratioH));
        this.ng.getRetour().setSize((int) round(263 * ratioW), (int) round(123 * ratioH));

        newimg = this.ng.getStartI().getScaledInstance(((int) round(1012 * ratioW)), ((int) round(141 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getStart().setIcon(new ImageIcon(newimg));
        this.ng.getStart().setBounds((int) round((449 + insets.left) * ratioW), ((int) round((904 + insets.top) * ratioH)), (int) round(1012 * ratioW), (int) round(141 * ratioH));
        this.ng.getStart().setSize((int) round(1012 * ratioW), (int) round(141 * ratioH));
        
        newimg = this.ng.getPrecI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getPreccolor1().setIcon(new ImageIcon(newimg));
        this.ng.getPreccolor1().setBounds((int) round((15 + insets.left) * ratioW), ((int) round((389 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getPreccolor1().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));
        
        newimg = this.ng.getColorImgs()[this.ng.getP1colorselect()].getScaledInstance(((int) round(412 * ratioW)), ((int) round(103 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getP1color().setIcon(new ImageIcon(newimg));
        this.ng.getP1color().setBounds((int) round((129 + insets.left) * ratioW), ((int) round((394 + insets.top) * ratioH)), (int) round(1012 * ratioW), (int) round(141 * ratioH));
        this.ng.getP1color().setSize((int) round(412 * ratioW), (int) round(103 * ratioH));
        
        newimg = this.ng.getSupI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getSupcolor1().setIcon(new ImageIcon(newimg));
        this.ng.getSupcolor1().setBounds((int) round((536 + insets.left) * ratioW), ((int) round((387 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getSupcolor1().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));
        
        newimg = this.ng.getPrecI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getPreccolor2().setIcon(new ImageIcon(newimg));
        this.ng.getPreccolor2().setBounds((int) round((1267 + insets.left) * ratioW), ((int) round((389 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getPreccolor2().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));
        
        newimg = this.ng.getColorImgs()[this.ng.getP2colorselect()].getScaledInstance(((int) round(412 * ratioW)), ((int) round(103 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getP2color().setIcon(new ImageIcon(newimg));
        this.ng.getP2color().setBounds((int) round((1381 + insets.left) * ratioW), ((int) round((394 + insets.top) * ratioH)), (int) round(1012 * ratioW), (int) round(141 * ratioH));
        this.ng.getP2color().setSize((int) round(412 * ratioW), (int) round(103 * ratioH));
        
        newimg = this.ng.getSupI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getSupcolor2().setIcon(new ImageIcon(newimg));
        this.ng.getSupcolor2().setBounds((int) round((1788 + insets.left) * ratioW), ((int) round((387 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getSupcolor2().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));
        
        newimg = this.ng.getPrecI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getPrec1().setIcon(new ImageIcon(newimg));
        this.ng.getPrec1().setBounds((int) round((15 + insets.left) * ratioW), ((int) round((577 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getPrec1().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));
        
        newimg = this.ng.getAIimgs()[this.ng.getP1select()].getScaledInstance(((int) round(412 * ratioW)), ((int) round(103 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getP1button().setIcon(new ImageIcon(newimg));
        this.ng.getP1button().setBounds((int) round((129 + insets.left) * ratioW), ((int) round((581 + insets.top) * ratioH)), (int) round(1012 * ratioW), (int) round(141 * ratioH));
        this.ng.getP1button().setSize((int) round(412 * ratioW), (int) round(103 * ratioH));
        
        newimg = this.ng.getSupI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getSup1().setIcon(new ImageIcon(newimg));
        this.ng.getSup1().setBounds((int) round((536 + insets.left) * ratioW), ((int) round((575 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getSup1().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));
        
        newimg = this.ng.getPrecI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getPrec2().setIcon(new ImageIcon(newimg));
        this.ng.getPrec2().setBounds((int) round((1267 + insets.left) * ratioW), ((int) round((577 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getPrec2().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));
        
        newimg = this.ng.getAIimgs()[this.ng.getP2select()].getScaledInstance(((int) round(412 * ratioW)), ((int) round(103 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getP2button().setIcon(new ImageIcon(newimg));
        this.ng.getP2button().setBounds((int) round((1381 + insets.left) * ratioW), ((int) round((581 + insets.top) * ratioH)), (int) round(1012 * ratioW), (int) round(141 * ratioH));
        this.ng.getP2button().setSize((int) round(412 * ratioW), (int) round(103 * ratioH));
        
        newimg = this.ng.getSupI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getSup2().setIcon(new ImageIcon(newimg));
        this.ng.getSup2().setBounds((int) round((1788 + insets.left) * ratioW), ((int) round((575 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getSup2().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));

        
        newimg = this.ng.getPrecI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getPrecgrille().setIcon(new ImageIcon(newimg));
        this.ng.getPrecgrille().setBounds((int) round((669 + insets.left) * ratioW), ((int) round((231 + insets.top) * ratioH)), (int) round(96 * ratioW), (int) round(102 * ratioH));
        this.ng.getPrecgrille().setSize((int) round(96 * ratioW), (int) round(102 * ratioH));
        
        newimg = this.ng.getSupI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getSupgrille().setIcon(new ImageIcon(newimg));
        this.ng.getSupgrille().setBounds((int) round((1155 + insets.left) * ratioW), ((int) round((229 + insets.top) * ratioH)), (int) round(96 * ratioW), (int) round(102 * ratioH));
        this.ng.getSupgrille().setSize((int) round(96 * ratioW), (int) round(102 * ratioH));
        
        this.ng.getName1().setBounds((int) round((180 + insets.left) * ratioW), ((int) round((749 + insets.top) * ratioH)), (int) round(466 * ratioW), (int) round(102 * ratioH));
        this.ng.getName1().setSize((int) round(466 * ratioW), (int) round(102 * ratioH));
        
        this.ng.getName2().setBounds((int) round((1432 + insets.left) * ratioW), ((int) round((749 + insets.top) * ratioH)), (int) round(466 * ratioW), (int) round(102 * ratioH));
        this.ng.getName2().setSize((int) round(466 * ratioW), (int) round(102 * ratioH));
        
        size = this.ng.getGrilleName().getPreferredSize();
        this.ng.getGrilleName().setBounds((int) round((769 + insets.left) * ratioW), ((int) round((229 + insets.top) * ratioH)), (int) round(384 * ratioW), (int) round(100 * ratioH));
        this.ng.getGrilleName().setSize((int) round(384 * ratioW), (int) round(100 * ratioH));
        
        this.ng.callResize();
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

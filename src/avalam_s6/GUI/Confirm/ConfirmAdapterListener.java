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
package avalam_s6.GUI.Confirm;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

/**
 * Resizes a confirmation page.
 * @author Team 7
 */
public class ConfirmAdapterListener implements ComponentListener {
    private final GUI_Confirm co;

    /**
     * Constructor
     * @param confirm the confirmation page to resize.
     */
    public ConfirmAdapterListener(GUI_Confirm confirm) {
        this.co = confirm;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.co.getInsets();
        Image newimg;
        //1280*720 => taille de base
        double ratioW = (double) this.co.getWidth() / (double) 1920;
        double ratioH = (double) this.co.getHeight() / (double) 1080;
        
        
        this.co.getTitre().setBounds(0, (int) round(40 * ratioH), this.co.getWidth(), 90);
        this.co.getTitre().setSize(this.co.getWidth(), 90);
        
        newimg = this.co.getYesI().getScaledInstance(((int) round(252 * ratioW)), ((int) round(111 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.co.getYes().setIcon(new ImageIcon(newimg));
        this.co.getYes().setBounds((int) round((355 + insets.left) * ratioW), ((int) round((539 + insets.top) * ratioH)), (int) round(252 * ratioW), (int) round(111*ratioH));
        this.co.getYes().setSize((int) round(252 * ratioW), (int) round(111 * ratioH));
        
        newimg = this.co.getNoI().getScaledInstance(((int) round(252 * ratioW)), ((int) round(111 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.co.getNo().setIcon(new ImageIcon(newimg));
        this.co.getNo().setBounds((int) round((1297 + insets.left) * ratioW), ((int) round((539 + insets.top) * ratioH)), (int) round(252 * ratioW), (int) round(111*ratioH));
        this.co.getNo().setSize((int) round(252 * ratioW), (int) round(111 * ratioH));
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

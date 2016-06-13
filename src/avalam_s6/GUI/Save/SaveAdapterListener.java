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
package avalam_s6.GUI.Save;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

/**
 * The resize system for the save GUI.
 * @author Team 7
 */
public class SaveAdapterListener implements ComponentListener {

    private final GUI_Save page;

    /**
     * Constructor.
     * @param page the Save_GUI this listener is linked to.
     */
    public SaveAdapterListener(GUI_Save page) {
        this.page = page;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.page.getInsets();
        double ratioW = (double) this.page.getWidth() / (double) 1920;
        double ratioH = (double) this.page.getHeight() / (double) 1080;

        Image newimg;

        newimg = this.page.getHomeReturnI().getScaledInstance((int) round(251 * ratioW), (int) round(111 * ratioH), java.awt.Image.SCALE_SMOOTH);
        this.page.getHomereturn().setIcon(new ImageIcon(newimg));
        this.page.getHomereturn().setBounds((int) round((30 + insets.left) * ratioW), ((int) round((930 + insets.top) * ratioH)), (int) round(251 * ratioW), (int) round(111 * ratioH));
        this.page.getHomereturn().setSize((int) round(251 * ratioW), (int) round(111 * ratioH));

        newimg = this.page.getLoadI().getScaledInstance((int) round(1012 * ratioW), (int) round(141 * ratioH), java.awt.Image.SCALE_SMOOTH);
        this.page.getSaveload().setIcon(new ImageIcon(newimg));
        this.page.getSaveload().setBounds((int) round((454 + insets.left) * ratioW), ((int) round((909 + insets.top) * ratioH)), (int) round(1012 * ratioW), (int) round(141 * ratioH));
        this.page.getSaveload().setSize((int) round(1012 * ratioW), (int) round(141 * ratioH));

        newimg = this.page.getSlotI().getScaledInstance((int) round(1200 * ratioW), (int) round(109 * ratioH), java.awt.Image.SCALE_SMOOTH);

        this.page.getSlots(1).setIcon(new ImageIcon(newimg));
        this.page.getSlots(1).setBounds((int) round((359 + insets.left) * ratioW), ((int) round((219 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(1).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        this.page.getSlots(2).setIcon(new ImageIcon(newimg));
        this.page.getSlots(2).setBounds((int) round((359 + insets.left) * ratioW), ((int) round((324 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(2).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        this.page.getSlots(3).setIcon(new ImageIcon(newimg));
        this.page.getSlots(3).setBounds((int) round((359 + insets.left) * ratioW), ((int) round((429 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(3).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        this.page.getSlots(4).setIcon(new ImageIcon(newimg));
        this.page.getSlots(4).setBounds((int) round((359 + insets.left) * ratioW), ((int) round((535 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(4).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        this.page.getSlots(5).setIcon(new ImageIcon(newimg));
        this.page.getSlots(5).setBounds((int) round((359 + insets.left) * ratioW), ((int) round((641 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(5).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        this.page.getSlots(6).setIcon(new ImageIcon(newimg));
        this.page.getSlots(6).setBounds((int) round((359 + insets.left) * ratioW), ((int) round((747 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlots(6).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        this.page.getSlotlabels(1).setBounds((int) round((359 + insets.left) * ratioW), ((int) round((219 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlotlabels(1).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlotlabels(1).setFont(this.page.getTextFont().deriveFont(45f * this.page.getWidth() / 1920));
        this.page.getSlotlabels(1).setText(this.page.getSlotlabels(1).getText());
        
        this.page.getSlotlabels(2).setBounds((int) round((359 + insets.left) * ratioW), ((int) round((324 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlotlabels(2).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlotlabels(2).setFont(this.page.getTextFont().deriveFont(45f * this.page.getWidth() / 1920));
        this.page.getSlotlabels(2).setText(this.page.getSlotlabels(2).getText());
        
        this.page.getSlotlabels(3).setBounds((int) round((359 + insets.left) * ratioW), ((int) round((429 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlotlabels(3).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlotlabels(3).setFont(this.page.getTextFont().deriveFont(45f * this.page.getWidth() / 1920));
        this.page.getSlotlabels(3).setText(this.page.getSlotlabels(3).getText());
        
        this.page.getSlotlabels(4).setBounds((int) round((359 + insets.left) * ratioW), ((int) round((535 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlotlabels(4).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlotlabels(4).setFont(this.page.getTextFont().deriveFont(45f * this.page.getWidth() / 1920));
        this.page.getSlotlabels(4).setText(this.page.getSlotlabels(4).getText());
        
        this.page.getSlotlabels(5).setBounds((int) round((359 + insets.left) * ratioW), ((int) round((641 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlotlabels(5).setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getSlotlabels(5).setFont(this.page.getTextFont().deriveFont(45f * this.page.getWidth() / 1920));
        this.page.getSlotlabels(5).setText(this.page.getSlotlabels(5).getText());
        
        this.page.getField().setFont(this.page.getTextFont().deriveFont(45f * this.page.getWidth() / 1920));
        this.page.getField().setText(this.page.getField().getText());
        this.page.getField().setBounds((int) round((359 + insets.left) * ratioW), ((int) round((747 + insets.top) * ratioH)), (int) round(1200 * ratioW), (int) round(109 * ratioH));
        this.page.getField().setSize((int) round(1200 * ratioW), (int) round(109 * ratioH));

        this.page.getField().setFont(this.page.getTextFont().deriveFont(45f * this.page.getWidth() / 1920));
        this.page.getField().setText(this.page.getField().getText());

        this.page.callResize();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
        this.page.loadSlotText();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

}

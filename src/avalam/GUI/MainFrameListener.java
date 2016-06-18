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
package avalam.GUI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 *
 * @author Yann Ducruy <yann.ducruy@gmail.com>
 */
public class MainFrameListener implements WindowFocusListener {

    Main_Frame mf;
    
    public MainFrameListener(Main_Frame mf){
        this.mf=mf;
    }
    
    @Override
    public void windowGainedFocus(WindowEvent e) {
        mf.resizeCurrent();
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        mf.resizeCurrent();
    }
    
}

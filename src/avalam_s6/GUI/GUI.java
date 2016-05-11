/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import avalam_s6.Core.Game;

/**
 *
 * @author TheDoctor
 */
public interface GUI {        
    //public void setGrid(Grid grid);
    public boolean isCloseRequested();
    public void input();

    public void render(Game game);

    public void dispose();
    public void close();
    public void afficheJoueur(int playerId);
}

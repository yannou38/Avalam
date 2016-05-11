/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

import javax.swing.Timer;

/**
 *
 * @author TheDoctor
 */
public interface Game {
    //public void init(Grid g, Player[] p,IHM ih,GUI wdow);
    public void input();
    public void cancel();
    public void redo();
    public void save(String filePath);
    public void load(String filePath);
    public void play();
    public Timer getT();


    public void update();
    //public Grid getGrid();
    public boolean isPlayerTurn();
    public boolean isInit();
}

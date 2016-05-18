/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.NewGame;

import avalam_s6.GUI.Main_Frame;
import avalam_s6.GUI.WindowState;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author loic
 */
public class NewGameListener implements MouseListener {

    private String name;
    private Image icon;
    private Image iconbase;
    private int playernum;
    private String theme;
    
    public NewGameListener(String buttonname, String theme, int playernumber) {
        this.name = buttonname;
        this.playernum = playernumber;
        this.theme = theme;
        try {
            this.icon = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/" + this.name + "_h.png"));
            this.iconbase = ImageIO.read(new File("./ressources/Themes/" + this.theme + "/playerselect/" + this.name + ".png"));
        } catch (Exception ex) {
            System.out.println("Error - "+NewGameListener.class.toString());
            Logger.getLogger(NewGameListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        GUI_NewGame newGame = ((GUI_NewGame)source.getParent());
        Main_Frame mainFrame = ((Main_Frame)newGame.getParent().getParent().getParent().getParent());
        switch (this.name){
            case "load" :
                break;
            case "start" :                
                mainFrame.initGame();                
                break;                
            case "return" :
                mainFrame.setwState(WindowState.MAIN);   
                break;
            case "player" :
                //TODO : Le choix de l'ia
                break;
            case "sup" :
                //TODO : Changer le bouton vers AIHard
                break;
            case "prec" :
                //TODO : Changer le bouton vers AIEasy
                break;
        }
        ((JButton)e.getSource()).setIcon(new ImageIcon(this.iconbase));
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //replace the icon with another
        ((JButton)e.getSource()).setIcon(new ImageIcon(this.icon));        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //replace the icon with another
        ((JButton)e.getSource()).setIcon(new ImageIcon(this.iconbase));
    }

}

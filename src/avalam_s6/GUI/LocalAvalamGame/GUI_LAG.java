/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.LocalAvalamGame;

import avalam_s6.Core.File_IO.Level_Parser;
import avalam_s6.Core.*;
import avalam_s6.Exceptions.GridSizeException;
import avalam_s6.GUI.HomePage.GUI_HomePage;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.Player.AIPlayerRandom;
import avalam_s6.Player.ControlledPlayer;
import avalam_s6.Player.Player;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author sazeratj
 */
public class GUI_LAG extends JPanel{
    private Game_INTERFACE game;
    private Image background;
    String theme;
    /**
     * Constructor.
     */
    public GUI_LAG() {
        this("Default");
    }
    
    public GUI_LAG(String theme){
        this.theme = theme;
        this.initComponents();
    }
    
    private void initComponents(){
        try {
            this.background = ImageIO.read(new File("./ressources/Themes/" + theme + "/board/background.png"));
        } catch (Exception ex) {
            Logger.getLogger(GUI_HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void initGame(){
        try {
            Player p1 = new ControlledPlayer("Jon Doe",Color.WHITE,Owner.PLAYER_1);
            Player p2 = new AIPlayerRandom("Bot_Frank",Color.BLACK,Owner.PLAYER_2);
            Level_Parser myParser = new Level_Parser("default");
            Grid g = new Grid(myParser.readLevel()); // IOException | GridSizeException | NumberFormatException
            Container mainFrame = this.getParent().getParent().getParent().getParent();
            System.out.println(mainFrame.toString());
            this.game = new Local_Avalam_Game(g, p1, p2,(Main_Frame) mainFrame); // GridSizeException
        } catch (IOException|GridSizeException|NumberFormatException ex) {
            Logger.getLogger(GUI_LAG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
    public void start() {
        this.game.getTimer().start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

}

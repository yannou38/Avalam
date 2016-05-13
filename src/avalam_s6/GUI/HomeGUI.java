/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author ducruyy
 */
/*
 * This class contain the home page with the avalam logo, and the diverse buttons to create a game,*
 * access options, check rules & tutorial, etc
 */
public class HomeGUI extends JComponent {

    JButton quick,play,settings,rules,tuto,exit;
    private Image background,quickI,playI,settingsI,rulesI,tutoI,exitI;
    String theme;

    public HomeGUI() {
        initComponents();
        this.theme="Default";
    }

    public HomeGUI(String theme) {
        initComponents();
        this.theme=theme;
    }

    private void initComponents() {
        try{
        background = ImageIO.read(new File("./ressources/GUI/"+theme+"/main/main_bg.png"));
        playI = ImageIO.read(new File("./ressources/GUI/"+theme+"/main/customgame.png"));
        tutoI = ImageIO.read(new File("./ressources/GUI/"+theme+"/main/tuto.png"));
        quickI = ImageIO.read(new File("./ressources/GUI/"+theme+"/main/quickgame.png"));
        settingsI = ImageIO.read(new File("./ressources/GUI/"+theme+"/main/options.png"));
        rulesI = ImageIO.read(new File("./ressources/GUI/"+theme+"/main/rules.png"));
        exitI = ImageIO.read(new File("./ressources/GUI/"+theme+"/main/quit.png"));
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        
        
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }
    
    
}

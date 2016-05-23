/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.Grid;
import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;
import avalam_s6.GUI.GuiManager_INTERFACE;
import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Seawolf
 */
public class AIPlayerEasyTest {
    
    public AIPlayerEasyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of play method, of class AIPlayerEasy.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        AIPlayerEasy instance = new AIPlayerEasy("Test",Color.BLACK,Owner.PLAYER_1);
        Move expResult = null;
        Grid magrid = new Grid("011111111011111111011111111011111111011111111011111111011111111011111111011111111");
    }
    
}

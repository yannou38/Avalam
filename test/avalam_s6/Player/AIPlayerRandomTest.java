/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Player;

import avalam_s6.Core.Move;
import avalam_s6.Core.Owner;
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
public class AIPlayerRandomTest {
    
    public AIPlayerRandomTest() {
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
     * Test of play method, of class AIPlayerRandom.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        AIPlayerRandom instance = new AIPlayerRandom("Test",Color.BLACK,Owner.PLAYER_1);
        Move result = instance.play();
    }
    
}

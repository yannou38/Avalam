/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

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
public class CellTest {
    
    public CellTest() {
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
     * Test of add method, of class Cell.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Owner p = Owner.PLAYER_1;
        Cell instance = new Cell(0);
        int result = instance.getSize();
        assertEquals(0, result);
        instance.add(p);
        result = instance.getSize();
        assertEquals(1, result);
        instance.add(p);
        result = instance.getSize();
        assertEquals(2, result);
        instance.add(p);
        result = instance.getSize();
        assertEquals(3, result);
        instance.add(p);
        result = instance.getSize();
        assertEquals(4, result);
        instance.add(p);
        result = instance.getSize();
        assertEquals(5, result);
        instance.add(p);
        result = instance.getSize();
        assertEquals(5, result);
        
    }

    /**
     * Test of removeAt method, of class Cell.
     */
    @Test
    public void testRemoveAt() {
        System.out.println("removeAt, pas fait, pas compris le retour");
        int i = 0;
        Cell instance = new Cell(0);
        Owner result = instance.removeAt(i);
    }

    /**
     * Test of getSize method, of class Cell.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        Cell instance = new Cell(0);
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOwner method, of class Cell.
     */
    @Test
    public void testGetOwner() {
        System.out.println("getOwner");
        Cell instance = new Cell(1);
        Owner expResult = Owner.PLAYER_1;
        Owner result = instance.getOwner();
        assertEquals(expResult, result);
        
        instance = new Cell(2);
        expResult = Owner.PLAYER_2;
        result = instance.getOwner();
        assertEquals(expResult, result);
        
        instance = new Cell(0);
        expResult = Owner.NO_OWNER;
        result = instance.getOwner();
        assertEquals(expResult, result);
        
        instance = new Cell(-1);
        expResult = Owner.NO_OWNER;
        result = instance.getOwner();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Cell.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Cell instance = new Cell(0);
        CellState expResult = null;
        CellState result = instance.getState();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}

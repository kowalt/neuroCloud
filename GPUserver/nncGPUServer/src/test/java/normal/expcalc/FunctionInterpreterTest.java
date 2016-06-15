/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normal.expcalc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomasz
 */
public class FunctionInterpreterTest {
    
    final double EPSILON = 0.001;
    
    public FunctionInterpreterTest() {
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
    
    @Test 
    public void testCalculateValue()
    {
        FunctionInterpreter f_inter = new FunctionInterpreter();
        
        assertEquals(-11.0, f_inter.calculateValueInfix("-5.5*2", new double[]{5.5}), EPSILON);
        assertEquals(-30.25, f_inter.calculateValueInfix("-5.5^2", new double[]{5.5}), EPSILON);
    }
    
    @Test
    public void testCalculateValueRPN() {
        FunctionInterpreter f_inter = new FunctionInterpreter();
        double actual = f_inter.calculateValueRPN(new String[]{"5.5","2","^","-"});
        assertEquals(-30.25, actual, EPSILON);
    }
}

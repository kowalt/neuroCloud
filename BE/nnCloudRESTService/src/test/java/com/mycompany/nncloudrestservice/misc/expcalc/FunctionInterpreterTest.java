/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.misc.expcalc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomek
 */
public class FunctionInterpreterTest {
    
    private final double PRECISION = 0.001;
    
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

    /**
     * Test of calculateValueInfix method, of class FunctionInterpreter.
     */
    @Test
    public void testCalculateValueInfix() {
        System.out.println("calculateValueInfix");
        String infix = "Sech(x)";
        FunctionInterpreter instance = new FunctionInterpreter();
        double expResult = 0.811048;
        double argument = 0.67;
        double result = instance.calculateValueInfix(infix.replaceAll("x", String.valueOf(argument)), new double[]{argument});
        assertEquals(expResult, result, PRECISION);
        //second case
        infix = "Sech(x)^2";
        expResult = 0.657799;
        result = instance.calculateValueInfix(infix.replaceAll("x", String.valueOf(argument)), new double[]{argument});
        assertEquals(expResult, result, PRECISION);
        //third
        infix = "5.0*Sech(x)^2*3.0+8.0";
        expResult = 17.866978;
        result = instance.calculateValueInfix(infix.replaceAll("x", String.valueOf(argument)), new double[]{argument});
        assertEquals(expResult, result, PRECISION);
    }

    /**
     * Test of toRPN method, of class FunctionInterpreter.
     */
    @Test
    public void testToRPN() {
        System.out.println("toRPN");
        String infix = "";
        FunctionInterpreter instance = new FunctionInterpreter();
        String[] expResult = null;
        String[] result = instance.toRPN(infix);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of substitute method, of class FunctionInterpreter.
     */
    @Test
    public void testSubstitute() {
        System.out.println("substitute");
        String s = "";
        double[] v = null;
        char exc = ' ';
        FunctionInterpreter instance = new FunctionInterpreter();
        String expResult = "";
        String result = instance.substitute(s, v, exc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateDim method, of class FunctionInterpreter.
     */
    @Test
    public void testCalculateDim() {
        System.out.println("calculateDim");
        String infix = "";
        FunctionInterpreter instance = new FunctionInterpreter();
        int expResult = 0;
        int result = instance.calculateDim(infix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

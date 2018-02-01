/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.localcalculations;

import com.mycompany.nncloudrestservice.pojo.ActivationFunction;
import java.util.List;
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
public class ValueCalculatorTest {
    
    public ValueCalculatorTest() {
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
     * Test of calculateValue method, of class ValueCalculator.
     */
    @Test
    public void testCalculateValue() {
        System.out.println("calculateValue");
        List<ActivationFunction> activation_functions = null;
        double input = 0.0;
        boolean derivative = false;
        ValueCalculator instance = new ValueCalculator();
        double expResult = 0.0;
        double result = instance.calculateValue(activation_functions, input, derivative);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

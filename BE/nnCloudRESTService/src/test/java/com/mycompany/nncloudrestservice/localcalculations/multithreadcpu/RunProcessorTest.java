/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.localcalculations.multithreadcpu;

import com.mycompany.nncloudrestservice.pojo.Layer;
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
public class RunProcessorTest {
    
    public RunProcessorTest() {
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
     * Test of setInput method, of class RunProcessor.
     */
    @Test
    public void testSetInput() {
        System.out.println("setInput");
        double[] input = null;
        RunProcessor instance = null;
        instance.setInput(input);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runLayer method, of class RunProcessor.
     */
    @Test
    public void testRunLayer() {
        System.out.println("runLayer");
        Layer l = null;
        RunProcessor instance = null;
        instance.runLayer(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

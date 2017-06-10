/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.localcalculations;

import com.mycompany.nncloudrestservice.localcalculations.RunManager;
import com.mycompany.nncloudrestservice.utils.TestUtils;
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
public class RunManagerTest {
    
    public final Double DELTA = 0.000001;
    
    public RunManagerTest() {
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
    public void run() 
    {
        double[] expecteds1 = {0.045};
        double[] testInput1 = {0.1};
        RunManager manager = new RunManager(TestUtils.createNetwork1());
        manager.setMode(RunManager.Mode.SINGLETHREAD);
        assertArrayEquals(expecteds1, manager.run(testInput1), DELTA);
        
        manager.setMode(RunManager.Mode.MULTITHREAD);
        assertArrayEquals(expecteds1, manager.run(testInput1), DELTA);
        
        //second network
        manager = new RunManager(TestUtils.createNetwork2());
        manager.setMode(RunManager.Mode.SINGLETHREAD);
        assertArrayEquals(expecteds1, manager.run(testInput1), DELTA);
    }
    

}

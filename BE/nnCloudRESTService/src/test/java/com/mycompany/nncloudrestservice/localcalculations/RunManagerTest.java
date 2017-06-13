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
    
    public final Double DELTA = 0.00001;
    
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
        double[] testInput2 = {0.1,0.4,0.5,0.9};
        double[] expecteds2 = {0.5123800463240914,0.5123800463240914};
        manager = new RunManager(TestUtils.createNetwork2());
        manager.setMode(RunManager.Mode.SINGLETHREAD);
        assertArrayEquals(expecteds2, manager.run(testInput2), DELTA);
        manager.setMode(RunManager.Mode.MULTITHREAD);
        assertArrayEquals(expecteds2, manager.run(testInput2), DELTA);
        
        //third
        double[] testInput3 = {0.33,0.24,0.9};
        double[] expecteds3 = {0.3261574509746072};
        manager = new RunManager(TestUtils.createNetwork3());
        manager.setMode(RunManager.Mode.SINGLETHREAD);
        assertArrayEquals(expecteds3, manager.run(testInput3), DELTA);
        manager.setMode(RunManager.Mode.MULTITHREAD);
        assertArrayEquals(expecteds3, manager.run(testInput3), DELTA);
        
        //fourth- largest network
        
        double[] testInput4 = {0.33,0.24,0.9,0.52};
        double[] expecteds4 = {0.8977604920144835,0.8977604920144835,0.8977604920144835,0.8977604920144835,0.8977604920144835};
        manager = new RunManager(TestUtils.createNetwork4());
        manager.setMode(RunManager.Mode.SINGLETHREAD);
        assertArrayEquals(expecteds4, manager.run(testInput4), DELTA);
        manager.setMode(RunManager.Mode.MULTITHREAD);
        assertArrayEquals(expecteds4, manager.run(testInput4), DELTA);
        
        //fifth
        double[] testInput5 = {0.33,0.24,0.9,0.52};
        double[] expecteds5 = {0.6620500831850127,0.6620500831850127,0.6620500831850127,0.6620500831850127,0.6620500831850127};
        manager = new RunManager(TestUtils.createNetwork5());
        manager.setMode(RunManager.Mode.SINGLETHREAD);
        assertArrayEquals(expecteds5, manager.run(testInput5), DELTA);
        manager.setMode(RunManager.Mode.MULTITHREAD);
        assertArrayEquals(expecteds5, manager.run(testInput5), DELTA);
        
        //sixth
        double[] testInput6 = {0.33,0.8,0.9};
        double[] expecteds6 = {0.5695772868696615, 0.7642207101974337};
        manager = new RunManager(TestUtils.createNetwork6());
        manager.setMode(RunManager.Mode.SINGLETHREAD);
        assertArrayEquals(expecteds6, manager.run(testInput6), DELTA);
        manager.setMode(RunManager.Mode.MULTITHREAD);
        assertArrayEquals(expecteds6, manager.run(testInput6), DELTA);
    }
}

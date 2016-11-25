/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.localcalculations.singlethreaded;

import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import java.util.ArrayList;
import java.util.List;
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
public class SingleThreadRunManagerTest {
    
    public final Double DELTA = 0.000001;
    
    public SingleThreadRunManagerTest() {
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
        Double[] expecteds = {0.1};
        //assertArrayEquals(expecteds, actuals, 0);
    }
    
    private Network createNetwork1()
    {
        Network n = new Network();
        List<Layer> layers = new ArrayList<Layer>();
        Layer l1 = new Layer();
        Neuron n1 = new Neuron();
        //n1.set TODO FINISH
        return null;
    }
}

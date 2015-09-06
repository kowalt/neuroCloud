/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.misc;

import com.mycompany.nncloudrestservice.model.ActivationFunction;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 *
 * @author Tomasz
 */
public class ActivationFunctionParserTest 
{

    public ActivationFunctionParserTest() 
    {
        
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        
    }
    
    @Before
    public void setUp() 
    {
        
    }
    
    @After
    public void tearDown() 
    {
        
    }
    
    @Test
    public void parseTest()
    {
        ActivationFunctionParser actualParser = new ActivationFunctionParser();
        List<ActivationFunction> actual = actualParser.parse("-1<=>x<=-1;x<=>x>-1,x<1;1<=>x>=1");
        
        assertEquals("-1", actual.get(0).getFunction());
        assertEquals("x<=-1", actual.get(0).getDomain_rule());
        
        assertEquals("x", actual.get(1).getFunction());
        assertEquals("x>-1,x<1", actual.get(1).getDomain_rule());
        
        assertEquals("1", actual.get(2).getFunction());
        assertEquals("x>=1", actual.get(2).getDomain_rule());
    }
}

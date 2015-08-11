/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice;

import com.mycompany.nncloudrestservice.utils.DomainFromURLUtil;
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
public class DomainFromURLUtilTest {
    
    public DomainFromURLUtilTest() {
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
    public void testGetDomain() 
    {
        String case1 = "www.interia.pl";
        String case2 = "http://cnn.com";
        String case3 = "anyprotocol://pw.edu.pl";
        String case4 = "178.62.119.189";
        String case5 = "http://178.62.119.189:8521/path/to/anywhere/77/foo";
        
        assertEquals("interia.pl", DomainFromURLUtil.getDomain(case1));
        assertEquals("cnn.com", DomainFromURLUtil.getDomain(case2));
        assertEquals("pw.edu.pl", DomainFromURLUtil.getDomain(case3));
        assertEquals("178.62.119.189", DomainFromURLUtil.getDomain(case4));
        assertEquals("178.62.119.189", DomainFromURLUtil.getDomain(case5));
    }
}

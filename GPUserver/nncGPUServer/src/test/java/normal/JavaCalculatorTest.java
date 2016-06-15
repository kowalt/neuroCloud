/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normal;

import java.util.ArrayList;
import java.util.List;
import model.original.ActivationFunction;
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
public class JavaCalculatorTest {
    private final double EPSILON = 0.01;
    
    public JavaCalculatorTest() {
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
    public void checkCalculateValue() 
    {
        List<ActivationFunction> activation_functions = new ArrayList<>();
        
        ActivationFunction af = new ActivationFunction();
        
        af.setFunction("x^2");
        af.setDomain_rule("x>5");
        
        activation_functions.add(af);
        
        ValueCalculator vc = new ValueCalculator();
        
        assertEquals(30.25,vc.calculateValue(activation_functions, 5.5),EPSILON);
        af.setDomain_rule("x>5,x<10");
        assertEquals(30.25,vc.calculateValue(activation_functions, 5.5),EPSILON);
    
        af.setFunction("(-1)*x^2");
        assertEquals(-30.25,vc.calculateValue(activation_functions, 5.5),EPSILON);
        
        af.setFunction("-x^2");
        assertEquals(-30.25,vc.calculateValue(activation_functions, 5.5),EPSILON);
        
        af.setFunction("(-x)^2");
        assertEquals(30.25,vc.calculateValue(activation_functions, 5.5),EPSILON);
    }
}

package com.mycompany.nncloudrestservice.localcalculations;

import com.mycompany.nncloudrestservice.misc.expcalc.FunctionInterpreter;
import com.mycompany.nncloudrestservice.pojo.ActivationFunction;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tomasz
 */

public class ValueCalculator
{
    private FunctionInterpreter f_interpreter;
    private static final Logger logger = LogManager.getLogger(ValueCalculator.class);
    
    public ValueCalculator()
    {
        f_interpreter = new FunctionInterpreter();
    }
    
    public double calculateValue(List<ActivationFunction> activation_functions, double input, boolean derivative)
    {
        double output = Double.NaN;
        for(ActivationFunction af: activation_functions)
        {
            if(checkIfDomainRuleApply(af.getDomain_rule(), input))
            {   
                if(derivative)
                    output = calculateFunctionValue(af.getFirst_derivative(), input);
                else
                    output = calculateFunctionValue(af.getFunction(), input);
                break;
            }
        }
        return output;
    }
    
    /**
     * Check if any of rules apply
     * @author Tomasz Kowal
    */
    private boolean checkIfDomainRuleApply(String domain_rules, double x_value)
    {
        Set<String> comparators = new HashSet<String>();
        
        comparators.add(">");
        comparators.add(">=");
        comparators.add("<");
        comparators.add("<=");
        comparators.add("=");

        String[] domain_rules_arr = domain_rules.split(",");
        if(domain_rules_arr[0].equals(""))
            return true;
        //check conjunction
        for(String domain_rule: domain_rules_arr)
        {
            for(String c: comparators)
            {    
                String[] rightAndLeft = domain_rule.split(c);
                if(rightAndLeft != null && rightAndLeft.length == 2)
                {   
                    String right = rightAndLeft[1];
                    String left = rightAndLeft[0];
                    if(isRuleTrue(left, right, c, x_value))
                        return true;
                }
            }
        }
     
        return false;
    }
    
    private boolean isRuleTrue(String left, String right, String comparator, double x)
    {   
        double leftValue = calculateFunctionValue(left, x);
        double rightValue = calculateFunctionValue(right, x);
        
        switch(comparator)
        {    
            case ">":
                return leftValue > rightValue;
            case ">=":
                return leftValue >= rightValue;
            case "<=":
                return leftValue <= rightValue;
            case "<":
                return leftValue <= rightValue;
            case "=":
                return leftValue == rightValue;
            default:
                return false;
        }
    }

    private double calculateFunctionValue(String function_raw, double argument)
    {
        logger.debug("calculateFunctionValue->function_raw="+function_raw+" ,argument="+String.valueOf(argument));
        return f_interpreter.calculateValueInfix(function_raw.replaceAll("x", String.valueOf(argument)), new double[]{argument});
    }
}


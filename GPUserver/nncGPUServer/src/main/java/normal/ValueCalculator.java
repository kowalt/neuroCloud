package normal;

import java.util.List;
import model.original.ActivationFunction;

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
    public double calculateValue(List<ActivationFunction> activation_functions, double input)
    {
        double output = Double.NaN;
        for(ActivationFunction af: activation_functions)
        {
            if(checkIfDomainRuleApply(af.getDomain_rule()))
            {    
                output = calculateFunctionValue(af.getFunction(),input);
                break;
            }
        }
        return output;
    }
    
    /**
     * Check if any of rules apply
     * @author Tomasz Kowal
    */
    private boolean checkIfDomainRuleApply(String domain_rules)
    {
        String[] domain_rules_arr = domain_rules.split(",");
        
        for(String domain_rule: domain_rules_arr)
        {
            
        }
        
        return false;
    }
    
    private double calculateFunctionValue(String funcion_raw, double argument)
    {
        return Double.NaN;
    }
}


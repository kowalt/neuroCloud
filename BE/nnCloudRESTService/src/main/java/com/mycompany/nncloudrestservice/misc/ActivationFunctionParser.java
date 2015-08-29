/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.misc;

import com.mycompany.nncloudrestservice.model.ActivationFunction;
import java.util.StringTokenizer;

/**
 *
 * @author Tomasz
 */
public class ActivationFunctionParser 
{
    
    public ActivationFunctionParser()
    {

    }
    
    public ActivationFunction parse(String raw)
    {
        StringTokenizer rulesTok = new StringTokenizer(raw, ";");
        String rule,function,domain;
        
        ActivationFunction af = new ActivationFunction();
        
        while(rulesTok.hasMoreElements())
        {
            rule = rulesTok.nextToken();
            StringTokenizer innerTok = new StringTokenizer(rule, "<=>");
            function = innerTok.nextToken();
            domain = innerTok.nextToken();
            
            af.setFunction(function);
            af.setDomain_rule(domain);
        }
        return af;
    }
}

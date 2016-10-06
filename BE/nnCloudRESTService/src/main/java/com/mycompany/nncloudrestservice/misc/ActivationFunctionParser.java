/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.misc;

import com.mycompany.nncloudrestservice.pojo.ActivationFunction;
import java.util.ArrayList;
import java.util.List;
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

    public List<ActivationFunction> parse(String raw)
    {
        StringTokenizer rulesTok = new StringTokenizer(raw, ";");

        
        List<ActivationFunction> af = new ArrayList<>();
        ActivationFunction afun;
        
        while(rulesTok.hasMoreElements())
        {
            String[] rule = rulesTok.nextToken().split("<=>");
            String function;
            String domain = "";
            function = rule[0];
            if(rule.length > 1)
                domain = rule[1];
            
            afun = new ActivationFunction();

            afun.setFunction(function);
            afun.setDomain_rule(domain);

            af.add(afun);
        }
        
        return af;
    }
}

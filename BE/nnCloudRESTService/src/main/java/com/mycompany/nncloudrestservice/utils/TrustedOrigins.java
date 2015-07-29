/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.utils;

/**
 *
 * @author Tomasz
 */
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class TrustedOrigins 
{
    private final static List<String> trustedOrigins;
    
    static
    {
        trustedOrigins = new ArrayList<>();
        trustedOrigins.add("http://localhost:80");
        trustedOrigins.add("http://178.62.119.189:80");
        trustedOrigins.add("chrome-extension://hgmloofddffdnphfgcellkdfbfbjeloo");
    }
    
    public static boolean check(String origin)
    {
        return trustedOrigins.contains(origin);
    }
}

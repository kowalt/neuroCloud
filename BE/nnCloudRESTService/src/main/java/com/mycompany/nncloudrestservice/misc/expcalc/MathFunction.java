/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expcalclib;

import java.lang.Math;
import java.util.Stack;
/**
 *
 * @author Tomek
 */
public class MathFunction 
{    
    public static double getValue(String symbol,double [] v)
    {
        switch(symbol)
        {
            case "Log":
                return Math.log(v[1])/Math.log(v[0]);
            case "Sin":
                return Math.sin(v[0]);
            case "Cos":
                return Math.cos(v[0]);
            case "Tg":
                return Math.tan(v[0]);
            case "Ctg":
                return 1/Math.tan(v[0]);
            case "Add":
                return v[0]+v[1];
            case "Sub":
                return v[0]-v[1];
            case "Mul":
                return v[0]*v[1];
            case "Div":
                return v[0]/v[1];
            case "Pow":
                return Math.pow(v[0],v[1]);
            case "Mod":
                return v[0]%v[1];
        }
        return 0.0;
    }        
    
    public static int getNumOfParams(String symbol)
    {
        switch(symbol)
        {
            case "Log":
                return 2;
            case "Sin":
                return 1;
            case "Cos":
                return 1;
            case "Tg":
                return 1;
            case "Ctg":
                return 1;
            case "Add":
                return 2;
            case "Sub":
                return 2;
            case "Mul":
                return 2;
            case "Div":
                return 2;
            case "Pow":
                return 2;
            case "Mod":
                return 2;           
        }
       return -1; 
    }
    
    //stos nie zawiera drugiej wartosci! dlaczego?!
    public static double getValue(String symbol,Stack<Double> v)
    {
        double [] vd = new double[v.size()];
        
        int vInitialSize = v.size();
        
        for(int i=0;i< vInitialSize;i++)
            vd[i] = v.pop();
        
        return getValue(symbol,vd);
    }        
    
}

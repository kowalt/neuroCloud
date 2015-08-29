/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expcalclib;


/**
 *
 * @author Tomek
 */
public class Operator 
{
    public char symbol;
    public boolean associativity;
    public int piority;
    
    public Operator(char symbol,boolean associativity,int piority)
    {
        this.symbol = symbol;
        this.associativity = associativity;
        this.piority = piority;
    }
}

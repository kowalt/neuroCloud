/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.misc.expcalc;

import java.lang.Math;
import java.util.Stack;
import java.util.LinkedList;
import java.lang.Double;
import java.lang.Character;
import java.text.DecimalFormat;
/**
 *
 * @author Tomek
 */

public class FunctionInterpreter
{
    private int numOfOps=5;
    private Operator [] ops = new Operator[numOfOps];
    private int tokCounter=0;  
        
    public FunctionInterpreter()
    {
         ops[0] = new Operator('+',false,2);
         ops[1] = new Operator('-',false,2);
         ops[2] = new Operator('*',false,3);
         ops[3] = new Operator('/',false,3);
         ops[4] = new Operator('^',true,4);
    }        
    
    public double calculateValueInfix(String infix,double[] v)
    {
        String rpnBuf=infix;
        String [] rpnBufTok;      
                
        rpnBuf = substitute(rpnBuf,v,'0');
        
        rpnBufTok = toRPN(rpnBuf);
               
        return calculateValueRPN(rpnBufTok);
    }

    private double calculateValueRPN(String [] rpn)
    {
        Stack<String> s = new Stack();
                               
        for(int i=0;i< rpn.length;i++)
        {
            if(Character.isDigit(rpn[i].charAt(rpn[i].length()-1)))
                s.push(rpn[i]);
            
            if(isOperator(rpn[i].charAt(rpn[i].length()-1)) && (s.size() >= 2))
            {
                double a,b;                
                a=Double.parseDouble(s.pop());
                b=Double.parseDouble(s.pop());                
                s.push(Double.toString(calculateInfixOp(b,rpn[i].charAt(0),a)));                
            }
            
            //czy funkcja
            if(Character.isUpperCase(rpn[i].charAt(0)) && !s.isEmpty())
            {
                Stack<Double> params = new Stack();
                //	zdejmij ze stosu oczekiwaną liczbę parametrów funkcji(ozn. a1...an)                                
                for(int j=0;j<MathFunction.getNumOfParams(rpn[i]);j++) //      
                    if(!s.isEmpty()) params.push(Double.parseDouble(s.pop()));                   
                
                //	odłóż na stos wynik funkcji dla parametrów a1...an
                s.push(Double.toString(MathFunction.getValue(rpn[i],params)));                
            }        
        }        
        return Double.parseDouble(s.pop());
    }
    
    public String [] toRPN(String infix)
    {
        String [] tokensBuf=tokenize(infix);
        
        
        LinkedList<String> printList = new LinkedList();
                
        Stack<String> s = new Stack();
               
        for(int i=0;i<tokCounter;i++)
        {               
            if(Character.isDigit(tokensBuf[i].charAt(tokensBuf[i].length()-1)) || (Character.isLowerCase(tokensBuf[i].charAt(0)) == true))
                printList.add(tokensBuf[i]);
            
            if(Character.isUpperCase(tokensBuf[i].charAt(0)) == true)
                s.push(tokensBuf[i]);
            
            if(tokensBuf[i].charAt(0) == ',')
                while(s.peek().charAt(0) != '(' && s.peek().charAt(0) != ')')      
                    printList.add(s.pop());
                                
            if(isOperator(tokensBuf[i].charAt(tokensBuf[i].length()-1)))
            {   
                int o1i;
                           
                o1i = getOpIndex(tokensBuf[i].charAt(0));
                                
                while(!s.isEmpty() && ((isOperator(s.peek().charAt(s.peek().length()-1))) && (((ops[o1i].associativity == false) && (ops[o1i].piority <= ops[getOpIndex(s.peek().charAt(s.peek().length()-1))].piority)) || (ops[o1i].piority < ops[getOpIndex(s.peek().charAt(s.peek().length()-1))].piority))))
                    printList.add(s.pop());
                
                s.push(tokensBuf[i]);                  
            }       
            
            if(tokensBuf[i].charAt(0) == '(') s.push(tokensBuf[i]);
            
            if(tokensBuf[i].charAt(0) == ')')
            {
                while((!s.isEmpty()) && (s.peek().charAt(0) != '(')) printList.add(s.pop());
                
                while((!s.isEmpty()) && (s.peek().charAt(0) == '(')) s.pop();
                               
                if((!s.isEmpty()) && Character.isUpperCase(s.peek().charAt(0)))
                    printList.add(s.pop());
            }                    
        }
                
        while(s.isEmpty() == false)
            printList.add(s.pop());
        //*Koniec algorytmu dworca przetokowego        
        
        int primitive = printList.size();
        String [] outputToksBuf = new String[primitive];
        
        for(int i=0;i<primitive;i++)        
            outputToksBuf[i] = printList.pop(); 
                
        return outputToksBuf;
    }   
        
    private String[] tokenize(String infix)
    {
        char [] buf;
        String [] tokens = new String[infix.length()];
        
        for(int i=0;i<infix.length();i++)
            tokens[i] = "";
        
        tokCounter = 0;
            
        buf = infix.toCharArray();
        
        for(int i=0; i < infix.length(); i++)
        {   
            //Czy liczba
            if(Character.isDigit(buf[i]) || (((i==0) && isOperator(buf[i])) || ((i>0) && ((isOperator(buf[i-1]) || (buf[i-1] == '(') || (buf[i-1] == ',')) && isOperator(buf[i])))))
            {
                int digitsCounter;
                
                digitsCounter=0;  
                
                if((((i==0) && isOperator(buf[i])) || ((i>0) && ((isOperator(buf[i-1]) || (buf[i-1] == '(') || (buf[i-1] == ',')) && isOperator(buf[i])))))
                {    
                    tokens[tokCounter] += buf[i];
                    digitsCounter++;
                }
                
                while((i+digitsCounter < infix.length()) && ((Character.isDigit(buf[i+digitsCounter])) || (buf[i+digitsCounter] == '.') ))
                {
                    tokens[tokCounter] += buf[i+digitsCounter];
                    digitsCounter++;
                }    
                  
                tokCounter++;                
                i+=digitsCounter-1;
                continue;
            }
            
            //Czy funkcja
            if(Character.isUpperCase(buf[i]))
            {
                int charsCounter=0;
                                
                while((i+charsCounter < infix.length()) && Character.isLetter(buf[i+charsCounter]))
                {                    
                    tokens[tokCounter] += buf[i+charsCounter];
                    charsCounter++;
                }
                tokCounter++;
                i+=charsCounter-1;
                continue;
            }                
            //pozostałe elementy
            tokens[tokCounter]+=buf[i];
            tokCounter++;            
        }
        //koniec algorytmu tokenizacji
        
        String [] returnBuffer = new String[tokCounter];
        
        System.arraycopy(tokens,0,returnBuffer,0,tokCounter);
        
        return returnBuffer;         
    }
    
    private boolean isOperator(char c)
    {
        for(int i=0;i<numOfOps;i++)        
            if(c == ops[i].symbol) return true;
        
        return false;
    }
    
    private int getOpIndex(char symbol)
    {        
        for(int i=0;i<numOfOps;i++)
            if(symbol == ops[i].symbol) return i;
        
        return 0;
    }    
    
    //zamienia wszystie zmienne na odpowiadajace im wartosci. s jest wyrażeniem zapisanym w notacji infiksowej! exc to litera którą pomijamy przy podstawianiu
    public String substitute(String s,double [] v,char exc)
    {
        StringBuffer sbuf = new StringBuffer(s);
        int c=0; //numer bieżącej zmiennej
        
        if(v != null)
        for(int i="a".codePointAt(0);i<"a".codePointAt(0)+v.length;i++) //Dla każdej zmiennej/wymiaru
        {    
            for(int j=0;j<sbuf.length()-1;j++)               
                if(Character.isLowerCase(sbuf.charAt(j)) && (sbuf.codePointAt(j) == i) && (sbuf.charAt(j) != exc))         
                        if(isOperator(sbuf.charAt(j+1)) || (sbuf.charAt(j+1) == ')') || (sbuf.charAt(j+1) == ',')) //jeżeli wyraz po prawej stronie małej litery jest operatorem, prawym nawiasem lub przecinkiem, to mamy do czynienia ze zmienną                                               
                            sbuf.replace(j, j+1,String.valueOf(v[c]));
                         
            //jeżeli wyraz jest końcowy, to
            if(Character.isLowerCase(sbuf.charAt(sbuf.length()-1)) && (sbuf.codePointAt(sbuf.length()-1) == i) && (sbuf.charAt(sbuf.length()-1) != exc))
                if((sbuf.length() == 1) || (sbuf.length() >= 2) && isOperator(sbuf.charAt(sbuf.length()-2)))
                    sbuf.replace(sbuf.length()-1,sbuf.length(),String.valueOf(v[c]));
            
            c++;            
        }
        return sbuf.toString();
    }
    
    //policz ile jest wymiarow
    public int calculateDim(String infix)
    {
        int d=0;//licznik zmiennych
        char last = '\0';
            
        for(int i=0;i<infix.length()-1;i++)
           if(Character.isLowerCase(infix.charAt(i)) && (infix.charAt(i) > last))
               if(isOperator(infix.charAt(i+1)) || (infix.charAt(i+1) == ')') || (infix.charAt(i+1) == ','))
               {                          
                     d++;
                     last = infix.charAt(i);   
               }
        
        //jeżeli wyraz jest końcowy, to
            if(Character.isLowerCase(infix.charAt(infix.length()-1)) && (infix.charAt(infix.length()-1) > last ))
                if((infix.length() == 1) || ((infix.length() >= 2) && isOperator(infix.charAt(infix.length()-2))))
                    d++;
        
        return d;
    }        
    
    private double calculateInfixOp(double a,char symbol,double b)
    {     
        switch(symbol)
        {
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
            case '/':    
                return a/b;
            case '^':
                return Math.pow(a,b);
        }
        return 0.0;
    }                     
}
    
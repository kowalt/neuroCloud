/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.exceptions;

/**
 *
 * @author Tomasz
 */
public class UserExistsException extends Exception 
{
    public UserExistsException(String message)
    {
        super(message);
    }
}

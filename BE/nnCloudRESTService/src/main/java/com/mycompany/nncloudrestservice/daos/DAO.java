/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.daos;

/**
 *
 * @author Tomasz
 */
public interface DAO<T>
{
    public void addItem(T item) throws Exception;
    public void updateItem(T item);
    public void removeItem(T item);
    public T getItem(String... keys) throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.comparators;

import com.mycompany.nncloudrestservice.pojo.Layer;
import java.util.Comparator;

/**
 *
 * @author Tomasz
 */
public class LayerDescendingComparator implements Comparator<Layer> {
    @Override
    public int compare(Layer o1, Layer o2) {
        return -(o1.getRelative_number() - o2.getRelative_number());
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.logic;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.training.ebp.EBP;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
public class TrainingManager {
    public void train(String x)
    {
        JSONObject rawData = new JSONObject(x);
        Integer networkId = (Integer) rawData.get("networkId");
        NetworkDAO ndao = new NetworkDAO();
        ndao.setLazyLoadMode(false);
        Network network = ndao.getItem(networkId.toString());
        network.setState("BUSY");
        Integer iterations = (Integer) rawData.get("iterations");
        network.setTrainingIterationsMax(iterations);
        network.setTrainingIterationsDone(0);
        ndao.updateItem(network);
        Double learningCoefficient = (Double) rawData.get("learningCoefficient");

        JSONArray learningSetArrRaw = new JSONArray(rawData.get("learningSet").toString());
        JSONArray trainingSetArrRaw = new JSONArray(rawData.get("trainingSet").toString());
        List<Double[]> learningSet = new ArrayList<>();
        List<Double[]> trainingSet = new ArrayList<>();
        
        for(int i=0; i<learningSetArrRaw.length(); i++)
        {
            learningSet.add(convertJSONArray(learningSetArrRaw.getJSONArray(i)));
            trainingSet.add(convertJSONArray(trainingSetArrRaw.getJSONArray(i)));
        }

        EBP ebp = new EBP(network, learningCoefficient, iterations, learningSet, trainingSet);
        new Thread(ebp).start();
    }
    
    private Double[] convertJSONArray(JSONArray arr)
    {
        Double[] rbuf = new Double[arr.length()];
        for(int i=0; i<arr.length(); i++)
        {       
            if(arr.get(i) instanceof Integer)
                rbuf[i] = ((Integer)arr.get(i)).doubleValue();
            else
                rbuf[i] = (Double)arr.get(i);
        }
        return rbuf;
    }
}

package com.mycompany.nncloudrestservice.training.ebp;

import com.mycompany.nncloudrestservice.localcalculations.singlethreaded.SingleThreadRunManager;
import com.mycompany.nncloudrestservice.pojo.Network;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;

public class EBP implements Runnable {
    private Network network;
    private Double learning_factor;
    private Integer iterations;
    private List<Double[]> learning_set;
    private List<Double[]> training_set;

    public EBP(Network network, Double learning_factor, Integer iterations, List<Double[]> learning_set, List<Double[]> training_set)
    {
        this.network = network;
        this.learning_factor = learning_factor;
        this.iterations = iterations;
        this.learning_set = learning_set;
        this.training_set = training_set;
    }

    private double calculateGoalFunction(double[] output_vector, double[] expected_vector)
    {
        double r = 0.0;

        for(int i=0; i<output_vector.length; i++)
            r += Math.pow(output_vector[i] - expected_vector[i], 2);

        return r/2.0;
    }
    
    private void updateWeights(double goal_function)
    {
        
    }
    
    private void train(Network n)
    {
        SingleThreadRunManager runManager = new SingleThreadRunManager(network);
        for(int i=0; i<iterations; i++)
        {
            for(int j=0; j<learning_set.size(); j++)
            {    
                double[] output_vector = runManager.run(ArrayUtils.toPrimitive(learning_set.get(j)));
                double[] expected_vector = ArrayUtils.toPrimitive(training_set.get(j));
                double goal_function = calculateGoalFunction(output_vector, expected_vector);
                updateWeights();
            }
        }
    }

    @Override
    public void run() {
        train(network);
    }
}

package com.mycompany.nncloudrestservice.training.ebp;

import com.mycompany.nncloudrestservice.pojo.Network;

public class EBP {
	private Network network;
	private Double learning_factor;
	private Integer iterations;
	private Double[] learning_set;
	private Double[] training_set;
	
	public EBP(Network network, Double learning_factor, Integer iterations, Double[] learning_set, Double[] training_set)
	{
		this.network = network;
		this.learning_factor = learning_factor;
		this.iterations = iterations;
		this.learning_set = learning_set;
		this.training_set = training_set;
	}
	
	public void train(Network n)
	{
		
	}
}

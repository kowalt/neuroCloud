package com.mycompany.nncloudrestservice.model;

import java.util.List;

import javax.ws.rs.Path;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;

public class UploadInput {
	public void uploadInput(JSONObject raw)
	{
		JSONArray arr = raw.getJSONArray("vector");
		int network_id = raw.getInt("id_network");
		
		double[] inputs = new double[arr.length()];
		for(int i=0; i<arr.length();i++)
			inputs[i] = (double)Double.parseDouble(arr.getJSONObject(i).toString());

		NetworkDAO ndao = new NetworkDAO();
		Network network = null;
		try {
			network = ndao.getItem(String.valueOf(network_id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Neuron> input_neurons = network.getLayers().get(0).getNeurons();
		
		int i = 0;
		for(Neuron neuron: input_neurons)
			for(Synapse synapse: neuron.getSynapses_in())
			{	
				synapse.setValue(inputs[i++]);
			}

		ndao.updateItem(network);
	}
}
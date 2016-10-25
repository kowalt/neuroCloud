package com.mycompany.nncloudrestservice.logic;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.pojo.Network;

public class NetworkRemovalManager {
	public void removeNetwork(int id)
	{
		NetworkDAO ndao = new NetworkDAO();
		ndao.setLazyLoadMode(true);
                Network network = ndao.getItem(String.valueOf(id));
		ndao.removeItem(network);
	}
}
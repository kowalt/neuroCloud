/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.logic;

import com.mycompany.nncloudrestservice.pojo.RMIServer;
import com.mycompany.nncloudrestservice.utils.ServerListContainer;

/**
 * Deletes inactive server from list
 * @author Tomasz
 */
public class RMIServerTimeoutChecker implements Runnable {
    private final Long CHECK_FREQUENCY_MS = 10000L;
    private final Long TIMEOUT_MS = 60000L;
    @Override
    public void run() {
        try {
            for(RMIServer s: ServerListContainer.getInstance())
            {
                if((s.getLast_report_time() + TIMEOUT_MS) < System.currentTimeMillis())
                    ServerListContainer.deleteAt(s.getId());
            }
            Thread.sleep(CHECK_FREQUENCY_MS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}

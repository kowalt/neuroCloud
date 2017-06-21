/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.websocket;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Tomek
 */
@Deprecated
@ServerEndpoint("/trainingprogress")
public class TrainingWebSocketServer {
    private static final Logger LOGGER = LogManager.getLogger(TrainingWebSocketServer.class);
    private Session session;
    @OnOpen
    public void onOpen(Session session)
    {
        LOGGER.info(session.getId() + " has opened a WebSocket connection.");
        this.session = session;
        try
        {
            this.session.getBasicRemote().sendText("Connection Established");
        }
        catch(IOException ex)
        {
            LOGGER.error(ex.getMessage());
        }
    }
    
    @OnClose
    public void onClose(Session session)
    {
        LOGGER.info("Session " + session.getId()+" has ended");
    }
    
    public void sendProgressInfo(int iter_done, int iter_max)
    {
        String text = String.format("%.3f%%(%d\\%d)",iter_done/iter_max*100.0, iter_done, iter_max);
        try
        {    
            this.session.getBasicRemote().sendText(text);
        }
        catch(IOException ex)
        {
            LOGGER.error(ex.getMessage());
        }
    }
}

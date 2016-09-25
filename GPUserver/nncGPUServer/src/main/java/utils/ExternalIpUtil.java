/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomasz
 */
public class ExternalIpUtil {
    public static String getExternalIP()
    {
        BufferedReader in = null;
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            return in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ExternalIpUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ExternalIpUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}

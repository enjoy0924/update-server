package com.allere.update.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by G_dragon on 2015/7/22.
 */
public class JettyServer {

    public static void main(String args[]){
        Server server = new Server(9090);

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/update");
        webAppContext.setWar("./src/main/webapp");

        server.setHandler(webAppContext);

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

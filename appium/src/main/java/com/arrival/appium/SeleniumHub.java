package com.arrival.appium;

import org.openqa.grid.internal.utils.GridHubConfiguration;
import org.openqa.grid.web.Hub;
import org.openqa.grid.internal.Registry;



/**
 * Created by Aaron on 22.05.2015.
 **/


//Todo: Should be a Singelton Class
public class SeleniumHub {

    private GridHubConfiguration gridHubConfig;
    private Hub hub;
    private String hubHost;
    private Integer hubPort;


    public SeleniumHub() {
        gridHubConfig = new GridHubConfiguration();
        hubHost = "localhost";
        hubPort = 4444;
        setUpHub();
    }

    public SeleniumHub(String host, Integer port) {
        gridHubConfig = new GridHubConfiguration();
        hubHost = host;
        hubPort = port;
    }

    public static void main(String[] args) {
        SeleniumHub hubNode = new SeleniumHub();
        hubNode.startHub();
        // hubNode.shutDownNodeAndHub();
    }

    private void setUpHub() {
        try {
            gridHubConfig.setHost(hubHost);
            gridHubConfig.setPort(hubPort);
            hub = new Hub(gridHubConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startHub() {
        try {
            hub.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopHub() {
        try {
            hub.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restartHub() {
        try {
            hub.stop();
            hub.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GridHubConfiguration getGridHubConfig() {
        return gridHubConfig;
    }

    public void setGridHubConfig(GridHubConfiguration gridHubConfig) {
        this.gridHubConfig = gridHubConfig;
    }

    public Hub getHub() {
        return hub;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
    }

    public String getHubHost() {
        return hubHost;
    }

    public void setHubHost(String hubHost) {
        this.hubHost = hubHost;
    }

    public Integer getHubPort() {
        return hubPort;
    }

    public void setHubPort(Integer hubPort) {
        this.hubPort = hubPort;
    }
}
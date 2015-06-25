package com.arrival.appium.server;

import com.arrival.appium.model.NodeConfig;
import java.nio.file.Path;


/**
 * Created by tecdesdev on 26/05/15.
 */
public class AppiumAndroid implements AppiumServer {

    private static String appiumPath = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
    private static String nodePath = "/Applications/Appium.app/Contents/Resources/node/bin/node";
    private NodeConfig nodeConfig = null;
    private Process process = null;


    /**
     * Standard Constructor
     */
    public AppiumAndroid() {
        nodeConfig = null;

    }

    public AppiumAndroid(NodeConfig nodeConfig1) {
        nodeConfig = nodeConfig1;
    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void startServer(){
        try{
            ProcessBuilder pb = new ProcessBuilder(
                                                          nodePath,     appiumPath,
                                                          "--address",  nodeConfig.getConfiguration().getHost(),
                                                          "--port",     nodeConfig.getConfiguration().getPort().toString(),
                                                          "--nodeconfig", nodeConfig.getConfigPath().toString()
            );
            process = pb.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This functions stop a current Server over commando line.
     **/
    @Override
    public void stopServer() {
        try {
            process.destroy();
        }
        catch(Throwable e) {
           e.printStackTrace();
        }
    }

    /**
     * This functions restart a current Server over commando line.
     **/
    @Override
    public void restartSever() {
        this.stopServer();
        this.startServer();
    }

    /**
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file exist.
     */
    @Override
    public void runServerWithJSON(Path JSONFilePath) {
        nodeConfig.setConfigPath(JSONFilePath);
    }

    /**
     * @return a Instance of AppiumServer e.g. AppiumSever for IOS oder Android
     */
    @Override
    public AppiumAndroid getInstance() {
        return this;
    }


    /**
     * Getter and Setter functions for appiumPath, nodePath and nodeConfig
     */
    public static String getAppiumPath() {
        return appiumPath;
    }

    public static void setAppiumPath(String appiumPath) {
        AppiumAndroid.appiumPath = appiumPath;
    }

    public static String getNodePath() {
        return nodePath;
    }

    public static void setNodePath(String nodePath) {
        AppiumAndroid.nodePath = nodePath;
    }

    public NodeConfig getNodeConfig() {
        return nodeConfig;
    }

    public void setNodeConfig(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }
}

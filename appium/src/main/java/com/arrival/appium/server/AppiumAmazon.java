package com.arrival.appium.server;

import com.arrival.appium.model.NodeConfig;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.nio.file.Path;

/**
 * Created by tecdesdev on 26/05/15.
 */

public class AppiumAmazon implements AppiumServer {

    private NodeConfig nodeConfig;
    private static String appiumPath = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
    private static String nodePath = "/Applications/Appium.app/Contents/Resources/node/bin/node";

    /**
     * Standard Constructor
     */
    public AppiumAmazon() {
        nodeConfig = null;
    }

    public AppiumAmazon(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void startServer() {
        try{
            CommandLine command = new CommandLine(nodePath);
            command.addArgument(appiumPath);
            command.addArgument("--address");
            command.addArgument(nodeConfig.getConfiguration().getHost());
            command.addArgument("--port");
            command.addArgument(nodeConfig.getConfiguration().getPort().toString());
            command.addArgument("--nodeconfig");
            command.addArgument(nodeConfig.getConfigPath().toString());
            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValue(1);
            executor.execute(command, resultHandler);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This functions stop a current Server over commando line.
     **/
    @Override
    public void stopServer() {

    }

    /**
     * This functions Restart a current Server over commando line.
     **/
    @Override
    public void restartSever() {

    }

    /**
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file existe.
     */
    @Override
    public void runServerWithJSON(Path JSONFilePath) {
        nodeConfig.setConfigPath(JSONFilePath);
    }

    /**
     * Getter and Setter functions for appiumPath, nodePath and nodeConfig
     */
    public static String getAppiumPath() {
        return appiumPath;
    }

    public static void setAppiumPath(String appiumPath) {
        AppiumAmazon.appiumPath = appiumPath;
    }

    public static String getNodePath() {
        return nodePath;
    }

    public static void setNodePath(String nodePath) {
        AppiumAmazon.nodePath = nodePath;
    }

    public NodeConfig getNodeConfig() {
        return nodeConfig;
    }

    public void setNodeConfig(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }
}

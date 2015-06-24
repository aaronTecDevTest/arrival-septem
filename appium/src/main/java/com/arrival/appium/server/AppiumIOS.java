package com.arrival.appium.server;

import com.arrival.appium.model.NodeConfig;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.nio.file.Path;

/**
 * Created by tecdesdev on 26/05/15.
 */
public class AppiumIOS implements AppiumServer {
    private final static int MIN_WEB_KIT_PORT = 27751;
    private final static int MAX_WEB_KIT_PORT = 27852;

    private static String appiumPath = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
    private static String nodePath = "/Applications/Appium.app/Contents/Resources/node/bin/node";
    private static Integer webKitProxyPort= 27751;
    private Process webKitProcess = null;
    private Process process = null;
    private NodeConfig nodeConfig;

    /**
     * Standard Constructor
     */
    public AppiumIOS() {
        nodeConfig = null;
    }

    public AppiumIOS(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void startServer() {
        try{
            ProcessBuilder pb = new ProcessBuilder(
                                                          nodePath,     appiumPath,
                                                          "--address",  nodeConfig.getConfiguration().getHost(),
                                                          "--port",     nodeConfig.getConfiguration().getPort().toString(),
                                                          "--nodeconfig", nodeConfig.getConfigPath().toString()
            );
            process = pb.start();
            startIOSWebKitDebugProxy();
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
            stopIOSWebKitDebugProxy();
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
        stopServer();
        startServer();
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
     * Getter and Setter functions for appiumPath, nodePath and nodeConfig.
     */
    public static String getAppiumPath() {
        return appiumPath;
    }

    public static void setAppiumPath(String appiumPath) {
        AppiumIOS.appiumPath = appiumPath;
    }

    public static String getNodePath() {
        return nodePath;
    }

    public static void setNodePath(String nodePath) {
        AppiumIOS.nodePath = nodePath;
    }

    public NodeConfig getNodeConfig() {
        return nodeConfig;
    }

    public void setNodeConfig(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    /**
     * Launch the IOSWebKitDebugProxy for Safari test.
     */
    public void startIOSWebKitDebugProxy(){
        AppiumIOS.webKitProxyPort++;
        try {
            ProcessBuilder pb = new ProcessBuilder();
            webKitProcess = pb.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stopIOSWebKitDebugProxy(){
        try {
            webKitProcess.destroy();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.arrival.appium.server;

import com.arrival.appium.model.NodeConfig;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;

import java.nio.file.Path;


/**
 * Created by tecdesdev on 26/05/15.
 */

public class AppiumAndroid implements AppiumServer {

    private NodeConfig nodeConfig;
    private static String appiumPath = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
    private static String nodePath = "/Applications/Appium.app/Contents/Resources/node/bin/node";
    private DefaultExecutor appiumProcess;

    /**
     * Standard Constructor
     */
    public AppiumAndroid() {
        nodeConfig = null;
    }

    public AppiumAndroid(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void startServer(){
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
            ExecuteWatchdog watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);

            appiumProcess = new DefaultExecutor();
            appiumProcess.setExitValue(0);
            appiumProcess.setWatchdog(watchdog);
            appiumProcess.execute(command, resultHandler);
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
            /* CommandLine command = new CommandLine("/usr/bin/killall");
            command.addArgument("-9");
            command.addArgument("node");
            command.addArgument("\"lsof -t -i:" + nodeConfig.getConfiguration().getProxy() + "\"");

            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValue(0);
            executor.execute(command, resultHandler);*/
            appiumProcess.getWatchdog().destroyProcess();
        }
        catch(Throwable e)
        {
           e.printStackTrace();
        }
    }

    /**
     * This functions Restart a current Server over commando line.
     **/
    @Override
    public void restartSever() {
        this.stopServer();
        this.startServer();
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

    /*
    So start is:
private Executor appiumProcess;
CommandLine cmdLine = new CommandLine(command);
DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
ExecuteWatchdog watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
PumpStreamHandler streamHandler = new PumpStreamHandler(new FileOutputStream("Device" + deviceIndex + "Logs.txt"));
appiumProcess = new DefaultExecutor();
appiumProcess.setExitValue(0);
appiumProcess.setWatchdog(watchdog);
appiumProcess.setStreamHandler(streamHandler);
appiumProcess.execute(command, resultHandler);

Stop is simple:
appiumProcess.getWatchdog().destroyProcess();

Cmd creation smth like that:
CommandLine cmd = new CommandLine("node");
cmd.addArgument(PropertyLoader.get("appium.path"));
cmd.addArgument("-a");
cmd.addArgument(PropertyLoader.get("appium.host"));
cmd.addArgument("-p");
cmd.addArgument(String.valueOf(Integer.parseInt(PropertyLoader.get("appium.port")) + deviceIndex));
cmd.addArgument("-bp");
cmd.addArgument(String.valueOf(Integer.parseInt(PropertyLoader.get("appium.bootstrapPort")) + deviceIndex));
cmd.addArgument("-U");
cmd.addArgument(device.getUDID());
     */
}

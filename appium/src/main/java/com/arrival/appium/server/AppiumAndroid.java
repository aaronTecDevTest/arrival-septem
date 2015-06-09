package com.arrival.appium.server;

import com.arrival.appium.model.NodeConfig;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.io.IOException;
import java.nio.file.Path;


/**
 * Created by tecdesdev on 26/05/15.
 */

public class AppiumAndroid implements AppiumServer {

    private NodeConfig nodeConfig;
    private static String appiumPath = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
    private static String nodePath = "/Applications/Appium.app/Contents/Resources/node/bin/node";

    /**
     * Standard Constructor
     */
    public AppiumAndroid() {
        nodeConfig = null;
    }

    public AppiumAndroid(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }
/*
    public static void main(String[] args) throws IOException {
        AppiumAndroid androidServer = new AppiumAndroid();
        androidServer.lgServer();
        androidServer.note3Sever();
    }

    public void note3Sever() throws IOException {
        CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
        command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js");

        command.addArgument("--address");
        command.addArgument("127.0.0.1");
        command.addArgument("--port");
        command.addArgument("5555");
        command.addArgument("--nodeconfig");
        command.addArgument("/Users/tecdesdev/IdeaProjects/arrival-septem/appium/src/main/resources/AppiumNodeNote3.json");

//	command.addArgument("--no-reset");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.execute(command, resultHandler);
    }

    public void lgServer() throws IOException {
        CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
        command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js");

        command.addArgument("--address");
        command.addArgument("127.0.0.1");
        command.addArgument("--port");
        command.addArgument("6666");
        command.addArgument("--nodeconfig");
        command.addArgument("/Users/tecdesdev/IdeaProjects/arrival-septem/appium/src/main/resources/AppiumNodeGFlex.json");
    	command.addArgument("--no-reset");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.execute(command, resultHandler);
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
     * This functions start a current Server over commando line.
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
}

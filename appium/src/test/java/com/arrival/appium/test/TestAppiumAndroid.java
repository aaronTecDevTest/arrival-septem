package com.arrival.appium.test;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.io.IOException;

/**
 * Created by tecdesdev on 15/06/15.
 */
public class TestAppiumAndroid {

    public static void main(String[] args) throws IOException {
        TestAppiumAndroid androidServer = new TestAppiumAndroid();
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
}
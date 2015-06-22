package com.arrival.appium;

/**
 * Created by tecdesdev on 28/05/15.
 */

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import sun.management.VMManagement;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * This Class is the Main-Class to run a SeleniumHub and to configure  AppiumSever as a Node
 * in SeleniumHub
 */
public class Main {

    public static void main(String[] args) throws IOException {
        SeleniumHub hub = new SeleniumHub();
        hub.startHub();

        Main main = new Main();
        main.lgFlexServer();

        main.findRunProcess();

        System.out.println("-----------------------------------------------");

        System.out.println(System.getProperty("PID"));
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());

        //main.note3Sever();
        //main.lgG2Server();
    }

    public void note3Sever() throws IOException {
        CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
        command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js");

        command.addArgument("--address");
        command.addArgument("127.0.0.1");
        command.addArgument("--port");
        command.addArgument("5555");
        command.addArgument("--log-level");
        command.addArgument("error");
        command.addArgument("--nodeconfig");
        command.addArgument("/Users/tecdesdev/IdeaProjects/arrival-septem/appium/src/main/resources/AppiumNodeNote3.json");
        command.addArgument("--no-reset");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.execute(command, resultHandler);
    }

    public void lgFlexServer() throws IOException {
        CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
        command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js");

        command.addArgument("--address");
        command.addArgument("127.0.0.1");
        command.addArgument("--port");
        command.addArgument("6666");
        command.addArgument("--log-level");
        command.addArgument("error");
        command.addArgument("--nodeconfig");
        command.addArgument("/Users/tecdesdev/IdeaProjects/arrival-septem/appium/src/main/resources/AppiumNodeGFlex.json");
        command.addArgument("--no-reset");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.execute(command, resultHandler);
    }

    public void lgG2Server() throws IOException {
        CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
        command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js");

        command.addArgument("--address");
        command.addArgument("127.0.0.1");
        command.addArgument("--port");
        command.addArgument("7777");
        command.addArgument("--log-level");
        command.addArgument("error");
        command.addArgument("--nodeconfig");
        command.addArgument("/Users/tecdesdev/IdeaProjects/arrival-septem/appium/src/main/resources/AppiumNodeLGG2.json");
        command.addArgument("--no-reset");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.execute(command, resultHandler);
    }

    public void killAppiumServer(Integer port) throws IOException{
        CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
        command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js");

        command.addArgument("kill");
        command.addArgument("-9");
        command.addArgument(port.toString()); //port

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.execute(command, resultHandler);
    }

    public void findRunProcess() {
        try {
            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
            Field jvmField = runtimeMXBean.getClass().getDeclaredField("jvm");
            jvmField.setAccessible(true);
            VMManagement vmManagement = (VMManagement) jvmField.get(runtimeMXBean);
            Method getProcessIdMethod = vmManagement.getClass().getDeclaredMethod("getProcessId");
            getProcessIdMethod.setAccessible(true);
            Integer processId = (Integer) getProcessIdMethod.invoke(vmManagement);
            System.out.println("################    ProcessId = " + processId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(System.getProperty("java.vm.name"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("java.vendor"));
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.specification.vendor"));
    }
}

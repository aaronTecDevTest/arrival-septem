package com.arrival.appium.server;

import com.arrival.appium.model.NodeConfig;

import java.nio.file.Path;

/**
 * Created by tecdesdev on 26/05/15.
 */
public interface AppiumServer {

    public String appiumPath = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
    public String nodePath = "/Applications/Appium.app/Contents/Resources/node/bin/node";

    /**
     * This functions start a current Server over commando line.
     **/
    public void startServer();


    /**
     * This functions start a current Server over commando line.
     **/
    public void stopServer();


    /**
     * This functions restart a current Server over commando line.
     **/
    public void restartSever();


    /**
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file exist.
     */
    public void runServerWithJSON(Path JSONFilePath);


}

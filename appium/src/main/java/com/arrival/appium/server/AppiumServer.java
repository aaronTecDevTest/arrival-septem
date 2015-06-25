package com.arrival.appium.server;

import com.arrival.appium.model.NodeConfig;
import jdk.nashorn.internal.objects.AccessorPropertyDescriptor;

import java.nio.file.Path;

/**
 * Created by tecdesdev on 26/05/15.
 */
public interface AppiumServer {

     String appiumPath = "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
     String nodePath = "/Applications/Appium.app/Contents/Resources/node/bin/node";

    /**
     * This functions start a current Server over commando line.
     **/
     void startServer();


    /**
     * This functions start a current Server over commando line.
     **/
     void stopServer();


    /**
     * This functions restart a current Server over commando line.
     **/
    void restartSever();


    /**
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file exist.
     */
     void runServerWithJSON(Path JSONFilePath);


    /**
     *
     * @return a Instance of AppiumServer e.g. AppiumSever for IOS oder Android
     */
     Object getInstance();
}

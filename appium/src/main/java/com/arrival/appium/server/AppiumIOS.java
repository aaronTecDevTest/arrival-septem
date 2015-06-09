package com.arrival.appium.server;

import com.arrival.appium.model.NodeConfig;

import java.nio.file.Path;

/**
 * Created by tecdesdev on 26/05/15.
 */


public class AppiumIOS implements AppiumServer {


    public AppiumIOS(NodeConfig nodeConfig) {

    }

    public void startIOSWebKitProxy(){

    }

    public void stopIOSWebKitProxy(){

    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void startServer() {

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
     *
     * @param JSONFilePath -> The Path where the file exicte.
     */
    @Override
    public void runServerWithJSON(Path JSONFilePath) {

    }
}

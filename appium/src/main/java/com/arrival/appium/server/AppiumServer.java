package com.arrival.appium.server;

/**
 * Created by tecdesdev on 26/05/15.
 */
public interface AppiumServer {

    /**
     * This functions start a current Server over commando line.
     **/
    public void startServer();


    /**
     * This functions start a current Server over commando line.
     **/
    public void stopServer();


    /**
     * This functions Restart a current Server over commando line.
     **/
    public void restartSever();


    /**
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file existe.
     */
    public void runServerWithJSON(String JSONFilePath);

}

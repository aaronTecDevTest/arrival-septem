package com.arrival.appium.server;

import com.arrival.appium.SeleniumHub;
import com.arrival.appium.model.DirectoryReader;
import com.arrival.appium.model.NodeConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;



/**
 * Created by tecdesdev on 02/06/15.
 *
 */
public class AppiumManager {

    private DirectoryReader dirReader;
    private ArrayList<Path> pathList;
    private ArrayList<NodeConfig> nodeConfigList;
    private SeleniumHub hub;
    private ArrayList<AppiumServer> appiumServersList;


    public static void main(String[] args) throws IOException {

        AppiumManager manager = new AppiumManager();
        manager.startHubWithNode();
        try{
            Thread.sleep(10000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        manager.stopHubWithNode();
        System.out.printf(manager.toString());
    }

    AppiumManager() {
        dirReader = new DirectoryReader();
        hub = new SeleniumHub();
        nodeConfigList = new ArrayList<>();

        pathList = dirReader.getPathList();
        iniNodeConfig();
        iniAppiumServer();
    }

    AppiumManager(String filePath, String hubHost, Integer hubPort) {
        dirReader = new DirectoryReader(filePath);
        hub = new SeleniumHub(hubHost, hubPort);
        nodeConfigList = new ArrayList<>();

        pathList = dirReader.getPathList();
        iniNodeConfig();
        iniAppiumServer();
    }

    private void iniDefaultAndoridServer(){
        AppiumAndroidDefault defaultAndroid = new AppiumAndroidDefault();
        appiumServersList.add(defaultAndroid);
    }

    private void iniDefaultIOSServer(){
        AppiumIOSDefault defaultIOS = new AppiumIOSDefault();
        appiumServersList.add(defaultIOS);
    }

    private void iniNodeConfig(){
        try {
            ArrayList<String> jsonConfigDataList = new ArrayList<>();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // Get Gson object and init NodeConfig Object
            for (Path path: pathList) {
                String jsonConfigDate = new String(Files.readAllBytes(path));
                NodeConfig node = gson.fromJson(jsonConfigDate, NodeConfig.class);

                jsonConfigDataList.add(jsonConfigDate);
                node.setConfigPath(path);
                nodeConfigList.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniAppiumServer(){
        appiumServersList = new ArrayList<>();
        String platform;
        String browserName;

        try {
            for(NodeConfig nodeConfig : nodeConfigList) {
                platform = nodeConfig.getSingelCapabiites().getPlatform();
                browserName = nodeConfig.getSingelCapabiites().getBrowserName();
                switch(platform) {
                    case "ANDROID":
                        AppiumAndroid android = new AppiumAndroid(nodeConfig);
                        appiumServersList.add(android);
                        break;

                    case "IOS":
                        if (!browserName.equalsIgnoreCase("safari")) {
                            AppiumIOS ios = new AppiumIOS(nodeConfig);
                            appiumServersList.add(ios);
                        }else {
                            AppiumIOS ios = new AppiumIOS(nodeConfig);
                            ios.startIOSWebKitDebugProxy();
                            appiumServersList.add(ios);
                        }
                        break;

                    default:
                        System.out.println("No Server Class found for NodeConfig!");
                        break;
               }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: Start the Appium Server in deferrent tread.
    private void startHubWithNode(){
        try{
            hub.startHub();
            for(AppiumServer appiumServer:appiumServersList){
                appiumServer.startServer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopHubWithNode(){
        try {
            for(AppiumServer appiumServer:appiumServersList){
                appiumServer.stopServer();
            }
            hub.stopHub();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ToDO: for hub und AppiumServer do implement
    private boolean isLocalPortInUse(int port) {
        try {
            // ServerSocket try to open a LOCAL port
            new ServerSocket(port).close();
            // local port can be opened, it's available
            return false;
        } catch(IOException e) {
            // local port cannot be opened, it's in use
            return true;
        }
    }

    //ToDO: for hub und AppiumServer do implement
    private boolean isRemotePortInUse(String hostName, int portNumber) {
        try {
            // Socket try to open a REMOTE port
            new Socket(hostName, portNumber).close();
            // remote port can be opened, this is a listening port on remote machine
            // this port is in use on the remote machine !
            return true;
        } catch(Exception e) {
            // remote port is closed, nothing is running on
            return false;
        }
    }

    public void startDefaulAppium(String plattformName){
        if(plattformName.equalsIgnoreCase("andorid")) {
            AppiumAndroidDefault androidDefault = new AppiumAndroidDefault();
            androidDefault.startServer();
        }
        else if(plattformName.equalsIgnoreCase("ios")){
            AppiumIOSDefault iosDefault = new AppiumIOSDefault();
            iosDefault.startServer();
            }
        else{
            System.out.println("PlattformName " + plattformName + " not found! Only 'Android' or 'IOS' its alloy.");
        }
    }

    public DirectoryReader getDirReader() {
        return dirReader;
    }

    public void setDirReader(DirectoryReader dirReader) {
        this.dirReader = dirReader;
    }

    public ArrayList<NodeConfig> getNodeConfigList() {
        return nodeConfigList;
    }

    public void setNodeConfigList(ArrayList<NodeConfig> nodeConfigList) {
        this.nodeConfigList = nodeConfigList;
    }
}

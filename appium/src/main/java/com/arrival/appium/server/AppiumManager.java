package com.arrival.appium.server;

import com.arrival.appium.SeleniumHub;
import com.arrival.appium.model.NodeConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;



/**
 * Created by tecdesdev on 02/06/15.
 * //http://www.journaldev.com/2321/google-gson-api-for-json-processing-example-tutorial
 */
public class AppiumManager {

    private DirectoryReader dirReader;
    private ArrayList<Path> pathList;
    private ArrayList<NodeConfig> nodeConfigList;
    private SeleniumHub hub;
    private ArrayList<AppiumServer> appiumServersList;


    public static void main(String[] args) throws IOException {

        AppiumManager readJson = new AppiumManager();
        readJson.startHubWithNode();
        /*try{
            Thread.sleep(60000);
        }
        catch (Exception e) {

        }*/
        //readJson.stopHubWithNode();
        System.out.printf(readJson.toString());
        //readJson=null;
    }

    AppiumManager() {
        dirReader = new DirectoryReader();
        hub = new SeleniumHub();
        nodeConfigList = new ArrayList<>();

        pathList = dirReader.getPathList();
        iniNotConfig();
        iniAppiumServer();
    }

    AppiumManager(String filePath,String hubHost, Integer hubPort) {
        dirReader = new DirectoryReader(filePath);
        hub = new SeleniumHub(hubHost, hubPort);
        nodeConfigList = new ArrayList<>();

        pathList = dirReader.getPathList();
        iniNotConfig();
        iniAppiumServer();
    }

    private void iniNotConfig(){

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

        try {
            for(NodeConfig nodeConfig : nodeConfigList) {
                platform = nodeConfig.getSingelCapabiites().getPlatform();
                switch(platform) {
                    case "ANDROID":
                        AppiumAndroid android = new AppiumAndroid(nodeConfig);
                        appiumServersList.add(android);
                        break;
                    case "AMAZONOS":
                        AppiumAmazon amazon = new AppiumAmazon(nodeConfig);
                        appiumServersList.add(amazon);
                        break;
                    case "IOS":
                        AppiumIOS ios = new AppiumIOS(nodeConfig);
                        appiumServersList.add(ios);
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

    private void startHubWithNode(){
        try{
            hub.startHub();

            //TODO: Start the Appium Server in deferrent tread.
            for(AppiumServer appiumServer:appiumServersList){
                appiumServer.startServer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopHubWithNode(){
        try {
            /*for(AppiumServer appiumServer:appiumServersList){
                appiumServer.stopServer();
            }*/
            hub.stopHub();
        } catch (Exception e) {
            e.printStackTrace();
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

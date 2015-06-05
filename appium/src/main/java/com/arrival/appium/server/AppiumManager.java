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
 */
public class AppiumManager {

    private DirectoryReader dirReader;
    private ArrayList<Path> pathList;
    private ArrayList<NodeConfig> nodeConfigList;
    private SeleniumHub hub;

    private ArrayList<AppiumServer> appiumServers;


    public static void main(String[] args) throws IOException {

        AppiumManager readJson = new AppiumManager();

        System.out.printf(readJson.toString());

    }

    AppiumManager() {
        dirReader = new DirectoryReader();
        hub = new SeleniumHub();
        nodeConfigList = new ArrayList<NodeConfig>();

        pathList = dirReader.getPathList();
        iniNotConfig();
    }


    AppiumManager(String filePath,String hubHost, Integer hubPort) {
        dirReader = new DirectoryReader(filePath);
        hub = new SeleniumHub(hubHost, hubPort);
        nodeConfigList = new ArrayList<NodeConfig>();

        pathList = dirReader.getPathList();
        iniNotConfig();
    }

    private void iniNotConfig(){

        try {
            ArrayList<String> jsonConfigDataList = new ArrayList<>();
            for (Path path: pathList) {
                    jsonConfigDataList.add(new String(Files.readAllBytes(path)));
            }

            // Get Gson object and init NodeConfig Object
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            for(String jsonConfigDate:jsonConfigDataList ){
                NodeConfig node = gson.fromJson(jsonConfigDate, NodeConfig.class);

                nodeConfigList.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startHubWithNode(){

    }

    private void stopHubWithNode(){

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
//http://www.journaldev.com/2321/google-gson-api-for-json-processing-example-tutorial

}

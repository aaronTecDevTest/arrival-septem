package com.arrival.json;

import com.arrival.json.model.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


/**
 * Created by tecdesdev on 02/06/15.
 */
public class ReadJSONConfigFile {

    private DirectoryReader dirReader;
    private ArrayList<NodeConfig> configList;


    public static void main(String[] args) throws IOException {

        ReadJSONConfigFile readJson = new ReadJSONConfigFile();


        // Get Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // read JSON file data as String
        Path path = readJson.getDirReader().getPathList().get(1);
        //String fileData = new String(Files.readAllBytes(Paths.get(path.toString())));
        String fileData = new String(Files.readAllBytes(path));


        // parse json string to object
        NodeConfig node = gson.fromJson(fileData, NodeConfig.class);


        System.out.println(node.getConfiguration().getCleanUpCycle());
        System.out.println(node.getConfiguration().getCleanUpCycle());

        System.out.println(node.getSingelCapabiites().getBrowserName());

        System.out.println(node.getSingelCapabiites().getBrowserName());
        // print object data
        System.out.println("\n\nEmployee Object\n\n" + node);

    }

    ReadJSONConfigFile() {
        dirReader = new DirectoryReader();

    }

    public DirectoryReader getDirReader() {
        return dirReader;
    }

    public void setDirReader(DirectoryReader dirReader) {
        this.dirReader = dirReader;
    }

    public ArrayList<NodeConfig> getConfigList() {
        return configList;
    }

    public void setConfigList(ArrayList<NodeConfig> configList) {
        this.configList = configList;
    }
//http://www.journaldev.com/2321/google-gson-api-for-json-processing-example-tutorial

}

package com.arrival.appium.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by tecdesdev on 02/06/15.
 */
public class DirectoryReader {

    public static final String jsonConfigDirectory = "../arrival-septem/json/src/main/resources";
    public ArrayList<Path> jsonConfigFiles = null;


    public DirectoryReader() {
        jsonConfigFiles = new ArrayList<>();
        jsonConfigFiles = readDirectory();
    }


    public DirectoryReader(String filePath) {
        jsonConfigFiles = new ArrayList<>();
        jsonConfigFiles = readDirectory(filePath);
    }

    /**
     * Main for testing/run Class: DirectoryReader
     *
     * @param args
     */
    public static void main(String[] args) {
        DirectoryReader jsonReader = new DirectoryReader("../arrival-septem/appium/src/main/resources");
        System.out.println(jsonReader.getJsonConfigFiles());
    }

    public static String getJsonConfigDirectory() {
        return jsonConfigDirectory;
    }

    public ArrayList<Path> readDirectory() {
        final ArrayList<Path> jsonConfigTemp = new ArrayList<>();

        try {
            Files.walk(Paths.get(jsonConfigDirectory)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    jsonConfigTemp.add(filePath);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getOnlyJsonPath(jsonConfigTemp);
    }

    public ArrayList<Path> readDirectory(String jsonConfigDirectory) {
        final ArrayList<Path> jsonConfigTemp = new ArrayList<>();

        try {
            Files.walk(Paths.get(jsonConfigDirectory)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    jsonConfigTemp.add(filePath);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getOnlyJsonPath(jsonConfigTemp);
    }

    public ArrayList<Path> getJsonConfigFiles() {
        return jsonConfigFiles;
    }

    private void setJsonConfigFiles(ArrayList<Path> jsonConfigFiles) {
        this.jsonConfigFiles = jsonConfigFiles;
    }

    private ArrayList<Path> getOnlyJsonPath(ArrayList<Path> allPath) {
        ArrayList<Path> temp = new ArrayList<>();

        for (Path path : allPath) {
            boolean isJson = path.getFileName().toString().contains("json");
            if (isJson) {
                temp.add(path);
            }
        }
        return temp;
    }
}

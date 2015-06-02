package com.arrival.json;

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
    public ArrayList<Path> pathList = null;


    public DirectoryReader() {
        pathList = new ArrayList<>();
        pathList = readDirectory();
    }


    public DirectoryReader(String filePath) {
        pathList = new ArrayList<>();
        pathList = readDirectory(filePath);
    }

    public static String getJsonConfigDirectory() {
        return jsonConfigDirectory;
    }

    private ArrayList<Path> readDirectory() {
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

    private ArrayList<Path> readDirectory(String jsonConfigDirectory) {
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

    public ArrayList<Path> getPathList() {
        return pathList;
    }

   /* public void setJsonConfigFiles(ArrayList<Path> pathList) {
        this.pathList = pathList;
    }*/
}

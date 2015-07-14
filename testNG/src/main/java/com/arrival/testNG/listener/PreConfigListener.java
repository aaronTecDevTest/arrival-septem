package com.arrival.testNG.listener;

import org.testng.IExecutionListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tecdesdev on 07/07/15.
 */
public class PreConfigListener implements IExecutionListener {
    private long startTime;
    private List<XmlClass> classes;


    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setClasses(List<XmlClass> classes) {
        this.classes = classes;
    }

    @Override
    public void onExecutionStart() {
        startTime = System.currentTimeMillis();
        System.out.println("TestNG is going to start");
        int size = classes.size();
        for(XmlClass xmlClass: classes) {
            System.out.println(xmlClass.getClass().getCanonicalName() + "hier muss was");
        }
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("TestNG has finished, took around " + (System.currentTimeMillis() - startTime) + "ms");
    }
}

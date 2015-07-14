package com.arrival.testNG;

/**
 * Created by a.kutekidila on 13.05.2015.
 */

import com.arrival.testNG.listener.EmailListener;
import com.arrival.testNG.listener.PreConfigListener;
import com.arrival.testNG.test.SimpleTest1;
import com.arrival.testNG.test.SimpleTest2;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class RunTestNGClasses {
    TestListenerAdapter tla;
    EmailListener eml;
    PreConfigListener pcl;
    TestNG testng;


    RunTestNGClasses() {
        tla = new TestListenerAdapter();
        eml = new EmailListener();
        pcl = new PreConfigListener();

        testng = new TestNG();
    }

    public static void main(String[] args) {
        RunTestNGClasses runTest = new RunTestNGClasses();
        runTest.run();
    }

    public void run() {
        testng.setTestClasses(new Class[]{SimpleTest1.class, SimpleTest2.class});
        testng.addListener(tla);
        testng.addListener(eml);
        testng.addListener(pcl);
        testng.run();
    }
}

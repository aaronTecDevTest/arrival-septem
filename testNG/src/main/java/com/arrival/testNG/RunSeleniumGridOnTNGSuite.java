package com.arrival.testNG;

import com.arrival.selenium.SeleniumMultipleGrid;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by a.kutekidila on 13.05.2015.
 **/

public class RunSeleniumGridOnTNGSuite {

    XmlSuite suite = new XmlSuite();
    List<XmlSuite> suites = new ArrayList<>();


    TestNG tng = new TestNG();
    List<XmlClass> classes = new ArrayList<>();

    XmlTest testXML = new XmlTest(suite);


    public RunSeleniumGridOnTNGSuite() {
        SeleniumMultipleGrid multipleGrid = new SeleniumMultipleGrid();
        multipleGrid.run();

        tng.setOutputDirectory(getNewPathDirectory());
        tng.setDefaultSuiteName("RegressionsTest");
        testXML.setName("RegressionsTest");
    }

    public static void main(String[] args) {
        RunSeleniumGridOnTNGSuite runTest = new RunSeleniumGridOnTNGSuite();
        runTest.runVirtualSuit();
    }

    public void runVirtualSuit() {
        createVirtualSuite();
        suites.add(suite);
        tng.setXmlSuites(suites);
        tng.run();
    }

    private void createVirtualSuite() {
        suite.setName("TmpSuite");
        suite.setParallel("methods");
        suite.setThreadCount(5);
        testXML.setName("TmpTest");

        classes.add(new XmlClass("com.arrival.selenium.SeleniumRunTestOnMultiGrid"));
        testXML.setXmlClasses(classes);
    }

    /**
     * @return Path as a String
     */
    private String getNewPathDirectory() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Calendar cal = Calendar.getInstance();
        String tempPath = "../arrival-septem/testNG/src/main/resources/testng/result/";
        // String outPutDirectory = tempPath;
        String outPutDirectory = tempPath + dateFormat.format(cal.getTime());

        createNewDirectory(outPutDirectory);
        return outPutDirectory;
    }

    private void createNewDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        boolean success = dir.mkdir();

        if (!success)
            System.out.println("Directory creation failed");
        else
            System.out.println("Directory creation success");
    }

    private String getPath() {
        String path;

        path = this.getClass().getPackage().getName();

        System.out.println(path);
        return path;
    }
}
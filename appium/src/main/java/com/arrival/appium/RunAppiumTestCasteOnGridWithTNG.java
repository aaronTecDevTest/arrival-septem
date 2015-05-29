package com.arrival.appium;

/**
 * Created by tecdesdev on 29/05/15.
 */

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


public class RunAppiumTestCasteOnGridWithTNG {

	XmlSuite suite = new XmlSuite();
	List<XmlSuite> suites = new ArrayList<>();

	TestNG tng = new TestNG();
	List<XmlClass> classes = new ArrayList<>();

	XmlTest testXML = new XmlTest(suite);


	public RunAppiumTestCasteOnGridWithTNG() {
		tng.setOutputDirectory(getNewPathDirectory());
		tng.setDefaultSuiteName("RegressionsTest");
		testXML.setName("RegressionsTest");
	}

	public static void main(String[] args) {
		RunAppiumTestCasteOnGridWithTNG runTest = new RunAppiumTestCasteOnGridWithTNG();
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
		suite.setParallel("classes");
		//suite.setParallel("methods");
		//suite.setParallel("tests");
		suite.setThreadCount(5);
		testXML.setName("TmpTest");

		classes.add(new XmlClass("com.arrival.appium.android.WetterInfo"));
		testXML.setXmlClasses(classes);

		// System.out.println(testXML.getSuite().toXml());
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

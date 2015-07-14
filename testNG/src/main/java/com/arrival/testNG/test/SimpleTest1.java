package com.arrival.testNG.test;

/**
 * Created by a.kutekidila on 04.05.2015.
 */


import com.arrival.testNG.generic.AppiumConfig;
import com.arrival.testNG.generic.ArrivalMobil;
import org.testng.Assert;
import org.testng.ITestClass;
import org.testng.ITestNGMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlTest;

public class SimpleTest1 extends ArrivalMobil implements ITestClass  {
    private SimpleTest1 test;

    @DataProvider(name = "driver")
    public Object[][] createDriver() {

        return null;
    }

    //@BeforeClass
    @BeforeTest
    public void setUp() {
        test = new SimpleTest1();
    }

    @Test(groups = {"fast"})
    public void aFastTest() {
        System.out.println("Fast test 2");
        Assert.assertFalse(true);
    }

    @Test(groups = {"slow"})
    public void aSlowTest() {
        System.out.println("Slow test 3");
        Assert.assertFalse(false);
    }

    @Override
    public void setAppiumConfig(AppiumConfig appiumConfig) {
        appiumConfi = appiumConfig;
    }

    @Override
    public AppiumConfig getAppiumConfig() {
        return appiumConfi;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public XmlTest getXmlTest() {
        return null;
    }

    @Override
    public XmlClass getXmlClass() {
        return null;
    }

    @Override
    public String getTestName() {
        return null;
    }

    @Override
    public Class getRealClass() {
        return null;
    }

    @Override
    public Object[] getInstances(boolean b) {
        return new Object[0];
    }

    @Override
    public long[] getInstanceHashCodes() {
        return new long[0];
    }

    @Override
    public void addInstance(Object o) {

    }

    @Override
    public int getInstanceCount() {
        return 0;
    }

    @Override
    public ITestNGMethod[] getTestMethods() {
        return new ITestNGMethod[0];
    }

    @Override
    public ITestNGMethod[] getBeforeTestMethods() {
        return new ITestNGMethod[0];
    }

    @Override
    public ITestNGMethod[] getAfterTestMethods() {
        return new ITestNGMethod[0];
    }

    @Override
    public ITestNGMethod[] getBeforeClassMethods() {
        return new ITestNGMethod[0];
    }

    @Override
    public ITestNGMethod[] getAfterClassMethods() {
        return new ITestNGMethod[0];
    }

    @Override
    public ITestNGMethod[] getBeforeSuiteMethods() {
        return new ITestNGMethod[0];
    }

    @Override
    public ITestNGMethod[] getAfterSuiteMethods() {
        return new ITestNGMethod[0];
    }

    @Override
    public ITestNGMethod[] getBeforeTestConfigurationMethods() {
        return new ITestNGMethod[0];
    }

    @Override
    public ITestNGMethod[] getAfterTestConfigurationMethods() {
        return new ITestNGMethod[0];
    }

    @Override
    public ITestNGMethod[] getBeforeGroupsMethods() {
        return new ITestNGMethod[0];
    }

    @Override
    public ITestNGMethod[] getAfterGroupsMethods() {
        return new ITestNGMethod[0];
    }
}

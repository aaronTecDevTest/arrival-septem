package com.arrival.testNG.test;

/**
 * Created by a.kutekidila on 04.05.2015.
 */


import org.testng.Assert;
import org.testng.annotations.*;

public class SimpleTest1 {
    private SimpleTest1 test;

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
}

package com.arrival.testNG.test;

/**
 * Created by a.kutekidila on 04.05.2015.
 */

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SimpleTest2 {
@BeforeClass
public void setUp() {
	// code that will be invoked when this test is instantiated
}

@Test(groups = {"fast"})
public void aFastTest() {
	System.out.println("Fast test 20");
}

@Test(groups = {"slow"})
public void aSlowTest() {
	System.out.println("Slow test 30");
}
}

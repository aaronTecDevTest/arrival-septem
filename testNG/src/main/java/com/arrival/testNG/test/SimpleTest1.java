package com.arrival.testNG.test;

/**
 * Created by a.kutekidila on 04.05.2015.
 */

import com.arrival.testNG.generic.ArrivalMobil;
import org.testng.annotations.Test;

public class SimpleTest1 extends ArrivalMobil{

    @Test(dataProvider = "driver", groups = {"fast"})
    public void aFastTest(String serverName, Integer id) {
        System.out.println("Fast test 202 " + serverName + " " + id);
    }

    @Test(dataProvider = "driver", groups = {"slow"})
    public void aSlowTest(String serverName, Integer id) {
        System.out.println("Slow test 303 " + serverName + " " + id);
    }
}

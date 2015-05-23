package com.arrival.selenium;

/**
 * Created by Aaron on 22.05.2015.
 **/
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.grid.common.GridRole;
import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.common.SeleniumProtocol;
import org.openqa.grid.internal.utils.GridHubConfiguration;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumMultipleGrid {

    protected GridHubConfiguration gridHubConfig;
    protected Hub hub;
    protected String hubHost;
    protected Integer hubPort;

    SelfRegisteringRemote remoteWebDriverNode;
    SelfRegisteringRemote remoteRCNode;

    public SeleniumMultipleGrid() {

        gridHubConfig = new GridHubConfiguration();
        hubHost = "localhost";
        hubPort = 4444;
        System.setProperty("webdriver.chrome.driver", "D:/Dev/project/arrival-septem/selenium/src/main/resources/chromedriver.exe");
    }

    public static void main(String[] args) {
        SeleniumMultipleGrid  multipleGrid = new SeleniumMultipleGrid();
        multipleGrid.run();
        //multipleGrid.setUpHubAndNode();
    }

    public void run() {
        setUpHubAndNode();
    }

    public void setUpHubAndNode() {
        gridHubConfig.setHost(hubHost);
        gridHubConfig.setPort(hubPort);
        hub = new Hub(gridHubConfig);
        try {
            hub.start();
        } catch (Exception e) {
            System.out.println("Error starting hub.");
            e.printStackTrace();
        }
        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        chrome.setBrowserName("*googlechrome");
        chrome.setPlatform(Platform.WINDOWS);

        try {
            remoteRCNode = attachNodeToHub(chrome, GridRole.NODE, 5556, SeleniumProtocol.Selenium);
            remoteWebDriverNode = attachNodeToHub(DesiredCapabilities.firefox(), GridRole.NODE, 5555, SeleniumProtocol.WebDriver);
        } catch (Exception e) {
            System.out.println("Error attaching node.");
            e.printStackTrace();
        }
    }

    public void setUpHubAndNodeFromJSONConfig(String configFile) {
        GridHubConfiguration gridHubConfig = new GridHubConfiguration();
        gridHubConfig.loadFromJSON( "build/resources/test/WebDriver.json" );
        hub = new Hub( gridHubConfig );
        try {
            hub.start();
        } catch (Exception e) {
            System.out.println("Error starting hub.");
            e.printStackTrace();
        }
        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        chrome.setBrowserName("*googlechrome");

        try {
            remoteRCNode = attachNodeToHub( chrome, GridRole.NODE, 5555, SeleniumProtocol.Selenium );
            remoteWebDriverNode = attachNodeToHub( DesiredCapabilities.firefox(), GridRole.NODE, 5556, SeleniumProtocol.WebDriver );
        } catch (Exception e) {
            System.out.println("Error attaching node.");
            e.printStackTrace();
        }
    }

    private SelfRegisteringRemote attachNodeToHub( DesiredCapabilities capability, GridRole role,
                                                   int nodePort, SeleniumProtocol protocol) throws Exception {
        SelfRegisteringRemote node = null;
        RegistrationRequest registrationRequest = new RegistrationRequest();
        capability.setCapability("seleniumProtocol", protocol);
        registrationRequest.addDesiredCapability(capability);
        registrationRequest.setRole(role);
        registrationRequest.setConfiguration(fetchNodeConfiguration(role, nodePort, protocol));
        node = new SelfRegisteringRemote(registrationRequest);
        node.startRemoteServer();
        node.startRegistrationProcess();
        return node;
    }

    private Map<String, Object> fetchNodeConfiguration(GridRole role, Integer portToRun, SeleniumProtocol protocol)
            throws MalformedURLException {
        Map<String, Object> nodeConfiguration = new HashMap<String, Object>();
        URL remoteURL = new URL("http://" + hub.getHost() + ":" + portToRun);

        nodeConfiguration.put(RegistrationRequest.AUTO_REGISTER, true);
        nodeConfiguration.put(RegistrationRequest.HUB_HOST, hub.getHost());
        nodeConfiguration.put(RegistrationRequest.HUB_PORT, hub.getPort());
        nodeConfiguration.put(RegistrationRequest.PORT, portToRun);
        nodeConfiguration.put(RegistrationRequest.PROXY_CLASS, "org.openqa.grid.selenium.proxy.DefaultRemoteProxy");
        nodeConfiguration.put(RegistrationRequest.MAX_SESSION, 1);
        nodeConfiguration.put(RegistrationRequest.CLEAN_UP_CYCLE, 2000);
        nodeConfiguration.put(RegistrationRequest.REMOTE_HOST, remoteURL);
        nodeConfiguration.put(RegistrationRequest.MAX_INSTANCES, 1);
        return nodeConfiguration;
    }

    public void shutDownNodeAndHub() throws Exception {
        if (remoteWebDriverNode != null) {
            remoteWebDriverNode.stopRemoteServer();
            System.out.println("WebDriver Node shutdown");
        }
        if (remoteRCNode != null) {
            remoteRCNode.stopRemoteServer();
            System.out.println("RC Node shutdown");
        }
        if ( hub != null ) {
            hub.stop();
            System.out.println("Local hub shutdown");
        }
    }
}

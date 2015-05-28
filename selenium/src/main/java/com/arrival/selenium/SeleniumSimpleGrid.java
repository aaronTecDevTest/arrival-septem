package com.arrival.selenium;

import org.openqa.grid.common.GridRole;
import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.common.SeleniumProtocol;
import org.openqa.grid.internal.utils.GridHubConfiguration;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Aaron on 22.05.2015.
 **/

public class SeleniumSimpleGrid {


protected String osName;
private GridHubConfiguration gridHubConfig;
private Hub hub;
private String hubHost;
private Integer hubPort;
private Map<String, Object> nodeConfiguration;
private RegistrationRequest req;
private SelfRegisteringRemote remoteRCNode;

public SeleniumSimpleGrid() {
	gridHubConfig = new GridHubConfiguration();
	hubHost = "localhost";
	hubPort = 4444;
	osName = System.getProperty("os.name");

	if (osName.contains("Mac OS X")) {
		System.setProperty("webdriver.chrome.driver", "../arrival-septem/selenium/src/main/resources/chromedriver");
	} else {
		System.setProperty("webdriver.chrome.driver", "../arrival-septem/selenium/src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.internetexplora.driver", "../arrival-septem/selenium/src/main/resources/IEDriverServer.exe");
	}
}

public static void main(String[] args) {
	SeleniumSimpleGrid hubNode = new SeleniumSimpleGrid();
	hubNode.runHubNode();
	// hubNode.shutDownNodeAndHub();
}

public void setUpHub() throws Exception {
	gridHubConfig.setHost(hubHost);
	gridHubConfig.setPort(hubPort);
	hub = new Hub(gridHubConfig);
	hub.start();
}

public void startRequest() throws Exception {
	req = new RegistrationRequest();
	req.setRole(GridRole.NODE);

	DesiredCapabilities firefox = DesiredCapabilities.firefox();
	firefox.setBrowserName("*firefox");
	FirefoxProfile profile = new ProfilesIni().getProfile("Selenium");
	firefox.setCapability(FirefoxDriver.PROFILE, profile);
	firefox.setCapability("seleniumProtocol", SeleniumProtocol.Selenium);
	firefox.setCapability("platform", Platform.WINDOWS);
	req.addDesiredCapability(firefox);

	nodeConfiguration = setUpNode();
	req.setConfiguration(nodeConfiguration);

	remoteRCNode = new SelfRegisteringRemote(req);
	remoteRCNode.startRemoteServer();
	remoteRCNode.startRegistrationProcess();
}


public Map<String, Object> setUpNode() throws MalformedURLException {
	Map<String, Object> nodeConfiguration = new HashMap<String, Object>();
	URL remoteURL = new URL("http://" + hub.getHost() + ":" + 5555);
	String proxy_class = "org.openqa.grid.selenium.proxy.DefaultRemoteProxy";

	nodeConfiguration.put(RegistrationRequest.AUTO_REGISTER, true);
	nodeConfiguration.put(RegistrationRequest.HUB_HOST, hub.getHost());
	nodeConfiguration.put(RegistrationRequest.HUB_PORT, hub.getPort());
	nodeConfiguration.put(RegistrationRequest.PORT, 5555);
	nodeConfiguration.put(RegistrationRequest.PROXY_CLASS, proxy_class);
	nodeConfiguration.put(RegistrationRequest.MAX_SESSION, 1);
	nodeConfiguration.put(RegistrationRequest.CLEAN_UP_CYCLE, 2000);
	nodeConfiguration.put(RegistrationRequest.REMOTE_HOST, remoteURL);
	nodeConfiguration.put(RegistrationRequest.MAX_INSTANCES, 1);

	return nodeConfiguration;
}


public void runHubNode() {
	try {
		setUpHub();
		startRequest();
	} catch (Exception e) {
		e.printStackTrace();
	}
}


public void shutDownNodeAndHub() throws Exception {
	if (remoteRCNode != null) {
		remoteRCNode.stopRemoteServer();
		System.out.println("RC Node shutdown");
	}
	if (hub != null) {
		hub.stop();
		System.out.println("Local hub shutdown");
	}
}
}

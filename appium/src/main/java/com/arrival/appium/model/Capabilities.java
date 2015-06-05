package com.arrival.appium.model;

/**
 * Created by tecdesdev on 02/06/15.
 */
public class Capabilities {

    private String browserName;
    private String version;
    private Integer maxInstances;
    private String platform;
    private String deviceName;
    private String udid;

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getMaxInstances() {
        return maxInstances;
    }

    public void setMaxInstances(Integer maxInstances) {
        this.maxInstances = maxInstances;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    @Override
    public String toString() {
        return "Capabilities: {" +
                       ", browserName='" + browserName + '\'' +
                       ", version='" + version + '\'' +
                       ", maxInstances=" + maxInstances +
                       ", platform='" + platform + '\'' +
                       ", deviceName='" + deviceName + '\'' +
                       ", udid='" + udid + '\'' +
                       '}';
    }
}

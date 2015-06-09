package com.arrival.appium.model;

import com.google.gson.annotations.SerializedName;

import java.nio.file.Path;
import java.util.Arrays;

/**
 * Created by tecdesdev on 02/06/15.
 */
public class NodeConfig {

    private Path configPath;

    @SerializedName("capabilities")
    private Capabilities[] capabilities;
    private Configuration configuration;

    public Capabilities[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities[] capabilities) {
        this.capabilities = capabilities;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Capabilities getSingelCapabiites(){
        return capabilities[0];
    }
    public Path getConfigPath() {
        return configPath;
    }

    public void setConfigPath(Path configPath) {
        this.configPath = configPath;
    }

    @Override
    public String toString() {
        return "NodeConfig{" +
                       "capabilities=" + Arrays.toString(capabilities) +
                       ", configuration=" + configuration +
                       '}';
    }
}

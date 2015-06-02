package com.arrival.json.model;

import java.util.List;
import java.util.Map;


import com.google.gson.annotations.SerializedName;
/**
 * Created by tecdesdev on 02/06/15.
 */
public class NodeConfig {

    @SerializedName("")
    private Map<String,String> capabilities;
    private Map<String, String> configuration;

    public Map<String,String> getCities() {
        return capabilities;
    }

    public void setCities(Map<String,String> capabilities) {
        this.capabilities = capabilities;
    }

    public Map<String, String> getProperties() {
        return configuration;
    }

    public void setProperties(Map<String, String> properties) {
        this.configuration = properties;
    }

    @Override
    public String toString() {
        return "NodeConfig:{" +
                       "cities=" + capabilities.toString() +
                       ", properties=" + configuration.toString() +
                       '}';
    }
}

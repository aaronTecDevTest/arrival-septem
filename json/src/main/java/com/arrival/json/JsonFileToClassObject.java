package com.arrival.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by tecdesdev on 01/06/15.
 */
public class JsonFileToClassObject {

public Map<String, String> jsonAsMap = new HashMap<>();

public String browserName;
public String version;
public String platform;
public String deviceNmae;
public String uid;


public static void main(String[] args) {
	String path = "../arrival-septem/json/src/main/resources/AppiumNodeGFlex.json";
	JsonFileToClassObject json = new JsonFileToClassObject();
	json.readJsonFileToMap(path);
}

public void readJsonFileToMap(String pathToJson) {
	try {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(pathToJson));
		JSONObject jsonObject = (JSONObject) obj;


		//browserName = (String)jsonObject.get("browserName");
		//browserName = (String)jsonObject.get("proxy");
		//System.out.println("BrowserName = [" + browserName + "]");

		JSONArray capabilitiesList = (JSONArray) jsonObject.get("capabilities");
		JSONObject configurationList = (JSONObject) jsonObject.get("configuration");

		browserName = (String) capabilitiesList.get(0);

		System.out.println( browserName);
		System.out.println( configurationList.get("version"));
		System.out.println( configurationList.get("platform"));
		System.out.println( configurationList.get("deviceNmae"));
		System.out.println( configurationList.get("udid"));

		/*
		Iterator<String> i = (Iterator <String>) capabilitiesList.iterator();

		while (i.hasNext()) {
			System.out.println(i.next().toString());
		}*/
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
}
}

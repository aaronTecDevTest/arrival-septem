package com.arrival.json;

/**
 * Created by tecdesdev on 01/06/15.
 */

		import java.io.FileNotFoundException;
		import java.io.FileReader;
		import java.io.IOException;

		import com.google.gson.stream.JsonReader;

public class GsonStreamAPIExample {
private static final String jsonFilePath = "../arrival-septem/json/src/main/resources/JSONExample.json";

public static void main(String[] args) {

	try {

		FileReader fileReader = new FileReader(jsonFilePath);

		JsonReader jsonReader = new JsonReader(fileReader);

		jsonReader.beginObject();

		while (jsonReader.hasNext()) {

			String name = jsonReader.nextName();

			if (name.equals("domain")) {

				System.out.println("domain: "+jsonReader.nextString());

			} else if (name.equals("members")) {

				System.out.println("members: "+jsonReader.nextInt());

			} else if (name.equals("names")) {

				System.out.println("names: ");

				jsonReader.beginArray();

				while (jsonReader.hasNext()) {
					System.out.println(" "+jsonReader.nextString());
				}

				jsonReader.endArray();

			} else {
				// use this when you are not sure about all the contents in th JSON file
				jsonReader.skipValue();
			}
		}

		jsonReader.endObject();
		jsonReader.close();

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}

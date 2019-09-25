package com.com.app.fboauthapp.data;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

public class DataContainer {

	private static volatile Map<String, JsonNode> resourceMap;
	private static DataContainer resourceDataHolder;

	private DataContainer() {

		resourceMap = new HashMap<String, JsonNode>();
	}

	public static DataContainer getInstance() {

		if (resourceDataHolder == null) {

			synchronized (DataContainer.class) {
				if (resourceDataHolder == null) {
					resourceDataHolder = new DataContainer();
				}
			}
		}

		return resourceDataHolder;
	}

	public void addResource(String key, JsonNode value) {
		resourceMap.put(key, value);
	}

	public JsonNode getResource(String key) {
		return resourceMap.get(key);
	}
	
	public Map<String, JsonNode> getAllResources(){
		return resourceMap;
	}

}

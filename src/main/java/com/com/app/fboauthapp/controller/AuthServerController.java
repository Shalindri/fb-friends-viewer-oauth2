package com.com.app.fboauthapp.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.com.app.fboauthapp.data.DataContainer;
import com.com.app.fboauthapp.util.AppConstant;
import com.com.app.fboauthapp.util.Log;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AuthServerController {

	@RequestMapping("/friendlistviewapp/callback")
	public ResponseEntity<?> getResource(@RequestParam("code") String code)
			throws JSONException, IOException {
		Log fileIO = new Log();
		String authCode = code;
		if (authCode != null && authCode.length() > 0) {
			
			// POST request for auth code grant
			HttpHeaders http_Headers = new HttpHeaders();
			//Setting up headers and other parameters
			http_Headers.set("Content-Type", "text/plain");
			String para1 = ("grant_type="+ URLEncoder.encode(AppConstant.GRANT_TYPE, StandardCharsets.UTF_8.name()));
			String para2 = ("redirect_uri="+ URLEncoder.encode(AppConstant.REDIRECT_URI, StandardCharsets.UTF_8.name()));
			String para3 = ("code="+ URLEncoder.encode(authCode, StandardCharsets.UTF_8.name()));
			String para4 = ("client_id="+ URLEncoder.encode(AppConstant.CLIENT_ID, StandardCharsets.UTF_8.name()));

			String auth_code=(para1+"&"+para2+"&"+para3+"&"+para4);
			
			//concat Auth header
			//credentials are encoded
			String cCredentials = AppConstant.CLIENT_ID + ":" + AppConstant.CLIENT_SECRET;
			String encodedCC = new String(Base64.encodeBase64(cCredentials.getBytes()));
			http_Headers.set("Authorization", "Basic " + encodedCC);
			
			//access token request generation
			HttpEntity<String> http_Entity = new HttpEntity<String>(auth_code, http_Headers);
			RestTemplate rest_Template = new RestTemplate();
			String responce = rest_Template.postForObject(AppConstant.TOKEN_ENDPOINT, http_Entity, String.class);
			
			
			//managing the access token response
			// access token response
			JSONObject responceO =  new JSONObject(responce);
			// idling the access token
			String accessToken = null;

			try {				
				accessToken = responceO.get("access_token").toString();
				fileIO.writeToFile(accessToken);

			} catch (JsonParseException e) {
				System.out.println("Error_while_parsing_the_response : " + e.getMessage());
			}

			// Requesting feed data along with the access token and profile 
	

			RestTemplate data_rest_template = new RestTemplate();
			HttpHeaders tokenHeader = new HttpHeaders();
			tokenHeader.add("Authorization", "Bearer " + accessToken);
			HttpEntity<?> dataHttpEntiy = new HttpEntity<>(tokenHeader);
 			ResponseEntity<String> dataResponce = data_rest_template.exchange(AppConstant.FEED_REQUEST_URI, HttpMethod.GET,dataHttpEntiy,String.class);
			
 			ObjectMapper obj_mapper = new ObjectMapper();
 			JsonNode root = obj_mapper.readTree(dataResponce.getBody());
 			JsonNode data = root.path("data");
// 			System.out.println(data);
 			//Requesting user's profileId
 			ResponseEntity<String> id_responce = data_rest_template.exchange(AppConstant.ID_FEILD_ENDPOINT, HttpMethod.GET,dataHttpEntiy,String.class);
 			JsonNode id_Root = obj_mapper.readTree(id_responce.getBody());
 			JsonNode id_Data = id_Root.path("id");
// 			System.out.println(id_Data);	
 			
 			//extracting the users feed data from the response
			DataContainer.getInstance().addResource(String.valueOf("feed"), data);
			fileIO.writeToFile(data.asText());
			
			return new ResponseEntity<>(HttpStatus.OK);
		

		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	 
	@RequestMapping("/friendlistviewapp/feed")
	public ResponseEntity<?> getAllFriendsInFeeds(){
		HashMap<String, JsonNode> detailsNode = (HashMap<String, JsonNode>) DataContainer.getInstance().getAllResources();
		return new ResponseEntity<>(detailsNode.values(),HttpStatus.OK);
	
	}
	
	

}

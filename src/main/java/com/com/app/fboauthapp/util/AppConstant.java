package com.com.app.fboauthapp.util;

public class AppConstant {
	//Graph endpoint
	public static final String GRAPH_ENDPOINT ="https://graph.facebook.com/v3.1/";
	//access token endpoint from graph.facebook API
	public static final String TOKEN_ENDPOINT = "https://graph.facebook.com/oauth/access_token";
	// endpoint to retrieve fields in user profile from graph.facebook API
	public static final String ID_FEILD_ENDPOINT ="https://graph.facebook.com/v2.8/me?fields=id";
	//oAuth grant type
	public static final String GRANT_TYPE = "authorization_code";
	//tagged
	public static final String TAGGED ="/tagged";
	//Redirect URL specified in the fb_deveoper account
	public static final String REDIRECT_URI = "http://localhost:9000/oauthlogin";
	//App Secret in the fb_deveoper account
	public static final String CLIENT_SECRET = "4f26e13fe4f8cd4ee84a16c9224a4e0d";
	// APP id in the fb_deveoper account
	public static final String CLIENT_ID = "1681012872028869";
	//friends who have tagged in the fb posts 
	public static final String TAGGABLE_FRIEMDS = "/taggable_friends";
	//URL to request 20 post form the users feed -graph facebook API
	public static final String FEED_REQUEST_URI = "https://graph.facebook.com/v2.10/me/feed?limit=20";


}

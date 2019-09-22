package com.texcolorado.httpclient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class HttpClient {

	public static void main(String[] args) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		
		String hostname = System.getenv("APP_HOSTNAME");
		
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://"+ hostname + "/api/todos");
	    
	    String JSON_STRING = "{\"text\": \"New Task added: " + dtf.format(now) + "\"}";
	    
	    HttpEntity stringEntity = new StringEntity(JSON_STRING,ContentType.APPLICATION_JSON);
	    httpPost.setEntity(stringEntity);
	    
	    try {
			CloseableHttpResponse response = httpclient.execute(httpPost);
			System.out.println("HTTP Response Status: " + response.getStatusLine().getStatusCode());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Protocol Exception");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IOException");
		}
	    
	    System.out.println("Done");
	}

}

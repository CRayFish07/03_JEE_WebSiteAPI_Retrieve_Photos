package com.joe.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class CheckingWebsite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=01a7b4e92ef717d79743811070c49f56&extras=original_format&per_page=30&format=json&tags=macbook";
		//String str = "http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=f3b43f6fa55b9b17935d65c7170b9a60&extras=original_format&per_page=30&format=json&tags=flower";
		
		try{
			URLConnection conn = new URL(str).openConnection();
			
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader  br = new BufferedReader(isr);
			
			StringBuilder buffer = new StringBuilder();
			String line;

		      while ((line = br.readLine()) != null)
		      {
		        buffer.append(line);
		      }
		      br.close();
		      isr.close();
		      is.close();
		      System.out.println(buffer.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}

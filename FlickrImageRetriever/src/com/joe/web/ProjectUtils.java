package com.joe.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class ProjectUtils {
	/**
	 * String queryString = "http://api.flickr.com/services/rest/?method=flickr.photos.search
	 * 		&api_key=01a7b4e92ef717d79743811070c49f56
	 * 		&extras=original_format
	 * 		&per_page=30&format=json
	 * 		&tags=macbook";
	 * 
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public static String getStringContentFromUrl(String queryString)
			throws Exception {
		
		//I run the following codes in hp office, if these 2 proxy related properties are not set.
		//then an exception will be thrown: connection refused...
		System.setProperty("http.proxyHost", "proxy.jpn.hp.com");
		System.setProperty("http.proxyPort", "8080");

		URL url = new URL(queryString);

		//if the proxy info are not set into System firstly, we can also generate a proxy instance for it.
		//Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("proxy.jpn.hp.com" , 8080));
		//URLConnection conn = url.openConnection(proxy);
		
		URLConnection conn = url.openConnection();

		InputStream is = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		StringBuilder buffer = new StringBuilder();
		String line;

		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}
		br.close();
		isr.close();
		is.close();

		System.out.println("returned:" + buffer.toString());
		return buffer.toString();
	}

	public static void write2LocalFromInternet(String imageUrl, File file)
			throws Exception {
		InputStream is = null;
		OutputStream os = null;

		try {
			URL url = new URL(imageUrl);
			is = url.openStream();

			os = new FileOutputStream(file);

			int length = -1;

			byte[] buffer = new byte[7092];
			while (-1 != (length = is.read(buffer, 0, 7092))) {
				os.write(buffer, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}

	}
}

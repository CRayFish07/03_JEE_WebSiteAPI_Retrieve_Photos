package com.joe.web.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class ReadContentsFromWeb {
	public static void main(String[] args) {
		ReadContentsFromWeb rcfw = new ReadContentsFromWeb();
		String pageUrl = "http://www.baidu.com";
		String filePath = "C://sssdb.txt";
		try {
			rcfw.createStaticPage(pageUrl, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//read contents from url
	public String readerPageByUrl(String pageUrl) {
		URL url;
		String pageString = "";
		try {
			//use proxy when in hp office
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("proxy.jpn.hp.com" , 8080));
			url = new URL(pageUrl);
			//when in hp office, should add the proxy param in the openConnection() function
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection(proxy);
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
				System.out.println(sb);
			}
			pageString = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageString;
	}

	//write contents into file
	public void writeStringToFile(String filePath, String fileStr)
			throws IOException {
		File file = new File(filePath);
		FileOutputStream fileout = new FileOutputStream(file);
		fileout.write(fileStr.getBytes());
		fileout.close();
	}

	//write contents into file
	public void createStaticPage(String pageUrl, String filePath) {
		String pageStr = readerPageByUrl(pageUrl);
		try {
			writeStringToFile(filePath, pageStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
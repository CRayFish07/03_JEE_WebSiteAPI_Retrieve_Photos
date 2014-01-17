package com.joe.web.util;

import java.io.File;

public class SaveImageThread extends Thread {
	private File directory;
	private String[] imageUrls;
	
	public SaveImageThread(File dir, String[] imageUrls) {
		this.directory = dir;
		this.imageUrls = imageUrls;
	}
	
	
	public void run() {
		try {
			for (String imageUrl:imageUrls) {
				int position = imageUrl.lastIndexOf("/");
				String imageName = imageUrl.substring(position +1);
				File file = new File(directory, imageName);
				NetworkUtil.write2LocalFromInternet(imageUrl, file);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

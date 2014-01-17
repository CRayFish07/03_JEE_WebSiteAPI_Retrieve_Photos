package com.joe.web;

import java.io.File;

import com.joe.web.ProjectUtils;

public class SaveImageThread extends Thread {
	private File directory;
	private String[] imageUrls;
	
	public SaveImageThread(File dir, String[] imageUrls) {
		this.directory = dir;
		this.imageUrls = imageUrls;
	}
		
	public void run() {
		long startTime = System.currentTimeMillis();
		try {
			for (String imageUrl:imageUrls) {
				int position = imageUrl.lastIndexOf("/");
				String imageName = imageUrl.substring(position +1);
				File file = new File(directory, imageName);
				ProjectUtils.write2LocalFromInternet(imageUrl, file);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(this.getName() + ":" + (endTime - startTime));
	}
}

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.joe.web.ProjectUtils;


public class DirectlyFromFlickr {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String keyword = "thinkpad";
		String urlString = "http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=01a7b4e92ef717d79743811070c49f56&extras=original_format&per_page=30&format=json";
		//&tags=macbook";";
		urlString = urlString + "&tags=" + keyword;
		
		/**
		 * returned:jsonFlickrApi({"photos":{"page":1, "pages":4120, "perpage":30, "total":"123576", 
		 * 		"photo":[{"id":"8199210098", "owner":"42356336@N04", "secret":"39be41ab3e", "server":"8197", "farm":9, "title":"Laptop Stand", "ispublic":1, "isfriend":0, "isfamily":0, "originalsecret":"988ba638a6", "originalformat":"jpg"}, 
		 * 				 {"id":"8199209986", "owner":"42356336@N04", "secret":"586f81845e", "server":"8210", "farm":9, "title":"Laptop Stand", "ispublic":1, "isfriend":0, "isfamily":0, "originalsecret":"06b7a07a29", "originalformat":"jpg"}, 
		 * 				 {"id":"8199210208", "owner":"42356336@N04", "secret":"a09ae49b08", "server":"8059", "farm":9, "title":"Laptop Stand", "ispublic":1, "isfriend":0, "isfamily":0, "originalsecret":"f3f6d8f0ef", "originalformat":"jpg"}, 
		 * 				 {"id":"8197516791", "owner":"23531080@N00", "secret":"af138c5851", "server":"8067", "farm":9, "title":"57\/365 my world", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8197019241", "owner":"57913824@N00", "secret":"9a8f6482d9", "server":"8347", "farm":9, "title":"Day 317 \/ Saturday November 10th, 2012", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8196949661", "owner":"36118493@N03", "secret":"e60ab46fc8", "server":"8488", "farm":9, "title":"20121118-DSC_5716.jpg", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8197520942", "owner":"23097875@N00", "secret":"d4138ded9a", "server":"8208", "farm":9, "title":"", "ispublic":1, "isfriend":0, "isfamily":0, "originalsecret":"4c5eaee46f", "originalformat":"jpg"}, 
		 * 				 {"id":"8197389476", "owner":"57296780@N05", "secret":"24fa53190c", "server":"8490", "farm":9, "title":"MacBook Pro", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8195937053", "owner":"59561606@N00", "secret":"5fc851427c", "server":"8486", "farm":9, "title":"Qu\u00e9 nerd", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8195378527", "owner":"51585572@N05", "secret":"9cc721c7ea", "server":"8063", "farm":9, "title":"Zetelhangen met schermpjes", "ispublic":1, "isfriend":0, "isfamily":0, "originalsecret":"2cf03d4e53", "originalformat":"jpg"}, 
		 * 				 {"id":"8194344059", "owner":"88813798@N05", "secret":"0b2e14c8b8", "server":"8484", "farm":9, "title":"This is a vintage baby.", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8195336846", "owner":"88813798@N05", "secret":"8362e54f5a", "server":"8344", "farm":9, "title":"Melina minha cat linda.", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8195318416", "owner":"88813798@N05", "secret":"55807f629b", "server":"8338", "farm":9, "title":"Peace please.", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8194207585", "owner":"88813798@N05", "secret":"d27cfe444c", "server":"8207", "farm":9, "title":"Olho do beb\u00ea formou um bokeh \u2665.", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8194192237", "owner":"88813798@N05", "secret":"6431dd469d", "server":"8477", "farm":9, "title":"i'm so sedution.", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8195282104", "owner":"88813798@N05", "secret":"e33e35a06d", "server":"8206", "farm":9, "title":"I don't much like black with white.", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8195279924", "owner":"88813798@N05", "secret":"27c1455209", "server":"8210", "farm":9, "title":"Flickrwhores look at this picture.", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8194149437", "owner":"12596561@N08", "secret":"0ff42bc9b5", "server":"8070", "farm":9, "title":"Macbook Air 13\" 2012", "ispublic":1, "isfriend":0, "isfamily":0, "originalsecret":"4511486013", "originalformat":"jpg"}, 
		 * 				 {"id":"8194816872", "owner":"50702869@N04", "secret":"a737ec964c", "server":"8203", "farm":9, "title":"When on the Seventh", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8193551415", "owner":"54199003@N00", "secret":"7cda16d81d", "server":"8478", "farm":9, "title":"Bella", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8192654815", "owner":"76826947@N08", "secret":"09a52ea2f4", "server":"8058", "farm":9, "title":"$", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8193656178", "owner":"76826947@N08", "secret":"cc21391f40", "server":"8070", "farm":9, "title":"Feel the Beat", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8193552878", "owner":"23617386@N05", "secret":"05d88f28ef", "server":"8348", "farm":9, "title":"", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8192377079", "owner":"46729310@N04", "secret":"193eab3955", "server":"8339", "farm":9, "title":"Meeting", "ispublic":1, "isfriend":0, "isfamily":0, "originalsecret":"2bc04d41a5", "originalformat":"jpg"}, 
		 * 				 {"id":"8193218988", "owner":"90098651@N05", "secret":"d5151363fd", "server":"8201", "farm":9, "title":"November 2012", "ispublic":1, "isfriend":0, "isfamily":0, "originalsecret":"61a509d2b0", "originalformat":"jpg"}, 
		 * 				 {"id":"8191973619", "owner":"89622796@N07", "secret":"5100a34da6", "server":"8483", "farm":9, "title":"GGMM Apple 11 and 13 inch MacBook Air Leather Laptop Bag", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8191910351", "owner":"90098651@N05", "secret":"9fcc85db92", "server":"8198", "farm":9, "title":"November 2012", "ispublic":1, "isfriend":0, "isfamily":0, "originalsecret":"81b560e983", "originalformat":"jpg"}, 
		 * 				 {"id":"8191579575", "owner":"90163607@N04", "secret":"24f97c9b4d", "server":"8205", "farm":9, "title":"macbook", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8190980497", "owner":"46336238@N03", "secret":"0a3373cf6c", "server":"8479", "farm":9, "title":"\uf8ff", "ispublic":1, "isfriend":0, "isfamily":0}, 
		 * 				 {"id":"8193047710", "owner":"79764582@N02", "secret":"45ba74f2ee", "server":"8349", "farm":9, "title":"Post !!", "ispublic":1, "isfriend":0, "isfamily":0}
		 * 		]
		 * 		}, "stat":"ok"})
		 */
		String jsonContent = ProjectUtils.getStringContentFromUrl(urlString);
		jsonContent = jsonContent.substring(14, jsonContent.length()-1);
		
		//now jsonContent is a pure json data
		JSONObject jsonObject = JSONObject.fromObject(jsonContent);
		JSONArray jsonArray = jsonObject.getJSONObject("photos").getJSONArray("photo");
		List<String> urls = new ArrayList<String>();
		
		for (int i=0; i<jsonArray.size();i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			
			String id= object.getString("id");
			int farmId= object.getInt("farm");
			String serverId= object.getString("server");
			String secretId = object.getString("secret");
			
			//http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
			StringBuffer buffer = new StringBuffer();
			buffer.append("http://farm").append(farmId).
					append(".staticflickr.com/").append(serverId).append("/")
					.append(id).append("_").append(secretId).append(".jpg");
			
			System.out.println(buffer.toString());
//			returnedList.add(buffer.toString());
			urls.add(buffer.toString());
		}
		
		
		
		String path = "/Download/others/flickr_photos/storeImage";
		//C:\JavaDevelopment\xiaojunproject\GetContentsFromWebSitesWorkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\FlickrImageRetriever\storeImage
		System.out.println("path:" + path);
		File directory = new File(path,keyword);
		directory.mkdirs();
		
		for(String imageUrl : urls) {
			int position = imageUrl.lastIndexOf("/");
			String imageName = imageUrl.substring(position + 1);
			File file = new File(directory,imageName);
			ProjectUtils.write2LocalFromInternet(imageUrl, file);
		}
	}

}

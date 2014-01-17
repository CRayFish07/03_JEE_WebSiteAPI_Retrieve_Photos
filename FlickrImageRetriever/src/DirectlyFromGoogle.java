import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.joe.web.ProjectUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DirectlyFromGoogle {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/**
		 * https://www.googleapis.com/customsearch/v1?key=YOUR_API_KEY
		 * &cx=YOUR_CSE_ID
		 * &q=flower
		 * &searchType=image
		 * &fileType=jpg
		 * &imgSize=small
		 * &alt=json
		 */
		 
		String googleBaseUrl = "http://ajax.googleapis.com/ajax/services/search/images";
		googleBaseUrl = googleBaseUrl + "?key=ABQIAAAAHMkDJuY1vzGaqUjliVAN-RSo6_Y-lPd_7a5rWdJRsISKKsAXgBQyI4z9PBvV4NjKXO02MH68cek4yA";
		//googleBaseUrl = googleBaseUrl + "&cx=161.151.171.123";
		googleBaseUrl = googleBaseUrl + "&cx=16.165.14.207";
		String googleImgSize = "large";
		//String googleSearchContent = "thinkpad";
		String googleSearchContent = "thinkpad";
		getGoogleImage(googleBaseUrl, googleImgSize, googleSearchContent);

	}

	public static void getGoogleImage(String googleBaseUrl, String googleImgSize, String googleSearchContent) throws Exception {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			String url = googleBaseUrl + "&q=" + googleSearchContent + "&rsz="
					+ googleImgSize + "&v=1.0&";
			url += "start=" + 8 * i;
			StringBuffer sb = new StringBuffer();
			InputStream is = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				System.setProperty("http.proxyHost", "proxy.jpn.hp.com");
				System.setProperty("http.proxyPort", "8080");
				is = new URL(url).openConnection().getInputStream();
				isr = new InputStreamReader(is, "UTF-8");
				br = new BufferedReader(isr);
				String line = null;
				while (null != (line = br.readLine())) {
					sb.append(line);
				}
				list.add(sb.toString());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (null != br) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != isr) {
					try {
						isr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		List<String> imageUrls = new ArrayList<String>(); 
		System.out.println("Image Pages from Google:" + list.size());
		for (String s : list) {
			System.out.println(s);
			parsingGoogleResponseData(s, imageUrls);
		}
		
		String path = "/Download/others/google_photos/storeImage";
		System.out.println("path:" + path);
		File directory = new File(path,googleSearchContent);
		directory.mkdirs();
		
		for(String imageUrl : imageUrls) {
			int position = imageUrl.lastIndexOf("/");
			String imageName = imageUrl.substring(position + 1);
			File file = new File(directory,imageName);
			ProjectUtils.write2LocalFromInternet(imageUrl, file);
		}
	}

	
	/**
	 * {"responseData": {"results":[
	 * 
	 * 						{"GsearchResultClass":"GimageSearch",
	 * 						 "width":"620","height":"400",
	 * 						 "imageId":"ANd9GcRzYZ1YuCVACopKe68c0Idu-h2K8hKy_k0UQFYfVweDMSAswasr0x3ahb4",
	 * 						 "tbWidth":"136","tbHeight":"88",
	 * 						 "unescapedUrl":"http://blog.laptopmag.com/wpress/wp-content/uploads/2012/05/Lenovo-ThinkPad-X230_g2-620x400.jpg",
	 * 						 "url":"http://blog.laptopmag.com/wpress/wp-content/uploads/2012/05/Lenovo-ThinkPad-X230_g2-620x400.jpg",
	 * 						 "visibleUrl":"blog.laptopmag.com",
	 * 						 "title":"Lenovo \u003cb\u003eThinkPad\u003c/b\u003e X230 Hands-On: Does the New Keyboard Cut It?",
	 * 						 "titleNoFormatting":"Lenovo ThinkPad X230 Hands-On: Does the New Keyboard Cut It?",
	 * 						 "originalContextUrl":"http://blog.laptopmag.com/lenovo-thinkpad-x230-hands-on-revamped-keyboard-simple-touch-ui",
	 * 						 "content":"Lenovo \u003cb\u003eThinkPad\u003c/b\u003e X230",
	 * 						 "contentNoFormatting":"Lenovo ThinkPad X230",
	 * 						 "tbUrl":"http://t3.gstatic.com/images?q\u003dtbn:ANd9GcRzYZ1YuCVACopKe68c0Idu-h2K8hKy_k0UQFYfVweDMSAswasr0x3ahb4"},
	 * 						{"GsearchResultClass":"GimageSearch",
	 * 						 "width":"512","height":"353",
	 * 						 "imageId":"ANd9GcSGEpE5Nx2sphj62T9GP2xF7KiYcvYAHzy6GHToLM0zkKo7p0s2UmPzdD4",
	 * 						 "tbWidth":"131","tbHeight":"90",
	 * 						 "unescapedUrl":"http://www.thinkpads.com/wp-content/uploads/2011/01/lenovo_thinkpad_x220-2.jpg",
	 * 						 "url":"http://www.thinkpads.com/wp-content/uploads/2011/01/lenovo_thinkpad_x220-2.jpg",
	 * 						 "visibleUrl":"www.thinkpads.com",
	 * 						 "title":"Leaked in China: refreshed Lenovo \u003cb\u003eThinkPad\u003c/b\u003e X220 | \u003cb\u003eThinkPads\u003c/b\u003e.",
	 * 						 "titleNoFormatting":"Leaked in China: refreshed Lenovo ThinkPad X220 | ThinkPads.",
	 * 						 "originalContextUrl":"http://www.thinkpads.com/2011/01/10/leaked-in-china-refreshed-lenovo-thinkpad-x220/",
	 * 						 "content":"Lenovo \u003cb\u003eThinkPad\u003c/b\u003e X220",
	 * 						 "contentNoFormatting":"Lenovo ThinkPad X220",
	 * 						 "tbUrl":"http://t0.gstatic.com/images?q\u003dtbn:ANd9GcSGEpE5Nx2sphj62T9GP2xF7KiYcvYAHzy6GHToLM0zkKo7p0s2UmPzdD4"},
	 * 	{"GsearchResultClass":"GimageSearch","width":"640","height":"508","imageId":"ANd9GcQ--6lnR9ZgADyKlI29z7LLxMFd62INS-4g6IdvrDXyQ2at9v-ppJp0tKI",
	 * 		"tbWidth":"137","tbHeight":"109","unescapedUrl":"http://cdn.arstechnica.net/wp-content/uploads/2012/08/thinkpad_x1_carbon_keyboard.jpg",
	 * 		"url":"http://cdn.arstechnica.net/wp-content/uploads/2012/08/thinkpad_x1_carbon_keyboard.jpg","visibleUrl":"arstechnica.com",
	 * 		"title":"A worthy Ultrabook appears: the \u003cb\u003eThinkPad\u003c/b\u003e X1 Carbon reviewed | Ars \u003cb\u003e...\u003c/b\u003e",
	 * 		"titleNoFormatting":"A worthy Ultrabook appears: the ThinkPad X1 Carbon reviewed | Ars ...",
	 * 		"originalContextUrl":"http://arstechnica.com/gadgets/2012/08/a-worthy-ultrabook-appears-the-thinkpad-x1-carbon-reviewed/",
	 * 		"content":"appears: the \u003cb\u003eThinkPad\u003c/b\u003e X1","contentNoFormatting":"appears: the ThinkPad X1",
	 * 		"tbUrl":"http://t1.gstatic.com/images?q\u003dtbn:ANd9GcQ--6lnR9ZgADyKlI29z7LLxMFd62INS-4g6IdvrDXyQ2at9v-ppJp0tKI"},
	 *  {"GsearchResultClass":"GimageSearch","width":"960","height":"639","imageId":"ANd9GcRtSxYfmRF51Y0Wn8i3m_65eFWUxZ-cqK2MoZ7APY94EMMR-mMPqikKWLQ",
	 *  	"tbWidth":"148","tbHeight":"99","unescapedUrl":"http://cdn.pocket-lint.com/images/Hn7P/lenovo-thinkpad-x1-carbon-ultrabook-pictures-and-hands-on-0.jpg?20120830-172817",
	 *  	"url":"http://cdn.pocket-lint.com/images/Hn7P/lenovo-thinkpad-x1-carbon-ultrabook-pictures-and-hands-on-0.jpg%3F20120830-172817",
	 *  	"visibleUrl":"www.pocket-lint.com","title":"Lenovo \u003cb\u003eThinkPad\u003c/b\u003e X1 Carbon pictures and hands-on - Pocket-",
	 *  	"titleNoFormatting":"Lenovo ThinkPad X1 Carbon pictures and hands-on - Pocket-",
	 *  	"originalContextUrl":"http://www.pocket-lint.com/news/47228/lenovo-thinkpad-x1-carbon-ultrabook-pictures-and-hands-on",
	 *  	"content":"Lenovo \u003cb\u003eThinkPad\u003c/b\u003e X1 Carbon","contentNoFormatting":"Lenovo ThinkPad X1 Carbon",
	 *  	"tbUrl":"http://t0.gstatic.com/images?q\u003dtbn:ANd9GcRtSxYfmRF51Y0Wn8i3m_65eFWUxZ-cqK2MoZ7APY94EMMR-mMPqikKWLQ"},
	 *  {"GsearchResultClass":"GimageSearch","width":"530","height":"430","imageId":"ANd9GcSovQ5g2o7KxBbhJF-SqCcGHRb3Isn1xm1BJPRV9zKIS2quQ-Wqm11Jchs",
	 *  	"tbWidth":"132","tbHeight":"107","unescapedUrl":"http://www.lenovo.com/shop/americas/content/img_lib/530x430/T520.jpg",
	 *  	"url":"http://www.lenovo.com/shop/americas/content/img_lib/530x430/T520.jpg","visibleUrl":"shop.lenovo.com",
	 *  	"title":"\u003cb\u003eThinkPad\u003c/b\u003e T520 15.6\u0026quot; Laptop | Shop | Lenovo","titleNoFormatting":"ThinkPad T520 15.6\u0026quot; Laptop | Shop | Lenovo",
	 *  	"originalContextUrl":"http://shop.lenovo.com/us/laptops/thinkpad/t-series/t520","content":"\u003cb\u003eThinkPad\u003c/b\u003e T520",
	 *  	"contentNoFormatting":"ThinkPad T520","tbUrl":"http://t3.gstatic.com/images?q\u003dtbn:ANd9GcSovQ5g2o7KxBbhJF-SqCcGHRb3Isn1xm1BJPRV9zKIS2quQ-Wqm11Jchs"},
	 *  {"GsearchResultClass":"GimageSearch","width":"540","height":"376","imageId":"ANd9GcTg0YxIMnAgdVWpMtK99Xy-B-EikMXR02h8mE0Y6XdY_w21AMZtc2l_geUC","tbWidth":"132",
	 *  	"tbHeight":"92","unescapedUrl":"http://images.pcworld.com/images/article/2012/05/thinkpad20x120carbon4-11360235.jpg",
	 *  	"url":"http://images.pcworld.com/images/article/2012/05/thinkpad20x120carbon4-11360235.jpg","visibleUrl":"www.pcworld.com",
	 *  	"title":"Lenovo Packs \u0026#39;Thinnest\u0026#39; \u003cb\u003eThinkPad\u003c/b\u003e Ultrabook With 4G LTE | PCWorld",
	 *  	"titleNoFormatting":"Lenovo Packs \u0026#39;Thinnest\u0026#39; ThinkPad Ultrabook With 4G LTE | PCWorld",
	 *  	"originalContextUrl":"http://www.pcworld.com/article/255601/lenovo_packs_thinnest_thinkpad_ultrabook_with_4g_lte.html",
	 *  	"content":"to \u003cb\u003eThinkPad\u003c/b\u003e customers.","contentNoFormatting":"to ThinkPad customers.",
	 *  	"tbUrl":"http://t1.gstatic.com/images?q\u003dtbn:ANd9GcTg0YxIMnAgdVWpMtK99Xy-B-EikMXR02h8mE0Y6XdY_w21AMZtc2l_geUC"},
	 *  {"GsearchResultClass":"GimageSearch","width":"800","height":"715","imageId":"ANd9GcSYxKuBGjj9Cgv6arr_XtfuUJ1cUFYMgyOr5qTlYBcfaT_eJBA0GhAAZ01Llw","tbWidth":"143",
	 *  	"tbHeight":"128","unescapedUrl":"http://www.notebookcheck.net/typo3temp/pics/326453a3d1.jpg","url":"http://www.notebookcheck.net/typo3temp/pics/326453a3d1.jpg",
	 *  	"visibleUrl":"www.notebookcheck.net","title":"Review Lenovo \u003cb\u003eThinkpad\u003c/b\u003e Edge E535 Notebook - Notebookcheck.net Reviews",
	 *  	"titleNoFormatting":"Review Lenovo Thinkpad Edge E535 Notebook - Notebookcheck.net Reviews",
	 *  	"originalContextUrl":"http://www.notebookcheck.net/Review-Lenovo-Thinkpad-Edge-E535-Notebook.81653.0.html",
	 *  	"content":"The \u003cb\u003eThinkpad\u003c/b\u003e Edge 535 has left us with a mixed impression - which relates it",
	 *  	"contentNoFormatting":"The Thinkpad Edge 535 has left us with a mixed impression - which relates it",
	 *  	"tbUrl":"http://t1.gstatic.com/images?q\u003dtbn:ANd9GcSYxKuBGjj9Cgv6arr_XtfuUJ1cUFYMgyOr5qTlYBcfaT_eJBA0GhAAZ01Llw"},
	 *  {"GsearchResultClass":"GimageSearch","width":"500","height":"386","imageId":"ANd9GcTRnaZbyFSqKNy7x9fpzNPKJMptNZHiGEzFVZiBkt9IxmAEkC8GSWcOmtX5","tbWidth":"130",
	 *  	"tbHeight":"100","unescapedUrl":"http://static.trustedreviews.com/6f9d8f%7C649f_7391-IMG3369s.jpg",
	 *  	"url":"http://static.trustedreviews.com/6f9d8f%257C649f_7391-IMG3369s.jpg","visibleUrl":"www.trustedreviews.com",
	 *  	"title":"Lenovo \u003cb\u003eThinkPad\u003c/b\u003e X300 review - Laptop - Trusted Reviews","titleNoFormatting":"Lenovo ThinkPad X300 review - Laptop - Trusted Reviews",
	 *  	"originalContextUrl":"http://www.trustedreviews.com/Lenovo-ThinkPad-X300_Laptop_review","content":"the \u003cb\u003eThinkPad\u003c/b\u003e brand manages",
	 *  	"contentNoFormatting":"the ThinkPad brand manages","tbUrl":"http://t1.gstatic.com/images?q\u003dtbn:ANd9GcTRnaZbyFSqKNy7x9fpzNPKJMptNZHiGEzFVZiBkt9IxmAEkC8GSWcOmtX5"},
	 *  {"GsearchResultClass":"GimageSearch","width":"453","height":"322","imageId":"ANd9GcRs4SHXk9U_vPx_33_ixAJlW4NyjPrnEzf60S3DiWyRzZeeQBKwIvboG44","tbWidth":"127","tbHeight":"90",
	 *  	"unescapedUrl":"http://www.blogcdn.com/www.engadget.com/media/2008/06/6-8-08-thinkpad-sl-series.jpg",
	 *  	"url":"http://www.blogcdn.com/www.engadget.com/media/2008/06/6-8-08-thinkpad-sl-series.jpg","visibleUrl":"www.engadget.com",
	 *  	"title":"Details surface for future \u003cb\u003eThinkPads\u003c/b\u003e: X200, SL, T and R series",
	 *  	"titleNoFormatting":"Details surface for future ThinkPads: X200, SL, T and R series",
	 *  	"originalContextUrl":"http://www.engadget.com/2008/06/08/details-surface-for-future-thinkpads-x200-sl-t-and-r-series/",
	 *  	"content":"of \u003cb\u003eThinkPad\u003c/b\u003e news coming at","contentNoFormatting":"of ThinkPad news coming at",
	 *  	"tbUrl":"http://t0.gstatic.com/images?q\u003dtbn:ANd9GcRs4SHXk9U_vPx_33_ixAJlW4NyjPrnEzf60S3DiWyRzZeeQBKwIvboG44"},
	 *  {"GsearchResultClass":"GimageSearch","width":"375","height":"367","imageId":"ANd9GcRT53tMLl0NuiFRG_ZinrqoJ9FQSpJHSl646irRZZOntKTdjBoJvV9S9Ag","tbWidth":"122",
	 *  	"tbHeight":"119","unescapedUrl":"http://www.tabletpc2.com/Graphics/Reviews/Lenovo%20X41/Lenovo%20Thinkpad%20X41Tablet%20PC.jpg",
	 *  	"url":"http://www.tabletpc2.com/Graphics/Reviews/Lenovo%2520X41/Lenovo%2520Thinkpad%2520X41Tablet%2520PC.jpg","visibleUrl":"www.tabletpc2.com",
	 *  	"title":"Lenovo \u003cb\u003eThinkpad\u003c/b\u003e X41 Tablet PC","titleNoFormatting":"Lenovo Thinkpad X41 Tablet PC",
	 *  	"originalContextUrl":"http://www.tabletpc2.com/Review-IBM%20Lenovo%20ThinkPad%20X41%20Tablet%20PC.htm",
	 *  	"content":"Lenovo \u003cb\u003eThinkpad\u003c/b\u003e X41 Tablet PC","contentNoFormatting":"Lenovo Thinkpad X41 Tablet PC",
	 *  	"tbUrl":"http://t2.gstatic.com/images?q\u003dtbn:ANd9GcRT53tMLl0NuiFRG_ZinrqoJ9FQSpJHSl646irRZZOntKTdjBoJvV9S9Ag"},
	 *  {"GsearchResultClass":"GimageSearch","width":"620","height":"433","imageId":"ANd9GcSWF-H6UpPM0bG79ZxdN8qw02-wYM9KnEDSw9KMXAqO6q0J_UE72AuMvKDN","tbWidth":"136","tbHeight":"95",
	 *  	"unescapedUrl":"http://i.i.com.com/cnwk.1d/i/tim/2012/08/07/ThinkPad_X1_Carbon_35299011_05_620x433.jpg",
	 *  	"url":"http://i.i.com.com/cnwk.1d/i/tim/2012/08/07/ThinkPad_X1_Carbon_35299011_05_620x433.jpg","visibleUrl":"www.cnet.com",
	 *  	"title":"Lenovo \u003cb\u003eThinkPad\u003c/b\u003e X1 Carbon Review - Watch CNET\u0026#39;s Video \u0026amp; Read Our \u003cb\u003e...\u003c/b\u003e",
	 *  	"titleNoFormatting":"Lenovo ThinkPad X1 Carbon Review - Watch CNET\u0026#39;s Video \u0026amp; Read Our ...",
	 *  	"originalContextUrl":"http://www.cnet.com/laptops/lenovo-thinkpad-x1-carbon/4505-3121_7-35299011.html","content":"Lenovo \u003cb\u003eThinkPad\u003c/b\u003e X1 Carbon",
	 *  	"contentNoFormatting":"Lenovo ThinkPad X1 Carbon","tbUrl":"http://t3.gstatic.com/images?q\u003dtbn:ANd9GcSWF-H6UpPM0bG79ZxdN8qw02-wYM9KnEDSw9KMXAqO6q0J_UE72AuMvKDN"},
	 *  {"GsearchResultClass":"GimageSearch","width":"440","height":"319","imageId":"ANd9GcQg095JkcXMXIrsnf1ALA5b2hQncsY0hUOIh89gT0swepUkO0YDMvUo4g","tbWidth":"127","tbHeight":"92",
	 *  	"unescapedUrl":"http://www.geek.com/wp-content/uploads/2008/10/thinkpad_w700_05.jpg","url":"http://www.geek.com/wp-content/uploads/2008/10/thinkpad_w700_05.jpg",
	 *  	"visibleUrl":"www.geek.com","title":"Review: Lenovo \u003cb\u003eThinkPad\u003c/b\u003e W700 � Computer Chips \u0026amp; Hardware \u003cb\u003e...\u003c/b\u003e",
	 *  	"titleNoFormatting":"Review: Lenovo ThinkPad W700 � Computer Chips \u0026amp; Hardware ...",
	 *  	"originalContextUrl":"http://www.geek.com/articles/chips/review-lenovo-thinkpad-w700-20081023/","content":"the first \u003cb\u003eThinkPad\u003c/b\u003e to come",
	 *  	"contentNoFormatting":"the first ThinkPad to come","tbUrl":"http://t3.gstatic.com/images?q\u003dtbn:ANd9GcQg095JkcXMXIrsnf1ALA5b2hQncsY0hUOIh89gT0swepUkO0YDMvUo4g"},
	 *  {"GsearchResultClass":"GimageSearch","width":"530","height":"430","imageId":"ANd9GcTSA9wrcz9WOwMn9yB1KiYiR4m8CZT03xreSZJ0J40mQo2hE9wToq07SSA","tbWidth":"132","tbHeight":"107",
	 *  	"unescapedUrl":"http://www.lenovo.com/shop/americas/content/img_lib/530x430/w701.jpg","url":"http://www.lenovo.com/shop/americas/content/img_lib/530x430/w701.jpg",
	 *  	"visibleUrl":"shop.lenovo.com","title":"Laptop computers - \u003cb\u003eThinkPad\u003c/b\u003e W701 | Lenovo","titleNoFormatting":"Laptop computers - ThinkPad W701 | Lenovo",
	 *  	"originalContextUrl":"http://shop.lenovo.com/us/laptops/thinkpad/w-series/w701","content":"Products similar to \u003cb\u003eThinkPad\u003c/b\u003e",
	 *  	"contentNoFormatting":"Products similar to ThinkPad","tbUrl":"http://t0.gstatic.com/images?q\u003dtbn:ANd9GcTSA9wrcz9WOwMn9yB1KiYiR4m8CZT03xreSZJ0J40mQo2hE9wToq07SSA"},
	 *  {"GsearchResultClass":"GimageSearch","width":"660","height":"371","imageId":"ANd9GcT2PkmnbLWnNOOb_vAMMkUK5pmMWDcV4hG--xL5uAguXhJzLJXR1I-IsVA","tbWidth":"138","tbHeight":"78",
	 *  	"unescapedUrl":"http://global.fncstatic.com/static/managed/img/Scitech/China%20Lenovo%20New%20Thinkpad%202.jpg",
	 *  	"url":"http://global.fncstatic.com/static/managed/img/Scitech/China%2520Lenovo%2520New%2520Thinkpad%25202.jpg",
	 *  	"visibleUrl":"www.foxnews.com","title":"Lighter, quicker \u003cb\u003eThinkPad\u003c/b\u003e laptop coming, Lenovo says | Fox News",
	 *  	"titleNoFormatting":"Lighter, quicker ThinkPad laptop coming, Lenovo says | Fox News",
	 *  	"originalContextUrl":"http://www.foxnews.com/tech/2012/08/06/lenovo-unveils-lighter-quicker-thinkpad-laptop/",
	 *  	"content":"China Lenovo New \u003cb\u003eThinkpad\u003c/b\u003e 2.","contentNoFormatting":"China Lenovo New Thinkpad 2.",
	 *  	"tbUrl":"http://t0.gstatic.com/images?q\u003dtbn:ANd9GcT2PkmnbLWnNOOb_vAMMkUK5pmMWDcV4hG--xL5uAguXhJzLJXR1I-IsVA"},
	 *  {"GsearchResultClass":"GimageSearch","width":"333","height":"254","imageId":"ANd9GcRRg7org4L8K9Lt686S85IXysQurY8CW4XizhC3doHJF_V2o_HelVDlxQ","tbWidth":"119","tbHeight":"91",
	 *  	"unescapedUrl":"http://www9.pcmag.com/media/images/363535-lenovo-thinkpad-x230t.jpg?thumb\u003dy",
	 *  	"url":"http://www9.pcmag.com/media/images/363535-lenovo-thinkpad-x230t.jpg%3Fthumb%3Dy","visibleUrl":"www.pcmag.com",
	 *  	"title":"Lenovo \u003cb\u003eThinkPad\u003c/b\u003e X1 Carbon Review \u0026amp; Rating | PCMag.",
	 *  	"titleNoFormatting":"Lenovo ThinkPad X1 Carbon Review \u0026amp; Rating | PCMag.","originalContextUrl":"http://www.pcmag.com/article2/0,2817,2408289,00.asp",
	 *  	"content":"Lenovo \u003cb\u003eThinkPad\u003c/b\u003e X230t","contentNoFormatting":"Lenovo ThinkPad X230t",
	 *  	"tbUrl":"http://t0.gstatic.com/images?q\u003dtbn:ANd9GcRRg7org4L8K9Lt686S85IXysQurY8CW4XizhC3doHJF_V2o_HelVDlxQ"},
	 *  {"GsearchResultClass":"GimageSearch","width":"1280","height":"800","imageId":"ANd9GcSflsVVKQRf1Cz6d8lWAJU7v99Cm8LcDZ-jysQLx4KN57W676yaQ0tA42Q","tbWidth":"150","tbHeight":"94",
	 *  	"unescapedUrl":"http://easycomputersupport.files.wordpress.com/2012/05/thinkpad-by-montevina-awesome-537416.jpg",
	 *  	"url":"http://easycomputersupport.files.wordpress.com/2012/05/thinkpad-by-montevina-awesome-537416.jpg","visibleUrl":"easycomputersupport.wordpress.com",
	 *  	"title":"The Beast Has Fallen End of \u003cb\u003eThinkPad\u003c/b\u003e T61 ? Easy Lenovo Modifications",
	 *  	"titleNoFormatting":"The Beast Has Fallen End of ThinkPad T61 ? Easy Lenovo Modifications",
	 *  	"originalContextUrl":"http://easycomputersupport.wordpress.com/2012/08/26/the-beast-has-fallen-end-of-thinkpad-t61/",
	 *  	"content":"Fallen End of \u003cb\u003eThinkPad\u003c/b\u003e T61","contentNoFormatting":"Fallen End of ThinkPad T61",
	 *  	"tbUrl":"http://t0.gstatic.com/images?q\u003dtbn:ANd9GcSflsVVKQRf1Cz6d8lWAJU7v99Cm8LcDZ-jysQLx4KN57W676yaQ0tA42Q"}
	 *  ],
	 *  "cursor":{"resultCount":"27,200,000","pages":[{"start":"0","label":1},{"start":"16","label":2},{"start":"32","label":3},{"start":"48","label":4},{"start":"64","label":5},
	 *  {"start":"80","label":6},{"start":"96","label":7},{"start":"112","label":8},{"start":"128","label":9},{"start":"144","label":10}],
	 *  "estimatedResultCount":"27200000","currentPageIndex":2,"moreResultsUrl":"","searchResultTime":"0.09"}}, "responseDetails": null, "responseStatus": 200}
	 * 
	 */
	private static void parsingGoogleResponseData(String result, List<String> imageUrls) {
		JSONObject jsonContent = JSONObject.fromObject(result); 
		JSONObject responseData = jsonContent.getJSONObject("responseData");  
		JSONArray results = responseData.getJSONArray("results");  
		for (int i = 0; i < results.size(); i++) {  
			JSONObject jsonObject = results.getJSONObject(i);  
			String url = jsonObject.getString("url");  
			//http://static.trustedreviews.com/c602e0%257C7c5d_2277-1.jpg
			System.out.println(url);
			imageUrls.add(url);  
		}  
	}
}

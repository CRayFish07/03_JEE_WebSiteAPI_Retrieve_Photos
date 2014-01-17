<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Search Page</title>
	<script type="text/javascript" src="./js/jquery-1.7.1.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#btnFlickr").click(function() {
				//this commented ajax call also can work correctly
				/*
				//var params = $("#myForm").serialize();
				$.ajax({
					//url : '/FlickrImageRetriever/flickrImage/searchPhotosAction.action',
					url : 'flickrImage/searchPhotosAction.action',
					type : 'GET',
					data : {keyword:$("#categoryName").val()},
					dataType : 'json',
					success : function(data) {
						$(data.urls).each(
								function(i, value) {
									$("#divPhotos").append(
											"<img src='" + value + "' width='150' height='150'>");
								});

					}
				});*/
				
				$.get(
						"flickrImage/searchPhotosAction.action",
						{keyword:$("#categoryName").val()},
						function(returnedData, status) {
							$("#divPhotos").empty();
							$(returnedData.urls).each(
									function(i, value) {
										$("#divPhotos").append(
												"<img src='" + value + "' width='150' height='150'>");
									});
						});
			});
			
			$("#btnSave").click(function() {
				$("#divSaveProcessingMessage").html("processing...");
				$.post(
						//this commented line is using only one thread to save photos
						//"flickrImageSave/savePhotosAction.action",
						"flickrImageSave/savePhotosInMultiThreadAction.action",
						{}, 
						function(){
							$("#divSaveProcessingMessage").html("");
							alert("saved");
							
						});
			});
		});
	</script>
</head>
<body>
	<form id="myForm">
		<input type="text" id="categoryName"/>
		<input type="button" value="Search From Flickr" id="btnFlickr"/>
		<input type="button" value="Save To Local Disk" id="btnSave"/>
	</form>
	<div id="divSaveProcessingMessage"></div>
	<div id="divPhotos"></div>
	
</body>
</html>
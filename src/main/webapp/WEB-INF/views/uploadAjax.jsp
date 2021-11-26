<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; chartset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h1>Upload with Ajax</h1>
		
		<div class='uploadDiv'>
			<input type='file' name='uploadFile' multiple>
		</div>
		
		<button id='uploadBtn'>Upload</button>
		
		<div class='uploadResult'>
			<ul>
			
			</ul>
		</div>
		
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script>
$(document).ready(function() {
	
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$")
	
	var maxSize = 5242880
	
	function checkExtension(fileName, fileSize) {
	
		if(fileSize >= maxSize) {
			alert("파일 사이즈 초과")
			return false
		}
		
		if(regex.test(fileName)) {
			alert("해당 종류의 파일은 업로드할 수 없습니다.")
			return false
		}
		
		return true
	}
	
	var cloneObj = $(".uploadDiv").clone()
	
	$("#uploadBtn").on("click", function(e) {
		
		var formData = new FormData()
		
		var inputFile = $("input[name='uploadFile']")
		
		var files = inputFile[0].files
		
		for(var i=0; i < files.length; i++) {
			
			if(!checkExtension(files[i].name, files[i].size)) {
				return false // 파일이 부적절할 경우 for문 종료
			}
			
			formData.append("uploadFile", files[i])
		}
		
		$.ajax({
			url: '/uploadAjaxAction',
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
			dataType: 'json',
			success: function(result){
				console.log(result)
				
				showUploadedFile(result)
				
				$(".uploadDiv").html(cloneObj.html())
			}
		})
	})
	
	var uploadResult = $(".uploadResult ul");
	
	function showUploadedFile(uploadResultArr) {
		
		var str = "";
		
		$(uploadResultArr).each(function(i, obj) {
			
			str += "<li>" + obj.fileName + "</li>";
		})
		
		uploadResult.append(str)
	}
	
	

})
</script>
	</body>
	
</html>




  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
	<form action="/sample/exUploadPost" method="POST" 
		enctype="multipart/form-data">
	<input type="file" name="files"><br>
	<input type="file" name="files"><br>
	<input type="file" name="files"><br>
	<input type="submit" value="save">
	</form>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta charset=UTF-8 />
</head>
<body>
<form action="doUpload" method="POST" enctype="multipart/form-data">
	<div>
		<input type="file" name="upload" multiple="multiple" />
	</div>
	<div>
		<input type="text" name="fontest" />
	</div>
	<button type="submit">send</button>
</form>

</body>
</html>
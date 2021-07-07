<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">
</script>
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>자유게시판</h1>
	</div>
	<div style="width:650px;" align="right">
		<a href="./main.ino">리스트로</a>
	</div>
	<hr style="width: 600px">
	
	<form name="writeForm" method="post" action="freeBoardWritePro.ino" enctype="multipart/form-data" >
		<div style="width: 150px; float: left;">이름 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="name"/></div>
		
		<div style="width: 150px; float: left;">제목 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="title"/></div>
		<div style="width: 150px; float: left;">내용 :</div>
		<div style="width: 500px; float: left;" align="left"><textarea name="content" rows="25" cols="65"  ></textarea></div>
		<div style="width: 150px; float: left;">파일추가 :</div>
		<div><input type="file" name="file"></div>
		<div align="right">
		<input type="submit" value="글쓰기">
		<input type="button" value="다시쓰기" onclick="initBtn()">
		<input type="button" value="취소" onclick="cancelBtn()" >
		&nbsp;&nbsp;&nbsp;
	</form>
	
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
	
	function cancelBtn() {
		 if (confirm("정말 취소하시겠습니까?") == true){    //확인

		   location.replace('freeBoardData.ino');

		 }else{   //취소

		     return false;

		 };
	};
	
	function initBtn() {
		
			$("Form").each(function () {
				this.reset();
		});
	};
	
	</script>
	
	
	
</body>
</html>
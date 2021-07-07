<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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

	<form name="boardForm" id="boardForm" role="form" action="./freeBoardWritePro.ino" method="post">
		<input type="hidden" name="num" value="${freeBoardDataDetailByNum.num}">
		<div style="width: 150px; float: left;">이름 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="name" value="${freeBoardDataDetailByNum.name }" readonly="readonly"/></div>

		<div style="width: 150px; float: left;">제목 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="title"  value="${freeBoardDataDetailByNum.title }" readonly="readonly"/></div>

		<div style="width: 150px; float: left;">작성날자</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="regdate"  value="${freeBoardDataDetailByNum.regdate }" readonly="readonly"/></div>

		<div style="width: 150px; float: left;">내용 :</div>
		<div style="width: 500px; float: left;" align="left"><textarea name="content" rows="25" cols="65"  readonly="readonly">${freeBoardDataDetailByNum.content }</textarea></div>
		<div style="width: 150px; float: left;">파일다운로드 :</div>
		<div style="border: 1px solid #dbdbdb;">

		<c:forEach var="file" items="${file}" >
			<input type="hidden" id="FILE_NO" name="FILE_NO" value="">
			<a href="#" onclick="fn_fileDown('${file.FILE_NO}'); return false;" >${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE})<br>
		</c:forEach>

		</div>
		<div align="right">
		<input type="button" value="수정" onclick="modifyBtn()">
		<input type="button" value="취소" onclick="cancelBtn()">
		&nbsp;&nbsp;&nbsp;
		</div>

	</form>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">

		function modifyBtn(num) {


			$("#boardForm").attr("action", "./freeBoardUpdate.ino");

			$("#boardForm").submit();

		};

		function cancelBtn() {
			 if (confirm("정말 취소하시겠습니까?") == true){    //확인

			   location.replace('freeBoardData.ino');

			 }else{   //취소

			     return false;

			 }
		};

		function fn_fileDown(fileNo){
			var formObj = $("form[name='boardForm']");
			$("#FILE_NO").attr("value", fileNo);
			formObj.attr("action", "fileDown.ino");
			formObj.submit();
		}


	</script>
</body>
</html>
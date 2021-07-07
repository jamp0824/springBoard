<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<form id="boardForm"  action="./modifyBoard.ino">
		<input type="hidden" name="num" value="${freeBoardDto.num}">
		<div style="width: 150px; float: left;">이름 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="name" value="${freeBoardDto.name }" readonly="readonly"/></div>
		
		<div style="width: 150px; float: left;">제목 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="title"  value="${freeBoardDto.title }" /></div>
	
		<div style="width: 150px; float: left;">작성날자</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="regdate"  value="${freeBoardDto.regdate }" readonly="readonly"/></div>
	
		<div style="width: 150px; float: left;">내용 :</div>
		<div style="width: 500px; float: left;" align="left"><textarea name="content" rows="25" cols="65"  >${freeBoardDto.content }</textarea></div>
		<div align="right">
		<input type="button" value="수정" onclick="modifyBtn()">
		<input type="button" value="삭제" onclick="delBtn()">
		<input type="button" value="취소" onclick="cancelBtn()">
		&nbsp;&nbsp;&nbsp;
		</div>
	
	</form>
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
	
		function modifyBtn() {
			if(confirm("정말 수정하시겠습니까?") == true) {
			$("#boardForm").submit();
			} else { //취소
				return false;	
			}
			
		}
		
		function delBtn() {
			if(confirm("정말 삭제하시겠습니까?")) {
				
				$("#boardForm").attr("action", "BoardDelete.ino");
				
				$("#boardForm").submit();			
			} else { //취소
				return false;
				
			}
		};
		
		function cancelBtn() {
			 if (confirm("정말 취소하시겠습니까?") == true){    //확인
	
			   location.replace('main.ino');
	
			 }else{   //취소
	
			     return false;
	
			 }
		};
	
	</script>
</body>
</html>
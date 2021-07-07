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

	<Form name="boardForm" id="boardForm" role="form" method="post" action="./dataUpdate.ino" enctype="multipart/form-data">
		<input type="hidden" name="num" value="${dataUpdate.num}">
		<input type="hidden" id="fileNoDel" name="fileNoDel[]" value="">
		<input type="hidden" id="fileNameDel" name="fileNameDel[]" value="">
		<div style="width: 150px; float: left;">이름 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="name" value="${dataUpdate.name }" readonly="readonly"/></div>

		<div style="width: 150px; float: left;">제목 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="title"  value="${dataUpdate.title }" /></div>

		<div style="width: 150px; float: left;">작성날자</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="regdate"  value="${dataUpdate.regdate }" readonly="readonly"/></div>

		<div style="width: 150px; float: left;">내용 :</div>
		<div style="width: 500px; float: left;" align="left"><textarea name="content" rows="25" cols="65"  >${dataUpdate.content }</textarea></div>
		<div style="width: 150px; float: left;">파일:</div>
		<div id="fileIndex">
		<c:forEach var="file" items="${file}" varStatus="var">

		<input type="hidden" id="FILE_NO" name="FILE_NO_${var.index}" value="${file.FILE_NO }">
		<input type="hidden" id="FILE_NAME" name="FILE_NAME" value="FILE_NO_${var.index}">
		<a href="#" id="fileName" onclick="return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)
		<button id="fileDel" onclick="fn_del('${file.FILE_NO}','FILE_NO_${var.index}');" type="button">삭제</button><br>

		</c:forEach>
		</div>
		<div align="right">
		<button type="button" class="fileAdd_btn">파일추가</button>
		<input type="button" value="수정" onclick="modifyBtn()">
		<input type="button" value="게시글 삭제" onclick="delBtn()">
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
		};

		function delBtn() {
			if(confirm("정말 삭제하시겠습니까?")) {

				$("#boardForm").attr("action", "BoardDataDelete.ino");

				$("#boardForm").submit();
			} else { //취소
				return false;

			}
		};

		function cancelBtn() {
			 if (confirm("정말 취소하시겠습니까?") == true){    //확인

			   location.replace('freeBoardData.ino');

			 }else{   //취소

			     return false;

			 }
		};

		$(document).ready(function(){
			var formObj = $("form[name='boardForm']");

			$(document).on("click","#fileDel", function(){
				$(this).parent().remove();
			})

			fn_addFile();
		})

		function fn_valiChk(){
			var boardForm = $("form[name='boardForm'] .chk").length;
			for(var i = 0; i<boardForm; i++){
				if($(".chk").eq(i).val() == "" || $(".chk").eq(i).val() == null){
					alert($(".chk").eq(i).attr("title"));
					return true;
				}
			}
		}

 		function fn_addFile(){
			var fileIndex = 1;
			$(".fileAdd_btn").on("click", function(){
				$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"+"<button type='button' style='float:right;' id='fileDelBtn'>"+"삭제"+"</button></div>"+"<br>");
			});
			$(document).on("click","#fileDelBtn", function(){
				$(this).parent().remove();

			});
		}
 		var fileNoArry = new Array();
 		var fileNameArry = new Array();
 		function fn_del(value, name){

 			fileNoArry.push(value);
 			fileNameArry.push(name);
 			$("#fileNoDel").attr("value", fileNoArry);
 			$("#fileNameDel").attr("value", fileNameArry);
 		}


	</script>
</body>
</html>
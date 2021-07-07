<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
		li {list-style: none; float: left; padding: 6px; display: inline;}
		ul {text-align: center;}
		.paging {display: inline-block;}
	
</style>
<body>

	<div>
		<h1>자료실</h1>
	</div>
	<div style="width:650px;" align="right">
		<a href="./freeBoardWrite.ino">글쓰기</a>
	</div>
	<hr style="width: 600px">
	
	<c:forEach items="${freeBoardData }" var="dto" >	
			<div style="width: 50px; float: left;">${dto.num }</div>	
			<div style="width: 300px; float: left;"><a href="./freeBoardDataDetailByNum.ino?num=${dto.num }">${dto.title }</a></div>
			<div style="width: 150px; float: left;">${dto.name }</div>
			<div style="width: 150px; float: left;">${dto.regdate }</div> 
		<hr style="width: 600px">
	</c:forEach>
	
	
<div class="paging">
  <ul>
  	<c:choose>
   	 <c:when test="${pageMaker.startPage != 1 }">
   	 	<li><a href="freeBoardData.ino">처음으로</a>
   	 </c:when>
   	 <c:otherwise>
   	 	<li><a>처음으로</a>
   	 </c:otherwise>
   	 </c:choose>
     <c:if  test="${pageMaker.prev}">
    	<li><a href="freeBoardData.ino${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
 	</c:if> 
 
    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    	<li><a href="freeBoardData.ino${pageMaker.makeSearch(idx)}">${idx}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li><a href="freeBoardData.ino${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
    </c:if>

    <c:choose>
   	<c:when test="${pageMaker.endPage <= pageMaker.last }">
   	 	<li><a href="freeBoardData.ino${pageMaker.makeSearch(pageMaker.last+1) }">끝으로</a>
   	</c:when>
    <c:otherwise>
   	 	<li><a>끝으로</a>
    </c:otherwise>
    </c:choose>
  </ul>
</div>
</body>
</html>
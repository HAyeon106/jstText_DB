<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, jspTest_DB.*" %>
<% 
String srchText = request.getParameter("srchText");
if(srchText == null) srchText="";
List<Book> list = BookDAO.findByAuthor(srchText);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	div.container {width:600px; margin: 50px auto;}
	table {border-collapse: collapse; width:100%; margin-top:30px;}
	thead {text-weight: bold; text-align:center; background-color: #eee;}
	td {border:1px solid lightgray; padding:4px;}
	button {width:80px;}
</style>
<body>

<div class="container">

<h1> 책목록 </h1>
<div class="input">
	<form>
		<label>저자</label>
		<input type="text" name="srchText" placeholder="검색조건" value="<%= srchText %>"/>
		<button type="submit">조회</button>
	</form>
</div>

<table>
	<thead>
		<tr>
			<td>id</td>
			<td>제목</td>
			<td>저자</td>
			<td>카테고리</td>
			<td>가격</td>
			<td>출판사</td>
		</tr>
	</thead>
	
	<tbody>
		<% for (Book book : list) { %>
		<tr>
			<td><%= book.getId() %></td>
			<td><%= book.getTitle() %></td>
			<td><%= book.getAuthor() %></td>
			<td><%= book.getCategoryName() %></td>
			<td><%= book.getPrice() %></td>
			<td><%= book.getPublisher() %></td>
		</tr>
		<% } %>
	</tbody>
</table>
</div>
</body>
</html>
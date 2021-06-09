<%@page import="com.ict.db.DAO2"%>
<%@page import="com.ict.db.VO2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	request.setCharacterEncoding("utf-8");

	String idx = request.getParameter("idx");
	
	VO2 vo = DAO2.getInstance().selectOne(idx);					 

	// 방법 1 수정과 삭제를 위해서 vo를 session에 담음
	// 방법 2 수정과 삭제시 idx와 pw를 필요할 때 마다 파라미터로 넘긴다.
	
	//방법2)
	pageContext.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 수정</title>
<style type="text/css">
	a{text-decoration: none;}
	table{width: 600px; border-collapse: collapse;; text-align: center;}
	table, th, td{border: 1px solid black; padding: 5px; margin: auto;}
	div{ width: 600px; margin: 100px auto; text-align: center;}
	
	.bg{background-color: #99ccff}
	input{padding: 5px;}
</style>
<script type="text/javascript">
	function update_ok(f) {
		// 비밀번호 체크
		if (f.pw.value == "${vo.pw}") {
			alert("수정완료");
			f.submit();
		} else {
			alert("비밀번호틀림");
			f.pw.value = "";
			f.pw.focus();
			return;
		}
	}
</script>
</head>
<body>
	<div>
		<h1>방명록:수정화면</h1>
		<hr>
		<p>
			[<a href="list.jsp">게시물목록으로</a>]
		</p>
		<form method="post" action="update_ok.jsp">
			<table>
				<tbody>
					<tr>
						<th class="bg">작성자</th>
						<td><input type="text" name="name" value="${vo.name}"></td>
					</tr>
					<tr>
						<th class="bg">제목</th>
						<td><input type="text" name="title" value="${vo.title}"></td>
					</tr>
					<tr>
						<th class="bg">이메일</th>
						<td><input type="text" name="email" value="${vo.email}"></td>
					</tr>
					<tr>
						<th class="bg">비밀번호</th>
						<td><input type="password" name="pw"></td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea rows="10" cols="60" name="content">${vo.content}</textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
						<input type="button" value="수정" onclick="update_ok(this.form)"> 
						<%-- DB 수정을 위해서 idx를 넘기자 --%>
						<input type="hidden" name="idx" value="${vo.idx}">
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>
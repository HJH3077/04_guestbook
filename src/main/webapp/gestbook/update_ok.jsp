<%@page import="com.ict.db.DAO2"%>
<%@page import="com.ict.db.VO2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	VO2 vo = new VO2();
	vo.setIdx(request.getParameter("idx"));
	vo.setName(request.getParameter("name"));
	vo.setTitle(request.getParameter("title"));
	vo.setContent(request.getParameter("content"));
	vo.setEmail(request.getParameter("email"));
	vo.setPw(request.getParameter("pw"));
	
	int result = DAO2.getInstance().getUpdate(vo);
	if(result > 0){
		response.sendRedirect("onelist.jsp?idx=" + vo.getIdx());
	}
%>
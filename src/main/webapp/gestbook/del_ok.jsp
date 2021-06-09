<%@page import="com.ict.db.DAO2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String idx = request.getParameter("idx");
	int result = DAO2.getInstance().getDelete(idx);
	if(result > 0){
		response.sendRedirect("list.jsp");
	}
%>
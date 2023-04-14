<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div class="header bg-black">
	<ul class="nav">
		<li class="nav-item active">회원관리</li>
		<li class="nav-item">
		<a href="${context}/gnr/list">영화관리</a>
		</li>
		<li class="nav-item">
		<a href="${context}/mbr/list">시스템관리</a>
		</li>
	</ul>
	<div class="inline profile">이름 <a href="${context}/mbr/logout">(Logout)</a></div>
</div>
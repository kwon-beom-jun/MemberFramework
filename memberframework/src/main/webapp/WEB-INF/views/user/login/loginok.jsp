<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<c:if test="${userInfo != null }">
<script>
	function deleteMember() {
		if (confirm("정말로 탈퇴??")) {
			//document.location.href = "${root}/user/delete.kitri";
			$(location).attr("href","${root}/user/delete.kitri");
		}
	}
</script>

<strong>${userInfo.name }(${userInfo.id})</strong>님 안녕하세요.<br>
<a href = "${root}/user/logout.kitri" >로그아웃</a>
<a href = "${root}/admin/modify.kitri" >정보 수정</a> <!-- get방식 -->
<a href = "#" onclick = "javascript:deletMember();">회원탈퇴</a>
	<c:if test="${'java2' == userInfo.id}">
		<a href = "${root}/admin/mvmemberlist.kitri" >관리자</a>
	</c:if>
</c:if>
<c:if test="${userInfo == null }">
	<c:redirect url="/user/login.kitri"></c:redirect>
</c:if>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>
















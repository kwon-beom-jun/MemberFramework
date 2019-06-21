<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<c:if test="${registerInfo != null}">
<strong>${registerInfo.name}</strong>님 회원가입을 환영합니다.<br>
로그인 후 모든 서비스를 이용할 수 있습니다.<br>
가입하신 정보는 다음과 같습니다.
이메일 : ${registerInfo.emailid}@${registerInfo.emaildomain}<br>
<!-- 객체를 담아오는것이므로 한글이 깨지지 않는다. -->
아이디 : ${registerInfo.id}<br>
전화번호 : ${registerInfo.tel1}-${registerInfo.tel2}-${registerInfo.tel3}<br>
주소 :${registerInfo.zipcode} ${registerInfo.address} ${registerInfo.addressDetail}<br>
<a href ='${root}/user/login.kitri'>로그인</a>
<br>
</c:if>
<c:if test="${registerInfo == null }">
	<c:redirect url="/user"></c:redirect>
</c:if>
<%@ include file="/WEB-INF/views/template/footer.jsp" %>
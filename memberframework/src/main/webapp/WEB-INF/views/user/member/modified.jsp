<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<style type="text/css">
div{
align-content: center;
}
</style>




<script type="text/javascript">

$(function() {
	
		if ($('#name').val().trim().length == 0) {
			alert('아이디 입력하기!!!');
			return;
		} else if ($('#pass').val().trim().length == 0) {
			alert('비밀번호 입력하기!!!');
			return;
		} else if ($('#pass').val().trim().length != $('#passcheck').val().trim().length) {
			alert('비밀번호 확인!!!');
			return;
		}
	
	$("#modified").click(function() {
		
		document.getElementById("memberform").action = "${root}/admin/modifyuser.kitri";
		document.getElementById("memberform").submit();
		
	});
	
});


</script>





</head>
<body>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>회원가입</h2>
		<form id="memberform" method="post" action="">
			<input type="hidden" name="act" value="register">
			<div class="form-group" align="left">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="이름입력">
			</div>
			<div class="form-group" align="left">
				<label for="">현재비밀번호</label>
				<input type="password" class="form-control" id="nowpass" name="nowpass" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="pass" name="pass" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호재입력</label>
				<input type="password" class="form-control" id="passcheck" name="passcheck" placeholder="">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="modified">회원 수정</button>
				<button type="reset" class="btn btn-warning">초기화</button>
			</div>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/views/user/member/zipsearch.jsp" %>
<%@ include file="/WEB-INF/views/template/footer.jsp" %>
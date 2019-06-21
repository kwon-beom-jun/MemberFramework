<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<%-- <script type="text/javascript" src="${root}/js/httpRequest.js"></script> --%>

<script type="text/javascript">

var cnt = 1; // json 0이면 사용가능임.

$(document).ready(function() {
	
	$('#id').keyup(function(){
	
		var id = $('#id').val();
		if(id.length < 5 || id.length >16){
			cnt = 1;
			$('#idresult').css("color", "gray"); // .css("color", "gray").css()가능함. jquery의 장점 체이닝이 있음.
			$('#idresult').text('아이디는 5자 이상 16자 이하입니다.'); //인자값 없으면 겟 있으면 셋
		}else {
			$('#idresult').text('');
			$.ajax({ // jquery ajax임. 오리지널 아님.
				type : "GET", //get은 셀렉트
				url : "${root}/user/idcheck.kitri",
				dataType : "json",
				data : {"checkid" : id}, // form으로 시리얼라이즈해서 넘기면 다넘어감.
				success : function(data) {
					// put한것을 가져옴.
					cnt = parseInt(data.idcount);
					if (cnt == 0) {
						$('#idresult').css("color", "steelblue");
						$('#idresult').html('<strong>'+id + '</strong>사용 가능합니다.'); 
					} else {
						$('#idresult').css("color", "red");
						$('#idresult').html('<strong>'+id + '</strong>사용 사용중입니다.'); 
					}
				}
			});
		}
		
	});
	
	// focusin에 대해서
	$('#zipcode').focusin(function() {
		$('#zipModal').modal();
	});
	
	// 회원가입
	$('#registerBtn').click(function() {
		
		// 정규표현식으로 꼭 바꾸기???
		if ($('#name').val().length > 6) {
			alert('이름을 6자 이하로 만들어 주세요!!!');
			return;
		} else if ($('#name').val().trim().length == 0) {
			alert('이름 입력하기!!!');
			return;
		} else if ($('#id').val().trim().length == 0) {
			alert('아이디 입력하기!!!');
			return;
		} else if ($('#pass').val().trim().length == 0) {
			alert('비밀번호 입력하기!!!');
			return;
		} else if ($('#pass').val().trim().length != $('#passcheck').val().trim().length) {
			alert('비밀번호 확인!!!');
			return;
		} else if (cnt != 0) {
			alert('아이디 중복 확인!!!');
			return;
		} else if ($('#tel2').val().length != 4 || $('#tel2').val().length != 4) {
			alert('전화번호 각각 4자리를 확인해주세요.');
			return;
		} else {
			$('#memberform').attr('action', '${root}/user/register.kitri').submit(); // action값 넣어주기.
		}
		
	});

});


</script>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>회원가입</h2>
		<form id="memberform" method="post" action="">
			<div class="form-group" align="left">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="이름입력">
			</div>
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="id" name="id" placeholder="4자이상 16자 이하" >
				<div id="idresult"></div>
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="pass" name="pass" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호재입력</label>
				<input type="password" class="form-control" id="passcheck" name="passcheck" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="email">이메일</label><br>
				<div id="email" class="custom-control-inline">
				<input type="text" class="form-control" id="emailid" name="emailid" placeholder="" size="25"> @
				<select class="form-control" id="emaildomain" name="emaildomain">
					<option value="naver.com">naver.com</option>
					<option value="google.com">google.com</option>
					<option value="daum.net">daum.net</option>
					<option value="nate.com">nate.com</option>
					<option value="hanmail.net">hanmail.net</option>
				</select>
				</div>
			</div>
			<div class="form-group" align="left">
				<label for="tel">전화번호</label>
				<div id="tel" class="custom-control-inline">
				<select class="form-control" id="tel1" name="tel1">
					<option value="010">010</option>
					<option value="02">02</option>
					<option value="031">031</option>
					<option value="032">032</option>
					<option value="041">041</option>
					<option value="051">051</option>
					<option value="061">061</option>
				</select> _
				<input type="text" class="form-control" id="tel2" name="tel2" placeholder="1234"> _
				<input type="text" class="form-control" id="tel3" name="tel3" placeholder="5678">
				</div>
			</div>
			<div class="form-group" align="left">
				<label for="">주소</label><br>
				<div id="addressdiv" class="custom-control-inline">
					<input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" size="7" maxlength="5" readonly="readonly">
					<!--<button type="button" class="btn btn-primary" onclick="javascript:">우편번호</button>-->
				</div>
				<input type="text" class="form-control" id="address" name="address" placeholder="" readonly="readonly">
				<input type="text" class="form-control" id="address_detail" name="addressDetail" placeholder="">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="registerBtn" >회원가입</button>
				<button type="reset" class="btn btn-warning">초기화</button>
			</div>
		</form>
	</div>
</div>
<%@ include file="/WEB-INF/views/user/member/zipsearch.jsp" %>
<%@ include file="/WEB-INF/views/template/footer.jsp" %>
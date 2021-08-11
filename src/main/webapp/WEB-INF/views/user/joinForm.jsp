<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div id="center-content">

		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>


		<div>
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
					<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
					<tr>
						<td><label for="txtId">아이디</label></td>
						<td><input id="txtId" type="text" name="id"></td>
						<td><button id="btnIdCheck" type="button">아이디체크</button></td>
					</tr>
					<tr>
						<td></td>
						<td id="tdMsg" colspan="2"></td>
					</tr>
					<tr>
						<td><label for="txtPassword">패스워드</label></td>
						<td><input id="txtPassword" type="password" name="password" value=""></td>
						<td></td>
					</tr>
					<tr>
						<td><label for="txtUserName">이름</label></td>
						<td><input id="txtUserName" type="text" name="userName" value=""></td>
						<td></td>
					</tr>
					<tr>
						<td><span>약관동의</span></td>
						<td colspan="3"><input id="chkAgree" type="checkbox" name="agree" value="y"> <label for="chkAgree">서비스 약관에 동의합니다.</label></td>
					</tr>
				</table>
				<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit">회원가입</button>
				</div>

			</form>

		</div>

		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>

	</div>

</body>

<script type="text/javascript">
	var chkchk;

	//아이디 중복 체크
	$("#btnIdCheck").on("click", function() {

		var id = $("[name=id]").val();
		console.log(id);

		$.ajax({

			url : "${pageContext.request.contextPath}/user/checkId",
			type : "post",
			// contentType : "application/json",
			data : {
				id : id
			},

			dataType : "json",
			success : function(check) {
				if (check === true) {
					$("#tdMsg").html("사용 가능합 아이디입니다");
					chkchk = true;
					console.log(chkchk);
				} else if (check === false) {
					$("#tdMsg").html("다른 아이디로 가입해 주세요");
					chkchk = false;
					console.log(chkchk); 
				} else {
					$("#tdMsg").html('관리자에게 문의');
					check = false;
					console.log(chkchk);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})

	});

	//Form 전송했을때
	$("#joinForm").on("submit", function() {
		console.log("form 전송 버튼 클릭했을때")
		console.log(chkchk);

		//Check 아이디
		if (chkchk == false) {
			alert("다른 아이디로 가입해 주세요.");
			console.log(chkchk);
			return chkchk;
		} else if (chkchk == null) {
			alert("아이디 중복을 확인해주세요.");
			console.log(chkchk);
			return false;
		}

		//ID 미입력 여부
		var id = $("#txtId").val();
		if (id.length < 1) {
			alert("아이디를 입력해주세요");
			return false;
		}

		//Password 미입력 여부
		var password = $("#txtPassword").val();
		if (password.length < 1) {
			alert("패스워드를 입력해 주세요.");
			return false;
		}

		//이름 미입력 여부
		var name = $("#txtUserName").val();
		if (name.length < 1) {
			alert("이름을 입력해 주세요");
			return false;
		}

		//약관동의 체크 여부
		var agree = $("#chkAgree").is(":checked");
		if (agree == false) {
			alert("약관에 동의해 주세요");
			return false;
		}

	});
</script>


</html>
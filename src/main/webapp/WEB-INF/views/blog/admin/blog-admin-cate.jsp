<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="/jblog/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>


		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/write">글작성</a></li>
			</ul>

			<!-- //admin-menu -->
			<div id="admin-content">

				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="cateList">
						<!-- 리스트 영역 -->
					</tbody>
				</table>

				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" name="name" value=""></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" name="desc"> <input type="hidden" name="id" value="${sessionScope.id}"></td>
					</tr>
				</table>

				<div id="btnArea">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
				</div>

			</div>
			<!-- //admin-content -->
		</div>
		<!-- //content -->


		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>

	</div>
	<!-- //wrap -->


</body>

<script type="text/javascript">
	//LIST 가져오기
	$(document).ready(function (){
		console.log("화면 로딩 직전")
		
		//ajax 리스트 불러오기
		fetchList();
		
	})

	//CATEGORY INSERT
	$("#btnAddCate").on("click", function() {
		
		var cateName = $("[name = 'name']").val()
		var description = $("[name = 'desc']").val()
		
		//Testing
		console.log(cateName);
		console.log(description);

		$.ajax({
			url : "${pageContext.request.contextPath }/admin/categoryInsert",
			type : "post",
			//contentType : "application/json",
			data : {
				cateName : cateName,
				description : description
			},
			//dataType : "json",
			success : function(categoryVo) {
				console.log("업데이트 완료");
				console.log(categoryVo);

				render(categoryVo, "up")
				
				//Clear
				$("[name = 'name']").val("")
				$("[name = 'desc']").val("")
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}

		})

	});
	
	//CATEGORY DELETE
	$("#cateList").on("click", ".btnCateDel", function(){
		console.log("삭제 버튼")
		
		var no = $(this).data("no")
		console.log(no)
		
		$.ajax({
			url : "${pageContext.request.contextPath }/admin/categoryDelete",
			type : "post",
			//contentType : "application/json",
			data : {
				cateNo : no,
			},
			
			//dataType : "json",
			success : function(check) {
				console.log(check + "삭제");
				
				if(check === true) {
					$("#t-" + no).remove();
				} else if (check === false) {
					alert("삭제할 수 없습니다.")
				} else {
					alert("관리자에게 문의해주세요.")
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
	})
	
	
	
	/////////////////////////////* METHOD */////////////////////////////
	//리스트 가져오기
	function fetchList(){
		
		$.ajax({

			url : "${pageContext.request.contextPath }/admin/categoryList",
			type : "post",
			//contentType : "application/json",
			//data : {	},

			//dataType : "json",
			success : function(categoryList) {
				
				console.log(categoryList)

				//화면에 그리기
				for (var i = 0; i < categoryList.length; i++) {

					render(categoryList[i], "down");

				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}

		});
		
	}
	
	//Render (Insert 후)
	function render(categoryList, type) {
		
		var str = '';
		str += '<tr id="t-' + categoryList.cateNo + '">';
		str += '	<td>' + categoryList.rn + '</td>';
		str += '	<td>' + categoryList.cateName + '</td>';
		str += '	<td>' + categoryList.postNo+ '</td>';
		str += '	<td>' + categoryList.description + '</td>';
		str += '	<td class="text-center"><img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg" data-no="'+ categoryList.cateNo +'"></td>';
		str += '</tr>';
		
		if (type === 'down'){
			$("#cateList").append(str);
		} else if (type === 'up'){
			$("#cateList").prepend(str);
		} else {
			alert("관리자에게 문의")
		}
		
	}	
	
</script>


</html>
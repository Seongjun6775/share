<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="date" value="<%=new Random().nextInt()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../include/stylescript.jsp" />
<script type="text/javascript">
	//장르검색 팝업
	var gnr;
	
	var ppl;
	function addGnrFn(data){
		var gnrItems = $("#addGnrBtn").closest(".create-group").find(".items");
		if(gnrItems.find("." + data.gnrid).length > 0){
			gnr.alert(data.gnrnm + "은(는) 이미 추가된 장르입니다.");
			return;
		}
		var len = $("#create_form").find(".gnr-item").length;
		var itemDiv = $("<div class='gnr-item "+data.gnrid+"'></div>");
		/* itemDiv.addClass(data.gnrid); */
		 
		var itemId = $("<input type='hidden' name='gnrList["+len+"].gnrId'/>");
		itemId.val(data.gnrid);
		itemDiv.append(itemId);
		
		var itemSpan = $("<span></span>");
		itemSpan.text(data.gnrnm);
		itemDiv.append(itemSpan);
		
		var itemRemoveBtn = $("<button>X</button>");
		itemRemoveBtn.click(function(){
			gnr.alert("!");
			$(this).closest("." + data.gnrid).remove();
		});
		itemDiv.append(itemRemoveBtn);
		gnrItems.append(itemDiv);
	}
	
	
	function addMvPplFn(data){
		console.log(data);
		var pplItems = $("#" + data.id).closest(".create-group").find(".items");
		if(pplItems.find("." + data.mvpplid).length > 0){
			ppl.alert(data.nm + "은(는) 이미 추가된 영화인입니다.");
			return;
		}
		var len = $("#create_form").find(".mvppl-item").length;
		var itemDiv = $("<div class='mvppl-item "+data.mvpplid+"'></div>");
		/* itemDiv.addClass(data.gnrid); */
		 
		var itemId = $("<input type='hidden' name='pplList["+len+"].mvPplId'/>");
		itemId.val(data.mvpplid);
		itemDiv.append(itemId);
		
		var mssnId = "";
		if(data.id == "addDirectorBtn"){
			mssnId = "DRCTR"; // 감독
		}else if (data.id =="addScripterBtn"){
			mssnId = "SCRPTR";
		}else if (data.id =="addProducerBtn"){
			mssnId = "PRDCR";	
		}else if (data.id =="addMainActorBtn"){
			mssnId = "MNACTR";			
		}else if (data.id =="addSubActorBtn"){
			mssnId = "SPRTACTR";
		}else if (data.id =="addExtraBtn"){
			mssnId = "EXTR";
		}
		
		var mssn = $("<input type='hidden' name='pplList["+len+"].mssn' placeholder='임무'/>");
		mssn.val(mssnId);
		itemDiv.append(mssn);
		
		
		var rspnsbltRolNm = $("<input type='text' name='pplList["+len+"].rspnsbltRolNm' placeholder='역할명'/>");
		rspnsbltRolNm.val();
		itemDiv.append(rspnsbltRolNm);
		
		var itemSpan = $("<span></span>");
		itemSpan.text(data.nm);
		itemDiv.append(itemSpan);
		
		var itemRemoveBtn = $("<button>X</button>");
		itemRemoveBtn.click(function(){
			ppl.alert("!");
			$(this).closest("." + data.mvpplid).remove();
		});
		itemDiv.append(itemRemoveBtn);
		pplItems.append(itemDiv);
	}
	$().ready(function() {
		var ajaxUtil = AjaxUtil();
		
		$("#addGnrBtn").click(function(event){
			event.preventDefault();
			gnr = window.open("${context}/gnr/search", "장르검색", "width=500, height=500");
		});
		$("#addDirectorBtn, #addScripterBtn, #addProducerBtn, #addMainActorBtn, #addSubActorBtn, #addExtraBtn").click(function(event){
			event.preventDefault();
			//ppl = window.open("${context}/mvppl/search", "영화인", "width=500, height=500");
			ppl = window.open("${context}/mvppl/search?targetId="+$(this).attr("id"), "영화인", "width=500, height=500");
			var that = this;
			// /api/mvppl/search 화면이 브라우저에 모두 로딩이 되었을 때 ==> 렌더링이 끝났을 때
			ppl.onload=function(){
				ppl.targetId = $(that).attr("id");
				ppl.targetNm = $(that).closest("label").val();
			}
		});
		
		$("#new_btn").click(function(){
			var ajaxUtil = new AjaxUtil
			ajaxUtil.upload("#create_form", "${context}/api/mv/create", function(response){
				if(response.status == "200 OK"){
					location.href = "${context}" + response.redirectURL;
				}
				else if(response.errorCode == "500"){
					alert(response.message)
				}
				else{
					alert("영화 등록에 실패하였습니다.");
				}
			},{"pstr":"uploadFile"});
		});
		$("#previewPstr").click(function(){
			$("#pstr").click();
		});
		$("#pstr").change(function(){
			var file = $(this)[0].files;
			if(file.length > 0){
				var fileReader = new FileReader();
				fileReader.onload = function(data){
					// FileReader 객체가 로드가 완료됐을 때
					$("#previewPstr").attr("src", data.target.result);
				}
				fileReader.readAsDataURL(file[0]);
				//$("#isDeletePctr").val("Y");
			}
			else{
				//기본 이미지로 변경
				$("#pstr").val("");
				$("#previewPstr").attr("src", "${context}/img/baseProfile.png");
				//$("#isDeletePctr").val("Y");
			}
		});
		$("#del_pstr").click(function(event){
			event.preventDefault();
			$("#pstr").val("");
			$("#previewPstr").attr("src", "${context}/img/baseProfile.png");
		})
	});
</script>
</head>
<body>
	<div class="main-layout">
		<jsp:include page="../include/header.jsp" />
		<div>
			<jsp:include page="../include/mvMgmtSidemenu.jsp" />
			<jsp:include page="../include/content.jsp" />

			<div class="path">영화 > 영화 관리 > 등록</div>

			<h1>영화 정보 등록</h1>
			<div>
				<form id ="create_form" enctype="multipart/form-data">
					<div class="create-group">
						<div style="position: relative;">
						<label for="pstr">포스터</label> <img class="profile" src="${context}/img/baseProfile.png" id="previewPstr" />
						<button id="del_pstr" style="position: absolute; right:10px; bottom:10px;">X</button> 
						<input type="file" name="pstr" id="pstr"  />
						<input type="hidden" id="isDeletePstr" name="isDeletePstr" value="N"/>
						</div>
					</div>
					<div class="create-group">
						<label for="mvTtl">영화제목</label> <input type="text" name="mvTtl"
							id="mvTtl" />
					</div>
					<div class="create-group">
						<label for="engTtl">영화제목(영어)</label> <input type="text"
							name="engTtl" id="engTtl" />
					</div>
					<div class="create-group">
						<label for="scrnStt">상영상태</label> <select id="scrnStt"
							name="scrnStt">
							<option value="상영중">상영중</option>
							<option value="상영예정">상영예정</option>
							<option value="상영종료">상영종료</option>
						</select>
					</div>
					<div class="create-group">
						<label for="scrnTm">상영시간(분)</label> <input type="number"
							name="scrnTm" id="scrnTm" value="0" />
					</div>
					<div class="create-group">
						<label for="opngDt">개봉일</label> <input type="date" name="opngDt"
							id="opngDt" />
					</div>
					<div class="create-group">
						<label for="grd">관람등급</label> <select id="grd" name="grd">
							<option>선택</option>
							<option value="전체관람가">전체관람가</option>
							<option value="7">7세 이상 관람가</option>
							<option value="12">12세 이상 관람가</option>
							<option value="15">15세 이상 전체관람가</option>
							<option value="19">청소년 관람불가</option>
						</select>
					</div>
					<div class="create-group">
						<label for="smr">줄거리</label> 
						<textarea id="smr" name="smr"></textarea>
					</div>
					<div class="create-group">
						<label for="useYn">개시여부</label> 
						<input type="checkbox" name="useYn" id="useYn" value="Y" checked="checked">
					</div>
					<div class="create-group">
						<label for="addGnrBtn">장르</label>
						<div>
							<button id="addGnrBtn" class="btn-primary">등록</button>
							<div class="items">
							</div>
						</div>
					</div>
					<div class="create-group">
						<label for="addDirectorBtn">감독</label>
						<div>
							<button id="addDirectorBtn" class="btn-primary">등록</button>
							<div class="items">
						</div> 
						</div>
					</div>
					<div class="create-group">
						<label for="addScripterBtn">각본</label> 
						<div>
							<button id="addScripterBtn" class="btn-primary">등록</button>
							<div class="items">
						</div>
						</div>
					</div>
					<div class="create-group">
						<label for="addProducerBtn">연출</label>
						<div>
							<button id="addProducerBtn" class="btn-primary">등록</button> 
							<div class="items">
						</div>
						</div>
					</div>
					<div class="create-group">
						<label for="addMainActorBtn">주연</label> 
						<div>
							<button id="addMainActorBtn" class="btn-primary">등록</button>
							<div class="items">
						</div>
						</div>
					</div>
					<div class="create-group">
						<label for="addSubActorBtn">조연</label> 
						<div>
							<button id="addSubActorBtn" class="btn-primary">등록</button>
							<div class="items">
						</div>
						</div>
					</div>
					<div class="create-group">
						<label for="addExtraBtn">기타</label> 
						<div>
							<button id="addExtraBtn" class="btn-primary">등록</button>
							<div class="items">
						</div>
						</div>
					</div>
				</form>
			</div>
			<div class="align-right">
				<button id="new_btn" class="btn-primary">등록</button>
				<button id="delete_btn" class="btn-delete">삭제</button>
			</div>
			<jsp:include page="../include/footer.jsp" />
		</div>
	</div>
</body>
</html>
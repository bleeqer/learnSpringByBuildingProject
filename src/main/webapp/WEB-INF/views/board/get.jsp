<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Read Post</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">Post View</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

          <div class="form-group">
          <label>Bno</label> 
          <input class="form-control" name='bno' value='<c:out value="${board.bno }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>Title</label> 
          <input class="form-control" name='title' value='<c:out value="${board.title }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>Text area</label>
          <textarea class="form-control" rows="3" name='content' readonly="readonly"><c:out value="${board.content}" />
          </textarea>
        </div>

        <div class="form-group">
          <label>Writer</label> 
          <input class="form-control" name='writer' value='<c:out value="${board.writer }"/>' readonly="readonly">
        </div>
		
		<!-- 추후 다양한 상황을 처리하기 위해 form 태그와 자바스크립트로 구현 -->
 		<button data-oper='modify' class="btn btn-default">Modify</button>
        <button data-oper='list' class="btn btn-info">>List</button>
        
        <!-- 필요한 데이터 전달 목적 -->
        <form id='operForm' action="/board/modify" method="get">
        	<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
        	<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
        	<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
        	<input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
        	<input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
        </form>


      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"><</i> Reply
				<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
			</div>
			
			
			<div class="panel-body">
				<ul class="comment">
<!-- 					<li class="left clearfix" data-rno='12'>
						<div>
							<div class="header">
								<Strong class="primary-font"></Strong>
								<small class="pull-right text-muted">2018-01-01 13:13</small>
							</div>
							<p>Good Job!</p>
						</div> -->
				</ul>
			</div>
			
			<div class="panel-footer">
				
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	    <div class="modal-content">
	        <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
	        </div>
	        <div class="modal-body">
	        	<div class="form-group">
	        		<label>Reply</label>
	        		<input class="form-control" name='reply' value='New Reply!'>
	        	</div>
	        	<div class="form-group">
	        		<label>Replyer</label>
	        		<input class="form-control" name='replyer' value='replyer'>
	        	</div>
	        	<div class="form-group">
	        		<label>Reply Date</label>
	        		<input class="form-control" name='replyDate' value=''>
	        	</div>
	        </div>
	        <div class="modal-footer">
	            <button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
	            <button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
	            <button id='modalCreateBtn' type="button" class="btn btn-primary">Create</button>
	            <button id='modalCloseBtn' type="button" class="btn btn-default" data-dismiss='modal'>Close</button>
	        </div>
	    </div>
	    <!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.Modal -->

<script type="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">
$(document).ready(function() {


var bnoValue = '<c:out value="${board.bno}"/>'
var replyUL = $(".comment")

showList(1)

function showList(page) {
	
	replyService.getList({bno:bnoValue, page: page || 1},
			
			function(replyCnt, list) {
				
				// page parameter에 -1 전달 시 마지막 페이지를 argument로 전달 하면서 showList 다시 호출
				if (page == -1) {
					pageNum = Math.ceil(replyCnt/10.0)
					showList(pageNum)
					return
				}
				
				var str=""
				
				if(list == null || list.length == 0) {
					return
				}
				
				for (var i = 0, len = list.length || 0; i < len; i++) {
					str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>"
					str += "	<div><div class='header'><strong class='primary-font'>[" + list[i].rno + "] " + list[i].replyer + "</strong>"
					str += "		<small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate)+ "</small></div>"
					str += "			<p>" + list[i].reply + "</p></div></li>"
				}
				
				replyUL.html(str)
				
				console.log("여기까지오나요?", pageNum)
				showReplyPage(replyCnt)
			}
	)
	

}

var pageNum = 1
var replyPageFooter = $(".panel-footer")

function showReplyPage(replyCnt) {
	
	// 나열된 페이지 번호의 마지막 넘버 구하기
	var endNum = Math.ceil(pageNum / 10.0) * 10
	
	// 마지막 넘버를 기준으로 첫번째 넘버 구하기
	var startNum = endNum - 9
	
	// 첫 페이지가 1이 아닐 때 (1보다 클 때) true 반환
	var prev = startNum != 1
	var next = false
	
	// 마지막 페이지 넘버에 10을 곱했을 때(한페이지에 10개씩 나타내므로) 실제 총 댓글수보다 크다면
	if (endNum * 10 >= replyCnt) {
		// 마지막 넘버를 총 댓글 갯수에 맞춤
		endNum = Math.ceil( replyCnt / 10.0 )
	}
	
	// 마지막 페이지 넘버가 (10단위) 총 댓글 수보다 작을 경우 next 값 true
	if (endNum * 10 < replyCnt) {
		next = true
	}
	
	var str = "<ul class='pagination pull-right'>"
	
	// 페이지네이션의 첫번째 페이지가 1보다 클 때
	if (prev) {
		str += "<li class='page-item'><a class='page-link' href='" + (startNum -1) + "'>Previous</a></li>"
	}
	
	for (var i = startNum; i <= endNum; i++) {
		
		// 페이지네이션을 그리다가 현재페이지넘버와 일치하면 active 속성
		var active = pageNum == i ? "active" : ""
		
		str += "<li class='page-item  " + active + "'><a class='page-link' href='" + i + "'>" + i + "</a></li>"
	}
	
	if (next) {

		str += "<li class='page-item'><a class='page-link' href='" + (endNum + 1) + "'>Next</a></li>"
	}
	
	str += "</ul></div>"
	

	
	replyPageFooter.html(str)
}

// 페이지 넘버 클릭 시
replyPageFooter.on("click", "li a", function(e) {
	// 기본 기능 막기
	e.preventDefault()
	

	
	// 페이지 넘버 앵커의 href 속성 가져오기
	var targetPageNum = $(this).attr("href")
	
	pageNum = targetPageNum
	console.log("페이지 클릭으로 페이지 넘버 바뀜 ", pageNum)
	

	
	// 페이지 넘버 앵커의 href 속성값을 showList의 argument로 전달
	showList(pageNum)
})
console.log("아아아!!!", pageNum)
	
	var modal = $(".modal")
	var modalInputReply = modal.find("input[name='reply']")
	var modalInputReplyer = modal.find("input[name='replyer']")
	var modalInputReplyDate = modal.find("input[name='replyDate']")
	
	var modalModBtn = $("#modalModBtn")
	var modalRemoveBtn = $("#modalRemoveBtn")
	var modalCreateBtn = $("#modalCreateBtn")
	/* var modalCloseBtn = $()"#modalCloseBtn") */
	
	$("#addReplyBtn").on("click", function(e) {
		modal.find("input").val("") // input 비우기
		modalInputReplyDate.closest("div").hide() // 해당 요소에서 가장 가까운 div 숨기기 (replyDate div 숨기기)
		modal.find("button[id !='modalCloseBtn']").hide() // 닫기 버튼을 제외한 버튼 숨기기 (수정, 삭제, 생성버튼)
		
		modalCreateBtn.show()
		
		$(".modal").modal("show")
	})
	
	modalCreateBtn.on("click", function(e) {
		var reply = {
				reply: modalInputReply.val(),
				replyer: modalInputReplyer.val(),
				bno: bnoValue
		}
		
		replyService.add(reply, function(result) {
			
			alert(result)
			
			modal.find("input").val("")
			modal.modal("hide")
			
			// showList(1)
			showList(-1) // 새 댓글 등록 시 댓글 맨 마지막 페이지로 이동
		})
	})
	
	modalModBtn.on("click", function(e) {
		
		var reply = {rno: modal.data("rno"), reply: modalInputReply.val()}
		
		replyService.update(reply, function(result) {
			
			alert(result)
			modal.modal("hide")

			// 페이지넘버를 클릭할 때마다 pageNum에 페이지넘버가 할당되므로 보고 있던 리스트의 페이지를 다시 불러올 수 있게 pageNum을 argument로 전달하며 리스트 다시 호출
			showList(pageNum)
		})
	})
	
	modalRemoveBtn.on("click", function(e) {
		
		var rno = modal.data("rno")
		
		replyService.remove(rno, function(result) {
			
			alert(result)
			modal.modal("hide")
			showList(pageNum)
		})
	})
	
	// comment를 이용해 이벤트를 처리하고 나중에 생성되는 li를 파라미터로 지정해서 실제 이벤트 대상으로 함
	$(".comment").on("click", "li", function(e) {
		
		var rno = $(this).data("rno")
		
		replyService.get(rno, function(reply) {
			modalInputReply.val(reply.reply)
			modalInputReplyer.val(reply.replyer)
			modalInputReplyDate.val(replyService.displayTime(reply.replyDate))
			.attr("readonly", "readonly") // modal - input의 attribute에 readonly 추가
			
			modal.data("rno", reply.rno)
			
			modal.find("button[id != 'modalCloseBtn']").hide()
			modalModBtn.show()
			modalRemoveBtn.show()
			
			$(".modal").modal("show")
		})
	})
})

</script>

<script type="text/javascript">
$(document).ready(function() {
  
  var operForm = $("#operForm"); 
  
  $("button[data-oper='modify']").on("click", function(e){
    
    operForm.attr("action","/board/modify").submit();
    
  });
  
    
  $("button[data-oper='list']").on("click", function(e){
    
	/* 데이터가 필요 없으므로 bno 삭제하고 이동 */
    operForm.find("#bno").remove();
    operForm.attr("action","/board/list")
    operForm.submit();
    
  });  
});
</script>


<%@include file="../includes/footer.jsp"%>

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
        </form>


      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

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

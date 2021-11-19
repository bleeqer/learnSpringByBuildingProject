<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		<%@include file="../includes/header.jsp" %>
		
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${list}" var="board">
                                <tr>
                                	<td><c:out value="${board.bno}"/></td>
                                	<td>
                                		<a href='/board/get?bno=<c:out value="${board.bno}"/>'><c:out value="${board.title}"/></a>
                                	</td>
                                	<td><c:out value="${board.writer}"/></td>
                                	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/></td>
                                	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}"/></td>
                                </tr>
                                </c:forEach>

                            </table>
                            <!-- /.table-responsive -->
                        </div>    
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
        
        <script>
        	$(document).ready(function() {
        		
        		var result = '<c:out value="${result}"/>';
        		
        		checkModal(result);
        		
        		history.replaceState({}, null, null);
        		
        		function checkModal(result) {
        			
        			if (result ==='' || history.state) {
        				return;
        			}
        			
        			if (parseInt(result) > 0) {
        				$(".modal-body").html(
        						"게시글 " + parseInt(result) + " 번이 등록 되었습니다.");		
        			}
        			$("#myModal").modal("show");
        		}
        		
        		$("#regBtn").on("click", function(){
        			self.location="/board/create";
        		});
        	});
        </script>
        
        <%@include file="../includes/footer.jsp" %>

    
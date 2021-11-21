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
                            <table width="100%" class="table table-striped table-bordered table-hover">
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
                                		<a class='move' href='<c:out value="${board.bno}"/>'><c:out value="${board.title}"/></a>
                                	</td>
                                	<td><c:out value="${board.writer}"/></td>
                                	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/></td>
                                	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}"/></td>
                                </tr>
                                </c:forEach>
		
                            </table>
                            
            <div class='row'>
            	<div class="col-lg-12">
            		<form id='searchForm' action="/board/list" method='get'>
						<select name='type'>
							<option value=""
							<c:out value="${pageMaker.cri.type == null ? 'selected' : ''}"/>>--</option>
							<option value="T"
							<c:out value="${pageMaker.cri.type eq 'T' ? 'selected' : ''}"/>>제목</option>
							<option value="C"
							<c:out value="${pageMaker.cri.type eq 'C' ? 'selected' : ''}"/>>내용</option>
							<option value="W"
							<c:out value="${pageMaker.cri.type eq 'W' ? 'selected' : ''}"/>>작성자</option>
							<option value="TC"
							<c:out value="${pageMaker.cri.type eq 'TC' ? 'selected' : ''}"/>>제목 or 내용</option>
							<option value="TW"
							<c:out value="${pageMaker.cri.type eq 'TW' ? 'selected' : ''}"/>>제목 or 작성자</option>
							<option value="TWC"
							<c:out value="${pageMaker.cri.type eq 'TWC' ? 'selected' : ''}"/>>제목 or 내용 or 작성자</option>
						</select> 
							<input type='text' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>' /> 
							<input type='hidden' name='pageNum' value='<c:out value="${pageMaker.cri.pageNum}"/>' /> 
							<input type='hidden' name='amount' value='<c:out value="${pageMaker.cri.amount}"/>' />
							<button class='btn btn-default'>Search</button>
					</form>
				</div>
			</div>
                            
                            <div class="pull-right">
                            	<ul class="pagination">
                            		
                            		<c:if test="${pageMaker.prev}">
                            			<li class="paginate_button previous">
                            				<a href="${pageMaker.startPage -1}">Previous</a>
                            			</li>
                            		</c:if>
                            		
                            		<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                            			<li class="paginate_button ${pageMaker.cri.pageNum == num ? "active":""} ">
                            				<a href="${num}">${num}</a>
                            			</li>
                            		</c:forEach>
                            		
                            		<c:if test="${pageMaker.next}">
                            			<li class="paginate_button next">
                            				<a href="${pageMaker.endPage +1}">Next</a>
                            			</li>
                            		</c:if>
                            	</ul>
                            	
                            	<form id='actionForm' action="/board/list" method='get'>
                            		<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
                            		<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
                            		<input type='hidden' name='type' value='${pageMaker.cri.type}'>
                            		<input type='hidden' name='keyword' value='${pageMaker.cri.keyword}'>
                            	</form>
                            </div>
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
        		
        		var actionForm = $("#actionForm");
        		
        		$(".paginate_button a").on("click", function(e) {
        			
        			e.preventDefault();
        			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
        			actionForm.submit();
        		});
				
				$(".move").on("click", function(e) {
					
					e.preventDefault();
					// 제목 클릭 시 actionForm에 input 태그 추가하여 bno(a 링크의 href값) 저장
					actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
					
					// action에 조회화면 경로로 바꿈
					actionForm.attr("action", "/board/get");
					
					actionForm.submit();
				})
				
				var searchForm = $("#searchForm");
				
				$("#searchForm button").on("click", function(e) {
					
					if(!searchForm.find("option:selected").val()) {
						alert("검색 종류를 선택하세요");
						return false;
					}
					
					if(!searchForm.find("input[name='keyword']").val()) {
						alert("키워드를 선택하세요");
						return false;
					}
					
					searchForm.find("input[name='pageNum']").val("1");
					e.preventDefault();
					
					searchForm.submit();
        		})
        	});
        </script>
        
        <%@include file="../includes/footer.jsp" %>

    
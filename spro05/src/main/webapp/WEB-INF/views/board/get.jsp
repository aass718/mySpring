<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 상세보기</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Basic Form Elements</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form action="/board/register" method="post">
							<div class="form-group">
								<label>글번호</label> <input class="form-control" name="title"
									value='${board.bno}' readonly>
								<p class="help-block"></p>
							</div>
							<div class="form-group">
								<label>제목</label> <input class="form-control" name="title"
									value='${board.title}' readonly>
								<p class="help-block"></p>
							</div>
							<div class="form-group">
								<label>글쓴이</label> <input class="form-control" name="writer"
									value='${board.writer}' readonly>
							</div>
							<div class="form-group">
								<label>내용</label>
								<textarea class="form-control" name="content" readonly>${board.content}</textarea>
							</div>
							<button type="button" data-oper="modify" class="btn btn-danger"
								onclick="location.href='/board/modify?bno=${board.bno}'">수정</button>
							<button type="button" data-oper="list" class="btn btn-info"
								onclick="location.href='/board/list'">목록</button>
						</form>
					</div>

				</div>
				<!-- /.row (nested) -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">댓글 등록</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>댓글 내용</label> <input class="form-control" name="reply"
						value="=New Reply!!">
				</div>
				<div class="form-group">
					<label>작성자</label> <input class="form-control" name="replyer"
						value="=replyer">
				</div>
				<div class="form-group">
					<label>작성일자</label> <input class="form-control" name="replyDate"
						value="=replyDate">
				</div>
			</div>
			<div class="modal-footer">
				<button id="modalModBtn" type="button" class="btn btn-warning">수정</button>
				<button id="modalRemoveBtn" type="button" class="btn btn-danger">삭제</button>
				<button id="modalRegisterBtn" type="button" class="btn btn-default">저장</button>
				<button id="modalCloseBtn" type="button" class="btn btn-primary"
					data-dismiss="modal">닫기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments a-fw"></i>댓글
				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">댓글추가</button>
			</div>
			<div class="pannel-body">
				<ul class="chat">
					<li class="left clearfix" data-rno="10">
						<div class="chat-body">
							<div class="header">
								<strong class="primary-font">user00</strong> <small
									class="pull-right text-muted ">2023-09-19 10:06 </small>
							</div>
							<p>댓글 내용이 들어감</p>
						</div>
					</li>
				</ul>
				<div class="panel-footer"></div>
			</div>
			<!-- end reply -->
		</div>
	</div>
</div>
<!-- /.row -->


<script type="text/javascript" src="/resources/js/reply.js"></script>

<script>
	$(document)
			.ready(
					function() {
						const bnoValue = '<c:out value="${board.bno}"/>';
						const replyUL = $(".chat");

						showList(1);
						function showList(page) {
							replyService.getList({bno : bnoValue,page : page || 1},
											function(replyCnt, list) {
												console.log("replyCnt===============",replyCnt);
												console.log("list=================",list);
												if (page == -1) {
													pageNum = Math.ceil(replyCnt / 10.0);
													//총 페이지
													showList(pageNum);
													return;
												}
												let str = "";
												if (list == null
														|| list.length == 0) {
													replyUL.html("");
													return;
												}
												for (let i = 0, len = list.length || 0; i < len; i++) {
													str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>";
													str += "<div><div class='header'><strong class='primary-font'>"
															+ list[i].replyer
															+ "</strong>";
													str += " <small class='pull-right text-muted'>"
															+ replyService
																	.displayTime(list[i].replyDate)
															+ "</small></div>";
													str += "<p>"
															+ list[i].reply
															+ "</p></div></li>";
												}
												replyUL.html(str);
												showReplyPage(replyCnt); 

											}); //end function 

						}//end showList

						let pageNum = 1;
						let replyPageFooter = $(".panel-footer");

						//페이지 호출을 할 때 pageNum 위에서 하게 될 경우 순서가 꼬이니까 주의. 물론 테스트를 위한 호출임.
						/* showReplyPage(130); */

						//댓글 페이지 함수
						function showReplyPage(replyCnt) {
							//페이지 끝 번호 
							let endNum = Math.ceil(pageNum / 10.0) * 10;
							//페이지 시작 번호
							let startNum = endNum - 9;
							const prev = startNum != 1;
							let next = false;

							if (endNum * 10 >= replyCnt) {
								endNum = Math.ceil(replyCnt / 10.0);
							}
							if (endNum * 10 < replyCnt) {
								next = true;
							}

							let str = "<div class='container text-center'><ul class='pagination pull-right'>";

							if (prev) {
								str += "<li class=:'page-item'><a class='page-link' href='"
										+ (startNum - 1) + "'>이전</a></li>";
							}
							for (let i = startNum; i <= endNum; i++) {
								const active = pageNum == i ? "active" : "";
								str += "<li class = 'page-item " 
											+ active + "'><a class='page-link' href='"
											+i+"'>"
										+ i + "</a></li>"
							}
							if (next) {
								str += "<li class='page-item'><a class='page-link' href='"
										+ (endNum + 1) + "'>다음</a></li>";
							}

							str += "</ul></div>";
							console.log(str);
							replyPageFooter.html(str);

						}
						
						// replyPageFooter를 클릭할 때 li a에 함수 전달. 
						//댓글 reply 테
						replyPageFooter.on("click","li a",function(e){
							e.preventDefault();
							console.log(".................PageNum Click");
							const targetPage = $(this).attr("href");
							console.log("targetpage............."+targetPage);
							
							pageNum = targetPage;
							showList(pageNum);
						})

						//댓글 모달 처리
						const modal = $("#myModal");
						const modalInputReply = modal
								.find("input[name='reply']");
						const modalInputReplyer = modal
								.find("input[name='replyer']");
						const modalInputReplyDate = modal
								.find("input[name='replyDate']");

						const modalModBtn = $("#modalModBtn");
						const modalRemoveBtn = $("#modalRemoveBtn");
						const modalRegisterBtn = $("#modalRegisterBtn");

						$("#addReplyBtn").on("click", function(e) {
							modal.find("input").val("");
							modalInputReplyDate.closest("div").hide();
							modal.find("button[id !='modalCloseBtn']").hide();
							modalRegisterBtn.show();
							$(".modal").modal("show");
						});

						$(".chat").on("click","li",
								function(e) {
											const rno = $(this).data("rno");
											console.log("chat ............rno :   "+ rno);
											replyService.get(rno,function(reply) {
																modalInputReply.val(reply.reply);
																modalInputReplyer.val(reply.replyer);
																modalInputReplyDate.val(
																				replyService.displayTime(reply.replyDate))
																		.attr("readonly","readonly");
																modal.data("rno",reply.rno);
																modal.find("button[id !='modalCloseBtn']").hide();
																modalInputReplyDate.closest("div").show();
																modalModBtn.show();
																modalRemoveBtn.show();

																$(".modal").modal("show");
															})
										})

						modalRegisterBtn.on("click", function(e) {
							const reply = {
								reply : modalInputReply.val(),
								replyer : modalInputReplyer.val(),
								bno : bnoValue
							}

							replyService.add(reply, function(result) {
								alert(result);
							})
							modal.find("input").val("");
							modal.modal("hide");
							showList(-1);
						})

						modalModBtn.on("click", function(e) {
							const reply = {
								rno : modal.data("rno"),
								reply : modalInputReply.val()
							};

							replyService.update(reply, function(result) {
								alert(result);
								modal.modal("hide");
								showList(1);
							})
						})
						modalRemoveBtn.on("click", function(e) {
							const rno = modal.data('rno');

							replyService.remove(rno, function(result) {
								alert(result);
								modal.modal("hide");
								showList(1);
							})
						})

					});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		console.log("=============================");
		console.log("========== JS TEST ==========");
		const bnoValue = '<c:out value="${board.bno}"/>';

		/* 	replyService.add(
		 {reply: 'JS TEST',
		 replyer: 'tester',
		 bno: bnoValue},
		 function(result){
		 alert("TESULT : "+result)
		 }
		 );
		
		 replyService.getList({bno:bnoValue, page:1},
		 function(list){
		 for(let i=0, len=list.length|| 0; i<len; i++){
		 console.log(list[i])
		 }
		 });
		 */

		/* 
		replyService.remove(73, 
			function(count){
			console.log("..remove count : ", count);
			if(count === "success"){
				alert("removed.........");
			}}, function(err){
				alert("ERROR!!!!");
			});
		
		replyService.update({
			rno: 47,
			bno: bnoValue,
			reply:"update from get.jsp"
		},function(result){
			alert("수정 완료!");
		});  */

		replyService.get(61, function(data) {
			console.log("...... get data : ", data);
		});

		console.log(replyService);

	})
</script>
<%@include file="../includes/footer.jsp"%>
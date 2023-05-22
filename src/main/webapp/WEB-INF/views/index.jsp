<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quartz Admin</title>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- jQuary -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script type="text/javascript" src="../js/schedule.js"></script>
</head>
<body>
	<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
	  <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="/job">Quartz Admin</a>
	  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	</header>

	<div class="container-fluid">
	  <div class="row" style="width:1400px; margin:0px auto;">
	    <main class="pt-5">
	      <h2 class="mb-4">Quart Scheduler</h2>
	      <a class="btn btn-primary mb-3" href="job/addJob">Job 추가</a>
	      <div class="table-responsive d-flex flex-wrap pt-3">
	        <table id="tableJob" class="table table-hover">
	          <thead>
	            <tr style="text-align: center;">
	              <th scope="col">#</th>
	              <th scope="col">TriggerName</th>
	              <th scope="col">TriggerGroup</th>
	              <th scope="col">CronExpression</th>
	              <th scope="col">JobName</th>
	              <th scope="col">JobGroup</th>
	              <th scope="col">CreateTime</th>
	              <th scope="col">Running</th>
	              <th scope="col"></th>
	            </tr>
	          </thead>
	          <tbody>
	          	<c:forEach var="list" items="${list}" varStatus="status">
		            <tr style="text-align: center;">
		              <td>${list.id}</td>
		              <td>${list.triggerName}</td>
		              <td>${list.triggerGroup}</td>
		              <td>${list.cronExpression}</td>
		              <td>${list.jobName}</td>
		              <td>${list.jobGroup}</td>
		              <td>${list.createTime}</td>
		              <td>
		              	<c:if test="${list.isRunning eq 'Y'}">
			              	실행 중
		              	</c:if>
		              	<c:if test="${list.isRunning eq 'N'}">
			              	정지
		              	</c:if>
	              	</td>
		              <td>
		              	<!-- ${status.current} -->
		              	<c:if test="${list.isRunning eq 'N'}">
			              	<button type="button" class="btn btn-outline-primary btnResumeJob">실행</button>
		              	</c:if>
		              	<c:if test="${list.isRunning eq 'Y'}">
			              	<button type="button" class="btn btn-outline-warning btnPauseJob">중지</button>
		              	</c:if>
		              	<button type="button" class="btn btn-outline-secondary btnRescheduleModal">수정</button>
		              	<button type="button" class="btn btn-outline-danger btnDeleteJob">삭제</button>
		              </td>
		            </tr>
	          	</c:forEach>
	          </tbody>
	        </table>
	      </div>
	    </main>
	  </div>
	</div>
	
	<!-- RescheduleJob Modal -->
	<div class="modal fade" id="modalReschedule" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header p-5 py-4 border-bottom-0">
	        <h2 class="fw-bold p-2 mb-0" id="staticBackdropLabel">Reschedule Job</h2>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body py-5 pt-3 mx-3">
					<form id="formRescheduleJob">
					  <div class="mb-3">
					    <label for="triggerName" class="form-label">triggerName</label>
					    <input type="text" class="form-control-plaintext rounded-4 fw-bold" id="triggerName" value="">
					  </div>
					  <div class="mb-3">
					    <label for="triggerGroup" class="form-label">triggerGroup</label>
					    <input type="text" class="form-control-plaintext rounded-4 fw-bold" id="triggerGroup" value="">
					  </div>
					  <div class="mb-3">
					    <label for="NcronExpression" class="form-label">현재 cronExpression</label>
					    <input type="text" class="form-control-plaintext rounded-4 fw-bold" id="NcronExpression" value="">
					  </div>
					  <div class="mb-3">
					    <label for="cronExpression" class="form-label">변경할 cronExpression을 입력하세요.</label>
					    <input type="text" class="form-control rounded-4" name="cronExpression" id="cronExpression">
					  </div>
					  <button type="button" id="btnRescheduleJob" class="w-100 mb-2 btn btn-lg rounded-4 btn-primary">수정하기</button>
					</form>
	      </div>
	    </div>
	  </div>
	</div>
</body>
</html>
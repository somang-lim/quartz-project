<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Job</title>
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
	
	<div class="container">
	  <main class="py-5 mb-5">
	    <div class="py-5 mb-5 text-center">
	      <h1 class="h3 fw-normal">스케줄 등록</h1>
	    </div>
	
	    <div class="row g-5">
	      <div class="col-md-8 col-lg-5" style="float:none; margin:0 auto;">
	        <form id="formJob" action="addJob" method="post">						
	        	<div style="margin-left:65px;">
							<div class="mb-3 row">
						    <label for="triggerName" class="col-sm-3 col-form-label">triggerName</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="triggerName" name="triggerName">
						    </div>
						  </div>
							<div class="mb-3 row">
						    <label for="triggerGroup" class="col-sm-3 col-form-label">triggerGroup</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="triggerGroup" name="triggerGroup">
						    </div>
						  </div>
						  <div class="mb-3 row">
						    <label for="cronExpression" class="col-sm-3 col-form-label">cronExpression</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="cronExpression" name="cronExpression">
						    </div>
						  </div>
							<div class="mb-3 row">
						    <label for="jobName" class="col-sm-3 col-form-label">jobName</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="jobName" name="jobName">
						    </div>
						  </div>
						  <div class="mb-3 row">
						    <label for="jobGroup" class="col-sm-3 col-form-label">jobGroup</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="jobGroup" name="jobGroup">
						    </div>
						  </div>
					  </div>
						<button class="btn btn-primary btn-lg btn-block col-12 my-4" type="button" id="btnAddJob">입력하기</button>
					</form>
	      </div>
	    </div>
	  </main>
	</div>
</body>
</html>
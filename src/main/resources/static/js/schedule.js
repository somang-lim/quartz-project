/**
 * 
 */

$(function(){
	
	$('#btnAddJob').click(function(){
		
		$('#formJob')[0].submit();
		
	});
	

	$('.btnResumeJob').click(function(){
		const resumeJob = $(this);
		const tr = resumeJob.parent().parent();
		const td = tr.children();
		
		const triggerName = td.eq(1).text();
		const triggerGroup = td.eq(2).text();
		
		$.ajax({
			url : 'job/resumeJob/' + triggerName
			, type : 'PUT'
			, data :
				{'triggerName' : triggerName
				, 'triggerGroup' : triggerGroup}
			, success : function(){
				location.reload();
			}
		})
		.done(function(){
			alert(triggerName + '이 실행되었습니다.');
		})
		.fail(function(){
			alert(triggerName + ' 실행에 오류가 발생했습니다.');
		})
	});
	
	
	$('.btnPauseJob').click(function(){
		const pauseJob = $(this);
		const tr = pauseJob.parent().parent();
		const td = tr.children();
		
		const triggerName = td.eq(1).text();
		const triggerGroup = td.eq(2).text();
		
		$.ajax({
			url : '/job/pauseJob/' + triggerName
			, type : 'PUT'
			, data :
				{'triggerName' : triggerName
				, 'triggerGroup' : triggerGroup}
			, success : function(){
				location.reload();
			}
		})
		.done(function(){
			alert(triggerName + '이 중지되었습니다.');
		})
		.fail(function(){
			alert(triggerName + ' 실행에 오류가 발생했습니다.');
		})
	});
	
	
	$('.btnRescheduleModal').click(function(){
		const rescheduleJob = $(this);
		const tr = rescheduleJob.parent().parent();
		const td = tr.children();
		
		const triggerName = td.eq(1).text();
		const triggerGroup = td.eq(2).text();
		const cronExpression = td.eq(3).text();

		
		$('#triggerName').val(triggerName);
		$('#triggerGroup').val(triggerGroup);
		$('#NcronExpression').val(cronExpression);
		
		$('#modalReschedule').modal('show');
	});
	
	
	$('#btnRescheduleJob').click(function(){
		const triggerName = $('#triggerName').val();
		const triggerGroup = $('#triggerGroup').val();
		const cronExpression = $('#cronExpression').val();

		$.ajax({
			url : '/job/rescheduleJob/' + triggerName
			, type : 'PUT'
			, data :
				{'triggerName' : triggerName
				, 'triggerGroup' : triggerGroup
				, 'cronExpression' : cronExpression}
			, success : function(){
				location.reload();
			}
		})
		.done(function(){
			alert(triggerName + '의 cronExpression이 변경되었습니다.');
		})
		.fail(function(){
			alert(triggerName + '의 cronExpression 변경에 오류가 발생했습니다.');
		})
	});
	
	
	$('.btnDeleteJob').click(function(){
		const deleteJob = $(this);
		const tr = deleteJob.parent().parent();
		const td = tr.children();
		
		const triggerName = td.eq(1).text();
		const triggerGroup = td.eq(2).text();
		
		const result = confirm(triggerName + '를 삭제하시겠습니까?');

		if(result){
			$.ajax({
				url : '/job/deleteJob/' + triggerName
				, type : 'DELETE'
				, data :
					{'triggerName' : triggerName
					, 'triggerGroup' : triggerGroup}
				, success : function(){
					location.reload();
				}
			})
		}
	});
	
})
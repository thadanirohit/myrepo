$(document).ready(function(){
	
	$(document.body).on("click", "button.delProject", function(e){
		e.preventDefault();
		
		var currentButton = $(this);
		var currentRow = currentButton.parent().parent();
		
		currentButton.attr("disabled","disabled");
		
		var dataToSend = {
			projectKey: $(this).attr("data-projectid"),
			y: 10
		};
		
		$.post("ajaxDelProject.do", dataToSend,
		    function(data, status){
		        //alert("###"+data+"###");
		        //$target.hide('slow', function(){ $target.remove(); });
				if(data == 'yes'){
					currentRow.hide('slow', function(){ currentRow.remove(); });
				}
				currentButton.removeAttr("disabled");
		    }
		 );
	});
	
	$(document.body).on("click", "button.editProject", function(e){
		e.preventDefault();
		e.stopPropagation();
		var parent_tr = $(this).parents("tr");
		parent_tr.removeClass("readOnly");
		parent_tr.addClass("editable");
		
		parent_tr.find("td").each(function(){			
			if(!$(this).hasClass("action")){
				//alert($(this).find("input").val());
				$(this).find("input").removeAttr("readonly");
			}
		});
		
	});
	
});
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("button").click(function(){
        $("#div1").load("http://localhost:8080/ppmvc-web/user/5",
        	function(responseTxt, statusTxt, xhr){        	
        		console.log(responseTxt);
        		var z = '{"info1":{"id":5,"name":"Rohit Thadani","address":"Indore"}, "info2":{"id":5,"name":"Rohit Thadani","address":"Indore"}}';
        		var p = '[{"id":5,"name":"Rohit Thadani","address":"Indore"},{"id":6,"name":"Ankur Jaiswal","address":"Bangali"}]';
        		var x = $.parseJSON(p);
        		console.log(x);
        		
        		$.each(x, function(key,value) {
        			$.each(value, function(key2,value2) {
        				console.log(key2+':'+value2);
        				$("#div1").append("<h3>"+key2+' : '+value2+"</h3>");
        			});
       			  //console.log(key+':'+value);
       			});
        		/* console.log(x.info1.id);
        		console.log(x.info1.name);
        		console.log(x.info2); */
        		//console.log(x.address);
        	}
        );
    });
});
</script>
</head>
<body>

<div id="div1"><h2>Let jQuery AJAX Change This Text</h2></div>

<button>Get External Content</button>

</body>
</html>

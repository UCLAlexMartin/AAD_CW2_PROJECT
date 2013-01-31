<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ConnectionManager.*" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CharityWare</title>		
		
		<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/style1.css" type="text/css" media="all">
			
		<script type="text/javascript" src="js/tabsScript.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
				
		
		<!--Load the AJAX API-->
    	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    	<script type="text/javascript">

	      // Load the Visualization API and the piechart package.
	      google.load('visualization', '1.0', {'packages':['corechart']});
	
	      // Set a callback to run when the Google Visualization API is loaded.
	      google.setOnLoadCallback(drawChart);
	      function drawChart(point) {
	    	  var data = new google.visualization.DataTable();
	    	  
	      }
	      // Callback that creates and populates a data table,
	      // instantiates the pie chart, passes in the data and
	      // draws it.

	     
	      jQuery(document).ready(function($){
		      $('#chart0').click(function(){
		    	  $('.content_5_charts').hide();

		    	  var data = new google.visualization.DataTable();
		    	  
		    	  	data.addColumn('string', 'Account');
					data.addColumn('number', 'Records');
					data.addRows([
					<%=DatabaseManager.readSystemVerificationCharity()%>
					]);
			       	var options = {'title':'Verified Account VS Unverified Account',
			                       'width':500,
			                       'height':400};
			
			        // Instantiate and draw our chart, passing in some options.
			        var chart0 = new google.visualization.PieChart(document.getElementById('chart0_div'));
			        chart0.draw(data, options);
			        $('#chart0_div').fadeIn();
			        return false;
			        
		      });
		      
		      $('#chart1').click(function(){
		    	  $('.content_5_charts').hide();
		    	  
		    	  var data = new google.visualization.DataTable();

		    	  data.addColumn('string', 'Date');
					data.addColumn('number', 'Records');
					data.addRows([
		<%=DatabaseManager.readSystemAccountDuration()%>
			]);
					var options = { 'title':'Date of Creating Account',
		                       		'width':500,
		                        	'height':400};
		
		        // Instantiate and draw our chart, passing in some options.
		        var chart1 = new google.visualization.PieChart(document.getElementById('chart1_div'));
		        chart1.draw(data, options);
		        $('#chart1_div').fadeIn();
			        return false;  
		      });
		      
		      $('#chart2').click(function(){
		    	  $('.content_5_charts').hide();
		    	  var data = new google.visualization.DataTable();

		    	  data.addColumn('string', 'Date');
					data.addColumn('number', 'Records');
					data.addRows([
		<%=DatabaseManager.readSystemActiveAccount()%>
			]);
					var options = { 'title':'Active Account VS Disable Account',
		                       		'width':500,
		                        	'height':400};
		        // Instantiate and draw our chart, passing in some options.
		        var chart2 = new google.visualization.PieChart(document.getElementById('chart2_div'));
		        chart2.draw(data, options);
		        $('#chart2_div').fadeIn();
			        return false;  
		      });
		      
	      });

    	</script>
    	
    	<!-- Google Charts Stuff -->
</head>
<body id="page1">
	<div class="body1">
		<div class="main">
	  
	     <jsp:include page="HeaderLoggedIn.jsp"></jsp:include>	   	    
	          
	    <!-- Main Content -->
	    <article id="content">
	      <div class="wrapper">
	        <div class="box1">
	        
	        	<p> 
					<h2> UCL Administration Panel </h2> 
				</p>
								
				<div id="tabs">
			        <ul> 
			            <li><a href="javascript:tabSwitch(1,3,'tab_', 'content_');" id="tab_1" class="active">Requests</a></li>  
			            <li><a href="javascript:tabSwitch(2,3,'tab_', 'content_');" id="tab_2">Manage Accounts</a></li>  
			            <li><a href="javascript:tabSwitch(3,3,'tab_', 'content_');" id="tab_3">Statistics</a></li>  
			        </ul>
			    </div> 
			    <div class="tabbed_area">       
			       <div id="content_1" class="tabContent">
			         <form name="frmRequests" method="post" action="">
      					Manage Charity Requests
				        <br/>
				        <br/>
				        
						<table class="resultSet">
						<tr>
					          
				            <td>Serial Number </td>
				            <td>Charity Name </td>
				            <td>Charity ID </td>
						    <td>Charity Email </td>
						    <td>Purpose </td>
						    <td>Comments </td>
					        <td>Action </td>      
						 </tr>
						 <tr>
						    <td></td>
					        <td></td>
					        <td></td>
						    <td></td>
						    <td></td>
						    <td></td>
					        <td>
					        <input type="radio" name="Action" value="yes" > Approve <br/>
						    <input type="radio" name="Action" value="no"> Decline </td>
						</tr>
				        </table>        
     				</form>
			     </div>
			     
			     <div id="content_2" class="tabContent">
			     		<ul id="menubar2">
				     		<li><a href =""> View Accounts </a> <b>|</b> </li>
	             	       	<li><a href = ""> Change Password Requests </a> <b>|</b> </li>
	                        <li><a href = ""> Delete Accounts </a></li>
                        </ul>
			     </div>  
			     
			     <div id="content_3" class="tabContent">
			     		<ul id="menubar2">
			     			<li><a id="chart0" href ="#"> Verified Account VS Unverified Account </a> <b>|</b> </li>
	             	       	<li><a id="chart1" href ="#"> Date of Creating Account </a> <b>|</b> </li>
	             	       	<li><a id="chart2" href ="#"> Active Account VS Disable Account </a> <b>|</b> </li>
	             	  		
                        </ul>
                        
                        <br/>
                        <br/>
			
			     		<!--Div that will hold the chart-->
    					<div id="chart0_div" class="content_5_charts"></div>
    					<div id="chart1_div" class="content_5_charts"></div>
    					<div id="chart2_div" class="content_5_charts"></div>
    					
			     </div>  
			    </div>  
				
	        </div>
	      </div>
		</article>
	    <!-- Main content -->
	    
	   <jsp:include page="Footer.jsp"></jsp:include>   
	    
	  </div>
	</div>
	</body>
</html>
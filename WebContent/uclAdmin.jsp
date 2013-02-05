<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ConnectionManager.*" %>   
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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
			       <%
			       	List<Charity> fields = ApproveCharityRequests.httpGet("http://localhost:8080/CharityWare/REST/charity/charities");
			       %>
			          <form name="frmRequests" method="POST" action="">
      					Manage Charity Requests
				        <br/>
				        <br/>
				        <table class="resultSet">
						<tr>
				            <td>Serial Number </td>
				            <td>Charity Name </td>
				            <td>Charity Registration Number </td>
						    <td>Charity Email </td>
						    <td>Purpose </td>
					        <td>Action </td>      
						 </tr>
						<%
						
				    		 
				    				 for(int i=0; i<fields.size();i++){
				    					 out.println("<tr>");
				    					 out.println("<td>" + (i+1));
				    					 out.println("<td>" + fields.get(i).getCharityName());
				    					 out.println("<td>" + fields.get(i).getRegistrationNo());
				    					 out.println("<td>" + fields.get(i).getEmail());
				    					 out.println("<td>" + fields.get(i).getCharityDescription());
				    					 String approve = "approve"+i;
				    					 String reject = "reject"+i;
				    					 out.println("<td>" +"<input id="+approve+" type= radio name=Action value= yes onclick = DatabaseManager.generateSchema(request.getParameter(fields.get(i).getCharityId()));>" + "Approve <br/>");
				    					 out.println("<input id="+reject+" type= radio name=Action value= no>" + "Decline <br/>");
				    					 out.println("<tr>");
				    				 }
				    				 
				    	%>
				        </table>        
     				</form>
			     </div>
			     
			     <div id="content_2" class="tabContent">
			     		<form name="listAccounts" method="POST" action="listAccounts.jsp">
      								Manage User Accounts
				        		<br/>
				        		<br/>
				        
				        		<table class="resultSet">
								<tr>
					            
								<td> Serial Number </td>
								<td> Charity Name </td>
								<td> Charity Email  </td>
								<td> Charity Description </td>
								<td> Delete Account</td>
								</tr>
								<%
						
				    		 		 List<Charity> fields1 = EditAccounts.httpGet("http://localhost:8080/CharityWare/REST/charity/charities");
				    				 for(int i=0; i<fields1.size();i++){
				    					 out.println("<tr>");
				    					 out.println("<td>" + (i+1));
				    					 out.println("<td>" + fields1.get(i).getCharityName());
				    					 out.println("<td>" + fields1.get(i).getEmail()); 
				    					 out.println("<td>" + fields1.get(i).getCharityDescription());
				    					 String input = "inpt"+i;
				    					 out.println("<td>" +"<input id="+input+" type= checkbox method = POST value = Delete>" + "Delete <br/>");
				    					 out.println("<tr>");
				    				 }
				    				 
				    	%>
								
				        </table>        
				        
				        
						
     				</form>
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
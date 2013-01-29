<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ConnectionManager.*" %>  
   <%@ page import="ConnectionManager.*" %>
    <%@ page import= "java.util.TreeMap"%>
    <%@ page import= "java.util.ArrayList"%>
    <%@ page import= "java.util.Set"%>
    <%@ page import= "java.util.Map.Entry"%>
    <%@ page import= "java.util.Iterator"%>  
    

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Add Events</title>
		<script type="text/javascript" src="js/calendar.js"></script>
   		<script type="text/javascript" src="js/tabsScript.js"></script>
		<script type="text/javascript" src="js/charityManager.js"></script>
		
		
		<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/style1.css" type="text/css" media="all">
   		<link rel="stylesheet" href="css/calendar.css"  type="text/css">
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
							<h2> Charity Administration Panel </h2> 
						</p>
								
					<div id="tabs">
			        	<ul> 
			            <li><a href="charityAdmin.jsp">Manage Application</a></li>  
			            <li><a href="charityAdmin.jsp" id="tab_2">Manage Users</a></li>  
			            <li><a href="charityAdmin.jsp" id="tab_3" class="active">Manage Events</a></li>
			            <li><a href="charityAdmin.jsp" id="tab_4">Search</a></li>
			            <li><a href="charityAdmin.jsp" id="tab_5">Statistics</a></li>  
			        	</ul>
			    	</div> 
			    	<br/><br/>
			    	<div class="tabbed_area">       
			       <div id="content_1" class="tabContent">
			       	<ul id="menubar2">
				     		<li><a href="ViewEvents.jsp" > View Events </a> <b>|</b> </li>
	             	       	<li><a href = "AddEvents.jsp"  class="active"> Add Events </a>  </li>
	                        
                        </ul>
                  
			    	<form id="form1" name="form1" method="post" action="">
				<table style="border-spacing:5px;border-collapse: inherit;">
				
						<tr>
							<td> <label for="ename">Event Name</label> </td>
			      			<td> <input type="text" class="loginTextbox" name="ename" id="ename" required> </td>
			      		</tr>	
			      			
						<tr>
							<td> <label for="edate">Event Date</label> </td>
			      			<td> <input type="text" class="loginTextbox" name="edate" id="edate" required readonly> 
			      				 <a href="#" onClick="setYears(2011, 2014); showCalender(this, 'edate');">
			      				 <img src="images/cal.gif" id="cal"> 
			      				 </a> 
			      		    </td>
			      		</tr>
						
						<tr>
							<td> <label for="etime">Event Time</label> </td>
			      			<td><select name="hr" id="marg_top"> <option value="00">00</option>
			      						 <option value="01">01</option>
			      						 <option value="02">02</option>
			      						 <option value="03">03</option>
			      						 <option value="04">04</option>
			      						 <option value="05">05</option>
			      						 <option value="06">06</option>
			      						 <option value="07">07</option>
			      						 <option value="08">08</option>
			      						 <option value="09">09</option>
			      						 <option value="10">10</option>
			      						 <option value="11">11</option>
			      				</select>
			      				
			      			<select name="mm" id="marg_top"> <option value="00">00</option>
			      						 <option value="15">15</option>
			      						 <option value="30">30</option>
			      						 <option value="45">45</option>
			      				</select>
			      				
			      			<select name="format" id="marg_top"> <option value="AM">AM</option>
			      						 <option value="PM">PM</option>
			      				</select></td>
			      		</tr>
			      		<tr></tr>
			      		<tr>
							<td> <label for="evenue">Event Venue</label> </td>
			      			<td> <input type="text" class="loginTextbox" name="evenue" id="evenue" required> </td>
			      		</tr>
			      		
			      		<tr>
							<td> <label for="edesc">Event Description</label> </td>
			      			<td> <input type="text" class="loginTextbox" name="edesc" id="edesc" required> </td>
			      		</tr>
						
						<tr></tr> <tr></tr>
					
						<tr>
							<td> <input class="contactSubmit" name="button1" type="submit" id="button1" value="Add Event"> </td>
						</tr>
						
				</table>
			    </form>
				</div>
				</div>
			    
			    	  </div>
	 </div>
   </article>	 	
			    <!-- Create Calender Table  -->  
			    <table id="calenderTable">
        			<tbody id="calenderTableHead">
          				<tr>
            			 <td colspan="4" align="center">
	          				<select onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
	           					this.selectedIndex, false));" id="selectMonth">
	              				<option value="0">Jan</option>
	              				<option value="1">Feb</option>
	              				<option value="2">Mar</option>
	              				<option value="3">Apr</option>
	              				<option value="4">May</option>
	              				<option value="5">Jun</option>
	              				<option value="6">Jul</option>
	              				<option value="7">Aug</option>
	              				<option value="8">Sep</option>
	              				<option value="9">Oct</option>
	              				<option value="10">Nov</option>
	              				<option value="11">Dec</option>
	          				</select>
            			 </td>
            			 <td colspan="2" align="center">
			    			<select onChange="showCalenderBody(createCalender(this.value, 
							document.getElementById('selectMonth').selectedIndex, false));" id="selectYear">
							</select>
						</td>
            			<td align="center">
			    			<a href="#" onClick="closeCalender();"><font color="#003333" size="+1"><img src="images/close.png" id="cal"></font>
                			</a>
						</td>
		  			</tr>
       			</tbody>
       			
       			<tbody id="calenderTableDays">
         			<tr style="">
           				<td id="margr">Sun</td><td id="margr">Mon</td><td id="margr">Tue</td><td id="margr">Wed</td><td id="margr">Thu</td><td id="margr">Fri</td><td id="margr">Sat</td>
         			</tr>
       			</tbody>
       			
       			<tbody id="calender"></tbody>
    	</table>
	
   
    <jsp:include page="Footer.jsp"></jsp:include>   	    
			      
  </div>
 </div>
</body>
</html>   	
			    
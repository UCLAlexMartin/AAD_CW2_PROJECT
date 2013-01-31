<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ConnectionManager.*" %>  
   <%@ page import="ConnectionManager.*" %>
    <%@ page import= "java.util.TreeMap"%>
    <%@ page import= "java.util.ArrayList"%>
    <%@ page import= "java.util.Set"%>
    <%@ page import= "java.util.Map.Entry"%>
    <%@ page import= "java.util.Map"%>
    <%@ page import= "java.util.Iterator"%>
    <%@ page import= "java.util.List"%>  
    <%@ page import= "RESTClient.*"%>  
    

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
				     		<li><a href="ViewEvents.jsp"> View Events </a> <b>|</b> </li>
	             	       	<li><a href = "AddEvents.jsp"  class="active"> Add Events </a>  </li>
	                        
                        </ul>
                    <form action="" method="get" name="search" id="ddh">
     					<select name="Month" size="" id="marg_top">
  							<option value="00"> ---Select Month-- </option>
  							<option value="01"> January </option>
  							<option value="02"> February </option>
  							<option value="03"> March </option>
  							<option value="04"> April </option>
  							<option value="05"> May </option>
  							<option value="06"> June </option>
  							<option value="07"> July </option>
  							<option value="08"> August </option>
  							<option value="09"> September </option>
  							<option value="10"> October </option>
  							<option value="11"> November </option>
  							<option value="12"> December </option>
  						</select>
     
     					<select name="Year" size="" id="marg_top">
     						<option value="00"> --Select Year -- </option>
     						<option value="2012"> 2012 </option>
     						<option value="2013"> 2013 </option>
     					</select>
     
     					<input name="b1" type="submit" value="Search" class="button2" >
     			</form>
   		
     		<div class="wrapper">
        		<div class="box2">
          		<div class="line1">
            		<div class="line2 wrapper">
            		<%     
            			Map<Integer,List<String>> datamap = EventClient.getEvents();
        				Set<Entry<Integer,List<String>>> entryset = datamap.entrySet();
        				Iterator<Entry<Integer, List<String>>> iter =  entryset.iterator();
            
						while (iter.hasNext()){
							List<String> eventsDetails =  iter.next().getValue();
            		%>
            		
            		<section class="col1">  
                		<h4><span><%=eventsDetails.get(0)%></span></h4>
                		<p class="pad_bot2"> <strong> NAME: </strong> <%=eventsDetails.get(1)%></p>
                		<p class="pad_bot2"> <strong>TIME: </strong> <%=eventsDetails.get(3)%></p>
                 		<p class="pad_bot2"> <strong> VENUE: </strong> <%=eventsDetails.get(2)%></p>                 		
                 	</section>
              		<%
              			} 
              		%> 
            		</div>
          		 </div>
        	   </div>
      		 </div>  		
			</div>      
          </div>
        </div>		   	
	  </div>

   </article>	 	
			    
   
    <jsp:include page="Footer.jsp"></jsp:include>   	    
			      
  </div>
 </div>
</body>
</html>   				
							
						
						
								
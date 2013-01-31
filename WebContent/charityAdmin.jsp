<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ConnectionManager.*" %>
<%@ page import="java.util.List"%>
<%@ page import= "java.util.TreeMap"%>
    <%@ page import= "java.util.ArrayList"%>
    <%@ page import= "java.util.Set"%>
    <%@ page import= "java.util.Map.Entry"%>
    <%@ page import= "java.util.Iterator"%> 
<%@page import="XMLParse.xmlParser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CharityWare</title>		
		
		<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/style1.css" type="text/css" media="all">
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
		 <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js"></script>
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
				  
		<script type="text/javascript" src="js/common-validator-2.0.min.js"></script>
		<script type="text/javascript" src="js/common-validator-messages.js"></script>
		<script type="text/javascript" src="js/common-validator-config.js"></script>
		
		<script type="text/javascript" src="js/tabsScript.js"></script>
		<script type="text/javascript" src="js/charityManager.js"></script>
		<script type="text/javascript" src="js/xhr.js"></script>
		<script type="text/javascript" src="js/panelSwitcher.js"></script>
    	<script type="text/javascript" src="js/GoogleCalendar.js"></script>
    	<script type="text/javascript">
    	  //Number of Records inputted per User	 
	      
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
	      
	      function populateData(data)
	      {
	    	  resp = xhr("/CharityWare/StatisticsDataServlet","GET",false);
	    	  obj = JSON.parse(resp);
	    	  data.addRows(obj);
	      }
	      jQuery(document).ready(function($){
	    	  
		      $('#chart0').click(function(){
		    	  $('.content_5_charts').hide();

		    	  var data = new google.visualization.DataTable();
		    	  data.addColumn('string', 'User');
			       data.addColumn('number', 'Records');
			       populateData(data);
			       var options = {'title':'Records inputted by each User',
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

		    	  data.addColumn('string', 'Account');
					data.addColumn('number', 'Records');
					data.addRows([
					<%=DatabaseManager.readCharityActiveAccount()%>]);
					var options = { 'title':'Active Account VS Disable Account',
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
					<%=DatabaseManager.readCharityFormDuration()%>]);
					var options = { 'title':'Date of Creating Forms',
		                       		'width':500,
		                        	'height':400};
		        // Instantiate and draw our chart, passing in some options.
		        var chart2 = new google.visualization.PieChart(document.getElementById('chart2_div'));
		        chart2.draw(data, options);
		        $('#chart2_div').fadeIn();
			        return false;  
		      });
		      
		      $('#chart3').click(function(){
		    	  $('.content_5_charts').hide();
			    	  var data = new google.visualization.DataTable();

		    	  	data.addColumn('string', 'Date');
					data.addColumn('number', 'Records');
					data.addRows([
					<%=DatabaseManager.readCharityAccountDuration()%>]);
						
					var options = { 'title':'Date of Creating Accounts',
		                  		'width':500,
		                    	'height':400};
						
					// Instantiate and draw our chart, passing in some options.
				    var chart3 = new google.visualization.PieChart(document.getElementById('chart3_div'));
				    chart3.draw(data, options);
				    $('#chart3_div').fadeIn();
			        return false;  
		      });
		      
		      $('#chart4').click(function(){
		    	  $('.content_5_charts').hide();
		    	  var data = new google.visualization.DataTable();

		    	  data.addColumn('string', 'Account');
					data.addColumn('number', 'Records');
					data.addRows([
					<%=DatabaseManager.readCharityActiveAccount()%>]); 		
					var options = { 'title':'Active Account VS Disable Account',
	                   		'width':500,
	                    	'height':400};
					
					// Instantiate and draw our chart, passing in some options.
			        var chart4 = new google.visualization.PieChart(document.getElementById('chart4_div'));
			        chart4.draw(data, options);
			        $('#chart4_div').fadeIn();
		        return false;  
	      		});
	      });
	      
	
		  //Tabs Scripts
	      function onBodyLoad()
	      {
	    	  tabSwitch(1,5,'tab_', 'content_');
	    	  document.getElementById("argc").value = 0;
		  }
	      
    	</script>
</head>
<body id="page1" onload="onBodyLoad()">
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
			            <li><a href="javascript:tabSwitch(1,5,'tab_', 'content_');" id="tab_1" class="active">Manage Application</a></li>  
			            <li><a href="javascript:tabSwitch(2,5,'tab_', 'content_');" id="tab_2">Manage Users</a></li>  
			            <li><a href="javascript:tabSwitch(3,5,'tab_', 'content_');" id="tab_3">Manage Events</a></li>
			            <li><a href="javascript:tabSwitch(4,5,'tab_', 'content_');" id="tab_4">Search</a></li>
			            <li><a href="javascript:tabSwitch(5,5,'tab_', 'content_');" id="tab_5">Statistics</a></li>  
			        </ul>
			    </div> 
			    <div class="tabbed_area">       
			       <div id="content_1" class="tabContent">
      					<fieldset id="myforms">
	      					<legend>My forms</legend>
	      					<c:choose>
	      					<c:when test='${sentForms!= null && sentForms.size() > 0}'>
		      					<label for="myformslist">Form name:</label>
		      					<select id="myformslist" onchange="currentFormChanged()">
			      				 	<c:forEach items="${sentForms}" var="theform">
			      				    	<option value="${theform.getFormId()}"><c:out value="${theform.getFormName() }"/></option>
			      				 	</c:forEach>
		      				 	</select>
		      				 	<button type="button" onclick="viewCurrentFormStructure()">View structure</button>
		      				 	<button type="button" onclick="viewCurrentFormData()">View data</button>
		      				 	<button type="button" onclick="deleteCurrentForm()">Remove this form</button>
	      					</c:when>
	      					<c:otherwise>
	      						Sorry, it appears you have no forms defined!
	      				 	</c:otherwise>
	      				 	</c:choose>  
	      				 	<br/>   				 	
	      					<button type="button" onclick="showFormWizard()">Add new Form</button>
      					</fieldset>
      					<c:if test="${sentForms!= null && sentForms.size() > 0}">
      					<fieldset id="currentformstructure" class="nodisplay">
      					<div id="currentformstructurefill"></div>
      					<button type="button" onclick="hideCurrentFormStructure()">Hide</button>
      					</fieldset>
      					<fieldset id="currentformdata" class="nodisplay">
      					<div id="currentformdatafill"></div>
      					<button type="button" onclick="hideCurrentFormData()">Hide</button>
      					</fieldset>
      					</c:if>
      					<fieldset id="formwizard" class="nodisplay">
      					<legend>Form Wizard</legend>
      					<label>Form name:</label>
      					<input id="formname" type="text" />
      					<button type="button" id="btnSubmitForm" onclick="">Create this form!</button>   					
      					<fieldset id = "fieldselect">
      					<legend>Field wizard</legend>
      					<label for="fieldname">Field name</label>
      					<input id="fieldname" type="text" validate="notEmpty:true, English:ture"/>
      					<label for="typeoptions">Input type</label>
      					<select id="typeoptions" onchange="onRowTypeChanged()">
      					<c:forEach items="${fieldTypes}" var="iType">
      					<option value="${iType.getField_type_id()}">${iType.getField_Description()}</option>
      					</c:forEach>
      					</select>
      					<input type="checkbox" id="rowrequired" name="rowrequired"/>
      					<label for="rowrequired">Mandatory?</label>
      					<button onclick="addRow()" type="button" >Add row</button>
      					<div id="extra" class="nodisplay"></div>
      					<div id="errmsg" class="nodisplay"></div>
      					</fieldset>
      					<form id="rowset" action="FormServlet" method="post">
      					<fieldset>
      					<input type="hidden" id="argc" name="argc" value="0"/>
      					<input type="hidden" name="req" value="create"/>
      					<legend>Current rows:</legend>
      					<div id="rowsetrows"></div>
      					<button type="button" onclick="hideFormWizard()">Hide</button>
      					<button type="button" id="clearbtn" onclick='removeChildren(document.getElementById("rowsetrows") ); document.getElementById("argc").value=0;'>Clear all rows</button>
      					</fieldset>
      					</form>
      					</fieldset>
				   
			     </div>
			     
			     <div id="content_2" class="tabContent">
			     		<ul id="menubar2">
				     		<li><a href ="#" onclick="changePanel('viewUser','content2'); return false;"> View Users </a> <b>|</b> </li>
	             	       	<li><a href ="#" onclick="changePanel('addUser','content2'); return false;"> Add Users </a> <b>|</b> </li>
	                        <li><a href ="#" onclick="changePanel('deleteUser','content2'); return false;"> Delete Users </a></li>
	                        <li><a href ="#" onclick="changePanel('mailingList','content2'); return false;"> Mailing List </a></li>
                        </ul>
                        
                        <div id="viewUser" class="subContent2" style="display:none;">
                        
                      	<form id="viewUser" name="viewUser" method="post" action="">
						<table id="tableView">
						
						<tr>
							<th id="tr1"> <label for="uname">Username</label> </th>
							<th id="tr1"> <label for="ucat">User Category</label> </th>
							<th id="tr1"> <label for="uemail">Email</label> </th>
							<th id="tr1"> <label for="uper">Permissions</label> </th>
							
			      		</tr>
			      		<%     
            			TreeMap<Integer,ArrayList<String>> datamap =(TreeMap<Integer,ArrayList<String>>)DatabaseManager.readUsers();
        				Set<Entry<Integer,ArrayList<String>>> entryset = datamap.entrySet();
        				Iterator<Entry<Integer, ArrayList<String>>> iter =  entryset.iterator();
            
						while (iter.hasNext()){
							ArrayList<String> userDetails =  iter.next().getValue();
            		%>
			      		
			      			<tr>
			      			<td id="th1"><%out.println(userDetails.get(0));%></td>
			      			<td id="th1"><%out.println(userDetails.get(1));%></td>
			      			<td id="th1"><%out.println(userDetails.get(2));%></td>
			      			<td id="th1"><%out.println(userDetails.get(3));%></td>
			      			
			      			</tr>
			      			<%} %>
			      		
						
					  </table>
				    </form>
                        
                        </div>
                        <div id="addUser" class="subContent2" style="display:none;">

                        <form id="addUser" name="addUser" method="post" action="">
						<table style="border-spacing:5px;border-collapse: inherit;">
						<tr/><tr/> <tr/><tr/>
						<tr>
							<td> <label for="uname">Username</label> </td>
			      			<td> <input type="text" class="loginTextbox" name="uname" id="uname" required> </td>
			      		</tr>
			      			<tr></tr>
			      		<tr>
							<td> <label for="pwd">Password</label> </td>
			      			<td> <input type="text" class="loginTextbox"  name="pwd" id="pwd" required> </td>
			      		</tr>
			      			<tr></tr>
			      		<tr>
							<td> <label for="uemail">Email</label> </td>
			      			<td> <input type="text" class="loginTextbox" name="uemail" id="uemail" required> </td>
			      		</tr>
			      		<tr></tr>	
			      		<tr>
			      			
							<td> <label for="ucat">User Category</label> </td>
							<td>	
								<select value="ucat" id="marg_top">
									<option value="0"> --Select-- </option>
									<% ArrayList<String> utype = (ArrayList<String>) DatabaseManager.UserType();									
									
									for(int i=0;i<utype.size();i++) {
									
									%>
									<option value="i"> <%out.println(utype.get(i));%> </option>
									<%} %>
								</select>
								</td>										      			
			      		</tr>
			      		<tr></tr>
			      		<tr>
							<td> <label for="uper">Form Permissions</label> </td>
			      			<tr> 
			      			<td/> <td>	
			      				
									<% ArrayList<String> fm = (ArrayList<String>) DatabaseManager.FormNames();									
									
									for(int i=0;i<fm.size();i++) {
									
									%>
									
									<input type="checkbox" name="<%fm.get(i);%>" value="<%fm.get(i);%>"> <%out.println(fm.get(i));%>
									
									
									<% }%>
									
			      			 </td>
			      		</tr>
                       <tr></tr> <tr></tr>
					
						<tr>
							<td> <input class="contactSubmit" name="button1" type="submit" id="button1" value="Add User"> </td>
						</tr>
						
					  </table>
				    </form>
                        
                        </div>
                        <div id="deleteUser" class="subContent2" style="display:none;">
                        
                         <form id="deleteUser" name="deleteUser" method="post" action="">
						<table id="tableDelete">
						
						<tr>
							<th id="tr1"> <label for="uname">Username</label> </th>
							<th id="tr1"> <label for="ucat">User Category</label> </th>
							<th id="tr1"> <label for="uemail">Email</label> </th>
							<th id="tr1"> <label for="uper">Permissions</label> </th>
							<th id="tr1"> <label for="udel">Delete</label> </th>
			      		</tr>
			      		<%     
            			TreeMap<Integer,ArrayList<String>> datamap2 =(TreeMap<Integer,ArrayList<String>>)DatabaseManager.readUsers();
        				Set<Entry<Integer,ArrayList<String>>> entryset2 = datamap2.entrySet();
        				Iterator<Entry<Integer, ArrayList<String>>> iter2 =  entryset2.iterator();
            
						while (iter.hasNext()){
							ArrayList<String> userDetails =  iter2.next().getValue();
            		%>
			      		
			      			<tr>
			      			<td id="th1"><%out.println(userDetails.get(0));%></td>
			      			<td id="th1"><%out.println(userDetails.get(1));%></td>
			      			<td id="th1"><%out.println(userDetails.get(2));%></td>
			      			<td id="th1"><%out.println(userDetails.get(3));%></td>
			      			<td id="th1"><a href="">Delete</a></td>
			      			</tr>
			      			<%} %>
			      		
						
					  </table>
				    </form>
                        
                        </div>
 						<div id="mailingList" class="subContent2" style="display:none;">
                        
                        <form id="addlist" name="addlist" method="post" action="">
						<table style="border-spacing:5px;border-collapse: inherit;">
						<tr/><tr/> <tr/><tr/>
						<tr>
							<td> <label for="uname">Mailing List Name</label> </td>
			      			<td> <input type="text" class="loginTextbox" name="mlname" id="mlname" required> </td>
			      			<td><td/><td><td/>
							<td> <input class="contactSubmit" name="button1" type="submit" id="button1" value="Add Mailing List"> </td>
						
			      		</tr>
                    	</table>
                    </form>
                    
                    <hr width=100% size="5" color=black> 
                    
                     <form id="maillist" name="maillist" method="post" action="">
						<table style="border-spacing:5px;border-collapse: inherit;">
						<tr/><tr/> <tr/><tr/>
						<tr>
							<td> <label for="uname">Mailing List Name</label> </td>
			      			<td>  </td>
			      			<td> 
			      				<select value="mlist" id="marg_top" >
			      				<option value="0"> --Select-- </option>
									<% ArrayList<String> mlist = (ArrayList<String>) DatabaseManager.MailingList();									
									
									for(int i=0;i<mlist.size();i++) {
									
									%>
									<option value=i> <%out.println(mlist.get(i));%> </option>
									<%} %>
			      				</select>
			      			</td>
			      		</tr>
			      		<tr/><tr/> <tr/><tr/><tr/><tr/> <tr/><tr/><tr/><tr/> <tr/><tr/>
			      		<tr>
			      		<td>
			      			<select id="lists" size="10" >
			      			
			      			</select>
			      		</td>
			      		<td><td/>
			      		<td>
			      		<input class="contactSubmit" name="button1" type="submit" id="button1" value="Add User ->">
			      		<td/>
			      		<td><td/><td><td/><td><td/><td><td/><td><td/><td><td/><td><td/>
			      		<td>
			      			<select id="lists" size=10>
			      				<% ArrayList<String> mlist1 = (ArrayList<String>) DatabaseManager.MailingListOldUsers();									
									
									for(int i=0;i<mlist1.size();i++) {
									
									%>
									<option value=i> <%out.println(mlist1.get(i));%> </option>
									<%} %>
			      			</select>
			      		</td>
			      		</tr>
                    	</table>
                    </form>
                        
                        </div>
			     </div>  
			     
			     <div id="content_3" class="tabContent">
			     		<iframe src="https://www.google.com/calendar/embed?src=mghh43qdbd9baft4ulhsugv3sc%40group.calendar.google.com&ctz=Europe/London" style="border: 5px" width="800" height="600" frameborder="0" scrolling="no">
			     		</iframe>
			     </div>  
						<!-- /**
 *  By Kede Bei
 * 	Last Updated: 30/01/2012 2:22am
 * **/ -->
						<script type="text/javascript">
							function sendGetRequest() {
								var xmlhttp;
								xmlhttp = new XMLHttpRequest();
								xmlhttp
										.open(
												"GET",
												"http://localhost:8080/WEB-INF/RESTservices",
												false);
								xmlhttp.setRequestHeader("Content-Type",
										"application/xml");
								xmlhttp.send(null);
								alert(xmlhttp.responseText);
							}

							function sendPostRequest(a,b) {
								window.location.href ="search.action?a"+a;
								window.location.href ="search.action?b"+b;
								var xmlhttp;
								xmlhttp = new XMLHttpRequest();
								xmlhttp
										.open(
												"POST",
												"http://localhost:8080/WEB-INF/RESTservices",
												false);
								xmlhttp.setRequestHeader("Content-Type",
										"application/xml");

								xmlhttp.send("<"+a+">" + b
										+ "</"+b+">");
								alert(xmlhttp.responseText);
							}
						</script>
						<div id="content_4" class="tabContent">
							<Form id="charityAdmin.jsp" name="frmSearch" method="post"
								action="search">
								<p>
									<label for=""></label> 
									<select name="category" id="category" name="category">

										<%
											List<String> list = ConnectionManager.DatabaseManager.searchtitle();
											for (int i = 0; i < list.size(); i++) {
										%>
										<option>
											<%=list.get(i)%>
										</option>
										<%
											}
										%>
									</select> : <input type="text" name="keywords" />
								</p>
								<p>&nbsp;</p>
								<p>
									<%
										String a = request.getParameter("category");
										String b = request.getParameter("keywords");
									%>
									<input type="submit" name="Search" id="Search" value="Search"
										onclick="sendGetRequest(<%=a%>,<%=b%>)" />
								</p>
							</form>
						</div>
			     
			     <div id="content_5" class="tabContent">
			     		<ul id="menubar2">
			     			<li><a id="chart0" href ="#"> Records inputted per User </a> <b>|</b> </li>
	             	       	<li><a id="chart1" href ="#"> Account Status </a> <b>|</b> </li>
	             	       	<li><a id="chart2" href ="#"> Access Form Duration </a> <b>|</b> </li>
	             	  		<li><a id="chart3" href ="#"> Account Duration </a> <b>|</b> </li>
	             	  		<li><a id="chart4" href ="#"> Active Account VS Disable Account</a></li>
	             	 
                        </ul>
                        
                        <br/>
                        <br/>
			
			     		<!--Div that will hold thchart-->
    					<div id="chart0_div" class="content_5_charts"></div>
    					<div id="chart1_div" class="content_5_charts"></div>
    					<div id="chart2_div" class="content_5_charts"></div>
    					<div id="chart3_div" class="content_5_charts"></div>
    					<div id="chart4_div" class="content_5_charts"></div>
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

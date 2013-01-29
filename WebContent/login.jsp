<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
		<title>CharityWare</title>		
		
		<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/style1.css" type="text/css" media="all">	
</head>
<body id="page1">
	<div class="body1">
		<div class="main">
	  
	    <jsp:include page="Header.jsp"></jsp:include>	    
	          
	    <!-- Main Content -->  
	    <article id="content">
	      <div class="wrapper">
	        <div class="box1">
	          
	        	<form name="frmLogin" method="post" action="default.jsp">
			      <input type="hidden" name="LogginAttempt" id="LogginAttempt" value="LogginAttempt"/>
			      	<table style="border-spacing:5px;border-collapse: inherit;">
						<tr>
							<td> 
								<p>
								<h2> Login In </h2> 
								</p>
			      			</td>
						</tr>
						
						<tr>
							<td>
								<label for="txtUsername">Username</label>
			        		</td>
			        		<td>
			        			<input type="text" class="loginTextbox" style="width:240px;" name="txtUsername" id="txtUsername" required>
							</td>
						</tr>
						
						<tr>
							<td>
								<label for="txtPassword">Password  </label>
			        		</td>
			        		<td>
			        		  	<input type="password" class="loginTextbox" style="width:240px;" name="txtPassword" id="txtPassword" required>
							</td>
						</tr>
						
						<tr>
							<td>
								<input class="contactSubmit" name="button1" type="submit" id="button1" value="Log In">
							</td>
						
						</tr>
											
						<tr>
							<td>
							<br/>
							</td>
						</tr>	
						
						<tr>
							<td>
			      				<p> Need an Account ? <a href="register.jsp">Register Here</a></p>
			      			</td>
						</tr>
					</table>
			      
      			</form>
	          
	        </div>
	      </div>
		</article>
	    <!-- Main content -->
	    
	    
	    	 <jsp:include page="Footer.jsp"></jsp:include>   
	    
	  </div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
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
	          
	        	<form name="frmRegister" method="post" action="">
			      
			      	<table style="border-spacing:5px;border-collapse: inherit;">
						<tr>
							<td> 
								<p> 
									<h2> Register </h2> 
								</p>
			      			</td>
						</tr>
						
						<tr>
							<td>
								<label for="txtCharity">Charity Name</label>
			        		</td>
			        		<td>
			        			<input type="text" class="loginTextbox" style="width:240px;" name="txtCharity" id="txtCharity" required>
							</td>
						</tr>
						
						<tr>
							<td>
								<label for="txtReg">Registration Number</label>
			        		</td>
			        		<td>
			        		  	<input type="text" class="loginTextbox" style="width:240px;" name="txtReg" id="txtReg" required>
							</td>
						</tr>
						
						<tr>
							<td>
								<label for="txtChEmail">Charity Email</label>
			        		</td>
			        		<td>
			        		  	<input type="text" class="loginTextbox" style="width:240px;" name="txtChEmail" id="txtChEmail" required>
							</td>
						</tr>
						
						<tr>
							<td>
								<label for="txtChDetails">Charity Details</label>
			        		</td>
			        		<td>
			        		  	<input type="text" class="loginTextbox" style="width:240px;" name="txtChDetails" id="txtChDetails" required>
							</td>
						</tr>
						
						
						<tr>
							<td>
								<input class="contactSubmit" name="btnRegister" type="submit" id="btnRegister" value="Register">
							</td>
						
						</tr>
											
						<tr>
							<td>
							<br/>
							</td>
						</tr>	
						
						<tr>
							<td>
			      				<p> <a href="registerHelp.jsp"> Require Assistance ?</a></p>
			      			</td>
						</tr>
					</table>
			      
      			</form>
	          
	        </div>
	      </div>
		</article>
	    <!-- Main content -->
	    
	     <jsp:include page="Footer.jsp"></jsp:include> 
	      
	      <div id="footer_text">Copyright &copy; <a href="http://www.ucl.ac.uk">UCL</a> All Rights Reserved &nbsp;&nbsp;&nbsp;&nbsp;
	        Design by <a target="_blank" href="http://www.ucl.ac.uk">UCL Computer Science</a></div>
	    </footer>
	    
	  </div>
	</div>
</body>
</html>
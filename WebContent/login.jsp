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
	  
	    <!-- header -->
	    <header>    
	    <div class="wrapper">
	   		<h1 id="Mh1"> <a href="default.jsp" id="footer_logo"><span>&nbsp;Charity</span>Ware</a></h1>
	          <ul id="menubar">
		          <li><a href="default.jsp">Home</a></li> 
		          <li><a href="info.jsp">About Us</a></li>
			  </ul> 		
	    </div>
	    </header>
	    <!-- header -->
	    
	          
	    <!-- Main Content -->
	    <div class="wrapper">
	    	<p class="quot"> Help all charities to reach out to people and help them. The data exchange feature allows your charity workers to exchange data and be informed about what is happening around in the world. <img src="images/quot2.png" alt=""> </p>
	    </div>
	    
	    <article id="content">
	      <div class="wrapper">
	        <div class="box1">
	          
	        	<form name="frmLogin" method="post" action="">
			      
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
	    
	    
	    <!-- Contact Us Footer -->
	    <footer>	     
	      <div class="wrapper">
			  <h3>Contact Us</h3>
			  <div style="display:block; padding:10px;">
				  <div style="float:left; width:300px; display:inline-block;">
				  	University College London,<br/>
					Gower Street, <br/>
					London, WC1E 6BT <br/>
					<br/>
					Tel: +44 (0) 20 7679 2000 	
					<br/>
					
					<ul id="icons">
			          <li><a href="#" class="normaltip"><img src="images/icon1.gif" alt=""></a></li>
			          <li><a href="#" class="normaltip"><img src="images/icon2.gif" alt=""></a></li>
			          <li><a href="#" class="normaltip"><img src="images/icon3.gif" alt=""></a></li>
			          <li><a href="#" class="normaltip"><img src="images/pp.png" height="20" alt=""></a></li>
			        </ul>
				  </div>
				  
				  <div style="float:left; width:600px;display:inline-block;">
					<table style="border-spacing:5px;border-collapse: inherit;">
						<tr>
							  	<td>Name<br/>
							  		<input type="text" class="contactTextbox" style="width:240px;">
							  	</td>
							  	<td>Email<br/>
							  		<input type="text" class="contactTextbox" style="width:240px;">
							  	</td>
						</tr>
						<tr>
								<td colspan=2>Comment<br/>
							  		<textarea cols=70 height=150 class="contactTextbox"></textarea>
							  	</td>
						</tr>
						<tr>
							  	<td colspan=2>
							  		<input type="submit" value="SUBMIT" class="contactSubmit"/>
							  	</td>
						</tr>
				 	</table>
				 </div>
		     </div>
	     </div>
	      <!-- Contact Us Footer -->
	      
	      <div id="footer_text">Copyright &copy; <a href="http://www.ucl.ac.uk">UCL</a> All Rights Reserved &nbsp;&nbsp;&nbsp;&nbsp;
	        Design by <a target="_blank" href="http://www.ucl.ac.uk">UCL Computer Science</a></div>
	    </footer>
	    
	  </div>
	</div>
</body>
</html>
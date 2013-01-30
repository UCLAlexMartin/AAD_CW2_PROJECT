<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ConnectionManager.*" %>   
<%@ page import="staticResources.*" %>


<%@ page import="com.sun.jersey.api.client.Client" %>
<%@ page import="com.sun.jersey.api.client.ClientResponse" %>
<%@ page import="com.sun.jersey.api.client.GenericType" %>
<%@ page import="com.sun.jersey.api.client.config.ClientConfig" %>
<%@ page import="com.sun.jersey.api.client.config.DefaultClientConfig" %>

<%@ page import="org.codehaus.jackson.jaxrs.JacksonJsonProvider" %>
<%@ page import="javax.ws.rs.core.MediaType" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sun.jersey.api.client.config.ClientConfig" %>
<%@ page import="RESTClient.*"%>
<%@ page import="hibernateEntities.*"%>



<%
		
		if(request.getParameter("LogginAttempt")!=null &&
			request.getParameter("txtUsername")!=null &&
			request.getParameter("txtPassword")!=null)
		{
			out.println("Loggin Attempted<br/>");
			out.println("LogginAttempt:"+request.getParameter("LogginAttempt") + "<br/>");
			out.println("txtUsername:"+request.getParameter("txtUsername") + "<br/>");
			out.println("txtPassword:"+request.getParameter("txtPassword") + "<br/>");
			String whatever = PasswordEncryption.createSalt();
			out.println("RandomSalt:"+whatever + "<br/>");
			out.println("PasswordHash:"+PasswordEncryption.encryptPassword(request.getParameter("txtPassword"), whatever) + "<br/>");
			
			com.sun.jersey.api.client.config.ClientConfig 	JersyConfig = 	new com.sun.jersey.api.client.config.DefaultClientConfig();
			com.sun.jersey.api.client.Client 				client 		= 	com.sun.jersey.api.client.Client.create(JersyConfig);
			com.sun.jersey.api.client.WebResource 			service 	= 	client.resource(Configuration.SiteUrl);
			
			try
			{
				/*com.sun.jersey.api.client.ClientResponse clientresponse = service.path("REST")
																				 .path("userService")
																				 .path("userName")
																				 .path(request.getParameter("txtUsername"))
																				 .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON)
																				 .get(com.sun.jersey.api.client.ClientResponse.class);
				*/
				
				User user = FormFieldsClient.get(request.getParameter("txtUsername"));
				//out.println(clientresponse.getEntityInputStream());
				out.println("User ID from Hibernate is:"+user.getUser_id());
				
				
																			
			}catch(Exception e)
			{
				e.printStackTrace();				
			}
			
		}
		else
		{
			out.println("Loggin Not-Attempted<br/>");
			
			if(request.getSession(false)!=null)
			{
				out.println("Session is ready<br/>");
				if(session.getAttribute("Authorized")=="true")
				{
					//Carry on Sir/Ma'am
				}
				else
				{
					//GTFO!
					response.sendRedirect("login.jsp");
				}
			}
			else
			{
				out.println("Session isnt ready<br/>");
				request.getSession(true);
			    String AuthorizedVal = "false";
			    session.setAttribute("Authorized", AuthorizedVal);
			    //GTFO!
			    response.sendRedirect("login.jsp");				
			}
		}

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CharityWare</title>		
		
		<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/style1.css" type="text/css" media="all">	
	</head>
	
	<body id="page1">
	<div class="body1">
		<div class="main">
		
		<jsp:include page="Header.jsp"></jsp:include>
		   
	  <article id="content">
	      <div class="wrapper">
	        <div class="box1">
	         <div class="line1">
	            <div class="line2 wrapper">
	              <section class="col1">
	                <h2><strong>R</strong>ed<span>Cross</span></h2>
	                <div class="pad_bot1">
	                  <figure><img src="images/page1_img1.jpg" alt=""></figure>
	                </div>
	                CharityWare has helped our charity's efficiency by reaching out to people more quickly.  </section>
	              <section class="col1 pad_left1">
	                <h2 class="color2"><strong>G</strong>row<span>Peace</span></h2>
	                <div class="pad_bot1">
	                  <figure><img src="images/page1_img2.jpg" alt=""></figure>
	                </div>
	                Our workers are up to date with information and we have faster access to data exchange. Thanks to CharityWare! </section>
	              <section class="col1 pad_left1">
	                <h2 class="color3"><strong>H</strong>ope<span> </span></h2>
	                <div class="pad_bot1">
	                  <figure><img src="images/page1_img3.jpg" alt=""></figure>
	                </div>
	               We could reach out to children who were in need. We are surely benefiting from this. </section>
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
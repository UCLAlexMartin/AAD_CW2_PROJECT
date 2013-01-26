<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="ConnectionManager.*" %>
    <%@ page import= "java.util.TreeMap"%>
    <%@ page import= "java.util.ArrayList"%>
    <%@ page import= "java.util.Set"%>
    <%@ page import= "java.util.Map.Entry"%>
    <%@ page import= "java.util.Iterator"%> 
       
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

	     <div class="wrapper">
        <div class="box2">
          <div class="line1">
            <div class="line2 wrapper">
            <% 
            
            TreeMap<Integer,ArrayList<String>> datamap =  (TreeMap<Integer,ArrayList<String>>)DatabaseManager.readEvents();
        	Set<Entry<Integer,ArrayList<String>>> entryset = datamap.entrySet();
        	Iterator<Entry<Integer, ArrayList<String>>> iter =  entryset.iterator();
            
			while (iter.hasNext()){
				ArrayList<String> eventsDetails =  iter.next().getValue();
            %>
            <section class="col1">
           
                <h4><span><%=eventsDetails.get(0)%></span></h4>
                <p class="pad_bot2"><strong>TIME</strong><%=eventsDetails.get(1)%></p>
                 <p class="pad_bot2"><strong>VENUE</strong><%=eventsDetails.get(2)%></p>
                  <p >description</p>
                 </section>
              <%
              } %>
         
         
         
            </div>
          </div>
        </div>
      </div>
   
    <!-- / content -->

  </div>
</div>
</body>
</html>
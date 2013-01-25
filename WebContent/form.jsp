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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form>

<table>

<% 
	TreeMap<Integer,ArrayList<String>> datamap =  (TreeMap<Integer,ArrayList<String>>)DatabaseManager.readFormData(1);
	Set<Entry<Integer,ArrayList<String>>> entryset = datamap.entrySet();
	Iterator<Entry<Integer, ArrayList<String>>> iter =  entryset.iterator();
	while(iter.hasNext()){
		ArrayList<String> iterlist =  iter.next().getValue();
		//if(iterlist.get(1).equals("Textbox")){
			%>
			<tr>
				<td><label for="lbl<%=iterlist.get(0) %>"><%=iterlist.get(0) %></label></td>
			</tr>
			
			<tr>
				<td>
				
				<input type="<%=iterlist.get(1) %>" class="loginTextbox" style="width:240px;" name="txt<%=iterlist.get(0) %>" id="txt<%=iterlist.get(0) %>" 
				<% if (Integer.parseInt(iterlist.get(3)) == 1) { %>
					required
					<%} %>></td>
			</tr>
						
		<% //}else if ()
	}
%>

</table>

</form>
</body>
</html>
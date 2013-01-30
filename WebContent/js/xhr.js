function xhr(url,method,async,callback)
{
	var xmlhttp = newXhr();
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  if(typeof callback !== "undefined")
			  callback(xmlhttp.responseText);
	    }
	  };
	
	switch(method)
	{
	case "GET" : 
		xmlhttp.open(method,url,async);
		xmlhttp.send();
		break;
	case "POST" : 
		var location = url.split("?",2)[0];
		var params = url.split("?",2)[1];
		xmlhttp.open(method,location,async);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send(params);
		break;
	default: throw "Unaccepted xhr method!";
	}
	return xmlhttp.responseText;
}

function newXhr()
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	return xmlhttp;
}
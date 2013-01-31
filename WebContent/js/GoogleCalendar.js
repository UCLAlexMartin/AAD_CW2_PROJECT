
	      // Load the Visualization API and the piechart package.
	      google.load('visualization', '1.0', {'packages':['corechart']});
	
	      // Set a callback to run when the Google Visualization API is loaded.
	      google.setOnLoadCallback(drawChart);
	
	      // Callback that creates and populates a data table,
	      // instantiates the pie chart, passes in the data and
	      // draws it.
	      function populateData(data)
	      {
	    	  resp = xhr("/CharityWare/StatisticsDataServlet","GET",false);
	    	  obj = JSON.parse(resp);
	    	  data.addRows(obj);
	      }
	      function drawChart() {
	
	        // Create the data table.
	        var data = new google.visualization.DataTable();
	        data.addColumn('string', 'User');
	        data.addColumn('number', 'Records');
	        populateData(data);
	        var options = {'title':'Records inputted by each User',
	                       'width':500,
	                       'height':400};
	
	        // Instantiate and draw our chart, passing in some options.
	        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	        chart.draw(data, options);
	      }

	      function onBodyLoad()
	      {
	    	  tabSwitch(1,5,'tab_', 'content_');
	    	  document.getElementById("argc").value = 0;
		  viewCurrentFormStructure();
		  }
	      
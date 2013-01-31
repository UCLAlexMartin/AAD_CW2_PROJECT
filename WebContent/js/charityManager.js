function init()
{
	 tabSwitch(1,5,'tab_', 'content_');
	 document.getElementById("argc").value = 0;
	 removeChildren(document.getElementById("extra"));
	 removeChildren(document.getElementById("errmsg"));
	 document.getElementById("typeoptions").selectedIndex = 0;
	 document.getElementById("fieldname").value = "";
}

function hideFormWizard()
{
	var wizard = document.getElementById("formwizard");
	hide(wizard);
}

function deleteCurrentForm()
{
	if(confirm("Are you sure you want to delete form "+getCurrentFormName()+"?"))
	{
		xhr("/CharityWare/FormServlet?req=delete&q="+getCurrentFormId(),"POST",false);
		location.reload(true);
	}
}

function viewCurrentFormStructure()
{
	var formId = getCurrentFormId();
	xhr("/CharityWare/FormServlet?req=structure&q="+formId,"GET",true,writeFormStructure);
}
function writeFormStructure(respText) {
	var colArray = JSON.parse(respText);
	var currentRows = document.getElementById("currentformstructurefill");
	removeChildren(currentRows);
	unhide(currentRows.parentNode);
	if(colArray.length > 0)
	{
	var theLegend = createLegend("Form " + getCurrentFormName()+" structure");
	currentRows.appendChild(theLegend);
	var structureTable = document.createElement("table");
	var header = document.createElement("tr");
	var nameHead = document.createElement("th");
	nameHead.appendChild(document.createTextNode("Column name"));
	var typeHead = document.createElement("th");
	typeHead.appendChild(document.createTextNode("Column type"));
	header.appendChild(nameHead);
	header.appendChild(typeHead);
	structureTable.appendChild(header);
	for(var i = 0 ; i < colArray.length; i++)
	{
		var row = document.createElement("tr");
		var nameRow = document.createElement("td");
		nameRow.appendChild(document.createTextNode(colArray[i].field_label));
		var typeRow = document.createElement("td");
		typeRow.appendChild(document.createTextNode(colArray[i].field_type_id.field_Description));
		row.appendChild(nameRow);
		row.appendChild(typeRow);
		structureTable.appendChild(row);
	}
	structureTable.className = "gridtable";
	currentRows.appendChild(structureTable);
	}
	else 
		currentRows.appendChild(document.createTextNode("Sorry, this table has no columns defined"));
}

function viewCurrentFormData()
{
	var formId = getCurrentFormId();
	xhr("/CharityWare/FormServlet?req=records&q="+formId,"GET",true,writeFormData);
}

function writeFormData(respText)
{
	var dataArray = JSON.parse(respText);
	var currentData = document.getElementById("currentformdatafill");
	removeChildren(currentData);
	unhide(currentData.parentNode);
	if(dataArray.records.length > 0)
	{
	var theLegend = createLegend("Form "+getCurrentFormName()+" data");
	currentData.appendChild(theLegend);
	var dataTable = document.createElement("table");
	var headerRow = document.createElement("tr");
	for(var i = 0; i < dataArray.columns.length; i++)
	{
		var th = document.createElement("th");
		th.appendChild(document.createTextNode(dataArray.columns[i]));
		headerRow.appendChild(th);
	}
	dataTable.appendChild(headerRow);
	for(var i = 0; i < dataArray.records.length; i++)
	{
		var row = document.createElement("tr");
		for(var j = 0 ; j < dataArray.columns.length; j++)
		{
			var cell = document.createElement("td");
			cell.appendChild(document.createTextNode(dataArray.records[i].colVals[dataArray.columns[j]]) || "&nbsp;");
			row.appendChild(cell);
		}
		dataTable.appendChild(row);
	}
	dataTable.className = "gridtable";
	currentData.appendChild(dataTable);
	window.scrollTo(0,dataTable.offsetTop);
	}
	else 
		currentData.appendChild(document.createTextNode("Sorry, this table has no data"));
	
}
function getCurrentFormId()
{
	var myforms = document.getElementById("myformslist");
	return myforms.options[myforms.selectedIndex].value;
}
function getCurrentFormName()
{
	var myforms = document.getElementById("myformslist");
	return myforms.options[myforms.selectedIndex].text;
}

function showFormWizard()
{
	var wizard = document.getElementById("formwizard");
	unhide(wizard);
}


function createLegend(text)
{
	var l = document.createElement("legend");
	l.appendChild(document.createTextNode(text));
	return l;
}
function removeChildren(elem)
{
	while(elem.hasChildNodes())
		elem.removeChild(elem.lastChild);
}

function addRow()
{
	errMsg = document.getElementById("errmsg");
	try
	{
		hide(errMsg);
		addMe = constructRow();
		document.getElementById("rowsetrows").appendChild(addMe);
		
	}
	catch(err)
	{
		unhide(errMsg);
		errMsg.style.color="#FF0000";
		errMsg.innerHTML = err;
	}
}

function constructRow()
{
	checkWizard();
	var row = document.getElementById("rowsetrows");
    var argcbox = document.getElementById("argc");
    var count=0;
    if(!argcbox.hasAttribute("value"))
    {
    	count = 0;
    }
    else
    {
    	count = parseInt(argcbox.value);
    }
	var divelem = document.createElement("div");

	var inputName = createTextBox("name_"+count,document.getElementById("fieldname").value,true);
	var labelName = createLabel("Name:",inputName.id);
	var inputType = createTextBox("type_"+count+"_name",getCurrentRowTypeName(),true);	
	var labelType = createLabel("Type:",inputType.id);   
	var hiddenType = document.createElement("input");
	hiddenType.type="hidden";
	hiddenType.value=getCurrentRowTypeId();
	hiddenType.id="type_"+count;
	hiddenType.name=hiddenType.id;
    divelem.appendChild(labelName);
    divelem.appendChild(inputName);
    divelem.appendChild(labelType);
    divelem.appendChild(inputType);
    divelem.appendChild(hiddenType);
    
    
    var req = document.createElement("input");
    req.type = "checkbox";
    req.id = "isReq_"+count;
    req.name = req.id;
    req.checked = document.getElementById("rowrequired").checked;
    req.disabled = true;
    var reqLabel = createLabel("Mandatory",req.id);
    divelem.appendChild(reqLabel);
    divelem.appendChild(req);
    
    var btnRemove = createButton("Remove row", 
    		function() {
    			row.removeChild(divelem);
    			var countbox = document.getElementById("argc");
    			if(countbox.hasAttribute("value")) //better safe than sorry
    			{
    				var ct = parseInt(countbox.value);
    				if(ct > 0)
    					countbox.value = ct-1;
    			}
    		}	
    );
    
    divelem.appendChild(btnRemove);
    
    
    count++;
    argcbox.value = count;
    return divelem;
    
}

function getCurrentRowTypeName()
{
	
	    var opt = document.getElementById("typeoptions");
	    var ctype = opt.options[opt.selectedIndex].text;
	    //TODO comment this out when you're done with it
	    var valArray = new Array();
	    if(opt.selectedIndex == getIndexOfDropdownOption())
	    {
	    	var cmb = document.getElementById("currenumvalues");
	    	for(var i = 0; i < cmb.options.length; i++)
		    	valArray[i] = cmb.options[i].text;
	    	
	    	ctype += JSON.stringify(valArray);

	    }
	   
	    return ctype;
}
function getCurrentRowTypeId()
{
	var opt = document.getElementById("typeoptions");
    var ctype = opt.options[opt.selectedIndex].value;
    var valArray = new Array();
    if(opt.selectedIndex == getIndexOfDropdownOption())
    {
    	var cmb = document.getElementById("currenumvalues");
    	
    	for(var i = 0; i < cmb.options.length; i++)
	    	valArray[i] = cmb.options[i].text;
    	
    	ctype += JSON.stringify(valArray);
    }
    return ctype;
}

function createTextBox(id,value,readonly)
{
	var txtb = document.createElement("input");
	txtb.type="text";
	txtb.id = id;
	txtb.name = id;
	txtb.value = value;
	readonly = (typeof readonly === "undefined") ? false : readonly;
	txtb.readOnly = readonly;
	return txtb;
}

function createLabel(text,forId)
{
	var lb = document.createElement("label");
	lb.setAttribute("for",forId);
	lb.appendChild(document.createTextNode(text));
	return lb;
}

function createButton(text,onclickhandler,id)
{
	var btn = document.createElement("button");
	btn.type= "button";
	btn.appendChild(document.createTextNode(text));
	btn.onclick = onclickhandler;
	if(typeof id !== "undefined")
		btn.id = id;
	return btn;
}

function createForm()
{
	var submitMe = document.getElementById("rowset");
	var hiddenName = document.createElement("input");
	hiddenName.type = "hidden";
	formName = document.getElementById("formname");	
	hiddenName.name = "formname";
	hiddenName.value = formName.value;
	submitMe.appendChild(hiddenName);
	
	enableCheckboxes(submitMe);
	
	submitMe.submit();
}


function enableCheckboxes(form)
{
	var formElems = form.getElementsByTagName('INPUT');
    for (var i = 0; i < formElems.length; i++)
    {  
       if (formElems[i].type == 'checkbox')
       { 
          formElems[i].disabled = false;
       }
    }
}
function checkWizard()
{
	var name = document.getElementById("fieldname");
	if(name.value == "")
		throw "Please input a field name";
	//TODO: check if type selected is combo then the list must be non-empty!
}


function onRowTypeChanged()
{
	var select = document.getElementById("typeoptions");
	var extra = document.getElementById("extra");
	hide(extra);
	removeChildren(extra);
	whatToAppend(extra,select);
}

function whatToAppend(extra,select)
{
	switch(select.selectedIndex)
	{
	case getIndexOfDropdownOption() :
		{
		var combo = document.createElement("select");
		combo.id = "currenumvalues";
		var inputValue = createTextBox("enumcurritem","");
		var btnAdd = createButton("Add",
				function() {
					txt = document.getElementById("enumcurritem");
					if(txt.value != "")
					{
						opt = document.createElement("option");
						opt.value=txt.value;
						opt.text = txt.value;
						var append = true;
						if(!combo.hasChildNodes())
							append = true;
						else
						{
							for(var i = 0; i < combo.options.length;i++)
							{
								if(opt.value == combo.options[i].value)
								{
									append = false;
									break;
								}
							}
						}
						if(append) combo.appendChild(opt);
						txt.value = "";
					}
			
			}		
		);		
		var subdiv2 = document.createElement("div");
	    subdiv2.appendChild(combo);
	    var subdiv1 = document.createElement("div");
		subdiv1.appendChild(inputValue);
		subdiv1.appendChild(btnAdd);
		removeChildren(extra);
		extra.appendChild(subdiv1);
		extra.appendChild(subdiv2);
		unhide(extra);
		break;
		}
	default : hide(extra); removeChildren(extra); break;
	}
}

function hideCurrentFormStructure()
{
	hide(document.getElementById("currentformstructure"));
	removeChildren(document.getElementById("currentformstructurefill"));
}
function hideCurrentFormData()
{
	hide(document.getElementById("currentformdata"));
	removeChildren(document.getElementById("currentformdatafill"));
}


function onCurrentFormChanged()
{
	hideCurrentFormStructure();
	hideCurrentFormData();
}


function hide(element)
{
	element.className = "nodisplay";
}

function unhide(element)
{
	element.className = "yesdisplay";
}

function getIndexOfDropdownOption()
{
	var select = document.getElementById("typeoptions");
	for(var i = 0; i < select.options.length; i++)
	{
		if(select.options[i].text == "Dropdown")
			return i;
	
	}
	return -1;
}
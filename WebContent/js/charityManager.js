function viewCurrentFormStructure(sender)
{
	var formId = sender.parentNode.getElementsByTagName("input")[0].value;
	xhr("/CharityWare/FormStructureServlet?q="+formId,true,
			function(respText) {
				var colArray = JSON.parse(respText);
				var currentRows = document.getElementById("currentformrows");
				removeChildren(currentRows);
				unhide(currentRows);
				var theLegend = createLegend("Form "+sender.parentNode.getElementsByTagName("input")[1].value+":");
				currentRows.appendChild(theLegend);
				for(var i = 0; i < colArray.length; i++)
				{
					var div = document.createElement("div");
					var inputname = createTextBox("crn_"+i,colArray[i].fieldLabel,true);
					var inputtype = createTextBox("crt_"+i,colArray[i].field_type_id,true);
					var namelabel = createLabel("Field name:",inputname.id);
					var typelabel = createLabel("Field type:",inputtype.id);
					div.appendChild(namelabel);
					div.appendChild(inputname);
					div.appendChild(typelabel);
					div.appendChild(inputtype);
					currentRows.appendChild(div);
				}
				
	}
	
	);
}

function showWizard(obj)
{
	obj.innerHTML="Hide wizard";
	obj.onclick = function() { hideWizard(obj); };
	var wizard = document.getElementById("formwizard");
	unhide(wizard);
}

function hideWizard(obj)
{
	obj.innerHTML="Add new form";
	obj.onclick = function() { showWizard(obj); };
	var wizard = document.getElementById("formwizard");
	hide(wizard);
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
	var inputType = createTextBox("type_"+count,getCurrentRowType(),true);	
	var labelType = createLabel("Type:",inputType.id);   
    divelem.appendChild(labelName);
    divelem.appendChild(inputName);
    divelem.appendChild(labelType);
    divelem.appendChild(inputType);
    
    
    var req = document.createElement("input");
    req.type = "checkbox";
    req.id = "req_"+count;
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

function getCurrentRowType()
{
	
	    var opt = document.getElementById("typeoptions");
	    var ctype = opt.options[opt.selectedIndex].value;
	    //yuck -- Please make sure that 'dropdown' is the last value in the allowed data types, two functions rely on that assumption!
	    if(opt.selectedIndex == opt.options.length - 1)
	    {
	    	var cmb = document.getElementById("currenumvalues");
	    	ctype+="[";
	    	for(var i = 0; i < cmb.options.length-1; i++)
	    	{
	    		ctype+="'"+cmb.options[i].value+"',";
	    	}
	    	ctype+="'"+cmb.options[cmb.options.length-1].value+"']";
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

function submitForm()
{
	var submitMe = document.getElementById("rowset");
	var hiddenName = document.createElement("input");
	var hiddenDesc = document.createElement("description");
	hiddenName.type = "hidden";
	hiddenDesc.type = "hidden";
	formName = document.getElementById("formname");
	formDesc = document.getElementById("formdesc");
	
	hiddenName.name = "formname";
	hiddenDesc.name = "formdesc";
	
	hiddenName.value = formName.value;
	hiddenDesc.value = formDesc.value;
	
	submitMe.appendChild(hiddenName);
	submitMe.appendChild(hiddenDesc);
	
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
	whatToAppend(extra,select);
	
	/*
	<option value="int" selected>Integer</option>
    <option value="string">String</option>
    <option value="datetime">Date</option>
    <option value="text">Text</option>
    <option value="bool">Yes/No</option>
    <option value="img">Image</option>
    <option value="enum">Dropdown</option>
	 */
}

function whatToAppend(extra,select)
{
	switch(select.selectedIndex)
	{
	case select.options.length - 1 :
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


function hide(element)
{
	element.className = "nodisplay";
}

function unhide(element)
{
	element.className = "yesdisplay";
}
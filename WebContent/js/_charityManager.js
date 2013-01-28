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
		errMsg.className = "nodisplay";
		addMe = constructRow();
		document.getElementById("rowsetrows").appendChild(addMe);
		
	}
	catch(err)
	{
		errMsg.className="yesdisplay";
		errMsg.style.color="#FF0000";
		errMsg.innerHTML = err;
	}
}

function constructRow()
{
	checkWizard();
	var row = document.getElementById("rowsetrows");
	var count = row.hasChildNodes() ? row.childNodes.length : 0 ;
	var divelem = document.createElement("div");
	var labelName = document.createElement("label");
	labelName.appendChild(document.createTextNode("Name:"));
	var inputName = document.createElement("input");
	inputName.type="text";
	inputName.disabled = true;
	inputName.id="row_"+count+"_name";
	labelName.setAttribute("for",inputName.id);
	var labelType = document.createElement("label");
	labelType.appendChild(document.createTextNode("Type:"));
	var inputType = document.createElement("input");
	inputType.type="text";
	inputType.disabled = true;
	inputType.id="row_"+count+"_type";
	labelType.setAttribute("for",inputType.id);	
	inputName.value = document.getElementById("fieldname").value ;
	var opt = document.getElementById("typeoptions");
    inputType.value = opt.options[opt.selectedIndex].value;
    divelem.appendChild(labelName);
    divelem.appendChild(inputName);
    divelem.appendChild(labelType);
    divelem.appendChild(inputType);
    var btnRemove = document.createElement("button");
    btnRemove.type="button";
    btnRemove.appendChild(document.createTextNode("Remove"));
    btnRemove.onclick = function() {
    	row.removeChild(divelem);
    };
    
    //yuck
    if(opt.selectedIndex == opt.options.length - 1)
    {
    	var cmb = document.getElementById("currenumvalues");
    	inputType.value+="(";
    	for(var i = 0; i < cmb.options.length-1; i++)
    	{
    		inputType.value+="'"+cmb.options[i].value+"',";
    	}
    	inputType.value+="'"+cmb.options[cmb.options.length-1].value+"')";
    }
    					
    divelem.appendChild(btnRemove);
    return divelem;
    
}

function submitForm(sender)
{
	var submitMe = document.getElementById("rowsetrows");
	submitMe.submit();
}
function removeRow(sender)
{
	var remove = sender.parentNode;
	var rows = remove.parentNode;
	rows.removeChild(remove);
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
		combo.id = "currenumvalues"
		var btnAdd = document.createElement("button");
		btnAdd.type="button";
		var inputValue = document.createElement("input");
		inputValue.type="text";
		inputValue.id = "enumcurritem";
		btnAdd.appendChild(document.createTextNode("Add"));
		btnAdd.onclick = function() {
			txt = document.getElementById("enumcurritem");
			if(txt.value != "")
				{
				opt = document.createElement("option");
				opt.value=txt.value;
				opt.text = txt.value;
				combo.appendChild(opt);
				txt.value = "";
				}
			
		};
		
		var subdiv2 = document.createElement("div");
	    subdiv2.appendChild(combo);
	    var subdiv1 = document.createElement("div");
		subdiv1.appendChild(inputValue);
		subdiv1.appendChild(btnAdd);
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

function removeRow(sender)
{
	sender.parentNode.removeChild(sender);
}
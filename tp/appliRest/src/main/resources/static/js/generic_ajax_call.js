function errCallback(err,responseStatus){
	if(err)
	   document.getElementById('spanResponse').innerText="error:" + err;
	else if(responseStatus==400)  document.getElementById('spanResponse').innerText="BAD_REQUEST";
	document.getElementById('spanStatus').innerHTML="<b>"+responseStatus+"</b>";
}

function genericCallback(response,responseStatus){
	document.getElementById('spanResponse').innerText=response;
	document.getElementById('spanStatus').innerHTML="<b>"+responseStatus+"</b>";
}


function onSend(evt){
	let mode = document.getElementById("selMode").value;
	let apiUrl = document.getElementById("txtApiUrl").value;
	let wsEndUrl = document.getElementById("txtWsEndUrl").value;
	let jsonData = document.getElementById("txtJsonData").value;
	var url = apiUrl + "/" + wsEndUrl;
	switch(mode){
	   case "GET" : makeAjaxGetRequest(url,genericCallback,errCallback); break;
	   case "POST" : makeAjaxPostRequest(url,jsonData,genericCallback,errCallback); break;
	   case "PUT" : makeAjaxPutRequest(url,jsonData,genericCallback,errCallback); break;
	   case "DELETE" : makeAjaxDeleteRequest(url,genericCallback,errCallback); break;
	}
}



window.onload=function(){
	document.getElementById("btnSend").addEventListener("click",onSend);
	document.getElementById("selMode").addEventListener("change" , function (evt){
		let mode = evt.target.value;
		if(mode=="GET" || mode=="DELETE")
		   document.getElementById("txtJsonData").style.display="none";
		else
		   document.getElementById("txtJsonData").style.display="block";
	});
	document.getElementById("selMode").value="GET"; //by default
	document.getElementById("txtJsonData").style.display="none";//by default
}
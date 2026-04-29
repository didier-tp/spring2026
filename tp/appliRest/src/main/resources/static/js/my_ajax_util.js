//subfunction with errCallback as optional callback
function registerCallbacks(xhr,callback,errCallback) {
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4){
			if((xhr.status == 200 || xhr.status == 204 ||  xhr.status == 201)) {
				//200: OK , 201: created (after good POST) , 204 : NoContent after good delete
				callback(xhr.responseText,xhr.status);
			}
			else {
				if(errCallback)
				     errCallback(xhr.responseText,xhr.status);
			}
		}
	};
}

function injectAuthBearerTokenInRequestHeader(xhrReq){
	authToken = sessionStorage.getItem("authToken");
	if(authToken!=null && authToken!="")
	    xhrReq.setRequestHeader('Authorization', 'Bearer ' + authToken);
}

function makeAjaxGetRequest(url,callback,errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr,callback,errCallback);
	xhr.open("GET", url, true);
	injectAuthBearerTokenInRequestHeader(xhr);
	xhr.send(null);
}

function makeAjaxDeleteRequest(url,callback,errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr,callback,errCallback);
	xhr.open("DELETE", url, true);
	injectAuthBearerTokenInRequestHeader(xhr);
	xhr.send(null);
}

function makeAjaxPostRequest(url,jsonData,callback,errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr,callback,errCallback);
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	injectAuthBearerTokenInRequestHeader(xhr);
	xhr.send(jsonData);
}

function makeAjaxPostMultipartRequest(url,formData,callback,errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr,callback,errCallback);
	xhr.open("POST", url, true);
	//xhr.setRequestHeader("Content-Type","multipart/form-data; boundary=....."); 
	//par defaut le "Content-Type" est bien renseigné avec bon boundary
	injectAuthBearerTokenInRequestHeader(xhr);
	xhr.send(formData);
}

function makeAjaxPutRequest(url,jsonData,callback,errCallback) {
	var xhr = new XMLHttpRequest();
	registerCallbacks(xhr,callback,errCallback);
	xhr.open("PUT", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	injectAuthBearerTokenInRequestHeader(xhr);
	xhr.send(jsonData);
}
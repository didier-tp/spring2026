
var stompClient = null;

function setConnected(connected) {
	document.getElementById('connect').disabled = connected;
	document.getElementById('disconnect').disabled = !connected;
	document.getElementById('conversationDiv').style.visibility
		= connected ? 'visible' : 'hidden';
	document.getElementById('response').innerHTML = '';
}

function connect() {
	let from = document.getElementById('from').value;
	if(from=="") return;
	let socket = new SockJS('/appliSpring/my-ws');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		
		stompClient.subscribe('/topic/messages', function(messageOutput) {
			showMessageOutput(JSON.parse(messageOutput.body));
		});
		
		stompClient.send("/app/chat", {}, 
		                  JSON.stringify({ type: "JOIN" , sender: from, content: "is connected" }));
	});
}

function disconnect() {
	let from = document.getElementById('from').value;
	if (stompClient != null) {
		stompClient.send("/app/chat", {}, 
		                  JSON.stringify({ type: "LEAVE" , sender: from, content: "is disconnected" }));
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendMessage() {
	let from = document.getElementById('from').value;
	let text = document.getElementById('text').value;
	stompClient.send("/app/chat", {},
		JSON.stringify({ type: "CHAT" , sender: from, content: text /* , time : null */}));
}

function showMessageOutput(messageOutput) {
	var response = document.getElementById('response');
	var li = document.createElement('li');
	li.innerHTML="<b>"+messageOutput.sender + "</b> :<i> "+ messageOutput.content + "</i> (" + messageOutput.time + ")";
	response.appendChild(li);
}

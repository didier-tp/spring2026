
var stompClient = null;

function setConnected(connected) {
	document.getElementById('connect').disabled = connected;
	document.getElementById('disconnect').disabled = !connected;
	document.getElementById('whenConnectedDiv').style.visibility
		= connected ? 'visible' : 'hidden';
	document.getElementById('taskListUl').innerHTML = '';
}

function connect() {
	let from = document.getElementById('from').value;
	if(from=="") return;
	let socket = new SockJS('/appliSpring/my-ws');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		
		stompClient.subscribe('/topic/tasks', function(messageOutput) {
			showMessageOutput(JSON.parse(messageOutput.body));
		});
		
		stompClient.send("/app/task", {},
		                  JSON.stringify({ type: "JOIN" , sender: from }));
	});
}

function disconnect() {
	let from = document.getElementById('from').value;
	if (stompClient != null) {
		stompClient.send("/app/task", {},
		                  JSON.stringify({ type: "LEAVE" , sender: from }));
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendDoneMessage() {
	let from = document.getElementById('from').value;
	let taskIdToComplete = document.getElementById('taskIdToComplete').value;
	let doneTaskResponse = document.getElementById('doneTaskResponse').value;
	stompClient.send("/app/task", {},
		JSON.stringify({ type: "DONE_TASK" , sender: from,
		                task: { numero : taskIdToComplete ,
		                       author: from ,
		                       response : doneTaskResponse } }));
}

function sendNewMessage() {
	let from = document.getElementById('from').value;
	let titleNewTask = document.getElementById('titleNewTask').value;
	let requestNewTask = document.getElementById('requestNewTask').value;
	stompClient.send("/app/task", {},
		JSON.stringify({ type: "NEW_TASK" , sender: from,
		                task: { title : titleNewTask ,
		                       request : requestNewTask } }));
}

function showMessageOutput(messageOutput) {
	let msg = document.getElementById('msg');
	let taskListUl = document.getElementById('taskListUl');
	taskListUl.innerHTML = '';
	msg.innerHTML="<b>"+messageOutput.message + "</b>";
	for(task of messageOutput.tasks) {
        let li = document.createElement('li');
        let subHtmlTask = task.response?"<b>"+JSON.stringify(task) +"</b>":JSON.stringify(task);
        li.innerHTML= subHtmlTask;
        taskListUl.appendChild(li);
    }

}

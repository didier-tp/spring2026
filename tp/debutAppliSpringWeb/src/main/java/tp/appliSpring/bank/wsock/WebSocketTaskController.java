package tp.appliSpring.bank.wsock;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import tp.appliSpring.bank.core.model.Task;
import tp.appliSpring.bank.core.service.ServiceTask;
import tp.appliSpring.generic.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@Slf4j
public class WebSocketTaskController {

	@Autowired
	private ServiceTask serviceTask;

	private List<Task> lastTasksList = new ArrayList<Task>();
	private String lastMessage = "";

	public void updateDoneTask(TaskMessage doneTaskMessage) {
		Task partialDoneTask = doneTaskMessage.getTask();
		long numero = partialDoneTask.getNumero();
		String author = partialDoneTask.getAuthor();
		String doneTaskResponse = partialDoneTask.getResponse();
        try {
            Task task = serviceTask.searchById(numero);
            task.setResponse(doneTaskResponse);
            task.setAuthor(author);
            task.setTimestamp(new Date());
            serviceTask.update(task);
            this.lastMessage="Task Done by "+author + " with numero=" + numero;
        } catch (EntityNotFoundException e) {
			this.lastMessage="WrongTask Done by "+author + " with non existing numero=" + numero;
        }
    }

	public void newTask(TaskMessage newTaskMessage) {
		Task partialNewTask = newTaskMessage.getTask();
	    String sender = newTaskMessage.getSender();
		Task savedTask = serviceTask.create(partialNewTask);
		long numero = savedTask.getNumero();
		this.lastMessage="New Task created by "+sender + " with numero=" + numero;
	}

	@MessageMapping("/task") //input message received from /chat
	@SendTo("/topic/tasks") //output message pubish to /topic/messages
	public TaskListMessage send(TaskMessage taskMessage) throws Exception {
	    TaskListMessage taskListMessage= new TaskListMessage();
		switch(taskMessage.getType()) {
			case "NEW_TASK":
				newTask(taskMessage);
				break;
			case "DONE_TASK":
				updateDoneTask(taskMessage);
				break;
			default:
				//JOIN or LEAVE:
				this.lastMessage=taskMessage.getSender() + " : " + taskMessage.getType();
		}
		this.lastTasksList=serviceTask.searchAll();
		taskListMessage.setTasks(this.lastTasksList);
		taskListMessage.setMessage(lastMessage);
        log.debug("WebSocketTaskController.lastTasksList="+this.lastTasksList);
		return taskListMessage;
	}

}
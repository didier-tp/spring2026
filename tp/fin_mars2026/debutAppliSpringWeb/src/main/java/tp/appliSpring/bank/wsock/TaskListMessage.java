package tp.appliSpring.bank.wsock;

import tp.appliSpring.bank.core.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskListMessage {
	    private String message;
	    private List<Task> tasks;

		public TaskListMessage(String message, List<Task> tasks) {
			super();
			this.message = message;
			this.tasks = tasks;
		}

	    public TaskListMessage(String message) {
			this(message,new ArrayList<Task>());
		}

		public TaskListMessage(){
			this(null);
		}

	@Override
	public String toString() {
		return "TaskListMessage{" +
				"message='" + message + '\'' +
				", tasks=" + tasks +
				'}';
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}

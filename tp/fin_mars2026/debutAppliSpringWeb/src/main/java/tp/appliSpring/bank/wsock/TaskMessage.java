package tp.appliSpring.bank.wsock;

import tp.appliSpring.bank.core.model.Task;

public class TaskMessage {

	    public enum MessageType { NEW_TASK, DONE_TASK, JOIN, LEAVE }

	    private String type; //MessageType as String
	    private String sender;//or from
	    private Task task;

	    public TaskMessage(String type, String sender) {
			this(type,sender,new Task());
		}

		public TaskMessage() {
			this(null,null);
		}

		public TaskMessage(String type, String sender, Task task) {
			this.type = type;
			this.sender = sender;
			this.task = task;
		}

	@Override
	public String toString() {
		return "TaskMessage{" +
				"type='" + type + '\'' +
				", sender='" + sender + '\'' +
				", task=" + task +
				'}';
	}

	public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getSender() {
	        return sender;
	    }

	    public void setSender(String sender) {
	        this.sender = sender;
	    }

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}

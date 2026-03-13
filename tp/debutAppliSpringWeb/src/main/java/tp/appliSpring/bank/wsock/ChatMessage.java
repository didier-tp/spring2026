package tp.appliSpring.bank.wsock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage {
	
	    public enum MessageType { CHAT, JOIN, LEAVE }
	    
	    private String type; //MessageType as String 
	    private String sender;//or from
	    private String content;//or text
	    private String time;

	    
	    public ChatMessage() {
		}

		public ChatMessage(String type, String sender, String content, String time) {
			super();
			this.type = type;
			this.sender = sender;
			this.content = content;
			this.time = time;
		}
	    
	    public ChatMessage(String type, String sender, String content) {
			this(type,sender,content,new SimpleDateFormat("HH:mm:ss").format(new Date()));
		}

		@Override
		public String toString() {
			return "ChatMessage [type=" + type + ", sender=" + sender + ", content=" + content + ", time=" + time + "]";
		}

		public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

	    public String getSender() {
	        return sender;
	    }

	    public void setSender(String sender) {
	        this.sender = sender;
	    }

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}
	    
	    
}

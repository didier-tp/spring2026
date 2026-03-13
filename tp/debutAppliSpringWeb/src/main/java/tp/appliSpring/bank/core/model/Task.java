package tp.appliSpring.bank.core.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tp.appliSpring.generic.model.WithId;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Task implements WithId<Long>  {

    private Long numero;

	@Override
	public Long extractId() {
		return this.numero;
	}

    private String title;

	private String request;

	private String response; //may be null

	private Date timestamp; //may be null

    private String author; //may be null

	public Task(Long numero, String title, String request, String response, Date timestamp, String author) {
		this.numero = numero;
		this.title = title;
		this.request = request;
		this.response = response;
		this.timestamp = timestamp;
		this.author = author;
	}

	public Task(Long numero, String title, String request){
		this(numero, title, request, null, null, null);
	}

}
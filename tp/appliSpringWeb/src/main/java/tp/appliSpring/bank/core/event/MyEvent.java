package tp.appliSpring.bank.core.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class MyEvent {
    private String text;
    private LocalDateTime dateTime;

    public MyEvent(String text){
        this.text=text;
        this.dateTime=LocalDateTime.now();
    }

    public MyEvent(){
        this("unknown_event");
    }
}


/*
to be sent by this.applicationEventPublisher.publishEvent(new MyEvent("searchAll was called"));
exemple : in ServiceCompteImpl.searchAll()

 */
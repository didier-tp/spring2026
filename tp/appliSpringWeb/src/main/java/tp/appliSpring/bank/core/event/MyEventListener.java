package tp.appliSpring.bank.core.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class MyEventListener {

    @Async //si @Async , le listener  est censé être exécuté de manière asynchrone sans bloquer this.applicationEventPublisher.publishEvent()
    //sans @Async , this.applicationEventPublisher.publishEvent() attend la fin de l'execution de tous les event-listeners

    //@Order(1)
    //@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT) //only fired after succesfull transaction
    //@TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK) //only fired after rollback transaction
    //@EventListener(condition = "#event.text.startsWith('searchAll')")
    @EventListener
    //this method will be automatically fired when
    //applicationEventPublisher.publishEvent(new MyEvent(...)) will be called
    //because arg of logMyEvent is of type MyEvent
    public void logMyEvent(MyEvent event) {
        log.info(event.toString() + " in MyEventListener executed by " + Thread.currentThread().getName());
    }

}

package tp.entity.purchase;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name="purchase" , schema = "purchase_db") //schema = "purchase_db" for postgres version initialized by flyway
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_stamp")
    private LocalDateTime dateTime;

    @Column(name = "customer_id")
    private Long cutomerId;

    private double amount;

    @Column(name = "order_id")
    private Long orderId;

    public Purchase(Long id, LocalDateTime dateTime, long cutomerId, double amount,long orderId) {
        this.id = id;
        this.dateTime = dateTime;
        this.cutomerId = cutomerId;
        this.amount = amount;
        this.orderId=orderId;
    }

    public Purchase(Long id, long cutomerId, double amount,long orderId){
        this(id,LocalDateTime.now(),cutomerId,amount,orderId);
    }

}

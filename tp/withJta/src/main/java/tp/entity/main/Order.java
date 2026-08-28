package tp.entity.main;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_order")  //table name must not be "order" (reserved sql key word)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="time_stamp")
    private LocalDateTime dateTime;

    @Column(name="customer_id")
    private Long customerId;

    @OneToMany(mappedBy = "order" , fetch = FetchType.EAGER , cascade = { CascadeType.ALL })
    private List<OrderLine> lines;

    public void addOrderLine(long productId,int quantity){
        OrderLinePk linePk = new OrderLinePk(productId,this.getId());
        OrderLine line = new OrderLine(linePk,this,quantity);
        if(this.lines==null)
            this.lines=new ArrayList<>();
        this.lines.add(line);
    }

    public Order(Long id, LocalDateTime dateTime, Long customerId, List<OrderLine> lines) {
        this.id = id;
        this.dateTime = dateTime;
        this.customerId = customerId;
        this.lines = lines;
    }

    public Order(Long customerId){
        this(null,LocalDateTime.now(),customerId,new ArrayList<>());
    }
}

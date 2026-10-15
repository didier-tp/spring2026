package tp.entity.purchase;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name="purchase" , schema = "purchase_db") //schema = "purchase_db" for postgres version initialized by flyway

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getCutomerId() {
        return cutomerId;
    }

    public void setCutomerId(Long cutomerId) {
        this.cutomerId = cutomerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", cutomerId=" + cutomerId +
                ", amount=" + amount +
                ", orderId=" + orderId +
                '}';
    }

    public Purchase() {
    }
}

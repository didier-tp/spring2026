package tp.entity.main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


public class OrderLinePk implements Serializable {
    private Long productId;
    private Long orderId;

    public OrderLinePk(Long productId, Long orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderLinePk() {
    }

    @Override
    public String toString() {
        return "OrderLinePk{" +
                "productId=" + productId +
                ", orderId=" + orderId +
                '}';
    }
}

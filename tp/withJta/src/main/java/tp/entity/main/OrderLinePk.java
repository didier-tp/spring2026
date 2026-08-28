package tp.entity.main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderLinePk implements Serializable {
    private Long productId;
    private Long orderId;

    public OrderLinePk(Long productId, Long orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }
}

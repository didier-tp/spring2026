package tp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class ProductSelection {
    private long productId;
    private int quantity;

    public ProductSelection(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}

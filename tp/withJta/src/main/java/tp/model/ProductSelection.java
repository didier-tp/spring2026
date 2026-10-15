package tp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class ProductSelection {
    private long productId;
    private int quantity;

    public ProductSelection(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductSelection() {
    }

    @Override
    public String toString() {
        return "ProductSelection{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}

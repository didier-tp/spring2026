package tp.entity.main;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="customer")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    //...


    public Customer(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}

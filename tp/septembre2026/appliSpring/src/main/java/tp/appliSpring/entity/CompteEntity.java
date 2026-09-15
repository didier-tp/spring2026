package tp.appliSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="compte")
@Getter @Setter
public class CompteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private String label;

    private Double solde;

    public CompteEntity(Long numero, String label, Double solde) {
        this.numero = numero;
        this.label = label;
        this.solde = solde;
    }

    public CompteEntity(){
        this(null,null,null);
    }

    @Override
    public String toString() {
        return "CompteEntity{" +
                "numero=" + numero +
                ", label='" + label + '\'' +
                ", solde=" + solde +
                '}';
    }
}

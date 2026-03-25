package tp.mySpringBoot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private String label;

    private Double solde;

    @Override
    public String toString() {
        return "Compte{" +
                "numero=" + numero +
                ", label='" + label + '\'' +
                ", solde=" + solde +
                '}';
    }

    @JsonIgnore
    @OneToMany(mappedBy = "compte" , fetch = FetchType.LAZY) //valeur de mappedBy = nom java de la relation inverse
    private List<Operation> operations; //avec get/set

    public Compte(Long numero, String label, Double solde) {
        this.numero = numero;
        this.label = label;
        this.solde = solde;
    }
}

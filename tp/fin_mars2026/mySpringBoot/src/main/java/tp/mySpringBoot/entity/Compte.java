package tp.mySpringBoot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private String label;

    private Double solde;

    @OneToMany(mappedBy = "compte") //valeur de mappedBy = nom java de la relation inverse
    private List<Operation> operations; //avec get/set

    public Compte(Long numero, String label, Double solde) {
        this.numero = numero;
        this.label = label;
        this.solde = solde;
    }
}

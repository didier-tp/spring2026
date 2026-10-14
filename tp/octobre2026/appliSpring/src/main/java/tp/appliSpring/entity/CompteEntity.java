package tp.appliSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="compte")
@Getter @Setter
@NoArgsConstructor
@ToString
public class CompteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private String label;
    private Double solde;

    @OneToMany(mappedBy = "compte" , fetch = FetchType.LAZY)
    private List<OperationEntity> operations;

    @ManyToMany(mappedBy = "comptes" , fetch = FetchType.LAZY)
    private List<ClientEntity> clients;

    public CompteEntity(Long numero, String label, Double solde) {
        this.numero = numero;
        this.label = label;
        this.solde = solde;
    }
}

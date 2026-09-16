package tp.appliSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="operation")
@Getter
@Setter
public class OperationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;


    @Column(name="label" , length = 64)
    private String label; //"achat 1" , ...

    private Double montant; //négatif pour un achat/debit , positif pour un credit

    @Column(name="date_op")
    private LocalDate dateOp;

    @ManyToOne
    @JoinColumn(name = "num_compte") //nom clef etrangére
    //@JsonIgnore
    private CompteEntity compte;

    @Override
    public String toString() {
        return "OperationEntity{" +
                "numero=" + numero +
                ", label='" + label + '\'' +
                ", montant=" + montant +
                ", dateOp=" + dateOp +
                '}';
    }

    public OperationEntity(Long numero, String label, Double montant, LocalDate dateOp, CompteEntity compte) {
        this.numero = numero;
        this.label = label;
        this.montant = montant;
        this.dateOp = dateOp;
        this.compte = compte;
    }

    public OperationEntity() {
    }
}

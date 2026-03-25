package tp.mySpringBoot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero ;

    private Double montant ;

    private String label;

    @Column(name="date_op")
    @Temporal(TemporalType.DATE) //en base "2026-03-25"
    private Date dateOp;

    //+ lien n-1 vers compte
    @ManyToOne()
    @JoinColumn(name="num_compte") //fk
    private Compte compte;

    public Operation(Long numero, Double montant, String label, Date dateOp, Compte compte) {
        this.numero = numero;
        this.montant = montant;
        this.label = label;
        this.dateOp = dateOp;
        this.compte = compte;
    }
    public Operation(Long numero, Double montant, String label, Date dateOp){
        this(numero,montant,label,dateOp,null);
    }
    public Operation(Long numero, Double montant, String label){
        this(numero,montant,label,new Date());
    }
}

package tp.appliSpring.bank.core.model;

import tp.appliSpring.generic.model.WithId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class Operation implements WithId<Long> {
    private Long numero;

    @Override
    public Long extractId() {
        return this.numero;
    }

    private String label;

    private Double montant;

    private Date dateOp;

    public Operation(Long numero, String label, Double montant, Date dateOp) {
        this.numero = numero;
        this.label = label;
        this.montant = montant;
        this.dateOp = dateOp;
    }

    public Operation(Long numero, String label, Double montant){
        this(numero,label,montant,new Date());
    }

}

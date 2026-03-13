package tp.appliSpring.bank.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tp.appliSpring.generic.model.WithId;

@Getter
@Setter
@ToString @NoArgsConstructor
public class Compte implements WithId<Long> {
    private Long numero;

    private String label;

    private Double solde;

    public Compte(Long numero, String label, Double solde) {
        this.numero = numero;
        this.label = label;
        this.solde = solde;
    }

    @Override
    public Long extractId() {
        return this.numero;
    }

}

package tp.appliSpring.bank.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tp.appliSpring.generic.model.WithId;
import tp.appliSpring.generic.model.WithIdAsString;

@Getter
@Setter
@ToString @NoArgsConstructor
public class Compte implements WithIdAsString {
    private String numero;

    private String label;

    private Double solde;

    public Compte(String numero, String label, Double solde) {
        this.numero = numero;
        this.label = label;
        this.solde = solde;
    }

    public Compte(long numero, String label, Double solde) {
        this.numero = String.valueOf(numero);
        this.label = label;
        this.solde = solde;
    }

    @Override
    public String extractId() {
        return this.numero;
    }

}

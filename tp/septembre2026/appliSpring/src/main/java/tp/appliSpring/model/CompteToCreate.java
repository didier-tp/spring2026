package tp.appliSpring.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/* classe sans annotation jpa , model ou DTO (Data Transfert Object) */
@Getter @Setter
@ToString
public class CompteToCreate extends Compte{

    @Override
    @Schema( description = "numéro de compte", defaultValue = "null" , hidden = true)
    public Long getNumero() {
        return super.getNumero();
    }

    public CompteToCreate(Long numero, String label, Double solde) {
        super(numero, label, solde);
    }

    public CompteToCreate() {
    }
}

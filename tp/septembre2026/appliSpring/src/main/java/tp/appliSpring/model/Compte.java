package tp.appliSpring.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/* classe sans annotation jpa , model ou DTO (Data Transfert Object) */
@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Compte {

    @Schema( description = "numéro de compte", defaultValue = "null")
    private Long numero;

    @Schema( description = "libellé du compte", defaultValue = "CompteXy")
    private String label;
    private Double solde;
}

package tp.appliSpring.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/* classe sans annotation jpa , model ou DTO (Data Transfert Object) */
@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Compte {

    @Schema( description = "numéro de compte", defaultValue = "null")
    private Long numero;

    @Schema( description = "libellé du compte", defaultValue = "CompteXy")
    @Length(min = 2 , max = 32 , message = "le label doit comporter 2 à 32 caractères")
    private String label;

    private Double solde;
}

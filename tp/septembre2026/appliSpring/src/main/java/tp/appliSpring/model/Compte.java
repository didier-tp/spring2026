package tp.appliSpring.model;

import lombok.*;

/* classe sans annotation jpa , model ou DTO (Data Transfert Object) */
@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Compte {
    private Long numero;
    private String label;
    private Double solde;
}

package tp.appliSpring.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Operation {

    private Long numero;
    private String label; //"achat 1" , ...
    private Double montant; //négatif pour un achat/debit , positif pour un credit
    private LocalDate dateOp;
}

package tp.appliSpring.AppliSpringWeb.entity;

import jakarta.persistence.*;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
@Entity //entity persistante en base de données
public class Compte {

    @Id //identifiant , clef primaire **
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pour auto_incrémentation
    private Long numero;

    private String label;

    private double solde;
}

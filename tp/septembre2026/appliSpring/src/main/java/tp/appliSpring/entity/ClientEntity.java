package tp.appliSpring.entity;

import jakarta.persistence.*;

@Entity
@Table(name="client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
}

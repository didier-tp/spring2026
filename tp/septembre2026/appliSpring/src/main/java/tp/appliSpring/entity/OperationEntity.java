package tp.appliSpring.entity;

import jakarta.persistence.*;

@Entity
@Table(name="operation")
public class OperationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
}

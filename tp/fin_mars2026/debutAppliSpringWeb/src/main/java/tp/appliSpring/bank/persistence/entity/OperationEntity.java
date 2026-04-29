package tp.appliSpring.bank.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name="Operation")
@Getter
@Setter
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="numero")
    private Long numero;
    
    @Column(name="label" , length = 64)
    private String label;
    
    private Double montant;
    
    private Date dateOp;
    
    @ManyToOne
    @JoinColumn(name = "num_compte")
    //@JsonIgnore
    private CompteEntity compte;
    

	public OperationEntity() {
		super();
	}

	public OperationEntity(Long numero, String label, Double montant, Date dateOp) {
		super();
		this.numero = numero;
		this.label = label;
		this.montant = montant;
		this.dateOp = dateOp;
	}

	@Override
	public String toString() {
		return "Operation [numero=" + numero + ", label=" + label + ", montant=" + montant + ", dateOp=" + dateOp + "]";
	}

}
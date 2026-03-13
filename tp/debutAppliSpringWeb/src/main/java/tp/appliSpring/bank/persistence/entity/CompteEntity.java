package tp.appliSpring.bank.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="COMPTE")
@NamedQuery(name = "CompteEntity.findWithOperations" ,
           query="SELECT cpt FROM CompteEntity cpt LEFT JOIN FETCH cpt.operations WHERE cpt.numero = ?1")
@Getter
@Setter
public class CompteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="numero")
    private Long numero;
    
    @Column(name="label" , length = 64)
    private String label;
    
    private Double solde;
    
    @OneToMany(mappedBy = "compte" , fetch = FetchType.LAZY ,cascade = CascadeType.REMOVE)
    //@JsonIgnore
    private List<OperationEntity> operations = new ArrayList<>(); //+get/set

	@ManyToMany(mappedBy = "comptes" , cascade = CascadeType.DETACH) // coté secondaire avec mappedBy="nomJavaRelationInverse"
	private List<ClientEntity> clients = new ArrayList<>();

  //+get/set , constructeur , toString()
    
	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}


	public CompteEntity(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}


	public CompteEntity() {
		super();
	}
	
}
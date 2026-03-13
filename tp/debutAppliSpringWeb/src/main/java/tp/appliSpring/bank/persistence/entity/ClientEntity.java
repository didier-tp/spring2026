package tp.appliSpring.bank.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="client")
//@NamedQuery(name="ClientEntity.findAll",query="SELECT c FROM ClientEntity c")
@NamedQuery(name="ClientEntity.findWithAccountById",
            query="SELECT c FROM ClientEntity c LEFT JOIN FETCH c.comptes WHERE c.numero = ?1")
@Getter
@Setter
public class ClientEntity {
	
	//un client aura souvent plusieurs comptes
	//bien que rare , un compte peut être associé à plusieurs client (ex: co-propriété)
	//many-to-many , avec coté principal "client" et coté secondaire "compte"
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "client_compte" ,
	    joinColumns = { @JoinColumn(name="numClient")} ,
	    inverseJoinColumns = { @JoinColumn(name="numCompte")}
	)
	@JsonIgnore //pour ignorer .comptes lorsque le client java sera transformé en client json
	            //MAIS c'est beaucoup moins bien que les DTO
	private List<CompteEntity> comptes=new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	private String prenom;
	private String nom;
	private String adresse;
	private String email;
	private String password; //mot de passe (crypté via bcrypt)
	//...
	

	public ClientEntity(Long numero, String prenom, String nom, String adresse, String email, String password) {
		super();
		this.numero = numero;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.email=email;
		this.password=password;
	}

	public ClientEntity(Long numero, String prenom, String nom, String adresse, String email) {
		this(numero,prenom,nom,adresse,email,null);
	}
	
	public ClientEntity() {
		this(null,null,null,null,null);
	}
	

	@Override
	public String toString() {
		return "Client [numero=" + numero + ", prenom=" + prenom + ", nom=" + nom + ", adresse=" + adresse + ", email="
				+ email + "]";
	}

}

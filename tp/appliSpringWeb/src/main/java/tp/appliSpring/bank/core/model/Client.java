package tp.appliSpring.bank.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tp.appliSpring.generic.model.WithId;
import tp.appliSpring.generic.model.WithIdAsString;

@Getter @Setter @ToString
public class Client implements WithIdAsString {
	private String numero;
	private String prenom;
	private String nom;
	private String email;
	private String adresse;
	private String password;

	public Client(String numero, String prenom, String nom, String email, String adresse,String password) {
		super();
		this.numero = numero;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.adresse = adresse;
		this.password=password;
}

	@Override
	public String extractId() {
		return this.numero;
	}

	public Client(String numero, String prenom, String nom, String adresse, String email) {
		this(numero,prenom,nom,adresse,email,null);
	}

	public Client() {
		this(null, null, null, null, null);
	}


}

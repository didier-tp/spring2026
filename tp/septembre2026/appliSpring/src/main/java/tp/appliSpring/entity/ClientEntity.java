package tp.appliSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="client")
@Getter @Setter
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private String nom;
    private String prenom;

    @ManyToMany()
    @JoinTable(name="client_compte",
            joinColumns =  { @JoinColumn(name="num_client")} ,
            inverseJoinColumns  =  { @JoinColumn(name="num_compte")}
    )
    private List<CompteEntity> comptes = new ArrayList<>();

    @Override
    public String toString() {
        return "ClientEntity{" +
                "numero=" + numero +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    public ClientEntity(Long numero, String nom, String prenom) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
    }

    public ClientEntity() {
    }
}

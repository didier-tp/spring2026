package tp.appliSpring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="compte")
@Getter @Setter
public class CompteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private String label;

    private Double solde;

    public CompteEntity(Long numero, String label, Double solde) {
        this.numero = numero;
        this.label = label;
        this.solde = solde;
    }

    @OneToMany(mappedBy = "compte") //coté inverse/secondaire d'une relation bi-directionnelle avec mappedBy="..."
    //@JsonIgnore
    private List<OperationEntity> operations;

    @ManyToMany(mappedBy = "comptes")  //coté inverse/secondaire d'une relation bi-directionnelle avec mappedBy="..."
    private List<ClientEntity> clients;

    public CompteEntity(){
        this(null,null,null);
    }

    @Override
    public String toString() {
        return "CompteEntity{" +
                "numero=" + numero +
                ", label='" + label + '\'' +
                ", solde=" + solde +
                '}';
    }
}

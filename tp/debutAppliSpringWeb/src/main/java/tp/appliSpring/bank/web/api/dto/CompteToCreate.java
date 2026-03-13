package tp.appliSpring.bank.web.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import tp.appliSpring.bank.core.model.Compte;

@Schema(description = "data values of new account to be created")
public class CompteToCreate extends Compte {
    public CompteToCreate(String label,Double solde){
        super(null,label,solde);
    }

    public CompteToCreate(){
        this("myBankAccount",0.0);
    }

    @Override
    //@Schema(description = "unknown account number before creation (should be null)")
    @Schema(hidden = true)  //ou bien @JsonIgnore
    public Long getNumero() {
        return super.getNumero();
    }

    @Schema(description = "account label" , defaultValue = "myBankAccount")
    @Length(min=2, max=30, message = "Invalid label , label length should be in [2,30]")
    public String getLabel(){
        return super.getLabel();
    }

    @Override
    @Min(value=-999 ,message = "Invalid solde , should be >= -999" )
    public Double getSolde() {
        return super.getSolde();
    }
}

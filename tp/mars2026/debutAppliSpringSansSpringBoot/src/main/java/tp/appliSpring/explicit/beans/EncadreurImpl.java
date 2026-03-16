package tp.appliSpring.explicit.beans;

public class EncadreurImpl implements Encadreur{

    private Prefixeur prefixeur;
    private Suffixeur suffixeur;

    public EncadreurImpl(Prefixeur prefixeur, Suffixeur suffixeur) {
        this.prefixeur = prefixeur;
        this.suffixeur = suffixeur;
    }

    public EncadreurImpl(){
        this(null,null);
    }

    @Override
    public String encadrer(String message) {
        String messageAvecSuffixe =suffixeur!=null?suffixeur.suffixer(message):message;
        return prefixeur!=null?prefixeur.prefixer(messageAvecSuffixe):messageAvecSuffixe;
    }

    public Prefixeur getPrefixeur() {
        return prefixeur;
    }

    public void setPrefixeur(Prefixeur prefixeur) {
        this.prefixeur = prefixeur;
    }

    public Suffixeur getSuffixeur() {
        return suffixeur;
    }

    public void setSuffixeur(Suffixeur suffixeur) {
        this.suffixeur = suffixeur;
    }
}

package tp.appliSpring.explicit.beans;

public class PrefixeurMaj implements Prefixeur{

    private String prefixe ;

    public PrefixeurMaj(String prefixe){
        this.prefixe=prefixe;
    }

    public PrefixeurMaj(){
        this("**"); //default value
    }
    
    @Override
    public String prefixer(String message) {
        return (prefixe + message).toUpperCase();
    }

    public String getPrefixe() {
        return prefixe;
    }

    public void setPrefixe(String prefixe) {
        this.prefixe = prefixe;
    }

}

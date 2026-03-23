package tp.appliSpring.explicit.beans;

public class PrefixeurBasic implements Prefixeur{

    private String prefixe ;

    public PrefixeurBasic(String prefixe){
        this.prefixe=prefixe;
    }

    public PrefixeurBasic(){ this("**"); //default value
    }

    @Override
    public String prefixer(String message) {
        return prefixe + message;
    }

    public String getPrefixe() {  return prefixe;   }

    public void setPrefixe(String prefixe) {   this.prefixe = prefixe;  }

}

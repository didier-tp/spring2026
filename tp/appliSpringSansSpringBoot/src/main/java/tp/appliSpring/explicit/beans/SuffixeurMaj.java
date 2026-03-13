package tp.appliSpring.explicit.beans;

public class SuffixeurMaj implements Suffixeur{

    private String suffixe ;

    public SuffixeurMaj(String suffixe){
        this.suffixe=suffixe;
    }

    public SuffixeurMaj(){ this("**"); //default value
    }

    @Override
    public String suffixer(String message) {
        return  message + suffixe;
    }

    public String getSuffixe() {  return suffixe;   }

    public void setSuffixe(String suffixe) { this.suffixe = suffixe;   }
}

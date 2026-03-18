package tp.conf.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "encadreur")
public class EncadreurProperties {
    private String prefixe="#";
    private String suffixe="#";
    private Boolean maj=false;

    @Override
    public String toString() {
        return "EncadreurProperties{" +
                "prefixe='" + prefixe + '\'' +
                ", suffixe='" + suffixe + '\'' +
                ", maj=" + maj +
                '}';
    }

    public String getPrefixe() {
        return prefixe;
    }

    public void setPrefixe(String prefixe) {
        this.prefixe = prefixe;
    }

    public String getSuffixe() {
        return suffixe;
    }

    public void setSuffixe(String suffixe) {
        this.suffixe = suffixe;
    }

    public Boolean getMaj() {
        return maj;
    }

    public void setMaj(Boolean maj) {
        this.maj = maj;
    }
}

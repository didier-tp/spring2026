package tp.appliRest.service;

import org.springframework.stereotype.Service;
import tp.appliRest.model.Currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private Map<String, Currency> mapDevise = new HashMap<>();

    public CurrencyServiceImpl(){
        mapDevise.put("EUR",new Currency("EUR","Euro",1.0));
        mapDevise.put("USD",new Currency("USD","Dollar",1.1));
        mapDevise.put("GBP",new Currency("GBP","Livre",0.9));
        mapDevise.put("JPY",new Currency("JPY","Yen",110.0));
    }

    @Override
    public Currency searchById(String id) {
        return mapDevise.get(id);
    }

    @Override
    public List<Currency> searchAll() {
        return new ArrayList<>(mapDevise.values());
    }

    @Override
    public List<Currency> searchByChangeMini(double changeMini) {
        return mapDevise.values().stream()
                .filter(d->d.getChange()>=changeMini)
                .toList();
    }

    @Override
    public void deleteById(String id) {
        mapDevise.remove(id);
    }

    @Override
    public Currency save(Currency d) {
        mapDevise.put(d.getCode(),d);
        return d;
    }

    @Override
    public void update(Currency d) {
        mapDevise.put(d.getCode(),d);
    }

    @Override
    public double convert(double amount, String sourceId, String targetId) {
        double result=0;
        Currency sourceCurrency = mapDevise.get(sourceId);
        Currency targetCurrency = mapDevise.get(targetId);
        if(sourceCurrency!=null && targetCurrency!=null){
            result=amount * targetCurrency.getChange() / sourceCurrency.getChange();
        }
        return result;
    }
}

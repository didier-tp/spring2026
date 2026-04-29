package tp.appliRest.service;


import tp.appliRest.model.Currency;

import java.util.List;

public interface CurrencyService {
    public Currency searchById(String id);
    public List<Currency> searchAll();
    public List<Currency> searchByChangeMini(double changeMini);
    public void deleteById(String id);
    public Currency save(Currency d);
    public void update(Currency d);
    double convert(double amount, String source,String target);
}

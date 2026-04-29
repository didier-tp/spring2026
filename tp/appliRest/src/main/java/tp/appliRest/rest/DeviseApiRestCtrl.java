package tp.appliRest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.appliRest.converter.MyConverter;
import tp.appliRest.dto.ConvertResponse;
import tp.appliRest.dto.Devise;
import tp.appliRest.model.Currency;
import tp.appliRest.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping(value = "/devise-api/v1")
public class DeviseApiRestCtrl implements DevisesApi{

    @Autowired
    private CurrencyService deviseService;

    @Override
    public ResponseEntity<ConvertResponse> convert(Double amount, String source,String target) {
        ConvertResponse convertResponse = new ConvertResponse().amount(amount).source(source).target(target);
        convertResponse.setResult(deviseService.convert(amount,source,target));
        return ResponseEntity.ok(convertResponse);
    }

    @Override
    public ResponseEntity<Void> deleteDeviseById(String id) {
        deviseService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<Devise>> getDeviseByCriteria(Double changeMini) {
        List<Currency> currencyList =
                changeMini!=null?deviseService.searchByChangeMini(changeMini):deviseService.searchAll();
        List<Devise> deviseList = currencyList.stream().map(c-> myConverter.currencyToDevise(c)).toList();
        return ResponseEntity.ok(deviseList);
    }

    /*
    //V1 without mapStruct
    private Devise currencyToDevise(Currency c){
        return new Devise().code(c.getCode())
                .name(c.getName())
                .change(c.getChange());
    }

    private Currency deviseToCurrency(Devise d){
        return new Currency(d.getCode(),d.getName(),d.getChange());
    }
    */

    //V2 with mapStruct
    @Autowired
    MyConverter myConverter;


    @Override
    public ResponseEntity<Devise> getDeviseById(String id) {
        Currency c = deviseService.searchById(id);
        if(c==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        Devise d = myConverter.currencyToDevise(c);
        return ResponseEntity.ok(d);
    }

    @Override
    public ResponseEntity<Devise> postDevise(Devise devise) {
        Currency c = myConverter.deviseToCurrency(devise);
        deviseService.save(c);
        return ResponseEntity.ok(devise);
    }

    @Override
    public ResponseEntity<Devise> putDevise(String id, Boolean v, Devise devise) {
        Currency c = myConverter.deviseToCurrency(devise);
        deviseService.update(c);
        if(v)
            return ResponseEntity.ok(devise);//if verbose
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();//ok without details
    }
}

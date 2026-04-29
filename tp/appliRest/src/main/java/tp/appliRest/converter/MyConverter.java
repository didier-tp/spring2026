package tp.appliRest.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tp.appliRest.dto.Devise;
import tp.appliRest.model.Currency;

import java.util.List;

//@Mapper // MyMapper.INSTANCE...
@Mapper(componentModel = "spring") //for @Autowired or ...
public interface MyConverter {
    MyConverter INSTANCE = Mappers.getMapper( MyConverter.class );

    Devise currencyToDevise(Currency c);
    List<Devise> currencyListToDeviseList(List<Currency> l);

    Currency deviseToCurrency(Devise d);
    List<Currency> deviseListToCurrencyList(List<Devise> l);
}

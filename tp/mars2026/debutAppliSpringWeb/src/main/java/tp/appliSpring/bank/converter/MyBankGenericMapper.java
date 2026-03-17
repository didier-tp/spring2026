package tp.appliSpring.bank.converter;

import org.springframework.stereotype.Component;
import tp.appliSpring.generic.converter.AbstractGenericConverter;

/*
MyBankGenericMapper inherit AbstractGenericConverter which implements GenericConverter
and is implemented by UltraBasicGenericMapper if no mapstruct Converter is set
if a mapstruct Converter is set, it will be used by AbstractGenericConverter
if methods convention name xxxToYyy()
 */
@Component
public class MyBankGenericMapper extends AbstractGenericConverter {
    public static MyBankGenericMapper CONVERTER = new MyBankGenericMapper();

    //returning efficient direct mapper/converter based on mapStruct:
    public MyBankConverter getMyBankConverter() {
        return MyBankConverter.INSTANCE;
    }

    //returning mapStruct  mapper/converter as Object
    // for indirect internal use inside this genericConverter
    @Override
    public Object getDtoConverter() {
        return this.getMyBankConverter();
    }
}
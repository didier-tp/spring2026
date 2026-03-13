package tp.appliSpring.generic.converter;

public interface MyGenericMapperUtil {

    default Long stringToLong(String str) {
        if (str == null) return null;
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    default String longToString(Long l){
            if (l == null) return null;
            return l.toString();
    }

}
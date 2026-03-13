package tp.appliSpring.generic.converter;

import java.util.List;

public interface GenericMapper {
    <S,D> D map(S source , Class<D> targetClass);
    <S,D> List<D> map(List<S> sourceList , Class<D> targetClass);
}

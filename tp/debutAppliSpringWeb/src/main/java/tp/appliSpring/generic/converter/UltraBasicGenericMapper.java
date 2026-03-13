package tp.appliSpring.generic.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;
/*
 * UltraBasicGenericMapper = mapper/convertisseur hyper générique utilisant 
 * seulement BeanUtils.copyProperties
 * 
 * NB: pour un eventuel basculement sur mapStruct ou autre,
 * les méthodes de sont pas "static"
 * et cet objet sera à la fois accessible via le singleton élémentaire
 * UltraBasicGenericMapper.MAPPER et comme un composant spring injectable
 */
@Slf4j
public class UltraBasicGenericMapper implements GenericMapper {
	
	public static UltraBasicGenericMapper MAPPER = new UltraBasicGenericMapper();

	//UltraBasicGenericMapper.MAPPER.map(compteEntity,CompteDto.class) sans spring
	//ultraBasicGenericMapper.map(compteEntity,CompteDto.class) avec injection spring
	public /*static*/ <S,D> D map(S source , Class<D> targetClass) {
		if(source==null || targetClass==null) return null;
		D target  = null;
		try {
			target = targetClass.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException("echec UltraBasicGenericMapper.map",e);
		}
        log.trace("UltraBasicGenericMapper.map() called with source = "+source + " and targetClass = "+targetClass.getName());
        log.trace("UltraBasicGenericMapper.map() result = "+target);
		return target;
	}
	
	//UltraBasicGenericMapper.MAPPER.map(ListeCompteEntity,CompteDto.class) sans spring
	//ultraBasicGenericMapper.map(ListeCompteEntity,CompteDto.class) avec injection spring
	public /*static*/ <S,D> List<D> map(List<S> sourceList , Class<D> targetClass){
		if(sourceList==null || targetClass==null) return null;
		return  sourceList.stream()
			   .map((source)->map(source,targetClass))
			   .collect(Collectors.toList());
	}

}

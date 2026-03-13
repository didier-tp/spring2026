package tp.appliSpring.generic.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/*
 * Version (plus performante) et plus sophistiquee de GenericMapper
 * deleguant des conversions spécifique à un DtoConverter
 * 
 * NB: l'objet dtoConverter à enregistrer en implémentant la méthode abstraite getDtoConverter()
 * doit idéalement comporter des méthodes de conversion qui respectent la convention de nommage suivante:
 * public Ddd sssToDdd(Sss source);
 * 
 * Si ces méthodes respectent bien le format attendu ,
 * elles seront alors automatiquement découvertes et utilisées
 * en interne lors d'un appel aux méthodes de conversion générique 
 *   .map(source , Ddd.class) et .map(sourceList , Ddd.class)
 *  
 * Dans le cas où une méthode de convertion ne respecterait pas la convention de nom .sssToDdd()
 * où bien pour optimiser un petit peu les performances , il est possible de pré-enregistrer
 * une méthode de transformation spécifique via un appel de ce genre:
 * GenericConverter.CONVERTER.preRegisterTransformFunction(Sss.class, Ddd.class, DtoConverter.INSTANCE::sssVersDdd);
 */

/* exemple d'utilisation:
 
 public class DtoConverter {
	public static DtoConverter INSTANCE = new DtoConverter();
	public NewsL0 newsToNewsL0(News newsEntity) { ....}
	public News newsL0ToNews(NewsL0 newsDto) { ....}
 }
 
 public class GenericConverter extends AbstractGenericConverter {
	public static GenericConverter CONVERTER = new GenericConverter();

	@Override
	public Object getDtoConverter() {
		return DtoConverter.INSTANCE;
	}
 }
 */

public abstract class AbstractGenericConverter extends UltraBasicGenericMapper {
	
	public static Logger logger = LoggerFactory.getLogger(AbstractGenericConverter.class);

	
	public abstract Object getDtoConverter();
	
	
	
	//map<SourceClassAsKey,...> of subMap .
	//subMaps are map<DestinationClassAsKey, Optional<Function<Object,Object> to transform source into destination)
	private Map<Class,Map<Class,Optional<Function<Object,Object>>>> convSDMap = new HashMap<>();

	static String withFirstLowerCase(String s) {
		return Character.toLowerCase(s.charAt(0)) + s.substring(1);
	}
	
	private Optional<Function<Object,Object>> optionalTransformFunctionWithMethodHandleForSDConv(Class<?> sourceClass , Class<?> destinationClass){
	try {
		String convertMethodName = withFirstLowerCase(
				sourceClass.getSimpleName() + "To" + destinationClass.getSimpleName());
		logger.debug("convertMethodName="+convertMethodName);
					
		MethodType convertMethType = 
				MethodType.methodType(destinationClass /*returnType*/, sourceClass /*paramType*/);
		MethodHandle handle = MethodHandles.publicLookup().findVirtual(getDtoConverter().getClass(), convertMethodName, convertMethType);
		
		Function<Object,Object> transformFunction =(entity)->{
			try {
				return handle.invoke(getDtoConverter(), entity);
			} catch (Throwable e) {
				e.printStackTrace();
				return null;
			}
		};
		return Optional.of(transformFunction);
	} catch (Exception e) {
		return Optional.empty();
	}
	}
		
	public <S, D> D map(S source, Class<D> destinationClass) {
		if(source==null || destinationClass==null)return null;
		D destination = null;
		try {
			Optional<Function<Object,Object>> optionalTransformFunction = null;
			Map<Class,Optional<Function<Object,Object>>> convSDSubMap = convSDMap.get(source.getClass());
			if(convSDSubMap==null){
				logger.debug("add entry in convSDMap for key="+source.getClass().getSimpleName());
				convSDSubMap = new HashMap<Class,Optional<Function<Object,Object>>>();
				convSDMap.put(source.getClass(), convSDSubMap);
			}
			optionalTransformFunction = convSDSubMap.get(destinationClass);
			if(optionalTransformFunction==null){
				logger.debug("add entry in convSDsubMap for key="+destinationClass.getSimpleName());
				Optional<MethodHandle> optionalHandle = null;
				optionalTransformFunction = optionalTransformFunctionWithMethodHandleForSDConv(source.getClass(),destinationClass);
				convSDSubMap.put(destinationClass,optionalTransformFunction);
			}
			
			if(optionalTransformFunction.isPresent()) {
				// first try With DtoConverter hadhoc function xxxToYyy()
				logger.debug("conversion using transformFunction ="+optionalTransformFunction.get());
				destination = (D) optionalTransformFunction.get().apply(source);
			}
			else {
				// second try with GenericMapper (as fault back)
				logger.debug("conversion using GenericMapper ...");
				destination = super.map(source, destinationClass);
			}
		}
		catch (Throwable ex) {
			ex.printStackTrace();
		}
		return destination;
	}
	
	//NB: calling this method is not mandatory ,
	//if a transformationFunction is not registered , it may be automatically discovered
	//and registered during first call of map(..,..) via methodHandle .
	public <S, D> void  preRegisterTransformFunction(Class<S> sourceClass,Class<D> destinationClass,
			Function<S,D> transformFunction) {
		
		Map<Class,Optional<Function<Object,Object>>> convSDSubMap = convSDMap.get(sourceClass);
		if(convSDSubMap==null){
			convSDSubMap = new HashMap<Class,Optional<Function<Object,Object>>>();
			convSDMap.put(sourceClass, convSDSubMap);
		}
		
		convSDSubMap.put(destinationClass,Optional.ofNullable((Function<Object,Object>)transformFunction));
	}

}

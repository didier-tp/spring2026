package tp.appliSpring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
@Order(2) //n+1 order = inner/after order called , n-1 order : outer/before called
//@Order(1) //n+1 order = inner/after order called , n-1 order : outer/before called
@Profile("perf")
public class MyBasicLogAspect {

	@Pointcut("execution(* tp.appliSpring.exemple.*.*(..))")
	public void surPackageExemple() {}
	
	@Pointcut("execution(* tp.appliSpring.service.*.*(..))")
	public void surPackageService() {}
	
	//@within pour annotation @Aff (avec @Target(ElementType.TYPE) placée sur l'ensemble d'une classe 
	@Pointcut("@within(tp.appliSpring.annotation.Aff)")
	public void annotAffPointcut(){ 
	} 
			
	//@within pour annotation @LogExecutionTime (avec @Target(ElementType.METHOD) placée sur une méthode 
	@Pointcut("@annotation(tp.appliSpring.annotation.LogExecutionTime)")
	public void annotLogExecutionTimePointcut(){ 
	}
	
	//@Around("annotAffPointcut()")
	//@Around("surPackageExemple() && annotAffPointcut()")
	//@Around("annotLogExecutionTimePointcut()")
	@Around("surPackageExemple() || surPackageService()")
	public Object doBasicLog(ProceedingJoinPoint pjp) throws Throwable {
		Object objRes = pjp.proceed();
		System.out.println(	">> basic-log : " + pjp.getSignature().toShortString() );
		return objRes;
	}
	
}

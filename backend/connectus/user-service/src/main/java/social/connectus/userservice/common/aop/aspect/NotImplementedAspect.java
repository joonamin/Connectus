package social.connectus.userservice.common.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class NotImplementedAspect {

	@Before("@annotation(YetNotImplemented)")
	public void notImplementedNotify(ProceedingJoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		throw new UnsupportedOperationException(name + "은 아직 구현되지 않은 메소드입니다.");
	}
}

package social.connectus.location.common.customannotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface UseCase {
    @AliasFor(annotation = Component.class)
    String value() default "";
}

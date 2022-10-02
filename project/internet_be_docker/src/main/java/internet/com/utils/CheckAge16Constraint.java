package internet.com.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = Age16Validator.class)
public @interface CheckAge16Constraint {
    String message() default "Chưa đủ 16 tuổi";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}

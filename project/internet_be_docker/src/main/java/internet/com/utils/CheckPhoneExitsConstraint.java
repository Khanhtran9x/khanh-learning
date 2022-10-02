package internet.com.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = CheckPhoneExitsValidator.class)
public @interface CheckPhoneExitsConstraint {
    String message() default "SĐT đã sử dụng";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}

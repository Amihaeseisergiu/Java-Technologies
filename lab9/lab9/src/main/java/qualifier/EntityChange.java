package qualifier;

import enumeration.DatabaseOperation;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

@Qualifier
@Target({ PARAMETER, FIELD })
@Retention(RUNTIME)
public @interface EntityChange {
    
    DatabaseOperation value();
}

package sv.edu.fmocc.tpi_2019.historial.cars.historycars.util;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;



@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Loggable {
}
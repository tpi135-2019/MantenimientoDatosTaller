package sv.edu.fmocc.tpi_2019.historial.cars.historycars.util;

import java.util.logging.Level;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;
import javax.inject.Inject;

@Loggable
@Interceptor
public class LoggingInterceptor {
    
    @Inject
    private Logger logger;

    @AroundInvoke
    private Object intercept(InvocationContext ic) throws Exception {
        Object re = null;
        String className = ic.getTarget().getClass().getName();
        String methodName = ic.getMethod().getName();
        logger.log(Level.INFO,"Entering Class: "+className+" Method: "+methodName);
        try {
            re = ic.proceed();
        } finally {
        logger.log(Level.INFO,"Exiting Class: "+className+" Method: "+methodName);
        }
        return re;
    }
}

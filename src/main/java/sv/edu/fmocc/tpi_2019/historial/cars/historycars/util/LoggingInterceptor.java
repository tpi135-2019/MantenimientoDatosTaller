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
        //logger.logp(Level.INFO, ic.getTarget().getClass().getName(), ic.getMethod().getName(), "Entering");
        logger.entering(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        try {
            re = ic.proceed();
        } finally {
            //logger.log(Level.SEVERE, re.toString(), re);
           logger.logp(Level.INFO, ic.getTarget().getClass().getName(), ic.getMethod().getName(), "Exiting");
        }
        return re;
    }
}

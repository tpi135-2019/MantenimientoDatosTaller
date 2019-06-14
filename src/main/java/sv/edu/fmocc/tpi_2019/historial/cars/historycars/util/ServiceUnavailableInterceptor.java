package sv.edu.fmocc.tpi_2019.historial.cars.historycars.util;

import java.util.Date;
import java.util.logging.Level;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ServiceUnavailable
@Interceptor
public class ServiceUnavailableInterceptor {

    @Inject
    private Logger logger;

    @AroundInvoke
    private Object intercept(InvocationContext ic) throws Exception {
        Object response = null;
        String className = ic.getTarget().getClass().getName();
        String methodName = ic.getMethod().getName();
        logger.log(Level.WARNING, "Entering Class: " + className + " Method: " + methodName);
        try {
            response = ic.proceed();
        } finally {
            logger.log(Level.WARNING, response.toString());
            if (response != null) {
                return response;
            }
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }

    }
}

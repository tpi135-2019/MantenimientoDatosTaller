/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author kevin
 */
@ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
         resources.add(sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources.CorsFilter.class);
        resources.add(sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources.DiagnosticoResource.class);
        resources.add(sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources.PersonalResource.class);
        resources.add(sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources.ProcesoResource.class);
        resources.add(sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources.ReparacionResource.class);
        resources.add(sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources.SucursalResource.class);
        resources.add(sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources.VehiculoResource.class);
        return resources;
    }

 
}

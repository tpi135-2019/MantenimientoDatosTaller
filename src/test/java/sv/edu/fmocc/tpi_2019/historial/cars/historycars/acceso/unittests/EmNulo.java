/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import javax.persistence.EntityManager;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.powermock.reflect.Whitebox;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;

/**
 *
 * @author kevin
 */
public class EmNulo implements TestRule{
    
    private FacadeGenerico cut;
    private EntityManager em = null;
    
    public EmNulo(FacadeGenerico cut){
        this.cut = cut;
    }
    
    public static EmNulo getInstance(FacadeGenerico cut){
        return new EmNulo(cut);
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                if(description.getMethodName().contains("EmNull")){
                    Whitebox.setInternalState(cut, "em", em);
                }
                base.evaluate();
            }
        };
    }
    
    
    
}

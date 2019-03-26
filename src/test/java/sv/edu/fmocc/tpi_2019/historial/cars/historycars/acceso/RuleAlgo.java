/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 *
 * @author kevin
 */
public class RuleAlgo implements TestRule{
    
     private FacadeGenerico cut;
     
     public RuleAlgo(FacadeGenerico cut){
        this.cut = cut;
    }
    
    public static RuleAlgo getInstance(FacadeGenerico cut){
        return new RuleAlgo(cut);
    }

    @Override
    public Statement apply(Statement stmnt, Description d) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println("algo");
            }
        };
    }
    
}

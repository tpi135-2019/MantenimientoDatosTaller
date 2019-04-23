/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.MarcaFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Marca;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmMarcaTest extends BackingBeanTest<Marca> {

    @Mock
    MarcaFacade marcaFacade;
    @Spy
    @InjectMocks
    FrmMarca cut = new FrmMarca();
    Marca marca = new Marca(1);

    List<Marca> registrosMarca = new ArrayList<>();

    @Before
    @Override
    public void setup() {
        registrosMarca.add(new Marca(1, "Toyota"));
        registrosMarca.add(new Marca(2, "Honda"));
    }

    @Override
    protected AbstractBean<Marca> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Marca> getFacade() {
        return marcaFacade;
    }

    @Override
    protected Marca getEntity() {
        return marca;
    }

    @Override
    protected List<Marca> getLista() {
        return registrosMarca;
    }
      @Test
   public void getRowDTest(){
       String key="";
       getRowDataTest(key);
   }

}

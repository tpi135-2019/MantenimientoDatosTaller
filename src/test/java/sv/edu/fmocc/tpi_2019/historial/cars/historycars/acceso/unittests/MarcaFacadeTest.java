/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.MarcaFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Marca;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Modelo;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class MarcaFacadeTest extends SessionBeanTest<Marca> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private MarcaFacade cut;
    private Marca marca = new Marca(1);
    private List<Marca> registrosEsperados = new ArrayList<>();
    private List<Modelo> modelos = new ArrayList<>();

    public MarcaFacadeTest() {
        super(Marca.class);
        registrosEsperados.add(new Marca(1, "nissan"));
        registrosEsperados.add(new Marca(2, "toyota"));
        modelos.add(new Modelo(1, "sentra"));
        modelos.add(new Modelo(2, "corolla"));

    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Marca getEntity() {
        return marca;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Marca> getLista() {
        return registrosEsperados;
    }

    @Test
    public void findModeloByMarcaLikeTest() {
        System.out.println("findModeloByMarcaLike");
        int idmarca = 1;
        String modelo = "";
        Mockito.when(ema.createNamedQuery("Modelo.ModeloByMarca")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(modelos);
        List resultados = cut.findModeloByMarcaLike(idmarca, modelo);
        Assert.assertEquals(modelos.size(), resultados.size());

        resultados = cut.findModeloByMarcaLike(-1, "");
        Assert.assertEquals(Collections.EMPTY_LIST.size(), resultados.size());

        setEmNull();
        cut.findModeloByMarcaLike(idmarca, "");
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }
}

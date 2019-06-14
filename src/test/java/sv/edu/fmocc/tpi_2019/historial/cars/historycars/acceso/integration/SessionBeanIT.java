/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.integration;

import java.util.List;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.util.ResourceProducer;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
@UsingDataSet("datasets/dataIT.json")
@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_TABLES_ONLY)
public abstract class SessionBeanIT<T> {

    FacadeGenerico cutGeneric;
    Class<?> entityClass;
    T entity;
    T entidadEditada;
    T entidadNueva;
    Object idNuevo;
    int numeroRegistros;
    List<T> registros;
    List<T> encontrados;

    protected abstract FacadeGenerico getSessionBean();

    protected abstract Object getId();

    protected abstract List<T> getResgistros();

    @Deployment
    public static WebArchive desplegar() {
        WebArchive salida = ShrinkWrap.create(WebArchive.class)
                .addPackage(PropietarioFacade.class.getPackage())
                .addPackage(Propietario.class.getPackage())
                .addClass(ResourceProducer.class)
                .addAsResource("persistence-arquillian.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        System.out.println(salida.toString(true));
        return salida;
    }

    @Before
    public void setUp() {
        this.cutGeneric = getSessionBean();
        this.registros = getResgistros();
        this.entity = registros.get(0);
        this.entidadNueva = registros.get(1);
        this.entidadEditada = registros.get(2);
        idNuevo = 5;
    }


    @Test
    public void testCreate() {
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(2, numeroRegistros);
        cutGeneric.create(entidadNueva);
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(3, numeroRegistros);
        T entidadCreada = (T) cutGeneric.find(idNuevo);
        Assert.assertTrue(entidadNueva.equals(entidadCreada));
    }

    @Test
    public void testDelete() {
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(2, numeroRegistros);

        cutGeneric.remove(entity);
        numeroRegistros = cutGeneric.count();
        T result = (T) cutGeneric.find(getId());
        Assert.assertNull(result);
        Assert.assertEquals(1, numeroRegistros);
    }

    @Test
    @InSequence(2)
    public void testEdit() {
        System.out.println("EditIT");
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(2, numeroRegistros);
        T encontrado = (T) cutGeneric.find(getId());
        cutGeneric.detach(entity);
        Assert.assertNotNull(encontrado);
        cutGeneric.edit(entidadEditada);
        Assert.assertTrue(!entidadEditada.equals(encontrado));
    }

    @Test
    @InSequence(1)
    public void testFind() {
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(2, numeroRegistros);
        T encontrado = (T) cutGeneric.find(getId());
        Assert.assertTrue(entity.equals(encontrado));
        System.out.println("");
    }

    @Test
    public void testFindAll() {
        encontrados = cutGeneric.findAll();
        Assert.assertEquals(2, encontrados.size());

    }

    @Test
    public void testFindRange() {
        encontrados = cutGeneric.findRange(0, 2);
        Assert.assertEquals(2, encontrados.size());
    }

    @Test
    public void testCount() {
        System.out.println("testCountIT");
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(2, numeroRegistros);

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.integration;

import java.util.List;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public abstract class SessionBeanIT<T> {

    FacadeGenerico cutGeneric;
    Class<?> entityClass;
    T entity;
    int numeroRegistros;
    List<T> registros;
    List<T> encontrados;

    protected abstract FacadeGenerico getSessionBean();

    protected abstract T getEntity();

    protected abstract Object getId();

    protected abstract List<T> getResgistros();

    protected abstract Class<?> getEntityClass();

    @Deployment
    public static WebArchive desplegar() {
        WebArchive salida = ShrinkWrap.create(WebArchive.class)
                .addPackage(PropietarioFacade.class.getPackage())
                .addPackage(Propietario.class.getPackage())
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
    }

    @After
    public void tearDown() {
        registros.forEach(registro -> {
            cutGeneric.remove(registro);
        });
    }

    @Test
    public void testCreate() {
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(0, numeroRegistros);
        cutGeneric.create(this.entity);
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(1, numeroRegistros);
    }

    @Test
    public void testDelete() {
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(0, numeroRegistros);
        cutGeneric.create(this.entity);
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(1, numeroRegistros);

        cutGeneric.remove(this.entity);
        numeroRegistros = cutGeneric.count();
        T result = (T) cutGeneric.find(getId());
        Assert.assertNull(result);
        Assert.assertEquals(0, numeroRegistros);
    }

    @Test
    public void testEdit() {
        System.out.println("EditIT");
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(0, numeroRegistros);
        cutGeneric.create(this.entity);

        T encontrado = (T) cutGeneric.find(getId());
        Assert.assertNotNull(encontrado);
        this.entity = getEntity();
        cutGeneric.edit(this.entity);
        Assert.assertTrue(!entity.equals(encontrado));
        cutGeneric.remove(this.entity);
    }

    @Test
    public void testFind() {
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(0, numeroRegistros);
        cutGeneric.create(this.entity);
        T encontrado = (T) cutGeneric.find(getId());
        Assert.assertTrue(entity.equals(encontrado));
    }

    @Test
    public void testFindAll() {
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(0, numeroRegistros);

        registros.forEach(registro -> {
            cutGeneric.create(registro);
        });
        encontrados = cutGeneric.findAll();
        Assert.assertEquals(registros, encontrados);

    }

    @Test
    public void testFindRange() {
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(0, numeroRegistros);

        registros.forEach(registro -> {
            cutGeneric.create(registro);
        });

        encontrados = cutGeneric.findRange(1, 2);
        Assert.assertEquals(2, encontrados.size());
    }

    @Test
    public void testCount() {
        System.out.println("testCountIT");
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(0, numeroRegistros);
        cutGeneric.create(this.entity);
        numeroRegistros = cutGeneric.count();
        Assert.assertEquals(1, numeroRegistros);

    }

}

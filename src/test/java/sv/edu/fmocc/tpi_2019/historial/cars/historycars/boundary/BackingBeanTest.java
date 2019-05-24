/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;

/**
 *
 * @author kevin
 * @param <T>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({FacesContext.class})
public abstract class BackingBeanTest<T> extends Iniciar {

    protected abstract AbstractBean<T> getBean();

    protected abstract FacadeGenerico<T> getFacade();

    protected abstract T getEntity();

    protected abstract List<T> getLista();

    @Spy
    Logger logger = Logger.getGlobal();

    @Spy
    LazyDataModel lazyDataModel = new LazyDataModel() {
    };

    @Mock
    protected FacesContext facesContext;

    @Before
    public void setup() {
        getBean().setLogger(logger);
    }

    @Test
    public void messageTest() {
        System.out.println("addMessage");
        PowerMockito.mockStatic(FacesContext.class);
        Mockito.when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
        ArgumentCaptor<String> clientId = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<FacesMessage> facesMessage = ArgumentCaptor.forClass(FacesMessage.class);
        String summary = "msj";
        getBean().addMessage(summary);
        Mockito.verify(facesContext).addMessage(clientId.capture(), facesMessage.capture());
        Assert.assertEquals(FacesMessage.SEVERITY_INFO, facesMessage.getValue().getSeverity());
        Assert.assertEquals(summary, facesMessage.getValue().getSummary());
    }

    @Test
    public void getKeyTest() {
        System.out.println("getKey");
        Assert.assertNotNull(getEntity());
        Object entidad = getBean().getKey(getEntity());
        Assert.assertNotNull(entidad);
        entidad = getBean().getKey(null);
        Assert.assertNull(entidad);

    }

    public void getRowDataTest(String rowkey) {
        if (rowkey == null || rowkey.isEmpty()) {
            rowkey = "1";
        }
        Object entidad = getBean().getrowD(null);
        Assert.assertNull(entidad);
        Mockito.when(getBean().getLazyModel()).thenReturn(lazyDataModel);
        Mockito.when(lazyDataModel.getWrappedData()).thenReturn(getLista());
        entidad = getBean().getrowD(rowkey);
        Assert.assertNotNull(entidad);

    }

    @Test
    public void onRowSelectTest() {
        System.out.println("onRowSelect");
        SelectEvent select = Mockito.mock(SelectEvent.class);
        getBean().onRowSelect(select);
        Mockito.verify(select).getObject();
    }

    @Test
    public void getRegistroTest() {
        System.out.println("getRegistro");
        T expect = getEntity();
        getBean().setRegistro(expect);
        T result = getBean().getRegistro();
        Assert.assertEquals(expect, result);
    }

    @Test
    public void getEstado() {
        System.out.println("getEstado");
        AbstractBean.EstadosCRUD expect;
        getBean().getEstado();
    }

    @Test
    public void crearNuevoTest() {
        System.out.println("crearNuevo");
        T entity = null;
        getBean().registro = entity;
        Assert.assertNull(getBean().registro);
        getBean().crearNuevo();
        Assert.assertNotNull(getBean().registro);
    }

    @Test
    public void getEstadoTest() {
        System.out.println("getEStado");
        AbstractBean.EstadosCRUD estado = AbstractBean.EstadosCRUD.NONE;
        getBean().estado = estado;
        estado = getBean().getEstado();
        Assert.assertNotNull(estado);
    }

    @Test
    public void btnNuevoHandlerTest() {
        System.out.println("btnNuevoHandler");
        AbstractBean.EstadosCRUD expect = AbstractBean.EstadosCRUD.NUEVO;
        getBean().btnNuevoHandler();
        AbstractBean.EstadosCRUD result = getBean().estado;
        Assert.assertEquals(expect, result);
    }

    @Test
    public void btnCancelarHandlerTest() {
        System.out.println("btnCancelarHandler");
        AbstractBean.EstadosCRUD expect = AbstractBean.EstadosCRUD.NONE;
        getBean().btncancelarHandler();
        AbstractBean.EstadosCRUD result = getBean().estado;
        Assert.assertEquals(expect, result);

    }

    @Test
    public void initTest() {
        System.out.println("init");
        AbstractBean.EstadosCRUD expect = AbstractBean.EstadosCRUD.NONE;
        getBean().init();
        AbstractBean.EstadosCRUD result = getBean().estado;
        Assert.assertEquals(expect, result);
    }

    @Test
    public void myLoadTest() {
        System.out.println("myLoad");
        Mockito.when(getFacade().findRange(Matchers.anyInt(), Matchers.anyInt())).thenReturn(getLista());
        List ls = getBean().myLoad(1, 2);
        Assert.assertEquals(getLista().size(), ls.size());
        Mockito.when(getFacade().findRange(Matchers.anyInt(), Matchers.anyInt())).thenThrow(Exception.class);
        getBean().myLoad(1, 2);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    @Test
    public void crearTest() {
        System.out.println("crear");
        Mockito.doNothing().when(getFacade()).create(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().crear();
        Mockito.verify(getFacade()).create(Matchers.anyObject());
        //catch
        Mockito.doThrow(new NullPointerException()).when(getFacade()).create(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().crear();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    @Test
    public void modificarTest() {
        System.out.println("modificar");
        Mockito.doNothing().when(getFacade()).edit(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().modificar();
        Mockito.verify(getFacade()).edit(Matchers.anyObject());

        Mockito.doThrow(new NullPointerException()).when(getFacade()).edit(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().modificar();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    @Test
    public void eliminarTest() {
        System.out.println("eliminar");
        Mockito.doNothing().when(getFacade()).remove(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().eliminar();
        Mockito.verify(getFacade()).remove(Matchers.anyObject());

        Mockito.doThrow(new NullPointerException()).when(getFacade()).remove(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().eliminar();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }

}

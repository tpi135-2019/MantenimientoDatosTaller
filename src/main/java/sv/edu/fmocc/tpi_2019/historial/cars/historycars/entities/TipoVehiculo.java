/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "tipo_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVehiculo.findAll", query = "SELECT t FROM TipoVehiculo t")
    , @NamedQuery(name = "TipoVehiculo.findByIdTipoVehiculo", query = "SELECT t FROM TipoVehiculo t WHERE t.idTipoVehiculo = :idTipoVehiculo")
    , @NamedQuery(name = "TipoVehiculo.findByNombre", query = "SELECT t FROM TipoVehiculo t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoVehiculo.findByDescripcion", query = "SELECT t FROM TipoVehiculo t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoVehiculo.findByCantidadEjes", query = "SELECT t FROM TipoVehiculo t WHERE t.cantidadEjes = :cantidadEjes")
    , @NamedQuery(name = "TipoVehiculo.findByActivo", query = "SELECT t FROM TipoVehiculo t WHERE t.activo = :activo")})
public class TipoVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_vehiculo")
    private Integer idTipoVehiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "cantidad_ejes")
    private String cantidadEjes;
    @Size(max = 45)
    @Column(name = "activo")
    private String activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoVehiculo")
    private List<Modelo> modeloList;

    public TipoVehiculo() {
    }

    public TipoVehiculo(Integer idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public TipoVehiculo(Integer idTipoVehiculo, String nombre) {
        this.idTipoVehiculo = idTipoVehiculo;
        this.nombre = nombre;
    }

    public Integer getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(Integer idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidadEjes() {
        return cantidadEjes;
    }

    public void setCantidadEjes(String cantidadEjes) {
        this.cantidadEjes = cantidadEjes;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Modelo> getModeloList() {
        return modeloList;
    }

    public void setModeloList(List<Modelo> modeloList) {
        this.modeloList = modeloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoVehiculo != null ? idTipoVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVehiculo)) {
            return false;
        }
        TipoVehiculo other = (TipoVehiculo) object;
        if ((this.idTipoVehiculo == null && other.idTipoVehiculo != null) || (this.idTipoVehiculo != null && !this.idTipoVehiculo.equals(other.idTipoVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.TipoVehiculo[ idTipoVehiculo=" + idTipoVehiculo + " ]";
    }
    
}

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
@Table(name = "estado_paso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoPaso.findAll", query = "SELECT e FROM EstadoPaso e")
    , @NamedQuery(name = "EstadoPaso.findByIdEstadoPaso", query = "SELECT e FROM EstadoPaso e WHERE e.idEstadoPaso = :idEstadoPaso")
    , @NamedQuery(name = "EstadoPaso.findByNombre", query = "SELECT e FROM EstadoPaso e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "EstadoPaso.findByActivo", query = "SELECT e FROM EstadoPaso e WHERE e.activo = :activo")
    , @NamedQuery(name = "EstadoPaso.findByDescripcion", query = "SELECT e FROM EstadoPaso e WHERE e.descripcion = :descripcion")})
public class EstadoPaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_paso")
    private Integer idEstadoPaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoPaso")
    private List<PasoReparacion> pasoReparacionList;

    public EstadoPaso() {
    }

    public EstadoPaso(Integer idEstadoPaso) {
        this.idEstadoPaso = idEstadoPaso;
    }

    public EstadoPaso(Integer idEstadoPaso, String nombre) {
        this.idEstadoPaso = idEstadoPaso;
        this.nombre = nombre;
    }

    public Integer getIdEstadoPaso() {
        return idEstadoPaso;
    }

    public void setIdEstadoPaso(Integer idEstadoPaso) {
        this.idEstadoPaso = idEstadoPaso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<PasoReparacion> getPasoReparacionList() {
        return pasoReparacionList;
    }

    public void setPasoReparacionList(List<PasoReparacion> pasoReparacionList) {
        this.pasoReparacionList = pasoReparacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoPaso != null ? idEstadoPaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPaso)) {
            return false;
        }
        EstadoPaso other = (EstadoPaso) object;
        if ((this.idEstadoPaso == null && other.idEstadoPaso != null) || (this.idEstadoPaso != null && !this.idEstadoPaso.equals(other.idEstadoPaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.EstadoPaso[ idEstadoPaso=" + idEstadoPaso + " ]";
    }
    
}

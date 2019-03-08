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
@Table(name = "tipo_paso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPaso.findAll", query = "SELECT t FROM TipoPaso t")
    , @NamedQuery(name = "TipoPaso.findByIdTipoPaso", query = "SELECT t FROM TipoPaso t WHERE t.idTipoPaso = :idTipoPaso")
    , @NamedQuery(name = "TipoPaso.findByNombre", query = "SELECT t FROM TipoPaso t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoPaso.findByActivo", query = "SELECT t FROM TipoPaso t WHERE t.activo = :activo")
    , @NamedQuery(name = "TipoPaso.findByDescripcion", query = "SELECT t FROM TipoPaso t WHERE t.descripcion = :descripcion")})
public class TipoPaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_paso")
    private Integer idTipoPaso;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPaso")
    private List<PasoProceso> pasoProcesoList;

    public TipoPaso() {
    }

    public TipoPaso(Integer idTipoPaso) {
        this.idTipoPaso = idTipoPaso;
    }

    public TipoPaso(Integer idTipoPaso, String nombre) {
        this.idTipoPaso = idTipoPaso;
        this.nombre = nombre;
    }

    public Integer getIdTipoPaso() {
        return idTipoPaso;
    }

    public void setIdTipoPaso(Integer idTipoPaso) {
        this.idTipoPaso = idTipoPaso;
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
    public List<PasoProceso> getPasoProcesoList() {
        return pasoProcesoList;
    }

    public void setPasoProcesoList(List<PasoProceso> pasoProcesoList) {
        this.pasoProcesoList = pasoProcesoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPaso != null ? idTipoPaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPaso)) {
            return false;
        }
        TipoPaso other = (TipoPaso) object;
        if ((this.idTipoPaso == null && other.idTipoPaso != null) || (this.idTipoPaso != null && !this.idTipoPaso.equals(other.idTipoPaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.TipoPaso[ idTipoPaso=" + idTipoPaso + " ]";
    }
    
}

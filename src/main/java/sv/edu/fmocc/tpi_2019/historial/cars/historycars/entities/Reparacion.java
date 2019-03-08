/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "reparacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reparacion.findAll", query = "SELECT r FROM Reparacion r")
    , @NamedQuery(name = "Reparacion.findByIdReparacion", query = "SELECT r FROM Reparacion r WHERE r.idReparacion = :idReparacion")
    , @NamedQuery(name = "Reparacion.findByFecha", query = "SELECT r FROM Reparacion r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "Reparacion.findByObservacion", query = "SELECT r FROM Reparacion r WHERE r.observacion = :observacion")})
public class Reparacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reparacion")
    private Integer idReparacion;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 100)
    @Column(name = "observacion")
    private String observacion;
    @ManyToMany(mappedBy = "reparacionList")
    private List<Pieza> piezaList;
    @ManyToMany(mappedBy = "reparacionList")
    private List<Personal> personalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reparacion")
    private List<PasoReparacion> pasoReparacionList;
    @JoinColumn(name = "id_diagnostico", referencedColumnName = "id_diagnostico")
    @ManyToOne(optional = false)
    private Diagnostico idDiagnostico;
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne(optional = false)
    private Proceso idProceso;

    public Reparacion() {
    }

    public Reparacion(Integer idReparacion) {
        this.idReparacion = idReparacion;
    }

    public Integer getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(Integer idReparacion) {
        this.idReparacion = idReparacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @XmlTransient
    public List<Pieza> getPiezaList() {
        return piezaList;
    }

    public void setPiezaList(List<Pieza> piezaList) {
        this.piezaList = piezaList;
    }

    @XmlTransient
    public List<Personal> getPersonalList() {
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }

    @XmlTransient
    public List<PasoReparacion> getPasoReparacionList() {
        return pasoReparacionList;
    }

    public void setPasoReparacionList(List<PasoReparacion> pasoReparacionList) {
        this.pasoReparacionList = pasoReparacionList;
    }

    public Diagnostico getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Diagnostico idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public Proceso getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Proceso idProceso) {
        this.idProceso = idProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReparacion != null ? idReparacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reparacion)) {
            return false;
        }
        Reparacion other = (Reparacion) object;
        if ((this.idReparacion == null && other.idReparacion != null) || (this.idReparacion != null && !this.idReparacion.equals(other.idReparacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Reparacion[ idReparacion=" + idReparacion + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "historial_propietario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialPropietario.findAll", query = "SELECT h FROM HistorialPropietario h")
    , @NamedQuery(name = "HistorialPropietario.findByIdPropietario", query = "SELECT h FROM HistorialPropietario h WHERE h.historialPropietarioPK.idPropietario = :idPropietario")
    , @NamedQuery(name = "HistorialPropietario.findByIdVehiculo", query = "SELECT h FROM HistorialPropietario h WHERE h.historialPropietarioPK.idVehiculo = :idVehiculo")
    , @NamedQuery(name = "HistorialPropietario.findByActual", query = "SELECT h FROM HistorialPropietario h WHERE h.actual = :actual")})
public class HistorialPropietario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistorialPropietarioPK historialPropietarioPK;
    @Size(max = 45)
    @Column(name = "actual")
    private String actual;
    @JoinColumn(name = "id_propietario", referencedColumnName = "id_propietario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Propietario propietario;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vehiculo vehiculo;

    public HistorialPropietario() {
    }

    public HistorialPropietario(HistorialPropietarioPK historialPropietarioPK) {
        this.historialPropietarioPK = historialPropietarioPK;
    }

    public HistorialPropietario(int idPropietario, String idVehiculo) {
        this.historialPropietarioPK = new HistorialPropietarioPK(idPropietario, idVehiculo);
    }

    public HistorialPropietarioPK getHistorialPropietarioPK() {
        return historialPropietarioPK;
    }

    public void setHistorialPropietarioPK(HistorialPropietarioPK historialPropietarioPK) {
        this.historialPropietarioPK = historialPropietarioPK;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historialPropietarioPK != null ? historialPropietarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialPropietario)) {
            return false;
        }
        HistorialPropietario other = (HistorialPropietario) object;
        if ((this.historialPropietarioPK == null && other.historialPropietarioPK != null) || (this.historialPropietarioPK != null && !this.historialPropietarioPK.equals(other.historialPropietarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.HistorialPropietario[ historialPropietarioPK=" + historialPropietarioPK + " ]";
    }
    
}

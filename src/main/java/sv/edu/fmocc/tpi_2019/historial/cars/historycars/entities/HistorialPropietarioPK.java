/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kevin
 */
@Embeddable
public class HistorialPropietarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_propietario")
    private int idPropietario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_vehiculo")
    private String idVehiculo;

    public HistorialPropietarioPK() {
    }

    public HistorialPropietarioPK(int idPropietario, String idVehiculo) {
        this.idPropietario = idPropietario;
        this.idVehiculo = idVehiculo;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPropietario;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialPropietarioPK)) {
            return false;
        }
        HistorialPropietarioPK other = (HistorialPropietarioPK) object;
        if (this.idPropietario != other.idPropietario) {
            return false;
        }
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.HistorialPropietarioPK[ idPropietario=" + idPropietario + ", idVehiculo=" + idVehiculo + " ]";
    }
    
}

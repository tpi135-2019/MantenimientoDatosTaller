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

/**
 *
 * @author kevin
 */
@Embeddable
public class PasoProcesoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proceso")
    private int idProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_paso")
    private int idPaso;

    public PasoProcesoPK() {
    }

    public PasoProcesoPK(int idProceso, int idPaso) {
        this.idProceso = idProceso;
        this.idPaso = idPaso;
    }

    public int getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }

    public int getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(int idPaso) {
        this.idPaso = idPaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProceso;
        hash += (int) idPaso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PasoProcesoPK)) {
            return false;
        }
        PasoProcesoPK other = (PasoProcesoPK) object;
        if (this.idProceso != other.idProceso) {
            return false;
        }
        if (this.idPaso != other.idPaso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.PasoProcesoPK[ idProceso=" + idProceso + ", idPaso=" + idPaso + " ]";
    }
    
}

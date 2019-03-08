/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "paso_reparacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PasoReparacion.findAll", query = "SELECT p FROM PasoReparacion p")
    , @NamedQuery(name = "PasoReparacion.findByIdReparacion", query = "SELECT p FROM PasoReparacion p WHERE p.pasoReparacionPK.idReparacion = :idReparacion")
    , @NamedQuery(name = "PasoReparacion.findByIdProceso", query = "SELECT p FROM PasoReparacion p WHERE p.pasoReparacionPK.idProceso = :idProceso")
    , @NamedQuery(name = "PasoReparacion.findByIdPaso", query = "SELECT p FROM PasoReparacion p WHERE p.pasoReparacionPK.idPaso = :idPaso")})
public class PasoReparacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PasoReparacionPK pasoReparacionPK;
    @JoinColumn(name = "id_estado_paso", referencedColumnName = "id_estado_paso")
    @ManyToOne(optional = false)
    private EstadoPaso idEstadoPaso;
    @JoinColumns({
        @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso", insertable = false, updatable = false)
        , @JoinColumn(name = "id_paso", referencedColumnName = "id_paso", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PasoProceso pasoProceso;
    @JoinColumn(name = "id_reparacion", referencedColumnName = "id_reparacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reparacion reparacion;

    public PasoReparacion() {
    }

    public PasoReparacion(PasoReparacionPK pasoReparacionPK) {
        this.pasoReparacionPK = pasoReparacionPK;
    }

    public PasoReparacion(int idReparacion, int idProceso, int idPaso) {
        this.pasoReparacionPK = new PasoReparacionPK(idReparacion, idProceso, idPaso);
    }

    public PasoReparacionPK getPasoReparacionPK() {
        return pasoReparacionPK;
    }

    public void setPasoReparacionPK(PasoReparacionPK pasoReparacionPK) {
        this.pasoReparacionPK = pasoReparacionPK;
    }

    public EstadoPaso getIdEstadoPaso() {
        return idEstadoPaso;
    }

    public void setIdEstadoPaso(EstadoPaso idEstadoPaso) {
        this.idEstadoPaso = idEstadoPaso;
    }

    public PasoProceso getPasoProceso() {
        return pasoProceso;
    }

    public void setPasoProceso(PasoProceso pasoProceso) {
        this.pasoProceso = pasoProceso;
    }

    public Reparacion getReparacion() {
        return reparacion;
    }

    public void setReparacion(Reparacion reparacion) {
        this.reparacion = reparacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pasoReparacionPK != null ? pasoReparacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PasoReparacion)) {
            return false;
        }
        PasoReparacion other = (PasoReparacion) object;
        if ((this.pasoReparacionPK == null && other.pasoReparacionPK != null) || (this.pasoReparacionPK != null && !this.pasoReparacionPK.equals(other.pasoReparacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.PasoReparacion[ pasoReparacionPK=" + pasoReparacionPK + " ]";
    }
    
}

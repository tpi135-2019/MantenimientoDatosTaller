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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "paso_proceso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PasoProceso.findAll", query = "SELECT p FROM PasoProceso p")
    , @NamedQuery(name = "PasoProceso.findByIdProceso", query = "SELECT p FROM PasoProceso p WHERE p.pasoProcesoPK.idProceso = :idProceso")
    , @NamedQuery(name = "PasoProceso.findByIdPaso", query = "SELECT p FROM PasoProceso p WHERE p.pasoProcesoPK.idPaso = :idPaso")
    , @NamedQuery(name = "PasoProceso.findByNumeroPaso", query = "SELECT p FROM PasoProceso p WHERE p.numeroPaso = :numeroPaso")})
public class PasoProceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PasoProcesoPK pasoProcesoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_paso")
    private int numeroPaso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pasoProceso")
    private List<PasoReparacion> pasoReparacionList;
    @JoinColumn(name = "id_tipo_paso", referencedColumnName = "id_tipo_paso")
    @ManyToOne(optional = false)
    private TipoPaso idTipoPaso;
    @JoinColumn(name = "id_paso", referencedColumnName = "id_paso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paso paso;
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proceso proceso;

    public PasoProceso() {
    }

    public PasoProceso(PasoProcesoPK pasoProcesoPK) {
        this.pasoProcesoPK = pasoProcesoPK;
    }

    public PasoProceso(PasoProcesoPK pasoProcesoPK, int numeroPaso) {
        this.pasoProcesoPK = pasoProcesoPK;
        this.numeroPaso = numeroPaso;
    }

    public PasoProceso(int idProceso, int idPaso) {
        this.pasoProcesoPK = new PasoProcesoPK(idProceso, idPaso);
    }

    public PasoProcesoPK getPasoProcesoPK() {
        return pasoProcesoPK;
    }

    public void setPasoProcesoPK(PasoProcesoPK pasoProcesoPK) {
        this.pasoProcesoPK = pasoProcesoPK;
    }

    public int getNumeroPaso() {
        return numeroPaso;
    }

    public void setNumeroPaso(int numeroPaso) {
        this.numeroPaso = numeroPaso;
    }

    @XmlTransient
    public List<PasoReparacion> getPasoReparacionList() {
        return pasoReparacionList;
    }

    public void setPasoReparacionList(List<PasoReparacion> pasoReparacionList) {
        this.pasoReparacionList = pasoReparacionList;
    }

    public TipoPaso getIdTipoPaso() {
        return idTipoPaso;
    }

    public void setIdTipoPaso(TipoPaso idTipoPaso) {
        this.idTipoPaso = idTipoPaso;
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pasoProcesoPK != null ? pasoProcesoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PasoProceso)) {
            return false;
        }
        PasoProceso other = (PasoProceso) object;
        if ((this.pasoProcesoPK == null && other.pasoProcesoPK != null) || (this.pasoProcesoPK != null && !this.pasoProcesoPK.equals(other.pasoProcesoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.PasoProceso[ pasoProcesoPK=" + pasoProcesoPK + " ]";
    }
    
}

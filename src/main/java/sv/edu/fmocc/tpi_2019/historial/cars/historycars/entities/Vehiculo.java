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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v")
    , @NamedQuery(name = "Vehiculo.findByIdVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.idVehiculo = :idVehiculo")
    , @NamedQuery(name = "Vehiculo.findByNumeroChasis", query = "SELECT v FROM Vehiculo v WHERE v.numeroChasis = :numeroChasis")
    , @NamedQuery(name = "Vehiculo.findByNumeroMotor", query = "SELECT v FROM Vehiculo v WHERE v.numeroMotor = :numeroMotor")
    , @NamedQuery(name = "Vehiculo.findByVin", query = "SELECT v FROM Vehiculo v WHERE v.vin = :vin")
    , @NamedQuery(name = "Vehiculo.findByColor", query = "SELECT v FROM Vehiculo v WHERE v.color = :color")})
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_vehiculo")
    private String idVehiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 17)
    @Column(name = "numero_chasis")
    private String numeroChasis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "numero_motor")
    private String numeroMotor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 17)
    @Column(name = "vin")
    private String vin;
    @Size(max = 45)
    @Column(name = "color")
    private String color;
    @JoinColumn(name = "modelo_id_modelo", referencedColumnName = "id_modelo")
    @ManyToOne(optional = false)
    private Modelo modeloIdModelo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVehiculo")
    private List<Diagnostico> diagnosticoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
    private List<HistorialPropietario> historialPropietarioList;

    public Vehiculo() {
    }

    public Vehiculo(String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Vehiculo(String idVehiculo, String numeroChasis, String numeroMotor, String vin) {
        this.idVehiculo = idVehiculo;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.vin = vin;
    }

    public String getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Modelo getModeloIdModelo() {
        return modeloIdModelo;
    }

    public void setModeloIdModelo(Modelo modeloIdModelo) {
        this.modeloIdModelo = modeloIdModelo;
    }

    @XmlTransient
    public List<Diagnostico> getDiagnosticoList() {
        return diagnosticoList;
    }

    public void setDiagnosticoList(List<Diagnostico> diagnosticoList) {
        this.diagnosticoList = diagnosticoList;
    }

    @XmlTransient
    public List<HistorialPropietario> getHistorialPropietarioList() {
        return historialPropietarioList;
    }

    public void setHistorialPropietarioList(List<HistorialPropietario> historialPropietarioList) {
        this.historialPropietarioList = historialPropietarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Vehiculo[ idVehiculo=" + idVehiculo + " ]";
    }
    
}

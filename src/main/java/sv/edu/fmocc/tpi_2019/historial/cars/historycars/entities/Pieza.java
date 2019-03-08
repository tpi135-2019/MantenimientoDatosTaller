/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "pieza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pieza.findAll", query = "SELECT p FROM Pieza p")
    , @NamedQuery(name = "Pieza.findByIdPieza", query = "SELECT p FROM Pieza p WHERE p.idPieza = :idPieza")
    , @NamedQuery(name = "Pieza.findByNombre", query = "SELECT p FROM Pieza p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Pieza.findByActivo", query = "SELECT p FROM Pieza p WHERE p.activo = :activo")
    , @NamedQuery(name = "Pieza.findByObservacion", query = "SELECT p FROM Pieza p WHERE p.observacion = :observacion")})
public class Pieza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pieza")
    private Integer idPieza;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 45)
    @Column(name = "observacion")
    private String observacion;
    @JoinTable(name = "pieza_reparacion", joinColumns = {
        @JoinColumn(name = "id_pieza", referencedColumnName = "id_pieza")}, inverseJoinColumns = {
        @JoinColumn(name = "id_reparacion", referencedColumnName = "id_reparacion")})
    @ManyToMany
    private List<Reparacion> reparacionList;
    @JoinColumn(name = "id_sub_parte", referencedColumnName = "id_sub_parte")
    @ManyToOne(optional = false)
    private SubParte idSubParte;

    public Pieza() {
    }

    public Pieza(Integer idPieza) {
        this.idPieza = idPieza;
    }

    public Pieza(Integer idPieza, String nombre) {
        this.idPieza = idPieza;
        this.nombre = nombre;
    }

    public Integer getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(Integer idPieza) {
        this.idPieza = idPieza;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @XmlTransient
    public List<Reparacion> getReparacionList() {
        return reparacionList;
    }

    public void setReparacionList(List<Reparacion> reparacionList) {
        this.reparacionList = reparacionList;
    }

    public SubParte getIdSubParte() {
        return idSubParte;
    }

    public void setIdSubParte(SubParte idSubParte) {
        this.idSubParte = idSubParte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPieza != null ? idPieza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pieza)) {
            return false;
        }
        Pieza other = (Pieza) object;
        if ((this.idPieza == null && other.idPieza != null) || (this.idPieza != null && !this.idPieza.equals(other.idPieza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Pieza[ idPieza=" + idPieza + " ]";
    }
    
}

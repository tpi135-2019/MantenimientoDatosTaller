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
@Table(name = "sub_parte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubParte.findAll", query = "SELECT s FROM SubParte s")
    , @NamedQuery(name = "SubParte.findByIdSubParte", query = "SELECT s FROM SubParte s WHERE s.idSubParte = :idSubParte")
    , @NamedQuery(name = "SubParte.findByNombre", query = "SELECT s FROM SubParte s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SubParte.findByActivo", query = "SELECT s FROM SubParte s WHERE s.activo = :activo")
    , @NamedQuery(name = "SubParte.findByDescripcion", query = "SELECT s FROM SubParte s WHERE s.descripcion = :descripcion")})
public class SubParte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sub_parte")
    private Integer idSubParte;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubParte")
    private List<Pieza> piezaList;
    @JoinColumn(name = "id_parte", referencedColumnName = "id_parte")
    @ManyToOne(optional = false)
    private Parte idParte;

    public SubParte() {
    }

    public SubParte(Integer idSubParte) {
        this.idSubParte = idSubParte;
    }

    public SubParte(Integer idSubParte, String nombre) {
        this.idSubParte = idSubParte;
        this.nombre = nombre;
    }

    public Integer getIdSubParte() {
        return idSubParte;
    }

    public void setIdSubParte(Integer idSubParte) {
        this.idSubParte = idSubParte;
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
    public List<Pieza> getPiezaList() {
        return piezaList;
    }

    public void setPiezaList(List<Pieza> piezaList) {
        this.piezaList = piezaList;
    }

    public Parte getIdParte() {
        return idParte;
    }

    public void setIdParte(Parte idParte) {
        this.idParte = idParte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubParte != null ? idSubParte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubParte)) {
            return false;
        }
        SubParte other = (SubParte) object;
        if ((this.idSubParte == null && other.idSubParte != null) || (this.idSubParte != null && !this.idSubParte.equals(other.idSubParte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.SubParte[ idSubParte=" + idSubParte + " ]";
    }
    
}

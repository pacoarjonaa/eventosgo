/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "ENTRADA_AFORO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaAforo.findAll", query = "SELECT e FROM EntradaAforo e")
    , @NamedQuery(name = "EntradaAforo.findById", query = "SELECT e FROM EntradaAforo e WHERE e.id = :id")
    , @NamedQuery(name = "EntradaAforo.findByFila", query = "SELECT e FROM EntradaAforo e WHERE e.fila = :fila")
    , @NamedQuery(name = "EntradaAforo.findByAsiento", query = "SELECT e FROM EntradaAforo e WHERE e.asiento = :asiento")})
public class EntradaAforo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FILA")
    private int fila;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASIENTO")
    private int asiento;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Entrada entrada;

    public EntradaAforo() {
    }

    public EntradaAforo(Integer id) {
        this.id = id;
    }

    public EntradaAforo(Integer id, int fila, int asiento) {
        this.id = id;
        this.fila = fila;
        this.asiento = asiento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradaAforo)) {
            return false;
        }
        EntradaAforo other = (EntradaAforo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eventosgowebapp.entities.EntradaAforo[ id=" + id + " ]";
    }
    
}

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
@Table(name = "EVENTO_AFORO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventoAforo.findAll", query = "SELECT e FROM EventoAforo e")
    , @NamedQuery(name = "EventoAforo.findById", query = "SELECT e FROM EventoAforo e WHERE e.id = :id")
    , @NamedQuery(name = "EventoAforo.findByFilas", query = "SELECT e FROM EventoAforo e WHERE e.filas = :filas")
    , @NamedQuery(name = "EventoAforo.findByAsientos", query = "SELECT e FROM EventoAforo e WHERE e.asientos = :asientos")})
public class EventoAforo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FILAS")
    private int filas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASIENTOS")
    private int asientos;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Evento evento;

    public EventoAforo() {
    }

    public EventoAforo(Integer id) {
        this.id = id;
    }

    public EventoAforo(Integer id, int filas, int asientos) {
        this.id = id;
        this.filas = filas;
        this.asientos = asientos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
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
        if (!(object instanceof EventoAforo)) {
            return false;
        }
        EventoAforo other = (EventoAforo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eventosgowebapp.entities.EventoAforo[ id=" + id + " ]";
    }
    
}

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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "EVENTO_ETIQUETA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventoEtiqueta.findAll", query = "SELECT e FROM EventoEtiqueta e")
    , @NamedQuery(name = "EventoEtiqueta.findById", query = "SELECT e FROM EventoEtiqueta e WHERE e.id = :id")})
public class EventoEtiqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "ID_ETIQUETA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Etiqueta idEtiqueta;
    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Evento idEvento;

    public EventoEtiqueta() {
    }

    public EventoEtiqueta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Etiqueta getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(Etiqueta idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
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
        if (!(object instanceof EventoEtiqueta)) {
            return false;
        }
        EventoEtiqueta other = (EventoEtiqueta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eventosgowebapp.entity.EventoEtiqueta[ id=" + id + " ]";
    }
    
}

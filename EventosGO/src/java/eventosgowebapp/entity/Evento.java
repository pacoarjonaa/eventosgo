/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kiko BM
 */
@Entity
@Table(name = "EVENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
    , @NamedQuery(name = "Evento.findById", query = "SELECT e FROM Evento e WHERE e.id = :id")
    , @NamedQuery(name = "Evento.findByTitulo", query = "SELECT e FROM Evento e WHERE e.titulo = :titulo")
    , @NamedQuery(name = "Evento.findByFechaEvento", query = "SELECT e FROM Evento e WHERE e.fechaEvento = :fechaEvento")
    , @NamedQuery(name = "Evento.findByFechaFinReservas", query = "SELECT e FROM Evento e WHERE e.fechaFinReservas = :fechaFinReservas")
    , @NamedQuery(name = "Evento.findByCoste", query = "SELECT e FROM Evento e WHERE e.coste = :coste")
    , @NamedQuery(name = "Evento.findByMaximoEntradasUsuario", query = "SELECT e FROM Evento e WHERE e.maximoEntradasUsuario = :maximoEntradasUsuario")
    , @NamedQuery(name = "Evento.findByAforo", query = "SELECT e FROM Evento e WHERE e.aforo = :aforo")})
public class Evento implements Serializable {

    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @Lob()
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "FECHA_EVENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaEvento;
    @Basic(optional = false)
    @Column(name = "FECHA_FIN_RESERVAS")
    @Temporal(TemporalType.DATE)
    private Date fechaFinReservas;
    @Basic(optional = false)
    @Column(name = "COSTE")
    private double coste;
    @Basic(optional = false)
    @Column(name = "MAXIMO_ENTRADAS_USUARIO")
    private int maximoEntradasUsuario;
    @Basic(optional = false)
    @Column(name = "AFORO")
    private int aforo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvento")
    private List<EventoEtiqueta> eventoEtiquetaList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "evento")
    private EventoAforo eventoAforo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvento")
    private List<Entrada> entradaList;
    @JoinColumn(name = "ID_CREADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario idCreador;

    public Evento() {
    }

    public Evento(Integer id) {
        this.id = id;
    }

    public Evento(Integer id, String titulo, String descripcion, Date fechaEvento, Date fechaFinReservas, double coste, int maximoEntradasUsuario, int aforo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaEvento = fechaEvento;
        this.fechaFinReservas = fechaFinReservas;
        this.coste = coste;
        this.maximoEntradasUsuario = maximoEntradasUsuario;
        this.aforo = aforo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaFinReservas() {
        return fechaFinReservas;
    }

    public void setFechaFinReservas(Date fechaFinReservas) {
        this.fechaFinReservas = fechaFinReservas;
    }


    public int getMaximoEntradasUsuario() {
        return maximoEntradasUsuario;
    }

    public void setMaximoEntradasUsuario(int maximoEntradasUsuario) {
        this.maximoEntradasUsuario = maximoEntradasUsuario;
    }


    @XmlTransient
    public List<EventoEtiqueta> getEventoEtiquetaList() {
        return eventoEtiquetaList;
    }

    public void setEventoEtiquetaList(List<EventoEtiqueta> eventoEtiquetaList) {
        this.eventoEtiquetaList = eventoEtiquetaList;
    }

    public EventoAforo getEventoAforo() {
        return eventoAforo;
    }

    public void setEventoAforo(EventoAforo eventoAforo) {
        this.eventoAforo = eventoAforo;
    }

    @XmlTransient
    public List<Entrada> getEntradaList() {
        return entradaList;
    }

    public void setEntradaList(List<Entrada> entradaList) {
        this.entradaList = entradaList;
    }

    public Usuario getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(Usuario idCreador) {
        this.idCreador = idCreador;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eventosgowebapp.entities.Evento[ id=" + id + " ]";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }
    
    
}

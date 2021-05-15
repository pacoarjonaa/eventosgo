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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author juanm
 */
@Entity
@Table(name = "USUARIO_EVENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioEvento.findAll", query = "SELECT u FROM UsuarioEvento u")
    , @NamedQuery(name = "UsuarioEvento.findById", query = "SELECT u FROM UsuarioEvento u WHERE u.id = :id")
    , @NamedQuery(name = "UsuarioEvento.findByApellidos", query = "SELECT u FROM UsuarioEvento u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "UsuarioEvento.findByDomicilio", query = "SELECT u FROM UsuarioEvento u WHERE u.domicilio = :domicilio")
    , @NamedQuery(name = "UsuarioEvento.findByCiudad", query = "SELECT u FROM UsuarioEvento u WHERE u.ciudad = :ciudad")
    , @NamedQuery(name = "UsuarioEvento.findByFechaNacimiento", query = "SELECT u FROM UsuarioEvento u WHERE u.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "UsuarioEvento.findBySexo", query = "SELECT u FROM UsuarioEvento u WHERE u.sexo = :sexo")})
public class UsuarioEvento implements Serializable {

    @Column(name = "APELLIDOS", length=50, nullable = false)
    private String apellidos;
    @Column(name = "DOMICILIO", length=100, nullable = false)
    private String domicilio;
    @Column(name = "CIUDAD", length=50, nullable = false)
    private String ciudad;
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "SEXO", nullable = false)
    private int sexo;

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Entrada> entradaList;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public UsuarioEvento() {
    }

    public UsuarioEvento(Integer id) {
        this.id = id;
    }

    public UsuarioEvento(Integer id, String apellidos, String domicilio, String ciudad, Date fechaNacimiento, int sexo) {
        this.id = id;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    @XmlTransient
    public List<Entrada> getEntradaList() {
        return entradaList;
    }

    public void setEntradaList(List<Entrada> entradaList) {
        this.entradaList = entradaList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof UsuarioEvento)) {
            return false;
        }
        UsuarioEvento other = (UsuarioEvento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eventosgowebapp.entities.UsuarioEvento[ id=" + id + " ]";
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.dao;

import eventosgowebapp.entity.Entrada;
import eventosgowebapp.entity.Evento;
import eventosgowebapp.entity.Usuario;
import eventosgowebapp.entity.UsuarioEvento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author juanm
 */
@Stateless
public class EntradaFacade extends AbstractFacade<Entrada> {

    @PersistenceContext(unitName = "eventosgoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntradaFacade() {
        super(Entrada.class);
    }

     
    public List<Entrada> findByIdEvento(Evento evento){
        Query q;
        
        q=em.createQuery("select e from Entrada e where e.idEvento = :idEvento");
        q.setParameter("idEvento", evento);
        return q.getResultList();
    }
    
    public List<Entrada> findByIdUsuario(Integer idUsuario){
        Query q;
        List<Entrada> listaEntradas = null;
        
        q=em.createQuery("select e from Entrada e where e.idUsuario.id = :idUsuario");
        q.setParameter("idUsuario", idUsuario);
        
        listaEntradas = q.getResultList();
        if( q.getResultList() == null){
            return null;
        }else{
            return listaEntradas;
        }
    }
    
    public List<Entrada> findByIdUsuarioAndIdEvento(UsuarioEvento usuario, Evento evento){
        Query q;
        
        q=em.createQuery("select e from Entrada e where e.idUsuario = :idUsuario and e.idEvento = :idEvento");
        q.setParameter("idUsuario", usuario);
        q.setParameter("idEvento", evento);
        return q.getResultList();
    }
}

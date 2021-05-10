/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.dao;

import eventosgowebapp.entity.Evento;
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
public class EventoFacade extends AbstractFacade<Evento> {

    @PersistenceContext(unitName = "eventosgoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventoFacade() {
        super(Evento.class);
    }
    
    public Evento getByID(int id) {
        Query q;
        List<Evento> res;
        q = this.em.createQuery("SELECT u FROM EVENTO u WHERE u.ID = :id");
        q.setParameter("id", id);
        res = q.getResultList();
        
        if (res == null || res.isEmpty()) {
            return null;
        } else {
            return res.get(0);
        }
    }
    
    public List<Evento> filtroAnio(int anio) {
        Query q;
        List<Evento> res;
        q = this.em.createQuery("SELECT e FROM EVENTO e WHERE YEAR(e.FECHA_EVENTO) = :anio");
        q.setParameter("anio", anio);
        res = q.getResultList();
        
        if (res == null || res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }
    
}

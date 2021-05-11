/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.dao;

import eventosgowebapp.entity.Evento;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;

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

    public List<Evento> filtroAnio(int anio, List<Evento> eventos) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
        List<Evento> res = new ArrayList<>();
        for (Evento e : eventos) {
            if (Integer.parseInt(sdf.format(e.getFechaEvento())) == anio) {
                res.add(e);
            }
        }
        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public List<Evento> findByIdCreador(Integer id) {
        Query q;

        q = em.createQuery("select e from Evento e where e.idCreador.id = :idCreador ");
        q.setParameter("idCreador", id);
        List<Evento> res = q.getResultList();
        return res;
    }

    public List<Evento> findBySimiliarName(String name) {
        Query q;

        q = em.createQuery("select e from Evento e where e.titulo like :titulo ");
        q.setParameter("titulo", "%" + name + "%");
        List<Evento> res = q.getResultList();
        return res;
    }

    public List<Evento> findBySimiliarNameAndIdCreador(String name, Integer id) {
        Query q;

        q = em.createQuery("select e from Evento e where e.titulo like :titulo and e.idCreador.id = :idCreador ");
        q.setParameter("titulo", "%" + name + "%");
        q.setParameter("idCreador", id);
        List<Evento> res = q.getResultList();
        return res;
    }

}

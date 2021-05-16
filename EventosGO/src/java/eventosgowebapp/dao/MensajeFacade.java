/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.dao;

import eventosgowebapp.entity.Mensaje;
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
public class MensajeFacade extends AbstractFacade<Mensaje> {

    @PersistenceContext(unitName = "eventosgoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MensajeFacade() {
        super(Mensaje.class);
    }

    public List<Mensaje> chat(int c) {
        Query q;
        List<Mensaje> res;
        q = this.em.createQuery("SELECT m FROM Mensaje m WHERE m.idConversacion = :c");
        q.setParameter("c", c);
        res = q.getResultList();
        return res;
    }
}

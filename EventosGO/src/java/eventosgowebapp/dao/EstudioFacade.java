/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.dao;

import eventosgowebapp.entity.Estudio;
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
public class EstudioFacade extends AbstractFacade<Estudio> {

    @PersistenceContext(unitName = "eventosgoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudioFacade() {
        super(Estudio.class);
    }
    
    public Estudio findByID(int id) {
        Query q;
        List<Estudio> res;
        q = this.em.createQuery("SELECT e FROM ESTUDIO e WHERE e.ID = :id");
        q.setParameter("id", id);
        res = q.getResultList();

        if (res == null || res.isEmpty()) {
            return null;
        } else {
            return res.get(0);
        }
    }
 
}

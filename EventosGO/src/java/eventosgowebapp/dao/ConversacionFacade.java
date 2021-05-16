/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.dao;

import eventosgowebapp.entity.Conversacion;
import eventosgowebapp.entity.Usuario;
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
public class ConversacionFacade extends AbstractFacade<Conversacion> {

    @PersistenceContext(unitName = "eventosgoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConversacionFacade() {
        super(Conversacion.class);
    }

    public List<Conversacion> findByUsuario(Usuario u) {
        Query q;
        q = this.em.createQuery("SELECT c FROM Conversacion c WHERE c.idUsuario = :u1 OR c.idTeleoperador = :u2");
        q.setParameter("u1", u);
        q.setParameter("u2", u);
        List<Conversacion> lista = q.getResultList();
        return lista;
    }

}

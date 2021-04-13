/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.dao;

import eventosgowebapp.entity.EventoEtiqueta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pacoa
 */
@Stateless
public class EventoEtiquetaFacade extends AbstractFacade<EventoEtiqueta> {

    @PersistenceContext(unitName = "EventosGOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventoEtiquetaFacade() {
        super(EventoEtiqueta.class);
    }
    
}

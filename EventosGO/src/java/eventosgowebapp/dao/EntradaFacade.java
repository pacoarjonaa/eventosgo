/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.dao;

import eventosgowebapp.entity.Entrada;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pacoa
 */
@Stateless
public class EntradaFacade extends AbstractFacade<Entrada> {

    @PersistenceContext(unitName = "EventosGOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntradaFacade() {
        super(Entrada.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.dao;

import eventosgowebapp.entity.Etiqueta;
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
public class EtiquetaFacade extends AbstractFacade<Etiqueta> {

    @PersistenceContext(unitName = "eventosgoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtiquetaFacade() {
        super(Etiqueta.class);
    }
    
    public Etiqueta findByName(String filtro){
        Query q;
        List<Etiqueta> lst;
        Etiqueta et = null;
        
        q = em.createQuery("SELECT e FROM Etiqueta e WHERE e.nombre=:nombre");
        q.setParameter("nombre", filtro);
        lst = q.getResultList();
        
        if(!lst.isEmpty()){
             et = lst.get(0);
        }
        return et;
    }
    
}

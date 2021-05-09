/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.dao;

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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "eventosgoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario findByCorreo(String email){
        Query q;
        
        q = em.createNamedQuery("Usuario.findByCorreo");
        q.setParameter("correo", email);
        List<Usuario> res = q.getResultList();
        Usuario u;
        
        if(res == null || res.isEmpty()){
            u=null;
        } else{
            u = res.get(0);
        }
        return u;
    }
    
}

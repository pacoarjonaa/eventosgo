/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.dao;

import eventosgowebapp.entity.Usuario;
import eventosgowebapp.entity.UsuarioEvento;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
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
public class UsuarioEventoFacade extends AbstractFacade<UsuarioEvento> {

    @PersistenceContext(unitName = "eventosgoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioEventoFacade() {
        super(UsuarioEvento.class);
    }

    public List<UsuarioEvento> filtroCiudad(String c, List<UsuarioEvento> l) {
        Query q;
        List<UsuarioEvento> res = new LinkedList<>();
        if (l == null) {
            q = this.em.createQuery("SELECT u FROM USUARIO_EVENTO u WHERE u.CIUDAD = :ciudad");
            q.setParameter("ciudad", c);
            res = q.getResultList();
        } else {
            for (UsuarioEvento u : l) {
                if (c.equalsIgnoreCase(u.getCiudad())) {
                    res.add(u);
                }
            }
        }

        return res;

    }

    public List<UsuarioEvento> filtroSexo(int s, List<UsuarioEvento> l) {
        Query q;
        List<UsuarioEvento> res = new LinkedList<>();
        if (l == null) {
            q = this.em.createQuery("SELECT u FROM USUARIO_EVENTO u WHERE u.SEXO = :sexo");
            q.setParameter("sexo", s);
            res = q.getResultList();
        } else {
            for (UsuarioEvento u : l) {
                if (s == u.getSexo()) {
                    res.add(u);
                }
            }
        }

        return res;

    }

    public List<UsuarioEvento> filtroEdad(int min, int max, List<UsuarioEvento> l) {
        Query q;
        List<UsuarioEvento> res = new LinkedList<>();
        if (l == null) {
            q = this.em.createQuery("SELECT u FROM USUARIO_EVENTO u WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, u.FECHA_NACIMIENTO)/12) >= :min AND TRUNC(MONTHS_BETWEEN(SYSDATE, u.FECHA_NACIMIENTO)/12) <= :max");
            q.setParameter("min", min);
            q.setParameter("max", max);
            res = q.getResultList();
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD/MM/YYYY");
            SimpleDateFormat sdt = new SimpleDateFormat("DD/MM/YYYY");

            LocalDate ld = LocalDate.now();
            for (UsuarioEvento u : l) {
                LocalDate fecha_nac = LocalDate.parse(sdt.format(u.getFechaNacimiento()), dtf);
                int x = Period.between(fecha_nac, ld).getYears();
                if (min <= x && x <= max) {
                    res.add(u);
                }
            }
        }

        return res;
    }

    public UsuarioEvento getByID(int id) {
        Query q;
        List<UsuarioEvento> res;
        q = this.em.createQuery("SELECT u FROM USUARIO_EVENTO u WHERE u.ID = :id");
        q.setParameter("id", id);
        res = q.getResultList();

        if (res == null || res.isEmpty()) {
            return null;
        } else {
            return res.get(0);
        }
    }

}

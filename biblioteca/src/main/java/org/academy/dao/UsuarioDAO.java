//classe responsável pelo CRUD da tabela "usuarios"
package org.academy.dao;


import org.academy.model.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioDAO {

    private SessionFactory factory = ServiceDAO.getSessionFactory();

    public void salvar(Usuario usuario) {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
        }catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new HibernateException("Erro ao salvar o usuário!" + e.getMessage());
        }
    }

    public List<Usuario> listar(){
        try(Session session = factory.openSession()) {
            return session.createQuery("from Usuario", Usuario.class).getResultList();
        }catch(HibernateException e) {
            e.printStackTrace();
            throw new HibernateException("Não é possível listar os usuários!: " + e.getMessage());
        }
    }

    public Usuario buscarPorEmail(String email){
        try(Session session = factory.openSession()) {
            return session.createQuery("from Usuario where email = :email", Usuario.class).setParameter("email", email).uniqueResult();
        }catch(HibernateException e) {
            e.printStackTrace();
            throw new HibernateException("Erro ao fazer a busca por e-mail!: " + e.getMessage());
        }
    }
}
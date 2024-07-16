//classe responsável pelo CRUD da tabela "livros"
package org.academy.dao;

import org.academy.model.Livro;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class LivroDAO {

    private SessionFactory factory = ServiceDAO.getSessionFactory();

    public List<Livro> findAll(){
        try (Session session = factory.openSession()) {
            return session.createQuery("from Livro").list();
        }catch (Exception e) {
            e.printStackTrace();
            throw new HibernateException("Não foi possivel encontrar todos os livros" + e.getMessage());
        }
    }

    public void save(Livro livro) {
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(livro);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new HibernateException("Erro ao gravar novo livro" + e.getMessage());
        }
    }

    public void delete(Livro livro) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(livro);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new HibernateException("Erro ao excluir livro" + e.getMessage());
        }
    }


    public Livro findById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Livro.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HibernateException("Erro ao buscar Livro " + e.getMessage());
        }
    }

    public void update(Livro livro) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.update(livro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new HibernateException("Erro ao atualizar Livro    " + e.getMessage());
        }
    }

}
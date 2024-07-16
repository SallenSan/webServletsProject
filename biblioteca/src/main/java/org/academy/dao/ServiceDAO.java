package org.academy.dao;


import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ServiceDAO {

    @Getter
    public static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try{
            return new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        }catch (Throwable e){
            throw new ExceptionInInitializerError("Erro na configuração do Hibernate" + e);
        }
    }

}
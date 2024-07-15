package org.academy.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.academy.model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private SessionFactory sessionFactory;

    public void init() throws ServletException {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Usuario.class).buildSessionFactory();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

       Session session = sessionFactory.getCurrentSession();

       try{
           session.beginTransaction();
           List<Usuario> usuarios = session.createQuery("from Usuario where email = :email and senha = :senha").setParameter("email", email).setParameter("senha", senha).getResultList();
           session.getTransaction().commit();

           if(!usuarios.isEmpty()){
               HttpSession httpSession = request.getSession();
               httpSession.setAttribute("usuario", usuarios.get(0));
               response.sendRedirect("index.jsp");
           }else{
               response.sendRedirect("login.jsp");
           }
       }finally {
           session.close();
       }
    }

}

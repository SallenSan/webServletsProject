package org.academy.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.academy.dao.UsuarioDAO;
import org.academy.model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    public void init() throws ServletException{
        usuarioDAO = new UsuarioDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String action = request.getParameter("action");
        if(action == null){
            action = "list";
        }
        switch(action){
            case "create":
                cadastrarUsuario(request, response);
                break;
            default:
                listarUsuarios(request, response);
                break;
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        listarUsuarios(request, response);
    }

    private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // validações de entrada
        if(nome == null || nome.isEmpty() || email == null || email.isEmpty() || senha == null || senha.isEmpty()){
            request.setAttribute("erro", "Os campos devem ser preenchidos!");
            request.getRequestDispatcher("/cadastroUsuario.jsp").forward(request, response);
            return;
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        usuarioDAO.salvar(usuario);
        response.sendRedirect("usuario");
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        List<Usuario> usuarios = usuarioDAO.listar();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/cadastroUsuario.jsp").forward(request, response);
    }

}


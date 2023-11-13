package pt.com.halof.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pt.com.halof.todolist.user.IUserRepository;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {


    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            var servletPath = request.getServletPath();

            if(servletPath.startsWith("/tasks/")){
                System.out.println("Chegou ao filter");
                //Pegar a autenticação (usuario e senha)
                var authorization = request.getHeader("Authorization");

                var authEncoded = authorization.substring("Basic".length()).trim();

                byte [] authDecode = Base64.getDecoder().decode(authEncoded);

                var authString = new String(authDecode);
                String[] credentials = authString.split(":");
                // [lolofpikadas,1234555]
                String username = credentials[0];
                String password = credentials[1];
                System.out.println(username);
                System.out.println(password);
                //Validar usuario
                var user = this.userRepository.findByUsername(username);
                if(user == null){
                    response.sendError(401,"Usuário sem autorização");
                } else {

                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    if(passwordVerify.verified){
                        request.setAttribute("idUser", user.getId());
                        filterChain.doFilter(request,response);

                    } else {
                        response.sendError(401,"Usuário sem autorização");
                    }

                }
                //validar senha
                //segue viagem
            } else {
                filterChain.doFilter(request,response);
            }
        }




    }



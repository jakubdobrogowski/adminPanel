package pl.sda.java9.servlets;

import pl.sda.java9.database.daos.UserDAO;
import pl.sda.java9.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();

        String user = request.getParameter("user");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        User userByLogin = UserDAO.getUserByLogin(user);

        if (userByLogin.getLogin() == null) {

            writer.print("<font color=red>Użytkownik o podanym adresie email nie istnieje</font>");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.include(request, response);

            return;
        }


        if (!userByLogin.getPassword().equals(password)) {

            writer.print("<font color=red>Hasło nieprawidłowe</font>");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.include(request, response);

            return;
        }

        session.setAttribute("user", userByLogin.getLogin());
//        session.setAttribute("id", userByLogin.getId());
        session.setAttribute("isAdmin", userByLogin.getIsAdmin());
        session.setMaxInactiveInterval(15 * 60);
        response.sendRedirect("/serverList.jsp");

    }

}

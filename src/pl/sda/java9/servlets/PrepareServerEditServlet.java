package pl.sda.java9.servlets;

import org.apache.commons.lang3.StringUtils;
import pl.sda.java9.database.daos.ServerDAO;
import pl.sda.java9.database.daos.UserDAO;
import pl.sda.java9.model.Server;
import pl.sda.java9.utils.CommonUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PrepareServerEditServlet", urlPatterns = "/editServer")
public class PrepareServerEditServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");


        if (!StringUtils.isNumeric(id)) {

            request.setAttribute("allUsers", CommonUtils.createUserMap(UserDAO.getAllUsers()));

            request.setAttribute("newServer", new Server()); // zeby nullpointer nie jebnał

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/editServer.jsp");
            requestDispatcher.forward(request, response);
            return;
        }

        Server server = ServerDAO.getServerById(Integer.valueOf(id));

        request.setAttribute("server", server);


        request.setAttribute("allUsers", CommonUtils.createUserMap(UserDAO.getAllUsers()));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/editServer.jsp");
        requestDispatcher.forward(request, response);


    }
}

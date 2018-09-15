package pl.sda.java9.servlets;

import pl.sda.java9.database.daos.ServerDAO;
import pl.sda.java9.model.Server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SaveServerServlet", urlPatterns = "/saveServer")
public class SaveServerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String host = request.getParameter("host");
        String port = request.getParameter("port");
        String owner = request.getParameter("owner");
        String status = request.getParameter("status");

        Integer id1 = null;
        if(id != null){

            id1 = Integer.valueOf(id);
        }
        Server server = new Server(id1, name, host, Integer.valueOf(port), Integer.valueOf(owner), status);


    }

}


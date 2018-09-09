package pl.sda.java9.database.daos;

import pl.sda.java9.database.DatabaseConector;
import pl.sda.java9.model.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ServerDAO {

    public static Server getServerById(Integer id) {
        Server server = null;

        try (Connection connection = DatabaseConector.getConnection()) {

            if (connection == null) {

                System.out.println("connection to db server is null xdd mamamacka here");
            }

            PreparedStatement ps = connection.prepareStatement("select * from server where id = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            server = createServerFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return server;
    }

    private static Server createServerFromResultSet(ResultSet rs) throws SQLException {

        Server server = new Server();

        while (rs.next()) {

            bulidServer(rs, server);
        }

        return server;
    }


    public static List<Server> getAllServers() {

        List<Server> servers = new ArrayList<>();


        try (Connection connection = DatabaseConector.getConnection()) {

            if (connection == null) {

                System.out.println("connection to db server is null xdd mamamacka here again");
            }

            PreparedStatement ps = connection.prepareStatement("select * from server");
            ResultSet rs = ps.executeQuery();


            servers = returnServersList(rs);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servers;
    }

    private static List<Server> returnServersList(ResultSet rs) throws SQLException {

        List<Server> servers = new ArrayList<>();
        while (rs.next()) {

            Server server = new Server();
            bulidServer(rs, server);
            servers.add(server);
        }
        return servers;
    }

    private static void bulidServer(ResultSet rs, Server server) throws SQLException {
        server.setId(rs.getInt("id"));
        server.setName(rs.getString("name"));
        server.setHost(rs.getString("host"));
        server.setPort(rs.getInt("port"));
        server.setOwner(rs.getInt("owner"));
        server.setStatus(rs.getString("status"));
    }
}

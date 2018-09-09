package pl.sda.java9.database.daos;

import pl.sda.java9.database.DatabaseConector;
import pl.sda.java9.model.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
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

            server.setId(rs.getInt("id"));
            server.setName(rs.getString("name"));
            server.setHost(rs.getString("host"));
            server.setPort(rs.getInt("port"));
            server.setOwner(rs.getInt("owner"));
            server.setStatus(rs.getString("status"));
        }

        return server;
    }


    public static Set<Server> getAllServers() {

        Set<Server> servers = new HashSet<>();


        try (Connection connection = DatabaseConector.getConnection()) {

            if (connection == null) {

                System.out.println("connection to db server is null xdd mamamacka here again");
            }

            PreparedStatement ps = connection.prepareStatement("select * from server");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servers;
    }
}

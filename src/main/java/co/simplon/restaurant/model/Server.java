package co.simplon.restaurant.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final int idServer;
    private final String firstName;
    private final String lastName;

    public Server(int idServer, String firstName, String lastName) {
        this.idServer = idServer;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return idServer + " - " + firstName + " " + lastName;
    }

    // Méthode pour afficher la liste des serveurs

    public static List<Server> getServer(Connection connection) throws SQLException {
        // Pouvoir lister des serveurs
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from server");

        List<Server> serverList = new ArrayList<>();

        while (resultats.next()) {
            Server dbServer = new Server(resultats.getInt("id"),
                    resultats.getString("first_name"),
                    resultats.getString("last_name"));
            serverList.add(dbServer);

            System.out.println(dbServer);
        }
        resultats.close();
        ordreSQL.close();

        return serverList;
    }
}

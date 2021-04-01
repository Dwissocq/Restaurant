package co.simplon.restaurant.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private final int idTable;
    private int numberOfGuest;

    public Table(int idTable, int numberOfGuest){
        this.idTable = idTable;
        this.numberOfGuest = numberOfGuest;
    }

    @Override
    public String toString() {
        return "Numéro de la table : "+ idTable + " => " + numberOfGuest + " (convives maximum)";
    }

    // Méthode pour afficher la liste des tables

    public static List<Table> getTable(Connection connection) throws SQLException {
        // Pouvoir lister les tables
        Statement orderSQL = connection.createStatement();
        ResultSet resultats = orderSQL.executeQuery("SELECT * from tables");

        List<Table> tableList = new ArrayList<>();

        while (resultats.next()) {
            Table dbTable = new Table(resultats.getInt("id"),
                    resultats.getInt("n_guest"));
            tableList.add(dbTable);

            System.out.println(dbTable);
        }
        resultats.close();
        orderSQL.close();

        return tableList;
    }
}


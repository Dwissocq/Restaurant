package co.simplon.restaurant.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dish {
    private final int idDish;
    private final String item;
    private final float unitPrice;

    public Dish(int idDish, String item, float unitPrice) {
        this.idDish = idDish;
        this.item = item;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return idDish+". "+item+" - "+unitPrice + " €";
    }

    // Méthode pour afficher la liste de plats

    public static List<Dish>getDish(Connection connection) throws SQLException {

        Statement orderSQL = connection.createStatement();
        ResultSet resultats = orderSQL.executeQuery("SELECT * from dish");

        List<Dish> dishList = new ArrayList<>();

        while(resultats.next()){
            Dish dbDish = new Dish(resultats.getInt("id"), resultats.getString("item"), resultats.getFloat("unit_price") );
            dishList.add(dbDish);

            System.out.println(dbDish);
        }
        resultats.close();
        orderSQL.close();

        return dishList;
    }
}

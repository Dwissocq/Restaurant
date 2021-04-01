package co.simplon.restaurant.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DishBill {

    private int dishIDX;
    private int billIDX;
    private int quantity;

    public DishBill(int dishIDX, int billIDX, int quantity) {
        this.dishIDX = dishIDX;
        this.billIDX = billIDX;
        this.quantity = quantity;
    }

    public DishBill(int dishIDX, int quantity) {
        this.dishIDX = dishIDX;
        this.quantity = quantity;
    }

    public void saveDishBill(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO dish_bill (dish_idx, bill_idx, quantity) VALUES ('" + dishIDX + "','" + billIDX + "','" + quantity + "')");
        ordreSQL.close();
    }
}

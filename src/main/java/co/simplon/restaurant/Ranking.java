package co.simplon.restaurant;

import java.sql.*;

public class Ranking {

    public static void tablesPerAnnualSales(){

        String url = "jdbc:postgresql://localhost:5432/restaurant";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement orderSQL = connection.createStatement();
            ResultSet resultSet = orderSQL.executeQuery("select table_idx numberoftable, sum(dish_bill.quantity * dish.unit_price) as totalpricetable from bill join dish_bill on bill.id = dish_bill.bill_idx join dish on dish_bill.dish_idx = dish.id group by table_idx order by totalpricetable desc");

            System.out.println("CLASSEMENT DES TABLES");
            while(resultSet.next()){
                System.out.println("La table n° "+resultSet.getString("numberoftable")+" a généré "+resultSet.getString("totalpricetable")+" euros cette année");
            }
            System.out.println("");

            resultSet.close();
            orderSQL.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void dishesPerAnnualSales(){

        String url = "jdbc:postgresql://localhost:5432/restaurant";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement orderSQL = connection.createStatement();
            ResultSet resultSet = orderSQL.executeQuery("select item dish, sum(dish_bill.quantity * dish.unit_price) as totalpricedish from dish join dish_bill on dish.id = dish_bill.dish_idx group by dish order by totalpricedish desc");

            System.out.println("CLASSEMENT DES PLATS");
            while(resultSet.next()){
                System.out.printf("%-30s a généré  %4s euros cette année\n", resultSet.getString("dish"), resultSet.getString("totalpricedish"));
            }
            System.out.println("");

            resultSet.close();
            orderSQL.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

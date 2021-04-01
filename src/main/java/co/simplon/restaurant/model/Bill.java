package co.simplon.restaurant.model;

import java.sql.*;
import java.util.Scanner;

public class Bill {

    // Attributs
    private int idBill;
    private final int tableIDX;
    private final int serverIDX;

    // Constructeur
    public Bill(int idBill, int tableIDX, int serverIDX) {
        this.idBill = idBill;
        this.tableIDX = tableIDX;
        this.serverIDX = serverIDX;
    }

    // Accesseur
    public int getIdBill() {
        return idBill;
    }

    // 2e constructeur sans l'ID
    public Bill(int tableIDX, int serverIDX) {
        this.tableIDX = tableIDX;
        this.serverIDX = serverIDX;
    }

    public static void makeBill() {

        Scanner sc = new Scanner(System.in);

        // Connection à la BDD
        String url = "jdbc:postgresql://localhost:5432/restaurant";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            // Afficher les tables dispos
            System.out.println("Voici les tables disponibles :");
            System.out.println("");
            Table.getTable(connection);
            System.out.println("");
            System.out.print("Quel est le numéro de la table choisie ? : ");
            int table = sc.nextInt();
            sc.nextLine();
            System.out.println("");

            // Afficher les serveurs dispos
            System.out.println("");
            System.out.println("Voici les serveurs disponibles :");
            System.out.println("");
            Server.getServer(connection);
            System.out.println("");
            System.out.print("Quel est le numéro du (de la) serveur(se) ? : ");
            int server = sc.nextInt();
            sc.nextLine();
            System.out.println("");

            // Création d'un objet de classe Bill et enregistrement dans la BDD
            Bill newBill = new Bill(table, server);
            newBill.saveBill(connection);

            // Vérification du n0° de l'ID
            int currentBill = newBill.getIdBill();
            System.out.println(currentBill);


//            // Boucler sur la demande de plats (à faire)

            // Afficher les plats
            System.out.println("");
            System.out.println("Voici les plats disponibles :");
            System.out.println("");
            Dish.getDish(connection);
            System.out.println("");
            System.out.print("Quel numéro de plat souhaites-tu sélectionner ? : ");
            int dish = sc.nextInt();
            sc.nextLine();
            System.out.println("");
            System.out.print("Quelle est la quantité de ce plat ? : ");
            int quantity = sc.nextInt();
            sc.nextLine();
            System.out.println("");

            // Création d'un objet de classe DishBill et enregistrement dans la BDD
            DishBill newDishBill = new DishBill(dish, quantity);
            newDishBill.saveDishBill(connection);

            connection.close();

        } catch (SQLException exception) {
            // Ma gestion du problème
            System.out.println("Erreur de connexion à la base de données");
            exception.printStackTrace();
        }

    }

    public void saveBill(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO bill (table_idx, server_idx) VALUES ('" + tableIDX + "','" + serverIDX + "')");
        ResultSet rs = ordreSQL.getGeneratedKeys();
        idBill = rs.next() ? rs.getInt(1) : 0;
        System.out.println("Mon numéro d'ID : "+idBill);
        ordreSQL.close();
    }
}
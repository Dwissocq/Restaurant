package co.simplon.restaurant.model;

import co.simplon.restaurant.Menu;
import co.simplon.restaurant.side.Read;

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
            int table = Read.readInt(sc, "Quel est le numéro de la table choisie ? : ");

            // A faire : faire en sorte que les choix soient valides (compris entre 1 et 8 pour les tables par exemple)
            // int table = Read.validTable(sc);

            System.out.println("");

            // Afficher les serveurs dispos
            System.out.println("");
            System.out.println("Voici les serveurs disponibles :");
            System.out.println("");
            Server.getServer(connection);
            System.out.println("");
            int server = Read.readInt(sc, "Quel est le numéro du (de la) serveur(se) ? : ");
            System.out.println("");

            // Création d'un objet de classe Bill et enregistrement dans la BDD
            Bill newBill = new Bill(table, server);
            newBill.saveBill(connection);

            // Vérification du n° de l'ID
            System.out.println("Le n° de l'ID est : " + newBill.idBill);

//            // Boucler sur la demande de plats (à faire)

            int nextDish = -1;

            while (nextDish != 0) {

                // Afficher les plats
                System.out.println("");
                System.out.println("Voici les plats disponibles :");
                System.out.println("");
                Dish.getDish(connection);
                System.out.println("");
                int dish = Read.readInt(sc, "Quel numéro de plat souhaites-tu sélectionner ? : ");
                System.out.println("");
                int quantity = Read.readInt(sc, "Quelle est la quantité de ce plat ? : ");
                System.out.println("");

                // Création d'un objet de classe DishBill et enregistrement dans la BDD
                DishBill newDishBill = new DishBill(dish, newBill.idBill, quantity);
                newDishBill.saveDishBill(connection);

                nextDish = Read.readInt(sc, "Voulez-vous ajouter un autre plat ? (Taper 0 pour quitter / 1 pour continuer) : ");

            }

            connection.close();

            Menu.menu();

        } catch (SQLException exception) {
            // Ma gestion du problème
            System.out.println("Erreur de connexion à la base de données");
            exception.printStackTrace();
        }
    }

    public void saveBill(Connection connection) throws SQLException {
        Statement orderSQL = connection.createStatement();
        orderSQL.execute("INSERT INTO bill (table_idx, server_idx) VALUES ('" + tableIDX + "','" + serverIDX + "')", Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = orderSQL.getGeneratedKeys();
        if (rs.next()) {
            idBill = rs.getInt(1);
        }
        orderSQL.close();
    }
}
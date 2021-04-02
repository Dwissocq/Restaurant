package co.simplon.restaurant;

import co.simplon.restaurant.model.Bill;
import co.simplon.restaurant.side.Read;

import java.util.Scanner;

public class Menu {

    public static void menu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Voici ce que je te propose :");
        System.out.println("0 : Rien, merci !");
        System.out.println("1 : Enregistrer une facture");
        System.out.println("2 : Afficher le classement des plats par chiffre d'affaire");
        System.out.println("3 : Afficher le classement des tables par chiffre d'affaire");
        System.out.println("");
        int choice = Read.readInt(sc, "Quel est ton choix ? : ");
        System.out.println("");

        switch (choice) {
            case 0: {
                System.out.println("Ok, n'hésites pas à revenir ! Bonne journée ;)");
                break;
            }
            case 1: {
                Bill.makeBill();
                break;
            }
            case 2: {
                Ranking.dishesPerAnnualSales();
                menu();
                break;
            }
            case 3: {
                Ranking.tablesPerAnnualSales();
                menu();
                break;
            }
            default: {
                System.out.println("Je n'ai pas compris ton choix, peux-tu recommencer ?");
                menu();
                break;
            }
        }
    }
}

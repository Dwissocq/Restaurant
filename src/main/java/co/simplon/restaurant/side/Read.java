package co.simplon.restaurant.side;

import java.util.Scanner;

public class Read {

    public static int readInt(Scanner sc, String message) {

        int res = -1;
        boolean isValid = false;

        while (!isValid) {
            System.out.println(message);
            if (sc.hasNextInt()) {
                res = sc.nextInt();
                sc.nextLine();
                isValid = true;
            } else {
                System.err.println("Merci d'enregistrer une donnée valide");
                sc.nextLine();
            }
        }
        return res;
    }

    public static int validTable(Scanner sc) {

        int res = -1;
        boolean isValid = false;

        while(!isValid) {

            res = readInt(sc, "Quel est le numéro de table choisi ? : ");
            sc.nextLine();

            if ((res > 0) && (res < 9)) { ;
                isValid = true;
            } else {
                System.err.println("La table n'existe pas. Merci de recommencer");
                sc.nextLine();
            }
        }
        return res;
    }
}
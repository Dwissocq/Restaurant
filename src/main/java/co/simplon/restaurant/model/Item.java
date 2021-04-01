package co.simplon.restaurant.model;

public class Item {
    private final String name;
    private final double unitPrice;
    private int quantity;

    // Ensuite on crée un constructeur pour créer des objets

    public Item(String name, double unitPrice){

        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = 0;
    }

    // Maintenant on met des accesseurs pour avoir aux données en private


    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    // On crée une méthode pour avoir le prix total (on anticipe ce dont on aura besoin)

    public double getTotalPrice(){
        return unitPrice*quantity;
    }

    // Egalement une méthode pour mettre à jour la quantité

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}
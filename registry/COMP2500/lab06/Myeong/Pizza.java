package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class Pizza extends Food {
    protected ArrayList<Topping> toppings = new ArrayList<>();

    protected Pizza() {
        super();
    }

    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }
}

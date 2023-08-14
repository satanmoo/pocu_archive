package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class BasePizza extends Menu {
    protected ArrayList<Topping> toppings = new ArrayList<>();

    protected BasePizza(MenuType menuType, int price) {
        super(menuType, price);
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }
}

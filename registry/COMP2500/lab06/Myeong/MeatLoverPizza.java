package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class MeatLoverPizza extends Pizza {
    private static final int PRICE = 21;
    private static final int MAX_VEGGIE_COUNT = 1;

    public MeatLoverPizza() {
        super();
        super.price = PRICE;
        super.maxVeggieCount = MAX_VEGGIE_COUNT;
        super.toppings.add(Topping.BACON);
        super.toppings.add(Topping.PEPERONI);
        super.toppings.add(Topping.HAM);
        super.toppings.add(Topping.SAUSAGES);
        super.toppings.add(Topping.CHEDDAR_CHEESE);
    }

    public boolean addBlackOlives() {
        if (isValid()) {
            return false;
        }

        this.toppings.add(Topping.BLACK_OLIVES);
        ++this.veggieCount;
        return true;
    }

    public boolean removeBlackOlives() {
        boolean isRemoved = this.toppings.remove(Topping.BLACK_OLIVES);

        if (isRemoved) {
            --this.veggieCount;
        }

        return isRemoved;
    }

    public boolean addRedOnions() {
        if (isValid()) {
            return false;
        }

        this.toppings.add(Topping.RED_ONIONS);
        ++this.veggieCount;
        return true;
    }

    public boolean removeRedOnions() {
        boolean isRemoved = this.toppings.remove(Topping.RED_ONIONS);

        if (isRemoved) {
            --this.veggieCount;
        }

        return isRemoved;
    }

    public boolean addGreenPeppers() {
        if (isValid()) {
            return false;
        }

        this.toppings.add(Topping.GREEN_PEPPERS);
        ++this.veggieCount;
        return true;
    }

    public boolean removeGreenPeppers() {
        boolean isRemoved = this.toppings.remove(Topping.GREEN_PEPPERS);

        if (isRemoved) {
            --this.veggieCount;
        }

        return isRemoved;
    }
}

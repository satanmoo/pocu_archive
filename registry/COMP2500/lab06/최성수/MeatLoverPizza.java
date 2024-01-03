package academy.pocu.comp2500.lab6;

public class MeatLoverPizza extends BasePizza {
    public MeatLoverPizza() {
        super(MenuType.MEAT_LOVER_PIZZA, 21);
        toppings.add(Topping.BACON);
        toppings.add(Topping.PEPERONI);
        toppings.add(Topping.HAM);
        toppings.add(Topping.SAUSAGES);
        toppings.add(Topping.CHEDDAR_CHEESE);
    }

    public boolean addBlackOlives() {
        if (isValid()) {
            return false;
        }

        toppings.add(Topping.BLACK_OLIVES);
        ++table[1];
        return true;
    }

    public boolean removeBlackOlives() {
        boolean isRemoved = toppings.remove(Topping.BLACK_OLIVES);

        if (isRemoved) {
            --table[1];
        }

        return isRemoved;
    }

    public boolean addRedOnions() {
        if (isValid()) {
            return false;
        }

        toppings.add(Topping.RED_ONIONS);
        ++table[1];
        return true;
    }

    public boolean removeRedOnions() {
        boolean isRemoved = toppings.remove(Topping.RED_ONIONS);

        if (isRemoved) {
            --table[1];
        }

        return isRemoved;
    }

    public boolean addGreenPeppers() {
        if (isValid()) {
            return false;
        }

        toppings.add(Topping.GREEN_PEPPERS);
        ++table[1];
        return true;
    }

    public boolean removeGreenPeppers() {
        boolean isRemoved = toppings.remove(Topping.GREEN_PEPPERS);

        if (isRemoved) {
            --table[1];
        }

        return isRemoved;
    }
}
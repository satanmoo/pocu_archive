package academy.pocu.comp2500.lab6;

public class HousePizza extends BasePizza {
    public HousePizza() {
        super(MenuType.HOUSE_PIZZA, 20);
        toppings.add(Topping.BLACK_OLIVES);
        toppings.add(Topping.RED_ONIONS);
        toppings.add(Topping.GREEN_PEPPERS);
        toppings.add(Topping.MOZZARELLA_CHEESE);
    }

    public boolean addBacon() {
        if (isValid()) {
            return false;
        }

        toppings.add(Topping.BACON);
        ++table[0];
        return true;
    }

    public boolean removeBacon() {
        boolean isRemoved = toppings.remove(Topping.BACON);

        if (isRemoved) {
            --table[0];
        }

        return isRemoved;
    }

    public boolean addPeperoni() {
        if (isValid()) {
            return false;
        }

        toppings.add(Topping.PEPERONI);
        ++table[0];
        return true;
    }

    public boolean removePeperoni() {
        boolean isRemoved = toppings.remove(Topping.PEPERONI);

        if (isRemoved) {
            --table[0];
        }

        return isRemoved;
    }

    public boolean addSausages() {
        if (isValid()) {
            return false;
        }

        toppings.add(Topping.SAUSAGES);
        ++table[0];
        return true;
    }

    public boolean removeSausages() {
        boolean isRemoved = toppings.remove(Topping.SAUSAGES);

        if (isRemoved) {
            --table[0];
        }

        return isRemoved;
    }
}
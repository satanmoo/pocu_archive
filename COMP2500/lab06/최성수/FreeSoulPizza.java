package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class FreeSoulPizza extends BasePizza {
    public FreeSoulPizza() {
        super(MenuType.FREE_SOUL_PIZZA, 25);
    }

    public boolean addTopping(Topping topping) {
        if ((isMeat(topping) && table[0] >= 2)
                || (isVeggie(topping) && table[1] >= 2)
                || (isCheese(topping) && table[2] == 1)) {
            return false;
        }

        toppings.add(topping);

        if (isMeat(topping)) {
            ++table[0];
        }

        if (isVeggie(topping)) {
            ++table[1];
        }

        if (isCheese(topping)) {
            ++table[2];
        }

        return true;
    }

    public boolean removeTopping(Topping topping) {
        boolean isRemoved = toppings.remove(topping);

        if (isRemoved) {
            if (isMeat(topping)) {
                --table[0];
            }

            if (isVeggie(topping)) {
                --table[1];
            }

            if (isCheese(topping)) {
                --table[2];
            }
        }

        return isRemoved;
    }

    private static boolean isMeat(Topping topping) {
        return topping == Topping.BACON
                || topping == Topping.CHICKEN
                || topping == Topping.PEPERONI
                || topping == Topping.SAUSAGES
                || topping == Topping.HAM;
    }

    private static boolean isVeggie(Topping topping) {
        return topping == Topping.BLACK_OLIVES
                || topping == Topping.RED_ONIONS
                || topping == Topping.GREEN_PEPPERS;
    }

    private static boolean isCheese(Topping topping) {
        return topping == Topping.MOZZARELLA_CHEESE
                || topping == Topping.CHEDDAR_CHEESE
                || topping == Topping.FETA_CHEESE;
    }
}

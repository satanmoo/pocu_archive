package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class NoHeavyMeal extends SetMenu {
    private static final int PRICE = 15;
    private static final int MAX_APPETIZER_COUNT = 2;
    private static final int MAX_DESSERT_COUNT = 1;

    public NoHeavyMeal() {
        super();
        super.price = PRICE;
        super.maxAppetizerCount = MAX_APPETIZER_COUNT;
        super.maxDessertCount = MAX_DESSERT_COUNT;
    }

    public void setAppetizers(Appetizer appetizer1, Appetizer appetizer2) {
        super.appetizers.clear();

        super.appetizers.add(appetizer1);
        super.appetizers.add(appetizer2);

        super.appetizerCount = super.appetizers.size();
    }

    public void setDessert(Dessert dessert) {
        super.desserts.clear();

        super.desserts.add(dessert);

        super.dessertCount = super.desserts.size();
    }

}

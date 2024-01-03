package academy.pocu.comp2500.lab6;

import java.util.ArrayList;

public class DeathByDesserts extends SetMenu {
    private static final int PRICE = 20;
    private static final int MAX_DESSERT_COUNT = 4;

    public DeathByDesserts() {
        super();
        super.price = PRICE;
        super.maxDessertCount = MAX_DESSERT_COUNT;
    }

    public void setDesserts(Dessert dessert1, Dessert dessert2, Dessert dessert3, Dessert dessert4) {
        super.desserts.clear();

        super.desserts.add(dessert1);
        super.desserts.add(dessert2);
        super.desserts.add(dessert3);
        super.desserts.add(dessert4);

        super.dessertCount = super.desserts.size();
    }
}

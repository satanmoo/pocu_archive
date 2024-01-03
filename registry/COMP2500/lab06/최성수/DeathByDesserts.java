package academy.pocu.comp2500.lab6;

public class DeathByDesserts extends SetMenu {
    public DeathByDesserts() {
        super(MenuType.DEATH_BY_DESSERTS, 20);
    }

    public void setDesserts(Dessert dessert1, Dessert dessert2, Dessert dessert3, Dessert dessert4) {
        desserts.clear();

        desserts.add(dessert1);
        desserts.add(dessert2);
        desserts.add(dessert3);
        desserts.add(dessert4);
        table[1] = 4;
    }
}
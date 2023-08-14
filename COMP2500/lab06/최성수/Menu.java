package academy.pocu.comp2500.lab6;

public class Menu {
    private MenuType menuType;
    private int price;
    // 0 : meat, 1 : veggie, 2 : cheese
    // 0 : appetizer, 1 : dessert, 2 : mainCourse
    protected byte[] table = new byte[3];

    protected Menu(MenuType menuType, int price) {
        this.menuType = menuType;
        this.price = price;
    }

    public boolean isValid() {
        if (menuType == MenuType.HOUSE_PIZZA) {
            return table[0] == 2;
        } else if (menuType == MenuType.MEAT_LOVER_PIZZA) {
            return table[1] == 1;
        } else if (menuType == MenuType.VEGGIE_PIZZA) {
            return table[2] == 2;
        } else if (menuType == MenuType.FREE_SOUL_PIZZA) {
            return table[0] == 2 && table[1] == 2 && table[2] == 1;
        } else if (menuType == MenuType.DEATH_BY_DESSERTS) {
            return table[1] == 4 && table[0] == 0 && table[2] == 0;
        } else if (menuType == MenuType.NO_HEAVY_MEAL) {
            return table[0] == 2 && table[1] == 1;
        }
        return table[0] == 1 && table[1] == 1 && table[2] == 1;
    }

    public int getPrice() {
        return price;
    }
}

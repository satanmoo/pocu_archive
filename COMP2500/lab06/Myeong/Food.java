package academy.pocu.comp2500.lab6;

public class Food {
    protected int price;
    protected int maxVeggieCount;
    protected int maxMeatCount;
    protected int maxCheeseCount;
    protected int veggieCount;
    protected int meatCount;
    protected int cheeseCount;
    protected int maxAppetizerCount;
    protected int maxMainCourseCount;
    protected int maxDessertCount;
    protected int appetizerCount;
    protected int mainCourseCount;
    protected int dessertCount;

    protected Food() {
    }

    public int getPrice() {
        return this.price;
    }

    public boolean isValid() {
        return (this.maxMeatCount == 0 || this.meatCount == this.maxMeatCount)
                && (this.maxVeggieCount == 0 || this.veggieCount == this.maxVeggieCount)
                && (this.maxCheeseCount == 0 || this.cheeseCount == this.maxCheeseCount)
                && (this.maxAppetizerCount == 0 || this.maxAppetizerCount == this.appetizerCount)
                && (this.maxMainCourseCount == 0 || this.maxMainCourseCount == this.mainCourseCount)
                && (this.maxDessertCount == 0 || this.maxDessertCount == this.dessertCount);
    }
}

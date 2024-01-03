package academy.pocu.comp2500.assignment2;

public class Calendar extends Product {
    private CalendarType calendarType;

    public Calendar(ShippingType shippingMethod, CalendarType calendarType) {
        super(shippingMethod);
        this.calendarType = calendarType;
        this.color = new Color(255, 255, 255);
        this.setPrice();
        this.setSize();
        this.setDisplayName();
    }

    public CalendarType getCalendarType() {
        return calendarType;
    }

    private void setPrice() {
        if (calendarType == CalendarType.WALL) {
            this.price = 1000;
        } else if (calendarType == CalendarType.DESK) {
            this.price = 1000;
        } else if (calendarType == CalendarType.MAGNET) {
            this.price = 1500;
        }
    }

    private void setSize() {
        if (calendarType == CalendarType.WALL) {
            this.size = new Size(400, 400);
        } else if (calendarType == CalendarType.DESK) {
            this.size = new Size(200, 150);
        } else if (calendarType == CalendarType.MAGNET) {
            this.size = new Size(100, 200);
        }
    }

    private void setDisplayName() {
        if (calendarType == CalendarType.WALL) {
            this.displayName = "Wall Calendar";
        } else if (calendarType == CalendarType.DESK) {
            this.displayName = "Desk Calendar";
        } else if (calendarType == CalendarType.MAGNET) {
            this.displayName = "Magnet Calendar";
        }
    }
}

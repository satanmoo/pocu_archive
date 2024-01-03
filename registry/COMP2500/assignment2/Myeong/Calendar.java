package academy.pocu.comp2500.assignment2;

import java.util.UUID;

public class Calendar extends Product {
    private CalendarType calendarType;

    public Calendar(UUID id, ShippingOption shippingMethod, CalendarType calendarType) {
        super(id, shippingMethod);
        super.name = "Calendar";
        super.color = new Color(0xFF, 0xFF, 0xFF);

        this.calendarType = calendarType;
        super.size = convertSizeByCalendarType();

        setCalendarDisplayName();
        updateCalendarPrice();
    }

    public CalendarType getCalendarType() {
        return this.calendarType;
    }

    private Size convertSizeByCalendarType() {
        Size copySize = new Size(1, 1);
        switch (this.calendarType) {
            case WALL_CALENDAR:
                copySize.setSize(400, 400);
                break;

            case DESK_CALENDAR:
                copySize.setSize(200, 150);
                break;

            case MAGNET_CALENDAR:
                copySize.setSize(100, 200);
                break;

            default:
                assert (false) : "Unrecognized Calendar Type" + this.calendarType;
                break;
        }
        return copySize;
    }

    private void updateCalendarPrice() {
        int price = 1000;
        if (this.calendarType.equals(CalendarType.MAGNET_CALENDAR)) {
            price += 500;
        }
        super.price = price;
    }

    private void setCalendarDisplayName() {
        String copyDisplayName = super.name;
        switch (this.calendarType) {
            case WALL_CALENDAR:
                copyDisplayName = "Wall ".concat(copyDisplayName);
                break;

            case DESK_CALENDAR:
                copyDisplayName = "Desk ".concat(copyDisplayName);
                break;

            case MAGNET_CALENDAR:
                copyDisplayName = "Magnet ".concat(copyDisplayName);
                break;

            default:
                assert (false) : "Unrecognized Calendar Type" + this.calendarType;
                break;
        }
        super.displayName = copyDisplayName;
    }
}

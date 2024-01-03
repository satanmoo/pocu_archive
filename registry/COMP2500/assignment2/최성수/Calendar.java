package academy.pocu.comp2500.assignment2;

public class Calendar extends Product {
    private final CalendarType calendarType;

    public Calendar(CalendarType calendarType) {
        super(getCalendarPrice(calendarType), getDisplayName(calendarType), getCalendarSize(calendarType), new RGB(0xff, 0xff, 0xff));
        this.calendarType = calendarType;
    }

    private static Size getCalendarSize(CalendarType calendarType) {
        if (calendarType == CalendarType.WALL) {
            return new Size(400, 400);
        } else if (calendarType == CalendarType.DESK) {
            return new Size(200, 150);
        } else {
            return new Size(100, 200);
        }
    }

    private static int getCalendarPrice(CalendarType calendarType) {
        if (calendarType == CalendarType.MAGNET) {
            return 1500;
        }
        return 1000;
    }

    private static String getDisplayName(CalendarType calendarType) {
        return String.format("%s Calendar", calendarType);
    }

    public CalendarType getCalendarMaterial() {
        return calendarType;
    }
}

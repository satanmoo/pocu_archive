package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.ResultBase;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class MaintenanceMiddleware implements IRequestHandler {
    private final IRequestHandler nextHandler;
    private final OffsetDateTime startDateTime;

    public MaintenanceMiddleware(IRequestHandler nextHandler, OffsetDateTime startDateTime) {
        this.nextHandler = nextHandler;
        this.startDateTime = startDateTime;
    }

    @Override
    public ResultBase handle(Request request) {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        if (now.isAfter(startDateTime) && now.isBefore(startDateTime.plusHours(1))) {
            return new ServiceUnavailableResult(this.startDateTime, this.startDateTime.plusHours(1));
        }

        return this.nextHandler.handle(request);
    }
}

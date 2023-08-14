package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.ResultBase;
import academy.pocu.comp2500.lab10.pocuflix.ResultCode;

import java.util.HashMap;

public class CacheMiddleware implements IRequestHandler {
    private final IRequestHandler nextHandler;
    private final int expiryCount;
    private final HashMap<Request, Integer> cached = new HashMap<>();

    public CacheMiddleware(IRequestHandler nextHandler, int expiryCount) {
        this.nextHandler = nextHandler;
        this.expiryCount = expiryCount;
    }

    @Override
    public ResultBase handle(Request request) {
        if (cached.containsKey(request)) {
            int count = cached.get(request) - 1;
            if (count > 0) {
                cached.put(request, count);
                return new CachedResult(count);
            }
        }

        ResultBase resultBase = nextHandler.handle(request);
        if (resultBase.getCode() == ResultCode.OK) {
            cached.put(request, this.expiryCount);
        }

        return resultBase;
    }
}

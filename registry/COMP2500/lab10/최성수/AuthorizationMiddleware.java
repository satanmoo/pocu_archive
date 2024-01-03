package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.ResultBase;
import academy.pocu.comp2500.lab10.pocuflix.User;

import java.util.HashSet;

public class AuthorizationMiddleware implements IRequestHandler {
    private final IRequestHandler nextHandler;
    private final HashSet<User> allowedUserSet;

    public AuthorizationMiddleware(IRequestHandler nextHandler, HashSet<User> allowedUserSet) {
        this.nextHandler = nextHandler;
        this.allowedUserSet = allowedUserSet;
    }

    @Override
    public ResultBase handle(Request request) {
        if (!allowedUserSet.contains(request.getUser())) {
            return new UnauthorizedResult();
        }

        return nextHandler.handle(request);
    }
}

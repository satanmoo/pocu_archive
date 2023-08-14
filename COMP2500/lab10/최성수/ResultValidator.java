package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.NotFoundResult;
import academy.pocu.comp2500.lab10.pocuflix.OkResult;
import academy.pocu.comp2500.lab10.pocuflix.ResultBase;
import academy.pocu.comp2500.lab10.pocuflix.ResultCode;

import java.lang.reflect.Method;

public class ResultValidator {
    private final ResultBase result;

    public ResultValidator(final ResultBase result) {
        this.result = result;
    }

    public boolean isValid(ResultCode code) {
        if (result instanceof OkResult) {
            return code == result.getCode() && code == ResultCode.OK;
        } else if (result instanceof CachedResult) {
            return code == result.getCode() && code == ResultCode.NOT_MODIFIED;
        } else if (result instanceof ServiceUnavailableResult) {
            return code == result.getCode() && code == ResultCode.SERVICE_UNAVAILABLE;
        } else if (result instanceof UnauthorizedResult) {
            return code == result.getCode() && code == ResultCode.UNAUTHORIZED;
        } else if (result instanceof NotFoundResult) {
            return code == result.getCode() && code == ResultCode.NOT_FOUND;
        }

        return false;
    }
}

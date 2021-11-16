package hello.advanced.app.trace.template;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    protected AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message) throws InterruptedException {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            //logic execute
            T result = call();

            trace.end(status);
            return result;

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call() throws InterruptedException;
}

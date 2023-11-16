package christmas.exception;

import java.util.function.Supplier;

public class ValidatingLoopTemplate {
    public <T> T execute(Supplier<T> callback) {
        while (true) {
            try {
                return callback.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

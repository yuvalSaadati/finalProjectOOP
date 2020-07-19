/**
 * A task is something that needs to happen, or something that we can run()
 * and return a value.
 * @param <T> the type
 */
public interface Task<T> {
    /**
     * @return a value or just run
     */
    T run();
}

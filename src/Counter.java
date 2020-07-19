/**
 * Counter is a simple class that is used for counting things.
 *
 * @author YuvalSaadati
 */
public class Counter {
    private int count;

    /**
     * add number to current count.
     * @param number that is a constructor
     */
    public Counter(int number) {
        this.count = number;
    }
    /**
     * add number to current count.
     * @param number the value that will be increase
     */
    public void increase(int number) {
        this.count += number;
    }
    /**
     * subtract number from current count.
     * @param number the value that will be decrease
     */
    public void decrease(int number) {
        this.count -= number;
    }
    /**
     * get current count.
     * @return the current value
     */
    public int getValue() {
        return this.count;
    }
    /**
     * changing the value of the counter.
     * @param number that will be the new value
     */
    public void setCount(int number) {
        this.count = number;
    }
}

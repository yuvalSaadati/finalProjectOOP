/**
 * menu which extends Animation.
 * @param <T> the type of the menu
 */
public interface Menu<T> extends Animation {
    /**
     * @param key the key that the user will put
     * @param message the message that will be show on the screen
     * @param returnVal the task that will be
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * @return the status of the menu.
     */
    T getStatus();
    /**
     * @param key the key that the user will put
     * @param message the discreption of this key
     * @param subMenu the menu that will be added
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
    /**
     * stop the menu.
     */
    void reset();
}

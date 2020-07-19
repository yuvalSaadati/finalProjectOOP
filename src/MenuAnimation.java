import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * create new menu and create method and actions on it accordingly the input
 * of the user.
 * @param <T> the type of the menu
 */
public class MenuAnimation<T> implements Menu<T> {
    private boolean stop;
    private KeyboardSensor keyboardSensor;
    private String menuName;
    private List<T> menuReturnValuesList;
    private List<String> menuItemNamesList;
    private List<String> menuItemKeysList;
    private List<Boolean> isSubMenuBooleanList;
    private List<Menu<T>> subMenusList;
    private T status;
    private AnimationRunner animationRunner;

    /**
     * @param myMenuName the name of the menu
     * @param keyboard a key that the user put
     * @param myAnimationRunner animation runer of the game
     */
    public MenuAnimation(String myMenuName, KeyboardSensor keyboard,
                         AnimationRunner myAnimationRunner) {
        this.stop = false;
        this.animationRunner = myAnimationRunner;
        this.keyboardSensor = keyboard;
        this.menuName = myMenuName;
        this.menuItemKeysList = new ArrayList();
        this.menuItemNamesList = new ArrayList();
        this.menuReturnValuesList = new ArrayList();
        this.isSubMenuBooleanList = new ArrayList();
        this.subMenusList = new ArrayList();
    }

    /**
     * @param key the key that the user will put
     * @param message the message that will be show on the screen
     * @param returnVal the task that will be
     */
    public void addSelection(String key, String message, T returnVal) {
        this.menuItemKeysList.add(key);
        this.menuItemNamesList.add(message);
        this.menuReturnValuesList.add(returnVal);
        this.isSubMenuBooleanList.add(false);
        this.subMenusList.add(null);
    }
    /**
     * stop the animation.
     */
    public void reset() {
        this.status = null;
        this.stop = false;
    }

    /**
     * @return the status of the menu.
     */
    public T getStatus() {
        return this.status;
    }
    /**
     * draw all the itemes on the screen.
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
        // draw the background of the game
        d.setColor(new Color(255, 158, 158));
        d.fillRectangle(0, 0, 800, 600);
        // draw the title of the menu
        d.setColor(new Color(156, 42, 72));
        d.drawText(301, 70, this.menuName, 51);
        d.setColor(Color.BLACK);
        d.drawText(300, 70, this.menuName, 50);
        int y = 150;
        // draw each selection
        for (int i = this.menuItemNamesList.size(); i > 0; --i) {
            String optionText =
                    "(" + (String) this.menuItemKeysList.get(i - 1) + ") " + (String) this.menuItemNamesList.get(i - 1);
            d.setColor(Color.BLACK);
            d.drawText(100, y, optionText, 24);
            y += 40;
        }
        for (int i = 0; i < this.menuReturnValuesList.size(); ++i) {
            // when the user chose level
            if (this.keyboardSensor.isPressed((String) this.menuItemKeysList.get(i))) {
                if (!(Boolean) this.isSubMenuBooleanList.get(i)) {
                    this.status = this.menuReturnValuesList.get(i);
                    this.stop = true;
                } else {
                    // when it is a sub menu so we will run it
                    Menu<T> subMenu = (Menu) this.subMenusList.get(i);
                    this.animationRunner.run(subMenu);
                    this.status = subMenu.getStatus();
                    this.stop = true;
                    subMenu.reset();
                }
                break;
            }
        }
        }
    /**
     * @return if the animation should stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
    /**
     * @param myStop change the file of stop by the input
     */
    public void setStop(boolean myStop) {
        this.status = null;
        this.stop = myStop;
    }
    /**
     * @param key the key that the user will put
     * @param message the discreption of this key
     * @param subMenu the menu that will be added
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.menuItemKeysList.add(key);
        this.menuItemNamesList.add(message);
        this.menuReturnValuesList.add(null);
        this.isSubMenuBooleanList.add(true);
        this.subMenusList.add(subMenu);
    }
}

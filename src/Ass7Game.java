/**
 * class Ass6Game create a game object, initializes and runs it.
 */
public class Ass7Game {
    /**
     * create a menu object, initializes and runs it.
     * @param args string from the command line
     */
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        if (args.length > 0) {
            menu.run(args[1]);
        } else {
            menu.run("levelset.txt");
        }
    }
}

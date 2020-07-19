import java.awt.Color;
import java.lang.reflect.Field;

/**
 * parse color definition and return the specified color.
 */
public class ColorsParser {
    /**
     * @param s the name of the color
     * @return the color
     */
    public static Color colorFromString(String s) {
        Color color;
        if (s.contains("RGB")) {
            // get only the name of the color
            String rgb = s.substring(10, s.length() - 2);
            // splite the name into 3 for red, green and blue
            String[] rgbColors = rgb.split(",", 3);
            // creat the color
            color = new Color(Integer.parseInt(rgbColors[0]),
                    Integer.parseInt(rgbColors[1]),
                    Integer.parseInt(rgbColors[2]));
        } else {
            // get only the name of the color
            String myColor = s.substring(6, s.length() - 1);
            try {
            Field field = Color.class.getField(myColor);
            color = (Color) field.get((Object) null);
        } catch (NoSuchFieldException e1) {
            throw new RuntimeException("Unsupported color name: " + myColor);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Unsupported color name: " + myColor);
        }
        }
        return color;
    }
}

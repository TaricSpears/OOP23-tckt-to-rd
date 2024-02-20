package it.unibo.commons;

import java.awt.Color;
import java.util.function.Function;

/**
 * This class is a converter from an integer to a color.
 */
public class IntToColorConverter implements Function<Integer, Color> {
    /**
     * This method converts an integer to a color.
     * 
     * @param t the integer to convert.
     * @return the color corresponding to the integer.
     * @throws IllegalArgumentException if the integer is not in the range [0, 8].
     */
    @Override
    public Color apply(final Integer t) {
        switch (t) {
            case 0:
                return Color.WHITE;
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.ORANGE;
            case 3:
                return Color.RED;
            case 4:
                return Color.GREEN;
            case 5:
                return Color.BLUE;
            case 6:
                return Color.MAGENTA;
            case 7:
                return Color.GRAY;
            case 8:
                return Color.BLACK;
            default:
                throw new IllegalArgumentException();
        }
    }

}

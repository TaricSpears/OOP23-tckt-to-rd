package it.unibo.commons;

import java.awt.Color;
import java.util.function.Function;

public class IntToColorConverter implements Function<Integer, Color>{

    @Override
    public Color apply(Integer t) throws IllegalArgumentException {
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

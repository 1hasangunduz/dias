package utils;

import lombok.Data;

@Data
public class Variables {

    private static final ThreadLocal<Variables> VARIABLES_TL = new ThreadLocal<>();

    private double price;


    private Variables() {}

    public static Variables getInstance() {
        if (VARIABLES_TL.get() == null) {
            VARIABLES_TL.set(new Variables());
        }
        return VARIABLES_TL.get();
    }

    public static void remove() {
        VARIABLES_TL.remove();
    }
}

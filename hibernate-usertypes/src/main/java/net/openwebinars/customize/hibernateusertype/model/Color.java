package net.openwebinars.customize.hibernateusertype.model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Se trata de una enumeración "con esteroides".
 * No solamente maneja un conjunto cerrado de colores,
 * sino que tenemos a nuestra disposición muchos más métodos.
 */
public enum Color {

    GRIS(128, 128, 128),
    ROJO(255, 0, 0),
    VERDE(0, 255, 0),
    AMARILLO(255,255,0),
    MORADO(88, 86, 214),
    AZUL_CLARO (52, 170, 220),
    AZUL(0,0,255),
    BLANCO(255,255,255);

    private int r;
    private int g;
    private int b;
    private String rgb;

    Color(final int r, final int g, final int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.rgb = r + ", " + g + ", " + b;
    }

    public String getRGBComponents() {
        return rgb;
    }

    public int getRed(){
        return r;
    }

    public int getGreen(){
        return g;
    }

    public int getBlue(){
        return r;
    }

    public static Color valueOfRGB(String rgb) {
        for (Color c : values()) {
            if (c.getRGBComponents().equals(rgb))
                return c;
        }
        return null;
    }

    public String getARGB(){
        return "0x" + Integer.toHexString(0xFF000000 | ((r << 16) & 0x00FF0000) | ((g << 8) & 0x0000FF00) | b);
    }

    public static Color getRandomColor() {
        return values()[ThreadLocalRandom.current().nextInt(Color.values().length-1)];
    }




}

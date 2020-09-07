package net.openwebinars.customize.attributeconverter.model;

public enum Aficiones {
    LECTURA, DEPORTE, TEATRO, VIAJAR, BAILAR, CANTAR, COMICS, VIDEOJUEGOS;


    /**
     * Este método nos ayuda a comprobar si un una cadena de caracters es un
     * valor válido de la enumeración.
     * Nos servirá para hacer una mínima validación en la creación de
     * nuevas Personas con Aficiones
     * @param text
     * @return
     */
    public static boolean contains(String text) {

        try {
            Aficiones.valueOf(text);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }


    }

}

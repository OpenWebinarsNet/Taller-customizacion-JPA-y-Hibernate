package net.openwebinars.customize.attributeconverter.database;

import net.openwebinars.customize.attributeconverter.model.Aficiones;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class EnumSetAficionesConverter implements AttributeConverter<EnumSet<Aficiones>, String> {

    private final String SEPARADOR = ", ";


    @Override
    public String convertToDatabaseColumn(EnumSet<Aficiones> attribute) {

        if (!attribute.isEmpty()) {
            return attribute.stream()
                                .map(Aficiones::name)
                                .collect(Collectors.joining(SEPARADOR));
        }
        return null;
    }

    @Override
    public EnumSet<Aficiones> convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            if (!dbData.isBlank()) { // isBlank Java 11
                return Arrays.stream(dbData.split(SEPARADOR)) // Separamos la cadena en un array de String y lo convertimos en Stream
                        .map(elem -> Aficiones.valueOf(elem)) // Mapeamos cada cadena con su correspondiente valor de la enumeracion
                        .collect(Collectors.toCollection(() -> EnumSet.noneOf(Aficiones.class))); // Los recogemos en la colección correspondiente
            }
        }
        return EnumSet.noneOf(Aficiones.class);
    }
}

package net.openwebinars.customize.attributeconverter.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.openwebinars.customize.attributeconverter.model.Aficiones;
import net.openwebinars.customize.attributeconverter.model.Persona;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreatePersonaDto {

    private String nombre;
    private String apellidos;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;
    // Damos por hecho que esta fecha nos la envían en formato dd/MM
    private String fechaOnomastica;

    private List<String> aficiones;


    /**
     * Método que nos ayuda a convertir el DTO en una instancia de Persona
     * para insertarla posteriormente a través del repositorio
     * @return
     */
    public Persona toPersona() {

        // Esta parte del código está muy simplificada y es muy mejorable
        MonthDay fechaOnomasticaProcesada = null;

        // Si el DTO no tiene fecha, o no contiene la barra /, la damos por nula
        // En otro caso, suponemos que está bien (puede lanzar DateTimeException)
        if (fechaOnomastica != null && fechaOnomastica.contains("/")) {
            String[] partes = this.getFechaOnomastica().split("/");
            fechaOnomasticaProcesada = MonthDay.of(Integer.valueOf(partes[1]), Integer.valueOf(partes[0]));
        }

        // A partir de esta línea, procesamos las aficiones que pueden venir incluidas en el DTO
        EnumSet<Aficiones> aficionesEnumSet = EnumSet.noneOf(Aficiones.class);

        // Si hay alguna afición
        if (aficiones != null) {
            aficionesEnumSet = aficiones.stream()
                        .map(String::toUpperCase)
                        .filter(Aficiones::contains) // Comprobamos a través del método propio contains si es una afición válida
                        .map(Aficiones::valueOf) // transformamos a un valor de la enumeración
                        .collect(Collectors.toCollection(() -> EnumSet.noneOf(Aficiones.class))); // los recogemos todos en una colección
        }


        return Persona.builder()
                .nombre(nombre)
                .apellidos(apellidos)
                .fechaNacimiento(fechaNacimiento)
                .fechaOnomastica(fechaOnomasticaProcesada)
                .aficiones(aficionesEnumSet)
                .build();

    }


}

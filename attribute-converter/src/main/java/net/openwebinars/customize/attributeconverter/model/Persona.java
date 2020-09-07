package net.openwebinars.customize.attributeconverter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.openwebinars.customize.attributeconverter.database.EnumSetAficionesConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.EnumSet;

@Entity @Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class Persona {

    @Id @GeneratedValue
    private long id;

    private String nombre;
    private String apellidos;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM")
    private MonthDay fechaOnomastica;

    @Convert(converter = EnumSetAficionesConverter.class)
    private EnumSet<Aficiones> aficiones;

}



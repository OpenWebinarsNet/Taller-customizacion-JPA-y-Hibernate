package net.openwebinars.customize.hibernatebasictype.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.openwebinars.customize.hibernatebasictype.model.CuerpoCeleste;

import java.math.BigDecimal;
import java.math.BigInteger;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuerpoCelesteGetDto {

    private long id;
    private String nombre;
    private BigInteger distanciaTierraKms;
    private BigDecimal disantciaTierraAniosLuz;



}

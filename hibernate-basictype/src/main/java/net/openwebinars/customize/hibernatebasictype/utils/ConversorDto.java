package net.openwebinars.customize.hibernatebasictype.utils;

import net.openwebinars.customize.hibernatebasictype.model.CuerpoCeleste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversorDto {

    @Autowired
    private ConversorKmAniosLuz conversorKmAniosLuz;

    public CuerpoCelesteGetDto fromCuerpoCelesteToDto(CuerpoCeleste cuerpoCeleste) {
        return CuerpoCelesteGetDto.builder()
                .id(cuerpoCeleste.getId())
                .nombre(cuerpoCeleste.getNombre())
                .distanciaTierraKms(cuerpoCeleste.getDistanciaTierra())
                .disantciaTierraAniosLuz(conversorKmAniosLuz.kilometrosAniosLuz(cuerpoCeleste.getDistanciaTierra()))
                .build();
    }

}

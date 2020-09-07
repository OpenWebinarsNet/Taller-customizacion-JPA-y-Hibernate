package net.openwebinars.customize.hibernatecompositeusertype.controller;

import lombok.*;

@Builder @NoArgsConstructor
@Data @AllArgsConstructor
public class CreatePersonaDto {

    private String nombre;
    private String apellidos;
    private String direccionLinea1;
    private String direccionLinea2;
    private String codigoPostal;
    private String poblacion;
    private String provincia;

}

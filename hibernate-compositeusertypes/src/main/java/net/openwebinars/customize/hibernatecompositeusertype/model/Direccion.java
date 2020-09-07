package net.openwebinars.customize.hibernatecompositeusertype.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class Direccion {

    private String linea1, linea2, codigoPostal, poblacion, provincia;

}

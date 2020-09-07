package net.openwebinars.customize.hibernateusertype.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Persona  {

    @Id @GeneratedValue
    private long id;

    private String nombre;

    private String apellidos;

    private String direccion;

    // Al usar @Type estamos indicando que la transformación de este atributo
    // se realizará a través del UserType que hemos definido.
    @Type(type = "net.openwebinars.customize.hibernateusertype.database.ColorUserType")
    private Color colorAsignado;

}

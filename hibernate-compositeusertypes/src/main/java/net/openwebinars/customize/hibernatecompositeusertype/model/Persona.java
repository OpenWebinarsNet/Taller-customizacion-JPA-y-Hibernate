package net.openwebinars.customize.hibernatecompositeusertype.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Persona  {

    @Id @GeneratedValue
    private long id;

    private String nombre;

    private String apellidos;


    @Columns(columns = {
            @Column(name = "linea1"), @Column(name = "linea2"), @Column(name = "codigo_postal"),
            @Column(name = "poblacion"), @Column(name = "provincia")
    })
    @Type(type = "net.openwebinars.customize.hibernatecompositeusertype.database.DireccionCompositeUserType")
    private Direccion direccion;

}

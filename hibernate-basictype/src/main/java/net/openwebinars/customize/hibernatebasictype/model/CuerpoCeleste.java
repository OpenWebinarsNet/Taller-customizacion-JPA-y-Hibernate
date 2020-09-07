package net.openwebinars.customize.hibernatebasictype.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.openwebinars.customize.hibernatebasictype.database.BigIntegerStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

//@TypeDef(typeClass = BigIntegerStringType.class, defaultForType = BigInteger.class, name="biginteger")
@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class CuerpoCeleste {

    @Id @GeneratedValue
    private long id;

    private String nombre;

    @Type(type="biginteger")
    private BigInteger distanciaTierra;

}
